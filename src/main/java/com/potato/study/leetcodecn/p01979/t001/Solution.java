package com.potato.study.leetcodecn.p01979.t001;

import org.junit.Assert;

/**
 * 1979. 找出数组的最大公约数
 *
 * 给你一个整数数组 nums ，返回数组中最大数和最小数的 最大公约数 。
 *
 * 两个数的 最大公约数 是能够被两个数整除的最大正整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,6,9,10]
 * 输出：2
 * 解释：
 * nums 中最小的数是 2
 * nums 中最大的数是 10
 * 2 和 10 的最大公约数是 2
 * 示例 2：
 *
 * 输入：nums = [7,5,6,8,3]
 * 输出：1
 * 解释：
 * nums 中最小的数是 3
 * nums 中最大的数是 8
 * 3 和 8 的最大公约数是 1
 * 示例 3：
 *
 * 输入：nums = [3,3]
 * 输出：3
 * 解释：
 * nums 中最小的数是 3
 * nums 中最大的数是 3
 * 3 和 3 的最大公约数是 3
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-greatest-common-divisor-of-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1979
    public int findGCD(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 找到 min max
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 求 gcd 辗转相除法 https://baike.baidu.com/item/%E6%AC%A7%E5%87%A0%E9%87%8C%E5%BE%97%E7%AE%97%E6%B3%95/1647675?fromtitle=%E8%BE%97%E8%BD%AC%E7%9B%B8%E9%99%A4%E6%B3%95&fromid=4625352
        int a = max;
        int b = min;
        while (a % b != 0) {
            int remind = a % b;
            a = b;
            b = remind;
        }
        return b;
    }

}
