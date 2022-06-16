package com.potato.study.leetcodecn.p02294.t001;

import java.util.Arrays;

/**
 * 2294. 划分数组使最大差为 K
 *
 * 给你一个整数数组 nums 和一个整数 k 。你可以将 nums 划分成一个或多个 子序列 ，使 nums 中的每个元素都 恰好 出现在一个子序列中。
 *
 * 在满足每个子序列中最大值和最小值之间的差值最多为 k 的前提下，返回需要划分的 最少 子序列数目。
 *
 * 子序列 本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,1,2,5], k = 2
 * 输出：2
 * 解释：
 * 可以将 nums 划分为两个子序列 [3,1,2] 和 [6,5] 。
 * 第一个子序列中最大值和最小值的差值是 3 - 1 = 2 。
 * 第二个子序列中最大值和最小值的差值是 6 - 5 = 1 。
 * 由于创建了两个子序列，返回 2 。可以证明需要划分的最少子序列数目就是 2 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 1
 * 输出：2
 * 解释：
 * 可以将 nums 划分为两个子序列 [1,2] 和 [3] 。
 * 第一个子序列中最大值和最小值的差值是 2 - 1 = 1 。
 * 第二个子序列中最大值和最小值的差值是 3 - 3 = 0 。
 * 由于创建了两个子序列，返回 2 。注意，另一种最优解法是将 nums 划分成子序列 [1] 和 [2,3] 。
 * 示例 3：
 *
 * 输入：nums = [2,2,4,5], k = 0
 * 输出：3
 * 解释：
 * 可以将 nums 划分为三个子序列 [2,2]、[4] 和 [5] 。
 * 第一个子序列中最大值和最小值的差值是 2 - 2 = 0 。
 * 第二个子序列中最大值和最小值的差值是 4 - 4 = 0 。
 * 第三个子序列中最大值和最小值的差值是 5 - 5 = 0 。
 * 由于创建了三个子序列，返回 3 。可以证明需要划分的最少子序列数目就是 3 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * 0 <= k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    //2294
    public int partitionArray(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        // 先排序
        Arrays.sort(nums);
        int partitionCount = 1;
        int min = nums[0];
        // 记录每个开始区间的最小值，如果当前元素差 大于k 新创建一个
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - min > k) {
                partitionCount++;
                min = nums[i];
            }
        }
        return partitionCount;
    }


}
