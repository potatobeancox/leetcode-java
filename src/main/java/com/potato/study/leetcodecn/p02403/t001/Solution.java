package com.potato.study.leetcodecn.p02403.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2403. 杀死所有怪物的最短时间
 *
 * 你有一个整数数组 power，其中  power[i] 是第 i 个怪物的力量。

 你从 0 点法力值开始，每天获取 gain 点法力值，最初 gain 等于 1。

 每天，在获得 gain 点法力值后，如果你的法力值大于或等于怪物的力量，你就可以打败怪物。当你打败怪物时:

 你的法力值会被重置为 0，并且

 gain 的值增加 1。

 返回打败所有怪物所需的 最少 天数。

  

 示例 1:

 输入: power = [3,1,4]
 输出: 4
 解释: 打败所有怪物的最佳方法是:
 - 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 2 个怪物。
 - 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。
 - 第 3 天: 获得 2 点法力值，现在总共拥有 4 点法力值。用尽所有法力值击杀第 3 个怪物。
 - 第 4 天: 获得 2 点法力值，现在总共拥有 4 点法力值。 用尽所有法力值击杀第 1 个怪物。
 可以证明，4 天是最少需要的天数。
 示例 2:

 输入: power = [1,1,4]
 输出: 4
 解释: 打败所有怪物的最佳方法是:
 - 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 1 个怪物。
 - 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。用尽所有法力值击杀第 2 个怪物。
 - 第 3 天: 获得 3 点法力值，现在总共拥有 3 点法力值。
 - 第 4 天: 获得 3 点法力值，现在总共拥有 6 点法力值。用尽所有法力值击杀第 3 个怪物。
 可以证明，4 天是最少需要的天数。
 示例 3:

 输入: power = [1,2,4,9]
 输出: 6
 解释: 打败所有怪物的最佳方法是:
 - 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 1 个怪物
 - 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。用尽所有法力值击杀第 2 个怪物。
 - 第 3 天: 获得 3 点法力值，现在总共拥有 3 点法力值。
 - 第 4 天: 获得 3 点法力值，现在总共拥有 6 点法力值。
 - 第 5 天: 获得 3 点法力值，现在总共拥有 9 点法力值。用尽所有法力值击杀第 4 个怪物。
 - 第 6 天: 获得 4 点法力值，现在总共拥有 4 点法力值。用尽所有法力值击杀第 3 个怪物。
 可以证明，6 天是最少需要的天数。
  

 提示:

 1 <= power.length <= 17
 1 <= power[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-time-to-kill-all-monsters
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long minimumTime(int[] power) {
        // 1 <= power.length <= 17 怪兽的数量 可以用2进制位来表示
        int length = power.length;
        int limit = (1 << length);
        // 使用一个 一位数组 dp status 表示 选择 状态为 status的数组 需要花费的最少天数
        long[] dp = new long[limit];
        // 最开始 每个状态的天数 都为 最大值
        Arrays.fill(dp, Long.MAX_VALUE);
        // dp 0 = 0 什么都不选花费 0 天 从 1开始遍历每个状态，内部增加 i 网上改最小值
        dp[0] = 0;
        for (int i = 0; i < limit; i++) {
            long gain = Long.bitCount(i) + 1L;
            for (int j = 0; j < length; j++) {
                // 获取bit
                int bit = (1 << j);
                // 计算最小值
                if ((i & bit) == 1) {
                    // 已经包含过了
                    continue;
                }
                int afterStatus = (i | bit);
                long cost;
                if (power[j] % gain == 0) {
                    cost = (long)power[j] / gain;
                } else {
                    cost = (long)power[j] / gain + 1;
                }
                dp[afterStatus] = Math.min(dp[afterStatus], dp[i] + cost);
            }
        }
        return dp[limit - 1];
    }

}
