package com.potato.study.leetcodecn.other.swordoffer.p0013.p1.t001;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 13. 机器人的运动范围
 *
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？

  

 示例 1：

 输入：m = 2, n = 3, k = 1
 输出：3
 示例 2：

 输入：m = 3, n = 1, k = 0
 输出：1
 提示：

 1 <= n,m <= 100
 0 <= k <= 20

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * bfs
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        boolean[][] visit = new boolean[m][n];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            // 判断当前节点能不能进入 判断当前点是不是之前访问过的
            if (!visit[poll[0]][poll[1]] && canMatch(x, y, k)) {
                count++;
            } else {
                // 进入不了就没有后续节点的技术了
                continue;
            }
            visit[poll[0]][poll[1]] = true;
            // 将与之相邻 没有访问过的都入队 可能访问的点
            if (x > 0 && !visit[x-1][y]) {
                queue.add(new int[]{x-1, y});
            }
            if (x < m - 1 && !visit[x+1][y]) {
                queue.add(new int[]{x+1, y});
            }
            if (y > 0 && !visit[x][y-1]) {
                queue.add(new int[]{x, y - 1});
            }
            if (y < n - 1 && !visit[x][y+1]) {
                queue.add(new int[]{x, y + 1});
            }
        }
        return count;
    }


    private boolean canMatch(int i, int j, int k) {
        int sum = 0;
        while (i > 0) {
            sum += (i % 10);
            i /= 10;
        }
        while (j > 0) {
            sum += (j % 10);
            j /= 10;
        }
        return sum <= k;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 2;
        int n = 3;
        int k = 1;
        int ints = solution.movingCount(m, n, k);
        System.out.println(ints);
        Assert.assertEquals(3, ints);

        m = 3;
        n = 1;
        k = 0;
        ints = solution.movingCount(m, n, k);
        System.out.println(ints);
        Assert.assertEquals(1, ints);

        m = 3;
        n = 2;
        k = 17;
        ints = solution.movingCount(m, n, k);
        System.out.println(ints);
        Assert.assertEquals(6, ints);


        m = 16;
        n = 8;
        k = 4;
        ints = solution.movingCount(m, n, k);
        System.out.println(ints);
        Assert.assertEquals(15, ints);
    }

}
