package com.potato.study.leetcodecn.p02087.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2087. 网格图中机器人回家的最小代价
 *
 * 给你一个 m x n 的网格图，其中 (0, 0) 是最左上角的格子，(m - 1, n - 1) 是最右下角的格子。给你一个整数数组 startPos ，startPos = [startrow, startcol] 表示 初始 有一个 机器人 在格子 (startrow, startcol) 处。同时给你一个整数数组 homePos ，homePos = [homerow, homecol] 表示机器人的 家 在格子 (homerow, homecol) 处。

 机器人需要回家。每一步它可以往四个方向移动：上，下，左，右，同时机器人不能移出边界。每一步移动都有一定代价。再给你两个下标从 0 开始的额整数数组：长度为 m 的数组 rowCosts  和长度为 n 的数组 colCosts 。

 如果机器人往 上 或者往 下 移动到第 r 行 的格子，那么代价为 rowCosts[r] 。
 如果机器人往 左 或者往 右 移动到第 c 列 的格子，那么代价为 colCosts[c] 。
 请你返回机器人回家需要的 最小总代价 。

  

 示例 1：



 输入：startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
 输出：18
 解释：一个最优路径为：
 从 (1, 0) 开始
 -> 往下走到 (2, 0) 。代价为 rowCosts[2] = 3 。
 -> 往右走到 (2, 1) 。代价为 colCosts[1] = 2 。
 -> 往右走到 (2, 2) 。代价为 colCosts[2] = 6 。
 -> 往右走到 (2, 3) 。代价为 colCosts[3] = 7 。
 总代价为 3 + 2 + 6 + 7 = 18
 示例 2：

 输入：startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
 输出：0
 解释：机器人已经在家了，所以不需要移动。总代价为 0 。
  

 提示：

 m == rowCosts.length
 n == colCosts.length
 1 <= m, n <= 105
 0 <= rowCosts[r], colCosts[c] <= 104
 startPos.length == 2
 homePos.length == 2
 0 <= startrow, homerow < m
 0 <= startcol, homecol < n

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        // 因为 每个 cost 都是整数 所以 最小路径就是 向着走的 路径 计算路径差
        int totalCost = 0;
        // 找到 row 的 from to
        int startRowIndex = startPos[0];
        int endRowIndex = homePos[0];

        int from;
        int to;

        if (startRowIndex > endRowIndex) {
            from = endRowIndex;
            to = startRowIndex - 1;
        } else {
            from = startRowIndex + 1;
            to = endRowIndex;
        }

        for (int i = from; i <= to; i++) {
            totalCost += rowCosts[i];
        }


        // 找到 column 的 from 和 to
        int startColumnIndex = startPos[1];
        int endColumnIndex = homePos[1];

        if (startColumnIndex > endColumnIndex) {
            from = endColumnIndex;
            to = startColumnIndex - 1;
        } else {
            from = startColumnIndex + 1;
            to = endColumnIndex;
        }


        for (int i = from; i <= to; i++) {
            totalCost += colCosts[i];
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] startPos = new int[] {
            5,5
        };
        int[] homePos = new int[] {
            5,2
        };
        int[] rowCosts = new int[] {
                7,1,3,3,5,3,22,10,23
        };
        int[] colCosts = new int[] {
                5,5,6,2,0,16
        };
        int i = solution.minCost(startPos, homePos, rowCosts, colCosts);
        System.out.println(i);
        Assert.assertEquals(8, i);
    }
}
