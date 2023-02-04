package com.potato.study.leetcodecn.p01778.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1778. 未知网格中的最短路径
 *
 * 这是一个交互式的问题。

 一个未知的网格里有一个机器人，你需要让机器人从起点走到终点。这个网格的大小是 m x n，网格中的每个位置只会是可通行和不可通行两种状态。题目保证机器人的起点和终点不同，且都是可通行的。

 你需要找到起点到终点的最短路径，然而你不知道网格的大小、起点和终点。你只能向 GridMaster 对象查询。

 GridMaster有这些方法：

 boolean canMove(char direction) 当机器人能向对应方向移动时，返回 true，否则返回 false。
 void move(char direction) 把机器人向这个方向移动。如果移动方向上是不可通行的或是网格的边界，则这次移动会被忽略，机器人会待在原地。
 boolean isTarget() 如果机器人当前位于终点，返回 true，否则返回 false。
 注意上述方法中的direction应该是 {'U','D','L','R'} 中的一个，分别代表上下左右四个方向。

 返回机器人的初始位置到终点的最短距离。如果在起点和终点间没有路径联通，返回 -1。

 自定义测试用例

 测试用例是一个 m x n 的二维矩阵 grid，其中：

 grid[i][j] == -1 表明机器人一开始位于位置 (i, j) （即起点）。
 grid[i][j] == 0 表明位置 (i, j) 不可通行。
 grid[i][j] == 1 表明位置 (i, j) 可以通行.
 grid[i][j] == 2 表明位置 (i, j) 是终点.
 grid 里恰有一个-1 和一个 2。记住在你的代码中，你对这些信息将一无所知。

 示例1：

 输入: grid = [[1,2],[-1,0]]
 输出: 2
 解释: 一种可能的交互过程如下：
 The robot is initially standing on cell (1, 0), denoted by the -1.
 - master.canMove('U') 返回 true.
 - master.canMove('D') 返回false.
 - master.canMove('L') 返回 false.
 - master.canMove('R') 返回 false.
 - master.move('U') 把机器人移动到 (0, 0).
 - master.isTarget() 返回 false.
 - master.canMove('U') 返回 false.
 - master.canMove('D') 返回 true.
 - master.canMove('L') 返回 false.
 - master.canMove('R') 返回 true.
 - master.move('R') 把机器人移动到 (0, 1).
 - master.isTarget() 返回 true.
 我们现在知道终点是点 (0, 1)，而且最短的路径是2.
 示例2:

 输入: grid = [[0,0,-1],[1,1,1],[2,0,0]]
 输出: 4
 解释: 机器人和终点的最短路径长是4.
 示例3:

 输入: grid = [[-1,0],[0,2]]
 输出: -1
 解释: 机器人和终点间没有可通行的路径.
  

 提示：

 1 <= n, m <= 500
 m == grid.length
 n == grid[i].length
 grid[i][j] 只可能是 -1, 0, 1 或 2
 grid 中 有且只有一个 -1
 grid 中 有且只有一个 2

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/shortest-path-in-a-hidden-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private Set<String> forbidSet;
    private Set<String> visitSet;
    private int[] target;
    private char[] directions;
    private int[][] dir;


    public int findShortestPath(GridMaster master) {
        // 先 dfs 生成 记录不能通行的点和 终点坐标
        this.forbidSet = new HashSet<>();
        this.directions = new char[]{'U','L','D','R'};
        this.dir = new int[][] {
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1}
        };
        // 记录当前已经 visit的
        this.visitSet = new HashSet<>();
        visitSet.add(0 + "_" + 0);
        dfs(master, 0, 0);
        if (target == null) {
            return -1;
        }
        // bdf 从 00 开始，往4哥方向找 直到找到终点 中间记录已经找过的点
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visitSet.clear();
        visitSet.add(0 + "_" + 0);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 找4个方向那个可以走
                for (int j = 0; j < 4; j++) {
                    int[] direc = this.dir[j];
                    int di = poll[0] + direc[0];
                    int dj = poll[1] + direc[1];
                    // 已经访问过
                    String nextKey = di + "_" + dj;
                    if (visitSet.contains(nextKey)) {
                        continue;
                    }
                    // 不能访问
                    if (forbidSet.contains(nextKey)) {
                        continue;
                    }
                    // 终点返回
                    visitSet.add(nextKey);
                    if (di == target[0] && dj == target[1]) {
                        return step;
                    }
                    // 不是终点 放入 下一波
                    queue.add(new int[] {di, dj});
                }
            }
            step++;
        }
        // 队列中的集合
        return -1;
    }

    private void dfs(GridMaster master, int i, int j) {
        // 分别往四个方向找
        for (int k = 0; k < 4; k++) {
            char direction = directions[k];
            int[] dir = this.dir[k];

            int di = i + dir[0];
            int dj = j + dir[1];
            // 目的地方向有没有走过
            String targetKey = di + "_" + dj;
            if (visitSet.contains(targetKey)) {
                continue;
            }
            if (forbidSet.contains(targetKey)) {
                continue;
            }
            // 往这个方向走 看看 能不能走
            if (!master.canMove(direction)) {
                forbidSet.add(targetKey);
                continue;
            }
            // 能走
            master.move(direction);
            if (master.isTarget()) {
                this.target = new int[] {di, dj};
            }
            // 往内部dfs
            visitSet.add(targetKey);
            dfs(master, di, dj);
            // 往回走
            master.move(directions[(k+2) %4]);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2],[-1,0]]");
        GridMaster master = new GridMaster(grid, 1, 0);
        int shortestPath = solution.findShortestPath(master);
        System.out.println(2);
        Assert.assertEquals(2, shortestPath);
    }
}


class GridMaster {

    private int[][] grid;
    private int i;
    private int j;


    public GridMaster(int[][] grid, int i, int j) {
        this.grid = grid;
        this.i = i;
        this.j = j;
    }

    public boolean canMove(char direction){
        int di = i;
        int dj = j;
        if (direction == 'U') {
            di--;
        } else if (direction == 'D') {
            di++;
        } else if (direction == 'L') {
            dj--;
        } else {
            dj++;
        }

        if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length) {
            return false;
        }
        if (grid[di][dj] == 0) {
            return false;
        }
        return true;
    }
    public void move(char direction) {
        int di = i;
        int dj = j;
        if (direction == 'U') {
            di--;
        } else if (direction == 'D') {
            di++;
        } else if (direction == 'L') {
            dj--;
        } else {
            dj++;
        }

        if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length) {
            return;
        }
        if (grid[di][dj] == 0) {
            return;
        }
        this.i = di;
        this.j = dj;
    }
    public boolean isTarget() {
        return grid[i][j] == 2;
    }
}
