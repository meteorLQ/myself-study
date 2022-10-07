package com.lq.prototype.deepClone;

import java.io.Serializable;

/**
 * 深拷贝(在Java中如果用clone方法默认实现的是浅拷贝)
 * @author LQ
 * @date 2020/08/23 11:57
 */
public class DeepCloneableTarget implements Cloneable ,Serializable{

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
