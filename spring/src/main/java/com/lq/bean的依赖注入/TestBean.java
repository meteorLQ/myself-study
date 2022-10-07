package com.lq.bean的依赖注入;

import com.lq.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author LQ
 * @date 2020/08/22 23:04
 */
public class TestBean {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("bean.xml");
//        User user = (User) context.getBean("user");
//        System.out.println(user);
//        //Car car = user.getCar();
//        System.out.println(user.car);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
        User bean = (User) context.getBean("user");

    }
}
