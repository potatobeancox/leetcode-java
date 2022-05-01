package com.potato.study.leetcodecn.p00790.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 790. 多米诺和托米诺平铺
 *
 * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 *
 *
 *
 * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。返回对 109 + 7 取模 的值。
 *
 * 平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: n = 3
 * 输出: 5
 * 解释: 五种不同的方法如上所示。
 * 示例 2:
 *
 * 输入: n = 1
 * 输出: 1
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/domino-and-tromino-tiling
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/domino-and-tromino-tiling/solution/duo-mi-nuo-yu-tuo-mi-nuo-ping-pu-by-leetcode/
     * @param n
     * @return
     */
    public int numTilings(int n) {
        int mod = 1_000_000_000 + 7;
        // dp 代表 当前 列 每行的铺砖情况 0 两行 都不铺 1 只铺第一行
        // 初始化 代表 第一列 情况 只有 全不铺才有 1个
        long[] dp = new long[] {1, 0, 0, 0};
        // 进行n-1次遍历 最终返回 全铺满的情况
        for (int i = 0; i < n; i++) {
            // 用一个 新的 dp 数组 表示当前存在情况
            long[] next = new long[4];
            // 当前列 的次数 当前列什么都不铺 ，之前列也没铺或者之前全铺了
            next[0] = dp[0] + dp[3];
            next[0] %= mod;
            // 当前列 铺了第一列，之前列只铺了第二列 然后整一个 横着的多米诺 或者什么都没铺
            next[1] = dp[2] + dp[0];
            next[1] %= mod;
            // 当前列 铺了第二列，第一列的 倒转
            next[2] += dp[1] + dp[0];
            next[2] %= mod;
            // 全铺满 上一列 全铺满 铺了1或者2
            next[3] += dp[0] + dp[1] + dp[2];
            next[3] %= mod;

            dp = next;
        }
        return (int) dp[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int i = solution.numTilings(n);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
