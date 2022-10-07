package com.lq.study.coreJava.Container.List.ArrayList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * List去重
 *
 * @author LQ
 * @date 2020/08/17 13:41
 */
public class ListRepetition {
    /**
     * 方法一
     *
     * @param list
     * @return
     */
    public static List<User> test1(List<User> list) {
        ArrayList<String> news = new ArrayList<>();
        ArrayList<User> result = new ArrayList<>();
        for (User user : list) {
            if (!news.contains(user.getName())) {
                news.add(user.getName());
                result.add(user);
            }
        }
        list.clear();
        list.addAll(result);
        return list;
    }

    /**
     * 方法二
     *
     * @param list
     * @return
     */
    public static List<User> test2(ArrayList<User> list) {
        HashSet<String> users = new HashSet<>(list.size());

        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (!users.add(user.getName())) {
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * @param list
     * @return
     */
    public static List<User> test3(ArrayList<User> list) {
        List<User> listNew = list.stream().collect(Collectors.
                collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getName))), ArrayList::new));

        return listNew;
    }


    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("詹三"));
        users.add(new User("李四"));
        users.add(new User("詹三"));
        users.add(new User("王五"));
        users.add(new User("赵六"));
        users.add(new User("孙七"));
        List<User> newList = test3(users);
        System.out.println(newList);

    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
