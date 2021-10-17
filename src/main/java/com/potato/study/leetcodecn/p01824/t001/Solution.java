package com.potato.study.leetcodecn.p01824.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

/**
 * 1824. 最少侧跳次数
 *
 * 给你一个长度为 n 的 3 跑道道路 ，它总共包含 n + 1 个 点 ，编号为 0 到 n 。一只青蛙从 0 号点第二条跑道 出发 ，它想要跳到点 n 处。然而道路上可能有一些障碍。
 *
 * 给你一个长度为 n + 1 的数组 obstacles ，其中 obstacles[i] （取值范围从 0 到 3）表示在点 i 处的 obstacles[i] 跑道上有一个障碍。如果 obstacles[i] ==
 * 0 ，那么点 i 处没有障碍。任何一个点的三条跑道中 最多有一个 障碍。
 *
 * 比方说，如果 obstacles[2] == 1 ，那么说明在点 2 处跑道 1 有障碍。
 * 这只青蛙从点 i 跳到点 i + 1 且跑道不变的前提是点 i + 1 的同一跑道上没有障碍。为了躲避障碍，这只青蛙也可以在 同一个 点处 侧跳 到 另外一条 跑道（这两条跑道可以不相邻），但前提是跳过去的跑道该点处没有障碍。
 *
 * 比方说，这只青蛙可以从点 3 处的跑道 3 跳到点 3 处的跑道 1 。
 * 这只青蛙从点 0 处跑道 2 出发，并想到达点 n 处的 任一跑道 ，请你返回 最少侧跳次数 。
 *
 * 注意：点 0 处和点 n 处的任一跑道都不会有障碍。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：obstacles = [0,1,2,3,0]
 * 输出：2
 * 解释：最优方案如上图箭头所示。总共有 2 次侧跳（红色箭头）。
 * 注意，这只青蛙只有当侧跳时才可以跳过障碍（如上图点 2 处所示）。
 * 示例 2：
 *
 *
 * 输入：obstacles = [0,1,1,3,3,0]
 * 输出：0
 * 解释：跑道 2 没有任何障碍，所以不需要任何侧跳。
 * 示例 3：
 *
 *
 * 输入：obstacles = [0,2,1,0,3,0]
 * 输出：2
 * 解释：最优方案如上图所示。总共有 2 次侧跳。
 *  
 *
 * 提示：
 *
 * obstacles.length == n + 1
 * 1 <= n <= 5 * 105
 * 0 <= obstacles[i] <= 3
 * obstacles[0] == obstacles[n] == 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-sideway-jumps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/minimum-sideway-jumps/solution/jian-dan-dp-by-chang-an-gui-gu-li-80-jku8/
     * @param obstacles
     * @return
     */
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        // dp i j j 0-2 表示到达 i位置 j赛道需要侧跳的次数
        int[][] dp = new int[n][3];
        // 设置障碍
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 6 * 100000);
        }
        // 初始化 dp 0 1 = 0 dp 00 = 1 dp 02 = 1
        dp[0][1] = 0;
        dp[0][0] = 1;
        dp[0][2] = 1;
        // 转移 方程 如果 obstacles i-1 有石头 dp ij = min 另外两个赛道 的最小值 + 1， 否则没有石头就直接 min 当前和 剩下最小值
        for (int i = 1; i < n; i++) {
            int obstacleIndex = obstacles[i-1] - 1;
            // 占用的位置不能使用了
            if (obstacleIndex == -1) {
                // 当前没有障碍
                dp[i][0] = Math.min(dp[i-1][0], Math.min(dp[i-1][1], dp[i-1][2]) + 1);
                dp[i][1] = Math.min(dp[i-1][1], Math.min(dp[i-1][0], dp[i-1][2]) + 1);
                dp[i][2] = Math.min(dp[i-1][2], Math.min(dp[i-1][0], dp[i-1][1]) + 1);
                continue;
            }
            // 当前有障碍
            if (obstacleIndex == 0) {
                // i-1 0 有障碍 不能通过了
                dp[i][1] = Math.min(dp[i-1][1], dp[i-1][2] + 1);
                dp[i][2] = Math.min(dp[i-1][2], dp[i-1][1] + 1);
            } else if (obstacleIndex == 1) {
                dp[i][0] = Math.min(dp[i-1][0], dp[i-1][2] + 1);
                dp[i][2] = Math.min(dp[i-1][2], dp[i-1][0] + 1);
            } else {
                // obstacleIndex == 2;
                dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1] + 1);
                dp[i][1] = Math.min(dp[i-1][1], dp[i-1][0] + 1);
            }
        }
        // 返回 dp n-1 min
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] obstacles = new int[]{0,1,2,3,0};
        int i = solution.minSideJumps(obstacles);
        System.out.println(i);
        Assert.assertEquals(2, i);

        obstacles = new int[]{0,1,1,3,3,0};
        i = solution.minSideJumps(obstacles);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
