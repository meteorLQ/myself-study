package com.lq.bean的生命周期;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IOC Bean创建的生命周期
 *
 * @author LQ
 * @date 2020/08/22 22:03
 */
public class TestBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
        Orders orders = (Orders) context.getBean("orders");
        System.out.println("第四步,获取bean的实例");
        //手动销毁bean实例
        context.close();

    }
}
