package com.potato.study.leetcodecn.p01922.t001;

import org.junit.Assert;

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
        // n 个位置 有多少个奇数 多少个偶数位置 1
        if (n == 1) {
            return 5;
        }
        // 最开始的位置 都只有 4中可能 看看 除了第一个位置外 有几个偶数位置几个奇数位置
        long evenCount = (n + 1)/2;
        long oddCount = n/2;
        // 奇数下标为质数 9以内的质数 2，,3，,5，,7
        long totoalOddCount = pow(4, oddCount);
        long totoalEvenCount = pow(5, evenCount);
        // 偶数下标为偶数 0，2,4,6,8
        int mod = 1_000_000_000 + 7;
        return (int) ((totoalOddCount * totoalEvenCount) % mod);
    }


    /**
     * 快速求 pow
     * @param base
     * @param index
     * @return
     */
    private long pow(long base, long index) {
        if (base == 1) {
            return 1;
        }
        if (index == 0) {
            return 1;
        }
        int mod = 1_000_000_000 + 7;
        long element = pow(base, index/2);
        if (index % 2 == 1) {
            return (((element * element) % mod) * base) % mod;
        }
        return (element * element) % mod;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.countGoodNumbers(1);
        System.out.println(i);
        Assert.assertEquals(5, i);

        i = solution.countGoodNumbers(4);
        System.out.println(i);
        Assert.assertEquals(400, i);


        i = solution.countGoodNumbers(50);
        System.out.println(i);
        Assert.assertEquals(564908303, i);



        i = solution.countGoodNumbers(3);
        System.out.println(i);
        Assert.assertEquals(100, i);
    }



}
