package com.vainglory.service.serviceImpl;

import com.vainglory.domain.ScoreItem;
import com.vainglory.mapper.ScoreMapper;
import com.vainglory.mapper.UserMapper;
import com.vainglory.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/10/9 0009 下午 7:43
 */
@Service
public class ScoreServiceImple implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ScoreItem> findAll() {
        List<ScoreItem> scoreItemList = scoreMapper.findAll();
        for (ScoreItem scoreItem : scoreItemList) {
            scoreItem.setUser(userMapper.findById(scoreItem.getUserId()));
        }
        return scoreItemList;
    }

    @Override
    public void deleteById(Integer scoreId) {
        scoreMapper.deleteById(scoreId);
    }

    @Override
    public ScoreItem findById(Integer scoreId) {
        return scoreMapper.findById(scoreId);
    }

    @Override
    public void updateScore(ScoreItem scoreItem) {
        scoreMapper.update(scoreItem);
    }
}
