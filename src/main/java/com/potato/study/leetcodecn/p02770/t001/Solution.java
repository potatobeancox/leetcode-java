package com.potato.study.leetcodecn.p02770.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 *
 * 2770. 达到末尾下标所需的最大跳跃次数
 *
 * 给你一个下标从 0 开始、由 n 个整数组成的数组 nums 和一个整数 target 。
 *
 * 你的初始位置在下标 0 。在一步操作中，你可以从下标 i 跳跃到任意满足下述条件的下标 j ：
 *
 * 0 <= i < j < n
 * -target <= nums[j] - nums[i] <= target
 * 返回到达下标 n - 1 处所需的 最大跳跃次数 。
 *
 * 如果无法到达下标 n - 1 ，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,6,4,1,2], target = 2
 * 输出：3
 * 解释：要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
 * - 从下标 0 跳跃到下标 1 。
 * - 从下标 1 跳跃到下标 3 。
 * - 从下标 3 跳跃到下标 5 。
 * 可以证明，从 0 到 n - 1 的所有方案中，不存在比 3 步更长的跳跃序列。因此，答案是 3 。
 * 示例 2：
 *
 * 输入：nums = [1,3,6,4,1,2], target = 3
 * 输出：5
 * 解释：要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
 * - 从下标 0 跳跃到下标 1 。
 * - 从下标 1 跳跃到下标 2 。
 * - 从下标 2 跳跃到下标 3 。
 * - 从下标 3 跳跃到下标 4 。
 * - 从下标 4 跳跃到下标 5 。
 * 可以证明，从 0 到 n - 1 的所有方案中，不存在比 5 步更长的跳跃序列。因此，答案是 5 。
 * 示例 3：
 *
 * 输入：nums = [1,3,6,4,1,2], target = 0
 * 输出：-1
 * 解释：可以证明不存在从 0 到 n - 1 的跳跃序列。因此，答案是 -1 。
 *  
 *
 * 提示：
 *
 * 2 <= nums.length == n <= 1000
 * -109 <= nums[i] <= 109
 * 0 <= target <= 2 * 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        // 跳到i位置 需要的最大次数
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // dp i 到达i结点 需要跳的max次数 dp i = max 「dp j」 + 1 如果 ij满足条件
        for (int i = 1; i < nums.length; i++) {
            // i 表示当前的终点 枚举终点前的位置
            for (int j = 0 ; j < i; j++) {
                int diff = nums[j] - nums[i];
                if (-1 * target <= diff && diff <= target && dp[j] < Integer.MAX_VALUE) {
                    if (dp[i] == Integer.MAX_VALUE) {
                        dp[i] = dp[j] + 1;
                    } else {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        if (Integer.MAX_VALUE == dp[n-1]) {
            return -1;
        }
        return dp[n-1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        nums = [1,3,6,4,1,2], target = 2
        int[] nums = LeetcodeInputUtils.inputString2IntArray("[1,3,6,4,1,2]");
        int target = 2;
        int i = solution.maximumJumps(nums, target);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }


}
