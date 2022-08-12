package com.potato.study.leetcodecn.p00286.t001;


import java.util.LinkedList;
import java.util.Queue;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 286. 墙与门
 *
 * 你被给定一个 m × n 的二维网格 rooms ，网格中有以下三种可能的初始化值：
 *
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近门的距离 ，如果无法到达门，则填 INF 即可。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,
 * -1,2147483647,2147483647]]
 * 输出：[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * 示例 2：
 *
 * 输入：rooms = [[-1]]
 * 输出：[[-1]]
 * 示例 3：
 *
 * 输入：rooms = [[2147483647]]
 * 输出：[[2147483647]]
 * 示例 4：
 *
 * 输入：rooms = [[0]]
 * 输出：[[0]]
 *  
 *
 * 提示：
 *
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] 是 -1、0 或 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/walls-and-gates
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public void wallsAndGates(int[][] rooms) {
        // 遍历 room 找到所有门的位置
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[] {i, j});
                }
            }
        }
        // bfs 找邻接 并计算 距离
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        int inf = Integer.MAX_VALUE - 1;
        int level = 1;
        while (!queue.isEmpty()) {
            // 本层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 4个方向是否已经ok
                for (int j = 0; j < 4; j++) {
                    int di = poll[0] + direction[j][0];
                    int dj = poll[1] + direction[j][1];
                    // 是否在 限制内部
                    if (di < 0 || di >= rooms.length
                            || dj < 0 || dj >= rooms[0].length) {
                        continue;
                    }
                    // 是否已经被访问
                    if (rooms[di][dj] >= 0 && rooms[di][dj] < inf) {
                        continue;
                    }
                    if (rooms[di][dj] == -1) {
                        continue;
                    }
                    // inf  visit 大于0 小于 inf  记录是否已经在 queue 中
                    rooms[di][dj] = level;
                    queue.add(new int[] {di, dj});
                }
            }
            level++;
        }
    }
}
