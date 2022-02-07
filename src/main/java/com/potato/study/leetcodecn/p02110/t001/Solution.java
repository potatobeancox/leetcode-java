package com.potato.study.leetcodecn.p02110.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 2110. 股票平滑下跌阶段的数目
 *
 * 给你一个整数数组 prices ，表示一支股票的历史每日股价，其中 prices[i] 是这支股票第 i 天的价格。
 *
 * 一个 平滑下降的阶段 定义为：对于 连续一天或者多天 ，每日股价都比 前一日股价恰好少 1 ，这个阶段第一天的股价没有限制。
 *
 * 请你返回 平滑下降阶段 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：prices = [3,2,1,4]
 * 输出：7
 * 解释：总共有 7 个平滑下降阶段：
 * [3], [2], [1], [4], [3,2], [2,1] 和 [3,2,1]
 * 注意，仅一天按照定义也是平滑下降阶段。
 * 示例 2：
 *
 * 输入：prices = [8,6,7,7]
 * 输出：4
 * 解释：总共有 4 个连续平滑下降阶段：[8], [6], [7] 和 [7]
 * 由于 8 - 6 ≠ 1 ，所以 [8,6] 不是平滑下降阶段。
 * 示例 3：
 *
 * 输入：prices = [1]
 * 输出：1
 * 解释：总共有 1 个平滑下降阶段：[1]
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 1 <= prices[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-smooth-descent-periods-of-a-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 2110
    public long getDescentPeriods(int[] prices) {
        // dp i 以i结尾的 平滑下降数量 如果 i 比 i-1小1 dpi = 1 + dpi-1 否则就是 1 遍历 dp 求和
        long[] dp = new long[prices.length];
        dp[0] = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i-1] - 1) {
                dp[i] = 1 + dp[i-1];
            } else {
                dp[i] = 1;
            }
        }
        // 计算有多少个
        long total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += dp[i];
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                3,2,1,4
        };
        long descentPeriods = solution.getDescentPeriods(arr);
        System.out.println(descentPeriods);
        Assert.assertEquals(7, descentPeriods);
    }
}
