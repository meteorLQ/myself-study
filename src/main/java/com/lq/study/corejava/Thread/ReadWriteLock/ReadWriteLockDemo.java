package com.lq.study.corejava.Thread.ReadWriteLock;

import com.lq.study.util.ThreadPoolUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author LQ
 * @date 2020/08/02 9:56
 */
public class ReadWriteLockDemo {
    private Map<Integer, Object> map = new HashMap<>();


    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public void write(Integer key, Object v) {
        lock.writeLock().lock();
        System.out.println("写入中" + key);
        try {
            map.put(key, v);
            System.out.println("写入成功" + key);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void read(Integer key) {
        lock.readLock().lock();
        System.out.println("读取中" + key);
        try {
            System.out.println("读取成功" + map.get(key));
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        try {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    if (finalI == 10) {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    readWriteLockDemo.read(finalI);
                });
            }

            for (int i = 0; i < 20; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    readWriteLockDemo.write(finalI, UUID.randomUUID().toString().substring(0, 5));
                });
            }
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }
    }

}
