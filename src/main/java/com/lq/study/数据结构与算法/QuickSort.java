package com.lq.study.数据结构与算法;

import java.util.Arrays;
import java.util.Random;

/**
 * 1. 1000个1-100的正整数（会有重复），如何进行排序
 *
 * @author LQ
 * @date 2020/08/06 22:16
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[1000];
        Random rm = new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println(rm.nextInt(100) + 1);
            int num = rm.nextInt(100) + 1;
            arr[i] = num;
        }
        System.out.println(arr.length);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }


    public static void quickSort(int[] arr, int startIndex,
                                 int endIndex) {
        // 递归结束条件：startIndex大于或等于endIndex时
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 根据基准元素，分成两部分进行递归排序
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     *
     *
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return
     */
    private static int partition(int[] arr, int startIndex,
                                 int endIndex) {
        // 取第1个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                mark++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }

        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }
}
