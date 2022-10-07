package com.lq.bean的依赖注入;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author LQ
 * @date 2020/08/22 23:01
 */
@DependsOn("user")
@Component
public class Car {
    private String name;

    public Car() {
        System.out.println("Car创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
