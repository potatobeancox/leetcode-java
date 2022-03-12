package com.potato.study.leetcodecn.other.swordoffer2.p0101.t001;

import java.util.Arrays;

/**
 * 剑指 Offer II 101. 分割等和子集
 *
 * 给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。

  

 示例 1：

 输入：nums = [1,5,11,5]
 输出：true
 解释：nums 可以分割成 [1, 5, 5] 和 [11] 。
 示例 2：

 输入：nums = [1,2,3,5]
 输出：false
 解释：nums 不可以分为和相等的两部分
  

 提示：

 1 <= nums.length <= 200
 1 <= nums[i] <= 100


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/NUPfPr
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int left = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            left += nums[i];
            if (left == sum - left) {
                return true;
            }
        }
        return false;
    }
}
