package com.lq.study.json.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

/**
 * @author LQ
 * @date 2020/11/05 17:55
 */
public class JsonTest {
    @Test
    void test1() {
        Car car = new Car();
    }

    @Test
    void test2() throws JsonProcessingException {
        Car car = new Car();
        car.setNews(Boolean.FALSE);
        String fastjson = JSON.toJSONString(car);
        System.out.println("fastjson" + fastjson);
        Gson gson = new Gson();
        String gsonJson = gson.toJson(car);
        System.out.println("gsonJson" + gsonJson);

        ObjectMapper objectMapper = new ObjectMapper();
        String jackson = objectMapper.writeValueAsString(car);
        System.out.println("jackson" + jackson);

    }
}
