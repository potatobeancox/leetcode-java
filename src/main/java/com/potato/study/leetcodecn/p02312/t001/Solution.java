package com.potato.study.leetcodecn.p02312.t001;

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




    // 2312
    public long sellingWood(int m, int n, int[][] prices) {
        // 两位二维数组 一个记录 高 宽对应的碎片 卖多少前
        long[][] price = new long[m+1][n+1];
        for (int i = 0; i < prices.length; i++) {
            int[] eachPrice = prices[i];
            int hight = eachPrice[0];
            int width = eachPrice[1];
            int p = eachPrice[2];
            price[hight][width] = Math.max(price[hight][width], p);
        }
        // 另一个记录 高块的 大块 最多卖多少钱
        long[][]totalPrice = new long[m+1][n+1];
        // 每次 从中间点开始 分割 找到 最大值 返回 dfs
        long maxPrice = dfs(m, n, price, totalPrice);
        return maxPrice;
    }

    /**
     * m * n 的木头块 最多能获取的奖金
     * @param m
     * @param n
     * @param price
     * @param totalPrice
     * @return
     */
    private long dfs(int m, int n, long[][] price, long[][] totalPrice) {
        // 没有当然是 0
        if (m == 0 || n == 0) {
            return 0;
        }
        // 之前已经计算过这个结果了
        if (totalPrice[m][n] > 0) {
            return totalPrice[m][n];
        }
        if (price[m][n] > 0) {
            return price[m][n];
        }
        // 计算mn
        long maxTotal = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long total = dfs(i, j, price, totalPrice)
                        + dfs(m-i, n-j, price, totalPrice);
                maxTotal = Math.max(maxTotal, total);
            }
        }
        totalPrice[m][n] = maxTotal;
        return maxTotal;
    }

}
