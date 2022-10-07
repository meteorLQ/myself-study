package com.lq.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 *
 * @author LQ
 * @date 2020/08/19 16:06
 */
public class CharacterEncodingFilter implements Filter {

    // 初始化  web服务器启动就初始化了
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");
    }

    // 具体操作
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        // 链  处理完后继续向下执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 销毁 web服务器关闭销毁
    @Override
    public void destroy() {
        System.out.println("filter销毁");
    }
}
