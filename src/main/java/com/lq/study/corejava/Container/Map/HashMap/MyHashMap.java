package com.lq.study.corejava.Container.Map.HashMap;


import javax.swing.tree.TreeNode;

/**
 * ＆	与  如果相对应位都是1，则结果为1，否则为0	（A＆B），得到12，即0000 1100
 * |	或  如果相对应位都是 0，则结果为 0，否则为 1	（A | B）得到61，即 0011 1101
 * ^    异或 如果相对应位值相同，则结果为0，否则为1  (A ^ B）得到49，即 0011 0001
 *
 * @author LQ
 * @date 2020/07/29 0:22
 */
public class MyHashMap<K, V> {

    /**
     * 默认初始容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 最大容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 当桶(bucket)上的结点数大于这个值时会转成红黑树
     */
    static final int TREEIFY_THRESHOLD = 8;


    /**
     * // 桶中结构转化为红黑树对应的table的最小大小(即数组的最小长度)
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 默认的负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;


    /**
     * 加载因子
     */
    final float loadFactor;

    /**
     * 每次扩容和更改map结构的计数器
     */
    transient int modCount;

    /**
     * 存储元素的数组，总是2的幂次倍
     */
    transient Node<K, V>[] table;

    /**
     * 临界值 当实际大小(容量*填充因子)超过临界值时，会进行扩容
     */
    int threshold;

    /**
     * 存放元素的个数，注意这个不等于数组的长度。
     */
    transient int size;


    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        // 如果初始容量大于最大容量直接赋值为最大容量
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        // 加载因子不能小于等于零，否则抛出非法的加载因子
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        // 初始化加载因子
        this.loadFactor = loadFactor;
        // 初始化阈值
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * 返回一个大于或等于cap且最接近cap的2的整数次幂的一个数
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 扰动函数
     * <p>
     * key的hash值高16位不变，低16位与高16位异或作为key的最终hash值。
     * （h >>> 16，表示无符号右移16位，高位补0，任何数跟0异或都是其本身，因此key的hash值高16位不变。）
     * 问题1.为什么不直接使用hashCode的值作为hash值,还要进行异或运算
     * 答：因为如果hashCode出来的值高位变化特别大,而低位没有进行变化，
     * 这样在进行i = (n - 1) & hash运算时就会导致计算处理的值相同的会很多(即会产生hash碰撞)
     * 举例说明
     * 假如hashCode出来的值是h1 h2,默认是n是16   n-1=15
     * 15     0000 0000 0000 0000 0000 0000 0000 1111
     * h1     0000 0000 1111 0000 1111 0000 1100 1100
     * h2     1111 1110 0000 1111 1111 0000 1100 1100
     * <p>
     * h1&15  0000 0000 0000 0000 0000 0000 0000 1100
     * <p>
     * h2&15  0000 0000 0000 0000 0000 0000 0000 1100
     * <p>
     * h1和h2 相同  产生了hash碰撞
     * <p>
     * 异或运算就是为了减少hash碰撞的产生
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 转入key和value
     * 调用hash[扰动函数]方法,用key来进行hash运算
     * 调用putVal方法
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        // 存储元素的数组
        Node<K, V>[] tab;
        //  元素
        Node<K, V> p;
        // 数组容量
        int n;
        // 通过hash计算确定数组位置
        int i;

