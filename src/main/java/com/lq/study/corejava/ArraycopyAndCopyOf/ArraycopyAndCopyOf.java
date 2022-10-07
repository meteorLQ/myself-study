package com.lq.study.corejava.ArraycopyAndCopyOf;

/**
 * @author LQ
 * @date 2020/07/30 17:28
 */
public class ArraycopyAndCopyOf {
    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        a[4] = 4;
        a[5] = 5;
//length a.size -index
        System.arraycopy(a, 2, a, 3, 1);

        a[2] = 99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }


        int[] ints = copyOf(a, 1);
        System.out.println(ints.length);


    }

    public static int[] copyOf(int[] original, int newLength) {
        int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0,
             newLength);
        return copy;
    }
}
