package com.lq.springdata.controller;

import com.lq.springdata.entity.UserInfo;
import com.lq.springdata.repository.UserInfoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author LQ
 * @date 2020/08/27 17:10
 */
@RestController
public class UserInfoController {
    @Autowired
    UserInfoRep userInfoRep;

    @PostMapping("/add")
    public void addUser(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString().split("-")[0]);
        userInfo.setUserName("LQ");
        userInfo.setVersion(1);
        userInfoRep.save(userInfo);
    }

    @GetMapping("/findAllUserAndPage")
    public Iterable<UserInfo> findAllUserAndPage() {
        PageRequest pageRequest = PageRequest.of(1, 10);
        Page<UserInfo> all = userInfoRep.findAll(pageRequest);
        return all;
    }

    @GetMapping("/findAllUserAndSort")
    public Iterable<UserInfo> findAllUserAndSort() {
        Iterable<UserInfo> all = userInfoRep.findAll(Sort.by(Sort.Direction.DESC, "userId"));
        return all;
    }

    @GetMapping("/findAll")
    public Iterable<UserInfo> findAllUser() {
        Iterable<UserInfo> all = userInfoRep.findAll();
        return all;
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userInfoRep.deleteById(userId);
    }

    @GetMapping("/findUserInfo/{userId}")
    public UserInfo findUserInfo(@PathVariable("userId") String userId) {
        UserInfo userInfo = userInfoRep.findUserInfo(userId);
        return userInfo;
    }
}
