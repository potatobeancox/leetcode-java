package com.potato.study.leetcodecn.p00499.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 499. 迷宫 III
 *
 * 由空地和墙组成的迷宫中有一个球。球可以向上（u）下（d）左（l）右（r）四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。迷宫中还有一个洞，当球运动经过洞时，就会掉进洞里。
 *
 * 给定球的起始位置，目的地和迷宫，找出让球以最短距离掉进洞里的路径。 距离的定义是球从起始位置（不包括）到目的地（包括）经过的空地个数。通过'u', 'd', 'l' 和 'r'输出球的移动方向。 由于可能有多条最短路径， 请输出字典序最小的路径。如果球无法进入洞，输出"impossible"。
 *
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 *
 *  
 *
 * 示例1:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * 输入 2: 球的初始位置 (rowBall, colBall) = (4, 3)
 * 输入 3: 洞的位置 (rowHole, colHole) = (0, 1)
 *
 * 输出: "lul"
 *
 * 解析: 有两条让球进洞的最短路径。
 * 第一条路径是 左 -> 上 -> 左, 记为 "lul".
 * 第二条路径是 上 -> 左, 记为 'ul'.
 * 两条路径都具有最短距离6, 但'l' < 'u'，故第一条路径字典序更小。因此输出"lul"。
 *
 * 示例 2:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * 输入 2: 球的初始位置 (rowBall, colBall) = (4, 3)
 * 输入 3: 洞的位置 (rowHole, colHole) = (3, 0)
 *
 * 输出: "impossible"
 *
 * 示例: 球无法到达洞。
 *
 *  
 *
 * 注意:
 *
 * 迷宫中只有一个球和一个目的地。
 * 球和洞都在空地上，且初始时它们不在同一位置。
 * 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。
 * 迷宫至少包括2块空地，行数和列数均不超过30。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-maze-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 499
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        char[] dirChar = new char[] {
                'd', 'l', 'r', 'u'
        };
        int[][] dir = new int[][] {
                {1, 0},
                {0, -1},
                {0, 1},
                {-1, 0}
        };
        int dirIndex = -1;
        // bfs 记录当前所在的位置 和目前的方向 按照当前方向往下走一步 看看有没有达到 更换方向的地步
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(ball[0],ball[1], 0, dirIndex, ""));

        boolean[][][] visited = new boolean[maze.length][maze[0].length][4];

        while (!queue.isEmpty()) {
            NodeInfo poll = queue.poll();
            int i = poll.i;
            int j = poll.j;

            int step = poll.step;
            dirIndex = poll.direction;

            String path = poll.path;
            // 按照当前方向计算下个位置是不是能够到达
            int di = -1;
            int dj = -1;
            if (dirIndex != -1) {
                di = i + dir[dirIndex][0];
                dj = j + dir[dirIndex][1];
            }
            // 如果当前这个方向 判断是不是遇到了 墙或者 如果是的 遇到墙或者 遇到了 边界 是可以改变方向的 改变方向的优先级按照 字段序列搞
            if (dirIndex != -1
                    && isValidPosition(di, dj, maze.length, maze[0].length)
                    && maze[di][dj] != 1
                    && !visited[di][dj][dirIndex]) {
                // 需要继续沿着之前的路径走
                queue.add(new NodeInfo(di, dj, step+1, dirIndex, path));
                visited[di][dj][dirIndex] = true;
                // di dj 是不是已经到了终点
                if (di == hole[0] && dj == hole[1]) {
                    return path;
                }
            } else {
                // 坐标非法 可以改变方向
                for (int k = 0; k < 4; k++) {
                    if (k == dirIndex) {
                        continue;
                    }
                    di = i + dir[k][0];
                    dj = j + dir[k][1];

                    if (!isValidPosition(di, dj, maze.length, maze[0].length)) {
                        continue;
                    }
                    // di dj 是不是已经到了终点
                    if (visited[di][dj][k]) {
                        continue;
                    }
                    if (di == hole[0] && dj == hole[1]) {
                        return path + dirChar[k];
                    }
                    if (maze[di][dj] == 1) {
                        continue;
                    }
                    queue.add(new NodeInfo(di, dj, step+1, k, path + dirChar[k]));
                    visited[di][dj][k] = true;

                }
            }

        }
        // 每次都判断 下一个位置是不是已经到了 终点 到了的话 进行路径的记录
        return "impossible";
    }

    private boolean isValidPosition(int i, int j, int limit1, int limit2) {
        return i >= 0 && i < limit1 && j >= 0 && j < limit2;
    }

    class NodeInfo {
        // 坐标位置
        public int i;
        public int j;

        public int step;
        // 当前前进的方向
        public int direction;

        public String path;


        public NodeInfo(int i, int j, int step, int direction, String path) {
            this.i = i;
            this.j = j;
            this.step = step;
            this.direction = direction;
            this.path = path;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] maze = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]");
        int[] ball = new int[] {4,3};
        int[] hole = new int[] {0,1};
        String shortestWay = solution.findShortestWay(maze, ball, hole);
        System.out.println(shortestWay);
        Assert.assertEquals("lul", shortestWay);


        maze = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]]");
        ball = new int[] {0,4};
        hole = new int[] {3,5};
        shortestWay = solution.findShortestWay(maze, ball, hole);
        System.out.println(shortestWay);
        Assert.assertEquals("dldr", shortestWay);
    }

}
