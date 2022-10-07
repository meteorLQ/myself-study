package com.lq.study.leetcode;

import java.util.HashMap;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 *
 * @author LQ
 * @date 2020/08/08 0:14
 */
public class 字符串中的第一个唯一字符 {
    public static void main(String[] args) {
        String str = "eetlcode";
        System.out.println(firstUniqChar1(str));
        System.out.println(firstUniqChar2(str));
    }

    public static int firstUniqChar1(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static int firstUniqChar2(String s) {
        int start;
        int end;
        int result = s.length();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            start = s.indexOf(ch);
            end = s.lastIndexOf(ch);
            if (start == end && start != -1) {
                result = Math.min(result, start);
            }
        }
        if (result == s.length()) {
            return -1;
        }
        return result;
    }
}
