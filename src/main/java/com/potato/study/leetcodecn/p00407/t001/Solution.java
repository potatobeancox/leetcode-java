package com.potato.study.leetcodecn.p00407.t001;

import java.util.*;

/**
 * 407. 接雨水 II
 *
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。

  

 示例 1:



 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 输出: 4
 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 示例 2:



 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 输出: 10
  

 提示:

 m == heightMap.length
 n == heightMap[i].length
 1 <= m, n <= 200
 0 <= heightMap[i][j] <= 2 * 104

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/trapping-rain-water-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        // 将 heightMap 最外圈 放入优先级队列 中
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[2], o2[2]);
        });
        boolean[][] visit = new boolean[heightMap.length][heightMap[0].length];
        for (int i = 0; i < heightMap.length; i++) {
            priorityQueue.add(new int[]{i, 0, heightMap[i][0]});
            priorityQueue.add(new int[]{i, heightMap[0].length - 1, heightMap[i][heightMap[0].length - 1]});

            visit[i][0] = true;
            visit[i][heightMap[0].length - 1] = true;
        }
        for (int i = 0; i < heightMap[0].length; i++) {
            priorityQueue.add(new int[]{0, i, heightMap[0][i]});
            priorityQueue.add(new int[]{heightMap.length - 1, i, heightMap[heightMap.length - 1][i]});

            visit[0][i] = true;
            visit[heightMap.length - 1][i] = true;
        }
        // 依次po 最小的高度值 计算相邻位置 没有访问的 吐过比 这个点小就可以关税灌水 否则直接替换 按到大的保留高度
        int total = 0;
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        while (!priorityQueue.isEmpty()) {
            // 弹出
            int[] poll = priorityQueue.poll();
            int i = poll[0];
            int j = poll[1];
            int val = poll[2];

            for (int k = 0; k < 4; k++) {
                int di = i + dir[k][0];
                int dj = j + dir[k][1];

                // 坐标合法性
                if (di < 0 || di >= heightMap.length
                        || dj < 0 || dj >= heightMap[0].length) {
                    continue;
                }

                if (visit[di][dj]) {
                    continue;
                }
                visit[di][dj] = true;
                if (val > heightMap[di][dj]) {
                    total += (val - heightMap[di][dj]);
                }
                // 放入
                priorityQueue.add(new int[]{di, dj, Math.max(heightMap[di][dj], val)});
            }
        }
        return total;
    }
}
