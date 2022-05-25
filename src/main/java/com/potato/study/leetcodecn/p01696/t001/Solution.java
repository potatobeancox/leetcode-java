package com.potato.study.leetcodecn.p01696.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;

/**
 * 1696. 跳跃游戏 VI
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
 *
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 *
 * 请你返回你能得到的 最大得分 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,-1,-2,4,-7,3], k = 2
 * 输出：7
 * 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
 * 示例 2：
 *
 * 输入：nums = [10,-5,-2,4,0,3], k = 3
 * 输出：17
 * 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
 * 示例 3：
 *
 * 输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * 输出：0
 *  
 *
 * 提示：
 *
 *  1 <= nums.length, k <= 105
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jump-game-vi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/jump-game-vi/solution/java-dong-tai-gui-hua-jian-zhi-7msji-bai-lscm/
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums, int k) {
        // dp i 标识 到 i位置能 获得的最大得分 则有 dp i = max dp i-1  i-k 中的最大值
        int[] dp = new int[nums.length];
        // 初始化 dp 1 以上都是 min dp 0 = num 0
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        // 外层 从 0 开始 内层从 i+1 开始 到 尾部
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < Math.min(nums.length, i+k+1) ; j++) {
                dp[j] = Math.max(dp[j], dp[i] + nums[j]);
                // i 可以被 j 覆盖 继续找下一个
                if (dp[j] >= dp[i]) {
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }


}
