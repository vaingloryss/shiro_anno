package com.vainglory.controller;

import com.vainglory.domain.ScoreItem;
import com.vainglory.domain.User;
import com.vainglory.service.ScoreService;
import com.vainglory.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/userController/")
//@RequiresAuthentication//类中的所有方法都需要身份验证
//@RequiresRoles(value = {"teacher","admin"},logical = Logical.OR)//类中的所有方法都需要角色，规则："或"
public class UserController {

    @Autowired
    ScoreService scoreService;

    @Autowired
    UserService userService;

    /* /userController/login = anon
      /userController/getScoreList = anon
      /userController/updateScore=authc,perms["scoreItem:update"]
      /userController/deleteScore=authc,perms["scoreItem:delete"]
      /userController/logout=logout*/


    @GetMapping("login")
    public String toLogin(){
        return "login";
    }
    @GetMapping("error")
    public String error(){
        return "error";
    }
    @GetMapping("toScoreList")
    public String toScoreList(){
        return "scoreList";
    }


    @GetMapping("updateScore")
    @RequiresPermissions(value = {"scoreItem:update","scoreItem:delete"},logical = Logical.OR)//默认“且”
    @RequiresAuthentication
    public String toUpdateScore(Integer scoreId,Model model){
        ScoreItem scoreItem = scoreService.findById(scoreId);
        model.addAttribute("scoreItem", scoreItem);
        return "update";
    }

    @RequestMapping("getScoreList")
    @RequiresAuthentication
    public String getScoreList(Model model){
        List<ScoreItem> scoreItemList = scoreService.findAll();
        model.addAttribute("scoreItemList", scoreItemList);
        return "scoreList";
    }

    @PostMapping("login")
    public String login(User user, @RequestParam(value = "rememberMe",required = false) String rememberMe){
        System.out.println("login ...");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        //记住我
        if(rememberMe!=null){
            System.out.println(rememberMe);
            token.setRememberMe(true);
        }
        subject.login(token);
        return "forward:getScoreList";
    }

    @GetMapping("deleteScore")
    @RequiresPermissions({"scoreItem:delete"})
    @RequiresAuthentication
    public String deleteScore(Integer scoreId){
        scoreService.deleteById(scoreId);
        return "forward:getScoreList";
    }

    @PostMapping("updateScore")
    public String updateScore(ScoreItem scoreItem){
        scoreService.updateScore(scoreItem);
        return "forward:getScoreList";
    }

    @GetMapping("register")
    public String toRegister(){
        return "register";
    }
    @PostMapping("register")
    public String register(User user){
        userService.register(user);
        return "redirect:login";
    }
}
