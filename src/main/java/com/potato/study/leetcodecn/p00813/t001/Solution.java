package com.potato.study.leetcodecn.p00813.t001;

import org.junit.Assert;

import java.lang.ref.SoftReference;

/**
 * 813. 最大平均值和的分组
 *
 * 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。

 注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。

 示例:
 输入:
 A = [9,1,2,3,9]
 K = 3
 输出: 20
 解释:
 A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
 我们也可以把 A 分成[9, 1], [2], [3, 9].
 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
 说明:

 1 <= A.length <= 100.
 1 <= A[i] <= 10000.
 1 <= K <= A.length.
 答案误差在 10^-6 内被视为是正确的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-sum-of-averages
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double largestSumOfAverages(int[] nums, int k) {
        // dp ij 从 0-i （包含i） 分成j最获取到的 最大平均值的和
        int n = nums.length;
        double[][] dp = new double[n][k+1];
        // 初始化一下 只分成1组的平均值
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            // 0-i 分成一组
            dp[i][1] = sum / (i+1);
        }

        for (int j = 2; j <= k; j++) {
            for (int i = j-1; i < nums.length; i++) {
                // 第 i 单独作为一组
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + nums[i]);
                // 和前一个分到一起 计算 从i位置 往前的和
                double rangSum = nums[i];
                for (int l = 1; i-l > 0; l++) {
                    rangSum += nums[i-l];
                    if (i-l < j-1) {
                        break;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i-l-1][j-1] + rangSum / (l+1));
                }
            }
        }
        return dp[n-1][k];
    }

    /**
     * [9,1,2,3,9]
     3
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                9,1,2,3,9
        };
        int k = 3;
        double v = solution.largestSumOfAverages(nums, k);
        System.out.println(v);
        Assert.assertEquals(20, v, 10e-6);


        nums = new int[] {
                1,2,3,4,5,6,7
        };
        k = 4;
        v = solution.largestSumOfAverages(nums, k);
        System.out.println(v);
        Assert.assertEquals(20.5, v, 10e-6);


    }
}
