package com.potato.study.leetcodecn.p00186.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 186. 翻转字符串里的单词 II
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。

 示例：

 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 注意：

 单词的定义是不包含空格的一系列字符
 输入字符串中不会包含前置或尾随的空格
 单词与单词之间永远是以单个空格隔开的
 进阶：使用 O(1) 额外空间复杂度的原地解法。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/reverse-words-in-a-string-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public void reverseWords(char[] s) {
        // 翻转2次 第一次全部翻转
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;

            left++;
            right--;
        }
        // 第二次局部翻转
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != ' ') {
                continue;
            }
            // 找到 空格 i-1 就是 end
            int end = i-1;
            left = start;
            right = end;
            while (left < right) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;

                left++;
                right--;
            }
            start = i+1;
        }
        // 处理最后一个字符
        if (start < s.length) {
            // 找到 空格 i-1 就是 end
            int end = s.length - 1;
            left = start;
            right = end;
            while (left < right) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;

                left++;
                right--;
            }
        }
    }
}
