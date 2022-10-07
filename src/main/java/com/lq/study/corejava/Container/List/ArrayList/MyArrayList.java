package com.lq.study.corejava.Container.List.ArrayList;

import java.util.Arrays;

/**
 * ArrayList
 * 底层数组实现，非线程安全，查询速率高，插入效率低
 * 源码解读
 *
 * @author LQ
 * @date 2020/07/30 14:15
 */
public class MyArrayList<E> {
    /**
     * 默认的初始容量10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 当指定容器是零时返回一个空的数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 默认返回一个空的数组(无参构造)
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 保存添加到数组中的元素
     * 当第一次添加元素时就会扩容数组到DEFAULT_CAPACITY
     */
    transient Object[] elementData;

    /**
     * 该ArrayList中元素的个数（它包含的元素的数量）。
     */
    private int size;

    /**
     * 结果参数 fail-fast 快速失败
     */
    protected transient int modCount = 0;

    /**
     * 要分配的最大数组大小
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    /**
     * 指定初始容量的空列表
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public MyArrayList(int initialCapacity) {
        // 如果指定的容量大于零就初始化对应容量
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
            // 指定的容量是零那么创建一个空数组
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            // 抛出一个不合法参数异常
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * 构造一个具有十一个初始容量的空列(其实这还是个空的)
     * DEFAULTCAPACITY_EMPTY_ELEMENTDATA 为0.初始化为10，也就是说初始其实是空数组 当添加第一个元素的时候数组容量才变成10
     */
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 在此列表中的指定位置插入指定的元素
     * 先调用 rangeCheckForAdd 对index进行界限检查；然后调用 ensureCapacityInternal 方法保证capacity足够大；
     * 再将从index开始之后的所有成员后移一个位置；将element插入index位置；最后size加1。
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        // arraycopy()方法实现数组自己复制自己
        // elementData:源数组;index:源数组中的起始位置;
        // elementData：目标数组；index + 1：
        // 目标数组中的起始位置； size - index：要复制的数组元素的数量；

        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * A version of rangeCheck used by add and addAll.
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * 将指定元素添加到此列表的末尾
     * <p>
     * 注意 ：JDK11 移除了 ensureCapacityInternal() 和 ensureExplicitCapacity() 方法
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        // 添加元素之前，先调用ensureCapacityInternal方法
        // 确认list容量，尝试容量加1，看看有无必要
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        // 这里看到ArrayList添加元素的实质就相当于为数组赋值
        // 添加元素
        elementData[size++] = e;
        return true;
    }


    /**
     * ：JDK11 移除了 ensureCapacityInternal()
     *
     * @param minCapacity 最小容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     * 计算最小扩容量
     *
     * @param elementData
     * @param minCapacity
     * @return
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        // 如果当前数组是一个空数组，那么就比较默认容量10 和  传入的小容量size+1是否满足
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * JDK11 移除了 ensureExplicitCapacity()
     * 判断是否需要扩容
     *
     * @param minCapacity 最小容量
     */
    private void ensureExplicitCapacity(int minCapacity) {
        // 结构化值加加
        modCount++;

        // overflow-conscious code
        // 可能发生数组溢出
        if (minCapacity - elementData.length > 0)
            //调用grow方法进行扩容，调用此方法代表已经开始扩容了
            grow(minCapacity);
    }


    /**
     * 增加，以确保它能够至少容纳最小容量参数指定的元素的数目的容量
     *
     * @param minCapacity 所需的最小容量
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        // oldCapacity为旧容量
        int oldCapacity = elementData.length;
        // newCapacity为新容量 扩容为原来的1.5倍
        //将oldCapacity 右移一位，其效果相当于oldCapacity /2，
        //我们知道位运算的速度远远快于整除运算，整句运算式的结果就是将新容量更新为旧容量的1.5倍，
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 如果新容量小于最小容量
        //然后检查新容量是否大于最小需要容量，若还是小于最小需要容量，那么就把最小需要容量当作数组的新容量，
        if (newCapacity - minCapacity < 0)
            //新容量为最小容量
            newCapacity = minCapacity;
        // 如果新容量大于了最大容量
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            // 如果新容量大于 MAX_ARRAY_SIZE,进入(执行) `hugeCapacity()` 方法来比较 minCapacity 和 MAX_ARRAY_SIZE，
            // 如果minCapacity大于最大容量，则新容量则为`Integer.MAX_VALUE`，
            // 否则，新容量大小则为 MAX_ARRAY_SIZE 即为 `Integer.MAX_VALUE - 8`。
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 巨大容量
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        // 最小容量小于零 内存不足溢出
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 降低数组的容量
     * 修改这个ArrayList实例的容量是列表的当前大小。 应用程序可以使用此操作来最小化ArrayList实例的存储。
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elementData, size);
        }
    }


    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.trimToSize();
    }
}
