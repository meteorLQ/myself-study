package com.lq.shardingjdbcdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.shardingjdbcdemo.entity.Course;
import org.springframework.stereotype.Repository;

/**
 * @author LQ
 * @date 2020/08/23 16:31
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {
}
