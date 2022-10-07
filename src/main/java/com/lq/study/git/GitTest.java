package com.lq.study.git;

import java.sql.SQLOutput;

/**
 * git分支管理测试
 *
 * @author LQ
 * @date 2020/08/08 11:29
 */

public class GitTest {
    private boolean isFlag;

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public static void main(String[] args) {

        System.out.println("这个是在develop修改的代码");
    }
}
