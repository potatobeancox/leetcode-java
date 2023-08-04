package com.potato.study.leetcodecn.other.lcr.p0101.t001;

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


    /**
     * https://leetcode-cn.com/problems/NUPfPr/solution/java-bei-bao-wen-ti-by-mou-zi-ming-z-75v4/
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // 简单dp bool 这个值是否可以达到 每次从 大往小遍历
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        // 遍历每个数字
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 从target 往前看 是否可以 | 组成
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }





}
