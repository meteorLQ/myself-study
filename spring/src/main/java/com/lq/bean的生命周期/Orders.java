package com.lq.bean的生命周期;

/**
 * @author LQ
 * @date 2020/08/22 21:52
 */
public class Orders {
    private String oname;

    public Orders() {
        System.out.println("第一步,执行无参数的构造创建bean实例");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步,调用set方法注入属性的值");
    }

    //创建初始化的方法
    public void initMethod(){
        System.out.println("第三步,执行初始化的方法");
    }
    //销毁bean执行的方法
    public void destroyMethod(){
        System.out.println("第五步,销毁bean执行的方法");
    }
}
