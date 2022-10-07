package com.lq.study.自定义注解;

/**
 * @author LQ
 * @date 2020/09/28 19:16
 */
public class Test {
    @Sensitive(prefixLength = 3, suffixLength = 4)
    private String userPhone;
    @Sensitive(prefixLength = 0, suffixLength = 4)
    private String idNum;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setUserPhone("12");
        test.setIdNum("152634199709182716");
        Test test1 = MaskUtils.maskObject(test);
        System.out.println(test1.getUserPhone());
        System.out.println(test1.getIdNum());
    }
}
