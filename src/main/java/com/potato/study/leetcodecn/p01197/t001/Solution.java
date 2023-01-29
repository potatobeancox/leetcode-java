package com.potato.study.leetcodecn.p01197.t001;


import org.junit.Assert;

import java.util.*;

/**
 * 1197. 进击的骑士
 *
 * 一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，你的 骑士 驻扎在坐标为 [0, 0] 的方格里。
 *
 * 骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。
 *
 * 每次移动，他都可以按图示八个方向之一前进。
 *
 *
 *
 * 返回 骑士前去征服坐标为 [x, y] 的部落所需的最小移动次数 。本题确保答案是一定存在的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 2, y = 1
 * 输出：1
 * 解释：[0, 0] → [2, 1]
 * 示例 2：
 *
 * 输入：x = 5, y = 5
 * 输出：4
 * 解释：[0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *  
 *
 * 提示：
 *
 * -300 <= x, y <= 300
 * 0 <= |x| + |y| <= 300
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-knight-moves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        // 将 xy 转化到 第一象限
        x = Math.abs(x);
        y = Math.abs(y);
        // bfs 开始将 原点移动 50 50
        x += 50;
        y += 50;
        // 从 00 开始 bfs 相当于 从50，50开始 bfs
        Queue<int[]> queue = new LinkedList<>();
        // arr[2] 记录当前位置用了多少步骤
        queue.add(new int[] {50, 50, 0});
        // 各个方向
        int[][] direction = new int[][] {
                {1, 2},
                {-1, 2},
                {-1, -2},
                {1, -2},
                {2, 1},
                {-2, 1},
                {-2, -1},
                {2, -1}
        };
        Set<String> visited = new HashSet<>();
        visited.add(50 + "_" + 50);
        int step = -1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int dx = poll[0];
            int dy = poll[1];
            int cost = poll[2];
            // 往8个方向找
            for (int i = 0; i < 8; i++) {
                int nextX = dx + direction[i][0];
                int nextY = dy + direction[i][1];
                String key = nextX + "_" + nextY;
                if (visited.contains(key)) {
                    continue;
                }
                if (nextX < 0 || nextY < 0) {
                    continue;
                }
                int[] nextNode = new int[] {nextX, nextY, cost + 1};
                if (nextX == x && nextY == y) {
                    return nextNode[2];
                }
                queue.add(nextNode);
                visited.add(key);
            }

        }
        return step;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minKnightMoves(2, 1);
        System.out.println(i);
        Assert.assertEquals(1, i);


        i = solution.minKnightMoves(5, 5);
        System.out.println(i);
        Assert.assertEquals(4, i);


        i = solution.minKnightMoves(0, 0);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
