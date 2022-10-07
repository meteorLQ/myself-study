package com.lq.study.代码块执行顺序;

/**
 * 对于Java是语言本身是值传递
 *
 * @author LQ
 * @date 2020/07/28 18:55
 */
public class StaticTest {
    public static void main(String[] args) {
        Person person1 = new Person("小米");
        Person person2 = new Person("华为");
        changeAge(person1, person2);
        System.out.println("person1" + person1.getName());
        System.out.println("person2" + person2.getName());
    }

    private static void changeAge(Person person1, Person person2) {
        Person temp = person1;
        person1 = person2;
        person2 = temp;

    }
}
