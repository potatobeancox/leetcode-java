package com.potato.study.leetcodecn.p01922.t001;

import java.util.Arrays;

/**
 * 1922. 统计好数字的数目
 *
 * 我们称一个数字字符串是 好数字 当它满足（下标从 0 开始）偶数 下标处的数字为 偶数 且 奇数 下标处的数字为 质数 （2，3，5 或 7）。
 *
 * 比方说，"2582" 是好数字，因为偶数下标处的数字（2 和 8）是偶数且奇数下标处的数字（5 和 2）为质数。但 "3245" 不是 好数字，因为 3 在偶数下标处但不是偶数。
 * 给你一个整数 n ，请你返回长度为 n 且为好数字的数字字符串 总数 。由于答案可能会很大，请你将它对 109 + 7 取余后返回 。
 *
 * 一个 数字字符串 是每一位都由 0 到 9 组成的字符串，且可能包含前导 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：5
 * 解释：长度为 1 的好数字包括 "0"，"2"，"4"，"6"，"8" 。
 * 示例 2：
 *
 * 输入：n = 4
 * 输出：400
 * 示例 3：
 *
 * 输入：n = 50
 * 输出：564908303
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1015
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-good-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1922
    public int countGoodNumbers(long n) {
        if (n == 0) {
            return 0;
        }
        // 看着就是 求 4 和5 的个数
        int mod = 1_000_000_000 + 7;
        long count = 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                count *= 5;
            } else {
                count *= 4;
            }
            count %= mod;
        }
        return (int) count;
    }



    private long pow(long base, long index) {
        return -1;
    }



}
