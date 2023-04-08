package com.potato.study.leetcodecn.other.swordoffer2.p0107.t001;

import java.util.*;

/**
 * 剑指 Offer II 107. 矩阵中的距离
 *
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。

 两个相邻元素间的距离为 1 。

  

 示例 1：



 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 输出：[[0,0,0],[0,1,0],[0,0,0]]
 示例 2：



 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 输出：[[0,0,0],[0,1,0],[1,2,1]]
  

 提示：

 m == mat.length
 n == mat[i].length
 1 <= m, n <= 104
 1 <= m * n <= 104
 mat[i][j] is either 0 or 1.
 mat 中至少有一个 0 
  

 注意：本题与主站 542 题相同：https://leetcode-cn.com/problems/01-matrix/



 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/2bCMpM
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[][] updateMatrix(int[][] mat) {
        // 遍历 mat 将值为0 的左边放进 queue中 并使用结果判断是否已经放在queue中
        int[][] res = new int[mat.length][mat[0].length];
        // 初始化为-1
        for (int i = 0; i < mat.length; i++) {
            Arrays.fill(res[i], -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                    queue.add(new int[] {i, j});
                }
            }
        }
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        // bfs
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int i = poll[0];
            int j = poll[1];

            int dis = res[i][j];
            // 方向
            for (int k = 0; k < 4; k++) {
                int di = i + dir[k][0];
                int dj = j + dir[k][1];

                if (di < 0 || di >= mat.length
                        || dj < 0 || dj >= mat[0].length) {
                    continue;
                }
                // 是不是已经算过
                if (res[di][dj] != -1) {
                    continue;
                }
                // 没算过
                res[di][dj] = dis + 1;
                queue.add(new int[] {di, dj});
            }
        }
        return res;
    }


}
