package com.potato.study.leetcodecn.p02165.t001;

import java.util.Arrays;

/**
 * 2165. 重排数字的最小值
 *
 * 给你一个整数 num 。重排 num 中的各位数字，使其值 最小化 且不含 任何 前导零。
 *
 * 返回不含前导零且值最小的重排数字。
 *
 * 注意，重排各位数字后，num 的符号不会改变。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 310
 * 输出：103
 * 解释：310 中各位数字的可行排列有：013、031、103、130、301、310 。
 * 不含任何前导零且值最小的重排数字是 103 。
 * 示例 2：
 *
 * 输入：num = -7605
 * 输出：-7650
 * 解释：-7605 中各位数字的部分可行排列为：-7650、-6705、-5076、-0567。
 * 不含任何前导零且值最小的重排数字是 -7650 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-value-of-the-rearranged-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2165
    public long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        // 判断 nums 正负
        boolean isNegative = num < 0;
        if (isNegative) {
            num *= -1;
        }
        // 变成 string 数组 ，对数组进行排序
        char[] numsArray = String.valueOf(num).toCharArray();
        // 按照正负获取 数组
        Arrays.sort(numsArray);
        long newNum = 0;
        if (isNegative) {
            for (int i = numsArray.length - 1; i >= 0; i--) {
                newNum *= 10;
                newNum += numsArray[i] - '0';
            }
            newNum *= -1;
        } else {
            // 找到第一个非0位置
            int notZeroIndex = 0;
            while (notZeroIndex < numsArray.length && numsArray[notZeroIndex] == 0) {
                notZeroIndex++;
            }
            if (notZeroIndex == numsArray.length) {
                return 0;
            }
            newNum += numsArray[notZeroIndex];
            for (int i = 0; i < numsArray.length; i++) {
                if (i == notZeroIndex) {
                    continue;
                }
                newNum *= 10;
                newNum += numsArray[i] - '0';
            }
        }
        return newNum;
    }
}
