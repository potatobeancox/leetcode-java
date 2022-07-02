package com.potato.study.leetcodecn.p01477.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1477. 找两个和为目标值且不重叠的子数组
 *
 * 给你一个整数数组 arr 和一个整数值 target 。
 *
 * 请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 *
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,2,4,3], target = 3
 * 输出：2
 * 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
 * 示例 2：
 *
 * 输入：arr = [7,3,4,7], target = 7
 * 输出：2
 * 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
 * 示例 3：
 *
 * 输入：arr = [4,3,2,6,2,3,4], target = 6
 * 输出：-1
 * 解释：我们只有一个和为 6 的子数组。
 * 示例 4：
 *
 * 输入：arr = [5,5,4,4,5], target = 3
 * 输出：-1
 * 解释：我们无法找到和为 3 的子数组。
 * 示例 5：
 *
 * 输入：arr = [3,1,1,1,5,1,2,1], target = 3
 * 输出：3
 * 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/solution/yi-ci-bian-li-hua-dong-chuang-kou-jia-dong-tai-gui/
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengths(int[] arr, int target) {
        // dp i i包含这个位置 之前 最小的等于 target的大小
        int n = arr.length;
        long[] dp = new long[n];
        // 初始值都是 Iny max
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        // dp i = min dp i-1, dp i, 当 【j-i】 和为 target 时间 j-i+1 + dpi-1
        int leftIndex = 0;
        int currentSum = 0;
        long ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            currentSum += arr[i];
            while (leftIndex <= i && currentSum > target) {
                currentSum -= arr[leftIndex];
                leftIndex++;
            }
            // 求现在和 是否满足等于 target
            if (target == currentSum) {
                // 计算之前的结果
                if (i > 0) {
                    // 最开始的第一次没有啥意义
                    if (leftIndex > 0) {
                        ans = Math.min(ans, dp[leftIndex-1] + i - leftIndex + 1);
                    }
                    dp[i] = Math.min(dp[i], dp[i-1]);
                }
                // 统计下 i之前的情况
                dp[i] = Math.min(i - leftIndex + 1, dp[i]);
            } else {
                // 当前不相等
                if (i > 0) {
                    dp[i] = Math.min(dp[i], dp[i-1]);
                }
            }
        }
        // 从 i= 0 开始 转移
        if (ans >= Integer.MAX_VALUE / 2) {
            return -1;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,6,1
        };
        int target = 7;
        int i = solution.minSumOfLengths(arr, target);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }
}