        // tab为null 或者tab的长度等于零时进行扩容
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        /**
         * 默认的初始容量是16 即n=16
         *  i = (n - 1) & hash
         * 16 -1=15 二进制0000 0000 0000 1111
         *二进制的15与运算是只有低位的1111进行运算
         *问题1 HashMap为什么不直接使用hashCode()处理后的哈希值直接作为table的下标
         *答:
         * hashCode()方法返回的是int整数类型，其范围为-(2 ^ 31)~(2 ^ 31 - 1)，约有40亿个映射空间，
         * 而HashMap的容量范围是在16（初始化默认值）~2 ^ 30，HashMap通常情况下是取不到最大值的，
         * 并且设备上也难以提供这么多的存储空间，从而导致通过hashCode()计算出的哈希值可能不在数组大小范围内，进而无法匹配存储位置
         *
         *
         */
        // (n - 1) & hash 确定元素存放在哪个桶中，桶为空，新生成结点放入桶中(此时，这个结点是放在数组中)即没有发生hash碰撞
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
            // 桶中已经存在元素 即发生了hash碰撞
        else {
            Node<K, V> e;
            K k;
            //  比较桶中第一个元素(数组中的结点)的hash值相等，key相等
            // 判断table[i]的元素的key是否与需要插入的key一样，若相同则直接用新的value覆盖掉旧的value
            // 判断原则equals() - 所以需要当key的对象重写该方法
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                //将第一个元素赋值给e，用e来记录
                e = p;
                // b.继续判断：需要插入的数据结构是红黑树还是链表
                // 如果是红黑树，则直接在树中插入 or 更新键值对
            else if (p instanceof TreeNode)
                e = ((MyTreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
                // 如果是链表，则在链表中插入 or 更新键值对
            else {
                // i .遍历table[i]，判断key是否已存在：采用equals对比当前遍历结点的key与需要插入数据的key
                //    如果存在相同的，则直接覆盖
                // ii.遍历完毕后任务发现上述情况，则直接在链表尾部插入数据
                //    插入完成后判断链表长度是否 > 8：若是，则把链表转换成红黑树
                // 在链表最末插入结点
                for (int binCount = 0; ; ++binCount) {
                    // 到达链表的尾部
                    if ((e = p.next) == null) {
                        // 在尾部插入新结点
                        p.next = newNode(hash, key, value, null);
                        // 结点数量达到阈值，转化为红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        // 跳出循环
                        break;
                    }
                    // 判断链表中结点的key值与插入的元素的key值是否相等
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        // 相等，跳出循环
                        break;
                    // 用于遍历桶中的链表，与前面的e = p.next组合，可以遍历链表
                    p = e;
                }
            }
            // 表示在桶中找到key值、hash值与插入元素相等的结点
            //对于i 情况的后续操作：发现key已存在，直接用新value覆盖旧value&返回旧value
            if (e != null) { // existing mapping for key
                // 记录e的value
                V oldValue = e.value;
                // onlyIfAbsent为false或者旧值为null
                if (!onlyIfAbsent || oldValue == null)
                    //用新值替换旧值
                    e.value = value;
                // 访问后回调
                afterNodeAccess(e);
                // 返回旧值
                return oldValue;
            }
        }
        // fail-fast 结构性修改
        ++modCount;
        // 实际大小大于阈值则扩容
        // 插入成功后，判断实际存在的键值对数量size > 最大容量
        // 如果大于则进行扩容
        if (++size > threshold)
            resize();
        // 插入成功时会调用的方法（默认实现为空）
        afterNodeInsertion(evict);
        return null;
    }

    /**
     * 当链表的长度为8时转为红黑树
     *
     * @param tab
     * @param hash
     */
    final void treeifyBin(Node<K, V>[] tab, int hash) {
//        int n, index;
//        Node<K, V> e;
//        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
//            resize();
//        else if ((e = tab[index = (n - 1) & hash]) != null) {
//            MyTreeNode<K, V> hd = null, tl = null;
//            do {
//                MyTreeNode<K, V> p = replacementTreeNode(e, null);
//                if (tl == null)
//                    hd = p;
//                else {
//                    p.prev = tl;
//                    tl.next = p;
//                }
//                tl = p;
//            } while ((e = e.next) != null);
//            if ((tab[index] = hd) != null)
//                hd.treeify(tab);
//        }
    }

    void afterNodeAccess(Node<K, V> p) {
    }

    void afterNodeInsertion(boolean evict) {
    }


    private Node<K, V> newNode(int hash, K key, V value, Object o) {
        return null;
    }

    /**
     * 扩容机制
     * 该函数有两种使用情况：
     * 1.初始化哈希表；
     * 2.当前数组容量过小，需要扩容
     *
     * @return
     */
    final Node<K, V>[] resize() {
        // 扩容前的数组（当前数组）
        Node<K, V>[] oldTab = table;
        // 扩容前的数组容量（数组长度）
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        // 扩容前数组的阈值
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            // 如果超过最大容量,不在扩容
            // 针对情况2：若扩容前的数组容量超过最大值，则不再扩容
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
                // 针对情况2：若没有超过最大值，就扩容为原来的2倍（左移1位）
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                // 阈值也扩容为原来的2倍
                newThr = oldThr << 1; // double threshold
            // 针对情况1：初始化哈希表（采用指定方式）即使用俩个参数的构造器或一个参数的构造器
        } else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            // 针对情况1：初始化哈希表（采用默认值方式）即使用无参构造器
            newCap = DEFAULT_INITIAL_CAPACITY;
            // 计算出默认扩容的阈值
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        // 针对情况1：初始化哈希表（采用指定方式）即使用俩个参数的构造器或一个参数的构造器 计算新的resize上限
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        // 将重新计算好的阈值赋值覆盖掉原来的
        threshold = newThr;
        // 重新初始化这个数组
        @SuppressWarnings({"rawtypes", "unchecked"})
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            // 把每个bucket都移动到新的buckets中
            // 遍历旧的数组
            for (int j = 0; j < oldCap; ++j) {
                // 临时节点
                Node<K, V> e;
                // 判断当前元素是否为null 且赋值给临时节点
                if ((e = oldTab[j]) != null) {
                    // 将当前数组位置元素置空
                    oldTab[j] = null;
                    // 判断该节点是否是一个链表
                    if (e.next == null)
                        // 如果没有,重新计算位置,放置元素
                        newTab[e.hash & (newCap - 1)] = e;
                        //判断该结点是否是一个红黑树
                    else if (e instanceof MyTreeNode)
                        ((MyTreeNode<K, V>) e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        // 否则是链表
                        // 定义头节点   尾节点
                        Node<K, V> loHead = null, loTail = null;
                        Node<K, V> hiHead = null, hiTail = null;
                        Node<K, V> next;
                        do {
                            next = e.next;
                            // 原索引
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                                // 原索引+oldCap
                            } else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        // 原索引放到bucket里
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        // 原索引+oldCap放到bucket里
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap =
                new MyHashMap<String, String>(16, 0.75f);
        myHashMap.put("1", "B");
    }
}
