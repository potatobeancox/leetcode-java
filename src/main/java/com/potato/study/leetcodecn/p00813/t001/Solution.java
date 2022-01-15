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
        // dp ij 从 0-i 分成j最获取到的最大平均值 对于每次分割 dp ij =
        int n = nums.length;
        double[][] dp = new double[n+1][k+1];


        dp[1][1] = nums[0];
        for (int l = 1; l <= k; l++) {
            for (int i = 2; i <= n; i++) {
                // 单独分组
                if (l != 1 && dp[i-1][l-1] == 0) {
                    continue;
                }
//                dp[i][l] = dp[i-1][l-1] + nums[i-1];
                int sum = 0;
                int count = 0;
                // 和前一个分到一起
                for (int j = i; j >= 1; j--) {
                    sum += nums[j-1];
                    count++;
                    dp[i][l] = Math.max(dp[j-1][l-1] + (sum * 1.0 / count), dp[i][l]);
                }
            }
        }
        return dp[n][k];
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
