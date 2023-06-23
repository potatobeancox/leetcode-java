package com.potato.study.leetcodecn.p02312.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 2312. 卖木头块
 *
 * 给你两个整数 m 和 n ，分别表示一块矩形木块的高和宽。同时给你一个二维整数数组 prices ，其中 prices[i] = [hi, wi, pricei] 表示你可以以 pricei 元的价格卖一块高为 hi 宽为 wi 的矩形木块。

 每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：

 沿垂直方向按高度 完全 切割木块，或
 沿水平方向按宽度 完全 切割木块
 在将一块木块切成若干小木块后，你可以根据 prices 卖木块。你可以卖多块同样尺寸的木块。你不需要将所有小木块都卖出去。你 不能 旋转切好后木块的高和宽。

 请你返回切割一块大小为 m x n 的木块后，能得到的 最多 钱数。

 注意你可以切割木块任意次。

  

 示例 1：



 输入：m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
 输出：19
 解释：上图展示了一个可行的方案。包括：
 - 2 块 2 x 2 的小木块，售出 2 * 7 = 14 元。
 - 1 块 2 x 1 的小木块，售出 1 * 3 = 3 元。
 - 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
 总共售出 14 + 3 + 2 = 19 元。
 19 元是最多能得到的钱数。
 示例 2：



 输入：m = 4, n = 6, prices = [[3,2,10],[1,4,2],[4,1,3]]
 输出：32
 解释：上图展示了一个可行的方案。包括：
 - 3 块 3 x 2 的小木块，售出 3 * 10 = 30 元。
 - 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
 总共售出 30 + 2 = 32 元。
 32 元是最多能得到的钱数。
 注意我们不能旋转 1 x 4 的木块来得到 4 x 1 的木块。
  

 提示：

 1 <= m, n <= 200
 1 <= prices.length <= 2 * 104
 prices[i].length == 3
 1 <= hi <= m
 1 <= wi <= n
 1 <= pricei <= 106
 所有 (hi, wi) 互不相同 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/selling-pieces-of-wood
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/selling-pieces-of-wood/solution/by-endlesscheng-mrmd/
     * @param m
     * @param n
     * @param prices
     * @return
     */
    public long sellingWood(int m, int n, int[][] prices) {
        // 两位二维数组 一个记录 高 宽对应的碎片 卖多少钱
        long[][] price = new long[m+1][n+1];
        for (int i = 0; i < prices.length; i++) {
            int[] eachPrice = prices[i];
            // prices[i] = [hi, wi, pricei]
            int height = eachPrice[0];
            int width = eachPrice[1];
            int p = eachPrice[2];
            price[height][width] = Math.max(price[height][width], p);
        }
        long[][] dp = new long[m+1][n+1];
        // 从小到大枚举高和宽
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = price[i][j];
                // 高度进行分割
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i-k][j]);
                }
                // 宽度进行分割
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j-k]);
                }
            }
        }
        return dp[m][n];
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 3;
        int n = 5;
        int[][] prices = new int[][]{{1,4,2},{2,2,7},{2,1,3}};
        long l = solution.sellingWood(m, n, prices);
        System.out.println(l);
        Assert.assertEquals(19, l);


        m = 4;
        n = 6;
        prices = new int[][]{
                {3,2,10},
                {1,4,2},
                {4,1,3}
        };
        l = solution.sellingWood(m, n, prices);
        System.out.println(l);
        Assert.assertEquals(32, l);


        m = 4;
        n = 2;
        // [[4,1,18],[4,2,5],[1,1,20],[3,1,21]]
        prices = new int[][]{

                {4,1,18},
                {4,2,5},
                {1,1,20},
                {3,1,21},
        };
        l = solution.sellingWood(m, n, prices);
        System.out.println(l);
        Assert.assertEquals(160, l);


        m = 11;
        n = 20;
        prices = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[10,9,14]]");
        l = solution.sellingWood(m, n, prices);
        System.out.println(l);
//        Assert.assertEquals(160, l);
    }

}
