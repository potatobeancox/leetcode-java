package com.potato.study.leetcodecn.p02523.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2523. 范围内最接近的两个质数
 *
 * 给你两个正整数 left 和 right ，请你找到两个整数 num1 和 num2 ，它们满足：
 *
 * left <= nums1 < nums2 <= right  。
 * nums1 和 nums2 都是 质数 。
 * nums2 - nums1 是满足上述条件的质数对中的 最小值 。
 * 请你返回正整数数组 ans = [nums1, nums2] 。如果有多个整数对满足上述条件，请你返回 nums1 最小的质数对。如果不存在符合题意的质数对，请你返回 [-1, -1] 。
 *
 * 如果一个整数大于 1 ，且只能被 1 和它自己整除，那么它是一个质数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：left = 10, right = 19
 * 输出：[11,13]
 * 解释：10 到 19 之间的质数为 11 ，13 ，17 和 19 。
 * 质数对的最小差值是 2 ，[11,13] 和 [17,19] 都可以得到最小差值。
 * 由于 11 比 17 小，我们返回第一个质数对。
 * 示例 2：
 *
 * 输入：left = 4, right = 6
 * 输出：[-1,-1]
 * 解释：给定范围内只有一个质数，所以题目条件无法被满足。
 *  
 *
 * 提示：
 *
 * 1 <= left <= right <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/closest-prime-numbers-in-range
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2523
    public int[] closestPrimes(int left, int right) {
        // 从 left 到 right 进行遍历
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        for (int i = 2; i <= right; i++) {
            // 判断i 是不是素数
            boolean isPrime = checkPrime(i, primeList);
            if (!isPrime) {
                continue;
            }
            primeList.add(i);
        }
        // 遍历 primeList 找到两个都在 left和 right 里边 并计算min
        int min = Integer.MAX_VALUE;
        int[] res = new int[] {
                -1, -1
        };
        for (int i = 1; i < primeList.size(); i++) {
            int pre = primeList.get(i-1);
            int current = primeList.get(i);
            if (pre < left || current > right) {
                continue;
            }
            if (current - pre < min) {
                min = current - pre;
                res[0] = pre;
                res[1] = current;
            }


        }
        return res;
    }

    private boolean checkPrime(int num, List<Integer> primeList) {
        for (int prime : primeList) {
            if (num % prime == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.closestPrimes(10, 19);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                11,13
        }, ints);
    }



}
