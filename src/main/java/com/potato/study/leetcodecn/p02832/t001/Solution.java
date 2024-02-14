package com.potato.study.leetcodecn.p02832.t001;


import java.util.List;

/**
 *
 * 2832. 每个元素为最大值的最大范围


 *
 * 现给定一个由 不同 整数构成的 0 索引数组 nums 。

 我们用以下方式定义与 nums 长度相同的 0 索引数组 ans ：

 ans[i] 是子数组 nums[l..r] 的 最大 长度，该子数组中的最大元素等于 nums[i] 。
 返回数组 ans 。

 注意，子数组 是数组的连续部分。



 示例 1：

 输入：nums = [1,5,4,3,6]
 输出：[1,4,2,1,5]
 解释：对于 nums[0]，最长的子数组，其中最大值为 1，是 nums[0..0]，所以 ans[0] = 1。
 对于 nums[1]，最长的子数组，是 nums[0..3]，其中最大值为 5，所以 ans[1] = 4。
 对于 nums[2]，最长的子数组，是 nums[2..3]，其中最大值为 4，所以 ans[2] = 2。
 对于 nums[3]，最长的子数组，是 nums[3..3]，其中最大值为 3，所以 ans[3] = 1。
 对于 nums[4]，最长的子数组，是 nums[0..4]，其中最大值为 6，所以 ans[4] = 5。
 示例 2：

 输入：nums = [1,2,3,4,5]
 输出：[1,2,3,4,5]
 解释：对于 nums[i]，最长的子数组，是 nums[0..i]，其中最大值与 nums[i] 相等，所以 ans[i] = i + 1。


 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 105
 所有 nums 中的元素都是不重复的。
 *
 */
public class Solution {



    public int[] maximumLengthOfRanges(int[] nums) {

        return null;
    }


}
