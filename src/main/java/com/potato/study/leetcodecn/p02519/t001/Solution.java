package com.potato.study.leetcodecn.p02519.t001;

import java.util.Arrays;

/**
 * 2519. 统计 K-Big 索引的数量
 *
 * 给定一个 下标从0开始 的整数数组 nums 和一个正整数 k 。

 如果满足以下条件，我们称下标 i 为 k-big ：

 存在至少 k 个不同的索引 idx1 ，满足 idx1 < i 且 nums[idx1] < nums[i] 。
 存在至少 k 个不同的索引 idx2 ，满足 idx2 > i 且 nums[idx2] < nums[i] 。
 返回 k-big 索引的数量。

  

 示例 1 ：

 输入：nums = [2,3,6,5,2,3], k = 2
 输出：2
 解释：在nums中只有两个 2-big 的索引:
 - i = 2 --> 有两个有效的 idx1: 0 和 1。有三个有效的 idx2: 2、3 和 4。
 - i = 3 --> 有两个有效的 idx1: 0 和 1。有两个有效的 idx2: 3 和 4。
 示例 2 ：

 输入：nums = [1,1,1], k = 3
 输出：0
 解释：在 nums 中没有 3-big 的索引
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i], k <= nums.length

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-the-number-of-k-big-indices
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int kBigIndices(int[] nums, int k) {

        return -1;
    }

}
