package com.potato.study.leetcodecn.p01765.t001;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.util.ArrayUtil;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.LeetcodeUtils;
import com.sun.tools.javac.util.ArrayUtils;

/**
 * 1765. 地图中的最高点
 *
 * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
 *
 * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
 * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
 * 你需要按照如下规则给每个单元格安排高度：
 *
 * 每个格子的高度都必须是非负的。
 * 如果一个格子是是 水域 ，那么它的高度必须为 0 。
 * 任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
 *
 * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：isWater = [[0,1],[0,0]]
 * 输出：[[1,0],[2,1]]
 * 解释：上图展示了给各个格子安排的高度。
 * 蓝色格子是水域格，绿色格子是陆地格。
 * 示例 2：
 *
 *
 *
 * 输入：isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * 输出：[[1,1,0],[0,1,1],[1,2,2]]
 * 解释：所有安排方案中，最高可行高度为 2 。
 * 任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
 *  
 *
 * 提示：
 *
 * m == isWater.length
 * n == isWater[i].length
 * 1 <= m, n <= 1000
 * isWater[i][j] 要么是 0 ，要么是 1 。
 * 至少有 1 个水域格子。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-of-highest-peak
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1765 地图上的最高值
    public int[][] highestPeak(int[][] isWater) {
        // 遍历 isWater 将每一个 水域位置 放入 queue 中，遍历 每层，将之前没有遍历过的
        int[][] height = new int[isWater.length][isWater[0].length];
        // 使用一个set 记录 是否放入过queue
        boolean[][] useStatus = new boolean[isWater.length][isWater[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[0].length; j++) {
                // 水域
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    queue.add(new int[]{i, j});
                    useStatus[i][j] = true;
                }
            }
        }
        int heightVal = 1;
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        // queue bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 找到相邻的位置
                for (int j = 0; j < direction.length; j++) {
                    int di = poll[0] + direction[j][0];
                    int dj = poll[1] + direction[j][1];

                    // di dj 合理性
                    if (di < 0 || di >= isWater.length
                            || dj < 0 || dj >= isWater[0].length) {
                        continue;
                    }

                    if (useStatus[di][dj]) {
                        continue;
                    }
                    // 没使用过
                    height[di][dj] = heightVal;
                    queue.add(new int[]{di, dj});
                    useStatus[di][dj] = true;
                }


            }
            heightVal++;
        }
        return height;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,1],[0,0]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[][] ints = solution.highestPeak(arr);
        ArrayUtil.printMatrix(ints);
    }
}
