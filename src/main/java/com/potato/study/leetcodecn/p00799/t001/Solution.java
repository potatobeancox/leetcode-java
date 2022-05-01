package com.potato.study.leetcodecn.p00799.t001;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * 799. 香槟塔
 *
 * 我们把玻璃杯摆成金字塔的形状，其中 第一层 有 1 个玻璃杯， 第二层 有 2 个，依次类推到第 100 层，每个玻璃杯 (250ml) 将盛有香槟。

 从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上）

 例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。在倒三杯香槟后，第二层的香槟满了 - 此时总共有三个满的玻璃杯。在倒第四杯后，第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟，如下图所示。



 现在当倾倒了非负整数杯香槟后，返回第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（ i 和 j 都从0开始）。

  

 示例 1:
 输入: poured(倾倒香槟总杯数) = 1, query_glass(杯子的位置数) = 1, query_row(行数) = 1
 输出: 0.00000
 解释: 我们在顶层（下标是（0，0））倒了一杯香槟后，没有溢出，因此所有在顶层以下的玻璃杯都是空的。

 示例 2:
 输入: poured(倾倒香槟总杯数) = 2, query_glass(杯子的位置数) = 1, query_row(行数) = 1
 输出: 0.50000
 解释: 我们在顶层（下标是（0，0）倒了两杯香槟后，有一杯量的香槟将从顶层溢出，位于（1，0）的玻璃杯和（1，1）的玻璃杯平分了这一杯香槟，所以每个玻璃杯有一半的香槟。
 示例 3:

 输入: poured = 100000009, query_row = 33, query_glass = 17
 输出: 1.00000
  

 提示:

 0 <= poured <= 109
 0 <= query_glass <= query_row < 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/champagne-tower
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/champagne-tower/solution/champagne-tower-by-leetcode/
     * @param poured
     * @param queryRow
     * @param queryGlass
     * @return
     */
    public double champagneTower(int poured, int queryRow, int queryGlass) {
        // dp ij 第i行 j列 有的 酒
        double[][] dp = new double[102][102];
        // 每次倒酒倒完了计算下面2个杯子的多少
        dp[0][0] = poured;
        // 所有酒都倒的第一个杯子里
        for (int i = 0; i <= queryRow; i++) {
            // 控制列
            for (int j = 0; j <= i; j++) {
                // 溢出了 多少
                double remind = (dp[i][j] - 1.0) / 2;
                if (remind > 0) {
                    dp[i+1][j] += remind;
                    dp[i+1][j+1] += remind;
                }
            }
        }
        return Math.min(1.0, dp[queryRow][queryGlass]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int poured = 1;
        int queryRow = 1;
        int queryGlass = 1;
        double v = solution.champagneTower(poured, queryRow, queryGlass);
        // 0.0
        System.out.println(v);


        poured = 2;
        queryRow = 1;
        queryGlass = 1;
        v = solution.champagneTower(poured, queryRow, queryGlass);
        // 0.5
        System.out.println(v);



        poured = 100000009;
        queryRow = 33;
        queryGlass = 17;
        v = solution.champagneTower(poured, queryRow, queryGlass);
        // 1
        System.out.println(v);


        poured = 25;
        queryRow = 6;
        queryGlass = 1;
        v = solution.champagneTower(poured, queryRow, queryGlass);
        // 0.1875
        System.out.println(v);
    }
}
