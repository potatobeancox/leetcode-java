package com.potato.study.leetcodecn.p01049.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

/**
 * 1049. 最后一块石头的重量 II
 *
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 *
 * 输入：stones = [1,2]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/last-stone-weight-ii/solution/zui-hou-yi-kuai-shi-tou-de-zhong-liang-i-95p9/
     *
     * 找到 sum - 2 small 最小的值
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        // 遍历 stones 求 sum / 2 和 len
        int sum = Arrays.stream(stones).sum();
        int limit = sum / 2;
        // 可能出现 等于 limit的情况
        boolean[][] dp = new boolean[stones.length + 1][limit + 1];
        // dp len, sum / 2 + 1 dp ij 为使用到 i能是否可以构成 sum = k的组合
        dp[0][0] = true;
        for (int i = 1; i <= stones.length; i++) {
            // 重量为 0 是啥都不选的状态
            dp[i][0] = true;
            for (int j = 1; j <= limit; j++) {
                // 转移方程 如果当前石头太大了 直接consinue了 否则 dp ij = dp i-1 j ｜｜ dp i-1， j-stone[i]
                if (j < stones[i-1]) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                // 用到 i-1 已经达到了 j或者 用了本次的石头
                dp[i][j] = dp[i-1][j] || dp[i-1][j - stones[i-1]];
            }
        }
        // 反向便利 dp 找到 limit 后第一个为true 就是要求的
        for (int i = limit; i >= 0; i--) {
            if (dp[stones.length][i]) {
                return sum - 2 * i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                2,7,4,1,8,1
        };
        int i = solution.lastStoneWeightII(arr);
        System.out.println(i);
        Assert.assertEquals(1, i);


        arr = new int[] {
                31,26,33,21,40
        };
        i = solution.lastStoneWeightII(arr);
        System.out.println(i);
        Assert.assertEquals(5, i);

        arr = new int[] {
                1, 2
        };
        i = solution.lastStoneWeightII(arr);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
