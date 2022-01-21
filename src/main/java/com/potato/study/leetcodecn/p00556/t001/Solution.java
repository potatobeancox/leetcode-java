package com.potato.study.leetcodecn.p00556.t001;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 556. 下一个更大元素 III
 *
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 *
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 *
 * 输入：n = 21
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 556 下一个数字
    public int nextGreaterElement(int n) {
        // 对于 每个位置 找到其之后的大于这个位置的数字，如果存在 那么就找到 大于 这个数字的最小数字
        String numStr = String.valueOf(n);
        char[] chars = numStr.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            int biggerIndex = -1;
            for (int j = i + 1; j < chars.length; j++) {
                // 找到大于 这个数字最小的值
                if (chars[j] > chars[i] &&
                        (biggerIndex == -1 || chars[j] < chars[biggerIndex])) {
                    biggerIndex = j;
                }
            }
            if (biggerIndex == -1) {
                continue;
            }
            char tmp = chars[i];
            chars[i] = chars[biggerIndex];
            chars[biggerIndex] = tmp;
            // 排序 返回
            Arrays.sort(chars, i + 1, chars.length);
            long nextBiggerNum = Long.parseLong(new String(chars));
            if (nextBiggerNum > Integer.MAX_VALUE) {
                continue;
            }
            return (int) nextBiggerNum;
        }
        return -1;
    }

}
