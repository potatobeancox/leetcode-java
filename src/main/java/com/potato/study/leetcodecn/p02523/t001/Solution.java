package com.potato.study.leetcodecn.p02523.t001;

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
        int min = Integer.MAX_VALUE;
        int[] res = new int[] {-1, -1};
        Integer prePrime = null;
        for (int i = 2; i <= right; i++) {
            // 判断i 是不是素数
            boolean isPrime = checkPrime(i, primeList);
            if (!isPrime) {
                continue;
            }
            primeList.add(i);
            // 跟之前求出距离
            if (i < left || i > right) {
                continue;
            }
            if (prePrime != null) {
                int dis = i - prePrime;
                if (min < dis) {
                    res[0] = prePrime;
                    res[1] = i;
                }
                min = Math.min(min, dis);
            }
            prePrime = i;

        }
        // 每个数字 判断其是否是素数 如果是的话 比较下最小值 跟之前的 素数做差 比较最小值记录下来
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



}
