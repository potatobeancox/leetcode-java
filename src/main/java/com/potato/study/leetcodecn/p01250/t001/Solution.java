package com.potato.study.leetcodecn.p01250.t001;


import org.junit.Assert;

/**
 * 1250. 检查「好数组」
 *
 * 给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。

 假如该和结果为 1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。

  

 示例 1：

 输入：nums = [12,5,7,23]
 输出：true
 解释：挑选数字 5 和 7。
 5*3 + 7*(-2) = 1
 示例 2：

 输入：nums = [29,6,10]
 输出：true
 解释：挑选数字 29, 6 和 10。
 29*1 + 6*(-3) + 10*(-1) = 1
 示例 3：

 输入：nums = [3,6]
 输出：false
  

 提示：

 1 <= nums.length <= 10^5
 1 <= nums[i] <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/check-if-it-is-a-good-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isGoodArray(int[] nums) {
        // 求 整个数组的最大公约数 是否为 1
        if (nums.length == 1) {
            return Math.abs(nums[0]) == 1;
        }
        int gcd = nums[0];
        for (int i = 1; i < nums.length; i++) {
            gcd = gcd(nums[i], gcd);
            if (gcd == 1) {
                return true;
            }
        }
        return false;
    }

    private int gcd (int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a%b);
    }
}
