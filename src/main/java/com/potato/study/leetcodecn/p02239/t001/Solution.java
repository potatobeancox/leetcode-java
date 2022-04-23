package com.potato.study.leetcodecn.p02239.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 2239. 找到最接近 0 的数字
 *
 * 给你一个长度为 n 的整数数组 nums ，请你返回 nums 中最 接近 0 的数字。如果有多个答案，请你返回它们中的 最大值 。

  

 示例 1：

 输入：nums = [-4,-2,1,4,8]
 输出：1
 解释：
 -4 到 0 的距离为 |-4| = 4 。
 -2 到 0 的距离为 |-2| = 2 。
 1 到 0 的距离为 |1| = 1 。
 4 到 0 的距离为 |4| = 4 。
 8 到 0 的距离为 |8| = 8 。
 所以，数组中距离 0 最近的数字为 1 。
 示例 2：

 输入：nums = [2,-1,1]
 输出：1
 解释：1 和 -1 都是距离 0 最近的数字，所以返回较大值 1 。
  

 提示：

 1 <= n <= 1000
 -105 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-closest-number-to-zero
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findClosestNumber(int[] nums) {
        int min = nums[0];
        int dis = Math.abs(min);

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i]) < dis || (Math.abs(nums[i]) == dis&& nums[i] > min)) {
                min = nums[i];
                dis = Math.abs(nums[i]);
            }
        }
        return min;
    }
}
