package com.potato.study.leetcodecn.p01984.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1984. 学生分数的最小差值
 *
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。

 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。

 返回可能的 最小差值 。

  

 示例 1：

 输入：nums = [90], k = 1
 输出：0
 解释：选出 1 名学生的分数，仅有 1 种方法：
 - [90] 最高分和最低分之间的差值是 90 - 90 = 0
 可能的最小差值是 0
 示例 2：

 输入：nums = [9,4,1,7], k = 2
 输出：2
 解释：选出 2 名学生的分数，有 6 种方法：
 - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
 - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
 - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
 - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
 - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
 - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
 可能的最小差值是 2
  

 提示：

 1 <= k <= nums.length <= 1000
 0 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int minimumDifference(int[] nums, int k) {
        // 对nums 进行排序
        Arrays.sort(nums);
        // 滑动窗口 比较最大值和最小值之前的差值的 min
        int min = Integer.MAX_VALUE;
        for (int i = k-1; i < nums.length; i++) {
            min = Math.min(min, nums[i] - nums[i-k+1]);
        }
        return min;
    }

}
