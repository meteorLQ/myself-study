package com.lq.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器
 *
 * @author LQ
 * @date 2020/08/19 17:10
 */
public class OnlineCountListener implements HttpSessionListener {



    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        String id = httpSessionEvent.getSession().getId();
        System.out.println(id);
        Integer num = (Integer) servletContext.getAttribute("num");
        if (num == null) {
            servletContext.setAttribute("num", 1);
        } else {
            num++;
            servletContext.setAttribute("num", num);
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer num = (Integer) servletContext.getAttribute("num");
        if (num == null) {
            //servletContext.setAttribute("num", 1);
        } else {
            num--;
            servletContext.setAttribute("num", num);
        }
    }
}
