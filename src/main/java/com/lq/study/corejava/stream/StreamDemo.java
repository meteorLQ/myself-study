package com.lq.study.corejava.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 一行代码实现
 * 1.id必须是偶数
 * 2.年龄大于23
 * 3.用户名转为大写
 * 4.用户名字母倒着排序
 * 5.只输出一个用户
 *
 * @author LQ
 * @date 2020/08/02 22:29
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 1);
        User u2 = new User(2, "c", 13);
        User u3 = new User(4, "D", 23);
        User u4 = new User(5, "G", 3);
        User u5 = new User(6, "d", 71);
        User u6 = new User(7, "E", 99);
        User u7 = new User(8, "z", 24);

        List<User> users = Arrays.asList(u1, u2, u3, u4, u5, u6, u7);

        users.stream()
                .filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 23)
                .map(user -> {
                    user.setName(user.getName().toUpperCase());
                    return user;
                }).sorted(Comparator.comparing(User::getName).reversed()).limit(1)
                .forEach(System.out::println);
    }
}
