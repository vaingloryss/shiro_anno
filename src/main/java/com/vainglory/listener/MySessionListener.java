package com.vainglory.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * @author vaingloryss
 * @date 2019/10/12 0012 下午 7:06
 */
public class MySessionListener extends SessionListenerAdapter {
    //当session创建时触发
    @Override
    public void onStart(Session session) {
        System.out.println("session:"+session.getId()+"start");
        //super.onStart(session);
    }

    //当session停止时触发
    @Override
    public void onStop(Session session) {
        System.out.println("session:"+session.getId()+"stop");
        //super.onStop(session);
    }

    //当session过期时触发，但不会主动触发，需要再次访问时，即又要使用session时才会发现session过期，并触发
    @Override
    public void onExpiration(Session session) {
        System.out.println("session:"+session.getId()+"expired");
        //super.onExpiration(session);
    }
}
