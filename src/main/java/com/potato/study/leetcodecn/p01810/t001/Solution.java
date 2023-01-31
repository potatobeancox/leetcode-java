package com.potato.study.leetcodecn.p01810.t001;

import java.util.*;

import org.junit.Assert;

/**
 * 1810. 隐藏网格下的最小消耗路径
 *
 * 这是一个交互问题。
 *
 * 有一个机器人存在于网格中，你需要通过不断尝试使他从初始单元到达目标单元。网格的规格为m x n，并且每个单元的属性值要不为空，要不已被占用。题目保证初始网格和目标网格不同且均为空。
 *
 * 每个单元格都有消耗值，你需要在每次移动至此单元格后支付该费用。在机器人启动前，初始单元的费用不被计算在内。
 *
 * 你需要找到机器人移动至目标网格的最小总消耗。但可惜的是你并不知道网格的尺寸、初始单元和目标单元。你只允许通过询问GridMaster类获得信息。
 *
 * GridMaster类存在以下功能：
 *
 * boolean canMove(char direction) 当机器人可以向这个方向移动时，返回true；反之返回false。
 * int move(char direction) 沿该方向移动机器人，并返回移动到该单元的消耗值。如果此移动将机器人移动到被占有的单元格或离开网格，则移动将被忽略，机器人将保持在相同的位置，函数将返回-1。
 * boolean isTarget() ：如果机器人当前位于目标单元格上，则返回true；反之返回 false 。
 * 请注意，上述函数中的方向应该是{ 'U'、'D'、'L'、'R' }中的字符，分别表示向上、向下、左和右方向。
 *
 * 返回使机器人从其初始起始单元到目标单元的最小总消耗。如果单元格之间不存在有效路径，则返回-1。
 *
 * 测试实例:
 *
 * 测试输入一个大小为m x n的二维数组 grid 和四个int型参数 r1, c1, r2, 和 c2 :
 *
 * grid[i][j] == 0 表示网格 (i, j) 已被占用。
 * grid[i][j] >= 1 表示网格单元 (i, j) 为空并且 grid[i][j] 的值为移动至此网格的成本值。
 * (r1, c1) 为初始单元。
 * (r2, c2) 为目标单元。
 * 请注意，你将无法在你的代码中获知这些信息。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: grid = [[2,3],[1,1]], r1 = 0, c1 = 1, r2 = 1, c2 = 0
 * 输出: 2
 * 解释: 其中一种可能路径描述如下：
 * 机器人最开始站在单元格 (0, 1) ，用 3 表示
 * - master.canMove('U') 返回 false
 * - master.canMove('D') 返回 true
 * - master.canMove('L') 返回 true
 * - master.canMove('R') 返回 false
 * - master.move('L') 机器人移动到单元格 (0, 0) 并返回 2
 * - master.isTarget() 返回 false
 * - master.canMove('U') 返回 false
 * - master.canMove('D') 返回 true
 * - master.canMove('L') 返回 false
 * - master.canMove('R') 返回 true
 * - master.move('D') 机器人移动到单元格 (1, 0) 并返回 1
 * - master.isTarget() 返回 true
 * - master.move('L') 机器人不移动并返回 -1
 * - master.move('R') 机器人移动到单元格 (1, 1) 并返回 1
 * 现在我们知道了机器人达到目标单元(1, 0)的最小消耗成本为2。
 * 示例 2:
 *
 * 输入: grid = [[0,3,1],[3,4,2],[1,2,0]], r1 = 2, c1 = 0, r2 = 0, c2 = 2
 * 输出: 9
 * 解释: 最小消耗路径为 (2,0) -> (2,1) -> (1,1) -> (1,2) -> (0,2).
 * 示例 3:
 *
 * 输入: grid = [[1,0],[0,1]], r1 = 0, c1 = 0, r2 = 1, c2 = 1
 * 输出: -1
 * 解释: 不存在可使机器人到达目标单元的路径。
 *  
 *
 * 提示:
 *
 * 1 <= n, m <= 100
 * m == grid.length
 * n == grid[i].length
 * 0 <= grid[i][j] <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-path-cost-in-a-hidden-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int[][] grid;
    private int endX;
    private int endY;


    /**
     *
     * @param master
     * @return
     */
    public int findShortestPath(GridMaster master) {
        // 先使用一个矩阵记录 200 * 200 的每个位置的消耗
        this.grid = new int[201][201];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], -1);
        }

        char[] dir = new char[] {
                'U','L', 'D', 'R'
        };
        int[][] direction = new int[][] {
                {0, 1},
                {-1, 0},
                {0, -1},
                {1, 0}
        };
        // 生成矩阵
        dfs(100, 100, dir, direction, master);
        // 再从开始位置 bfs 优先找消耗最少的点进行bfs [2]记录消耗
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        priorityQueue.add(new int[]{100, 100, 0});

        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int cost = poll[2];
            int x = poll[0];
            int y = poll[1];

            // 四个方向找
            for (int i = 0; i < 4; i++) {
                int dx = x + direction[i][0];
                int dy = y + direction[i][1];

                if (dx < 0 || dx >= 200 || dy < 0 || dy >= 200 || grid[dx][dy] <= 0) {
                    continue;
                }
                int currentCost = cost + grid[dx][dy];
                if (dx == endX && dy == endY) {
                    return currentCost;
                }
                grid[dx][dy] = -1;
                priorityQueue.add(new int[] {dx, dy, currentCost});
            }
        }
        return -1;
    }

    private void dfs(int x, int y, char[] dir, int[][] direction, GridMaster master) {
        // 往四个方向找
        for (int i = 0; i < 4; i++) {
            boolean canMove = master.canMove(dir[i]);
            if (!canMove) {
                continue;
            }
            int dx = x + direction[i][0];
            int dy = y + direction[i][1];
            if (dx < 0 || dx >= 200 || dy < 0 || dy >= 200 || grid[dx][dy] != -1) {
                continue;
            }
            // 移动
            int move = master.move(dir[i]);
            // 记录移动到这个点需要话费多少
            grid[dx][dy] = move;

            // 是否已经到终点了
            if (master.isTarget()) {
                this.endX = dx;
                this.endY = dy;
            }

            dfs(dx, dy, dir, direction, master);
            // 移动回来
            master.move(dir[(i + 2) % 4]);
        }
    }


}

class GridMaster {

    /**
     * 当机器人可以向这个方向移动时，返回true；反之返回false
     *
     * 上述函数中的方向应该是{ 'U'、'D'、'L'、'R' }中的字符，分别表示向上、向下、左和右方向
     * @param direction
     * @return
     */
    public boolean canMove(char direction) {
        return false;
    }

    /**
     * 沿该方向移动机器人，并返回移动到该单元的消耗值。
     * 如果此移动将机器人移动到被占有的单元格或离开网格，则移动将被忽略，机器人将保持在相同的位置，函数将返回-1。
     * @param direction
     * @return
     */
    public int move(char direction) {
        return -1;
    }

    /**
     * 如果机器人当前位于目标单元格上，则返回true；反之返回 false 。
     * @return
     */
    public boolean isTarget() {
        return false;
    }
}
