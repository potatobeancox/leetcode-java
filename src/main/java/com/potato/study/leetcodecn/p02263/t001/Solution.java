package com.potato.study.leetcodecn.p02263.t001;

import org.junit.Assert;


/**
 * 2263. 数组变为有序的最小操作次数
 *
 * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以：
 *
 * 在范围 0 <= i < nums.length 内选出一个下标 i
 * 将 nums[i] 的值变为 nums[i] + 1 或 nums[i] - 1
 * 返回将数组 nums 变为 非递增 或 非递减 所需的 最小 操作次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,4,5,0]
 * 输出：4
 * 解释：
 * 一种可行的操作顺序，能够将 nums 变为非递增排列：
 * - nums[1] 加 1 一次，使其变为 3 。
 * - nums[2] 减 1 一次，使其变为 3 。
 * - nums[3] 减 1 两次，使其变为 3 。
 * 经过 4 次操作后，nums 变为 [3,3,3,3,0] ，按非递增顺序排列。
 * 注意，也可以用 4 步操作将 nums 变为 [4,4,4,4,0] ，同样满足题目要求。
 * 可以证明最少需要 4 步操作才能将数组变为非递增或非递减排列。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,4]
 * 输出：0
 * 解释：数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 解释：数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-array-non-decreasing-or-non-increasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2263
    public int convertArray(int[] nums) {
        // dp ij 遍历到 i 最终结果是 j
        int ascResult = getAscResult(nums);
        int[] clone = nums.clone();
        for (int i = 0; i < nums.length / 2; i++) {
            int tmp = clone[i];
            clone[i] = clone[nums.length - 1 - i];
            clone[nums.length - 1 - i] = tmp;
        }
        int descResult = getAscResult(clone);
        return Math.min(ascResult, descResult);
    }

    private int getAscResult(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][1001];
        // 处理 0
        for (int i = 0; i < 1001; i++) {
            dp[0][i] = Math.abs(nums[0] - i);
        }
        for (int i = 1; i < n; i++) {
            long min = dp[i-1][0];
            for (int j = 0; j < 1001; j++) {
                // 维护一个小于等于 j的最小值
                min = Math.min(min, dp[i-1][j]);
                // 可以直接从 小于 j的变成j
                dp[i][j] = min + Math.abs(nums[i] - j);
            }
        }

        long res = Long.MAX_VALUE;
        for (long element : dp[n-1]) {
            res = Math.min(res, element);
        }
        return (int) res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,2,4,5,0
        };
        int i = solution.convertArray(nums);
        System.out.println(i);
        Assert.assertEquals(4, i);

        nums = new int[] {
                0
        };
        i = solution.convertArray(nums);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }

}
