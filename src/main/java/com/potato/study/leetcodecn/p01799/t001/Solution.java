package com.potato.study.leetcodecn.p01799.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1799. N 次操作后的最大分数和
 *
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
 *
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 *
 * 选择两个元素 x 和 y 。
 * 获得分数 i * gcd(x, y) 。
 * 将 x 和 y 从 nums 中删除。
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 *
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最优操作是：
 * (1 * gcd(1, 2)) = 1
 * 示例 2：
 *
 * 输入：nums = [3,4,6,8]
 * 输出：11
 * 解释：最优操作是：
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,5,6]
 * 输出：14
 * 解释：最优操作是：
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *  
 *
 * 提示：
 *
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximize-score-after-n-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1799
    public int maxScore(int[] nums) {
        int n = nums.length;
        // gcd ij 说明 两个 index的值的 最大公约数
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    gcd[i][j] = nums[i];
                    continue;
                }
                int g = gcd(nums[i], nums[j]);
                gcd[i][j] = g;
                gcd[j][i] = g;
            }
        }
        // 一共n次操作 1《《 n 个数
        int limit = (1 << n);
        int[] dp = new int[limit];
        // 状态从 0开始
        dp[0] = 0;
        for (int i = 0; i < limit; i++) {
            // 获取当前状态有多少个1
            int bitCount = Integer.bitCount(i);
            if (bitCount % 2 == 1) {
                // 目前有单数个 1 就是用了 n个数字
                continue;
            }
            // 用了偶数个 数字 遍历 内部的没2个1的位置
            for (int j = 0; j < n; j++) {
                // j 位置必须为1
                if (((1<<j) & i) == 0) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    // jk 不能相同
                    if (j == k) {
                        continue;
                    }
                    // jk位置必须都是1
                    if (((1<<k) & i) == 0) {
                        continue;
                    }
                    // 计算下 没用这两个位置之前的state
                    int state = i ^ (1<<j) ^ (1<<k);
                    int num = Integer.bitCount(i) / 2 ;
                    dp[i] = Math.max(dp[state] + num * gcd[j][k], dp[i]);
                }
            }
        }
        return dp[limit - 1];
    }

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1, 2
        };
        int i = solution.maxScore(nums);
        System.out.println(i);
        Assert.assertEquals(1, i);


        nums = new int[] {
                1,2,3,4,5,6
        };
        i = solution.maxScore(nums);
        System.out.println(i);
//        Assert.assertEquals(1, i);
    }
}
