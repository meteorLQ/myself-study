package com.lq.bean的依赖注入;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LQ
 * @date 2020/08/22 23:00
 */
@Component
public class User {
    private String name;
    private Integer id;

    @Autowired
    public Car car;

    public User() {
        System.out.println("user创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Car getCar() {
//        return car;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
}
