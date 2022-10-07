package com.lq.shardingjdbcdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lq.shardingjdbcdemo.entity.Course;
import com.lq.shardingjdbcdemo.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingjdbcdemoApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 水平分库
     */
    @Test
    public void addCourseDB() {
        Course course = new Course();
        course.setCname("Java");
        course.setCstatus("active");
        course.setUserId(9l);
        int insert = courseMapper.insert(course);
    }

    @Test
    public void selectCourseDB() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", 504788333814087680L);
        queryWrapper.eq("user_id",9L);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }

    /**
     * 水平分表方法
     */
    @Test
    void contextLoads() {
        Course course = new Course();
        course.setCname("Java");
        course.setCstatus("active");
        course.setUserId(12l);
        int insert = courseMapper.insert(course);
    }

}
