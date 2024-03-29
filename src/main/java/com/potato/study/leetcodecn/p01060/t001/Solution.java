package com.potato.study.leetcodecn.p01060.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1060. 有序数组中的缺失元素

 *
 * 现有一个按 升序 排列的整数数组 nums ，其中每个数字都 互不相同 。

 给你一个整数 k ，请你找出并返回从数组最左边开始的第 k 个缺失数字。

  

 示例 1：

 输入：nums = [4,7,9,10], k = 1
 输出：5
 解释：第一个缺失数字为 5 。
 示例 2：

 输入：nums = [4,7,9,10], k = 3
 输出：8
 解释：缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
 示例 3：

 输入：nums = [1,2,4], k = 3
 输出：6
 解释：缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
  

 提示：

 1 <= nums.length <= 5 * 104
 1 <= nums[i] <= 107
 nums 按 升序 排列，其中所有元素 互不相同 。
 1 <= k <= 108

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/missing-element-in-sorted-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int missingElement(int[] nums, int k) {
        // nums 已经是升序
        for (int i = 1; i < nums.length; i++) {
            // 连续了
            int interval = nums[i] - nums[i-1];
            if (interval == 1) {
                continue;
            }
            // 不连续中间空了多少个
            int blank = interval - 1;
            if (blank >= k) {
                return nums[i-1] + k;
            }
            // 没超过
            k -= blank;
        }
        return nums[nums.length - 1] + k;
    }
}
