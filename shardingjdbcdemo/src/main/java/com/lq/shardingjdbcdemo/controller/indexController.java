package com.lq.shardingjdbcdemo.controller;

import com.lq.shardingjdbcdemo.entity.Course;
import com.lq.shardingjdbcdemo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LQ
 * @date 2020/08/23 18:30
 */
@RestController
public class indexController {

    @Autowired
    private CourseMapper courseMapper;

    @PostMapping("/index")
    public void index() {
        courseMapper.insert(new Course());
    }

    public void run(){

    }
}
