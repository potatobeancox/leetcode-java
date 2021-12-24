package com.potato.study.leetcodecn.p01284.t001;


import java.util.*;

import org.junit.Assert;

/**
 * 1284. 转化为全零矩阵的最少反转次数
 *
 * 给你一个 m x n 的二进制矩阵 mat。
 *
 * 每一步，你可以选择一个单元格并将它反转（反转表示 0 变 1 ，1 变 0 ）。如果存在和它相邻的单元格，那么这些相邻的单元格也会被反转。（注：相邻的两个单元格共享同一条边。）
 *
 * 请你返回将矩阵 mat 转化为全零矩阵的最少反转次数，如果无法转化为全零矩阵，请返回 -1 。
 *
 * 二进制矩阵的每一个格子要么是 0 要么是 1 。
 *
 * 全零矩阵是所有格子都为 0 的矩阵。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[0,0],[0,1]]
 * 输出：3
 * 解释：一个可能的解是反转 (1, 0)，然后 (0, 1) ，最后是 (1, 1) 。
 * 示例 2：
 *
 * 输入：mat = [[0]]
 * 输出：0
 * 解释：给出的矩阵是全零矩阵，所以你不需要改变它。
 * 示例 3：
 *
 * 输入：mat = [[1,1,1],[1,0,1],[0,0,0]]
 * 输出：6
 * 示例 4：
 *
 * 输入：mat = [[1,0,0],[1,0,0]]
 * 输出：-1
 * 解释：该矩阵无法转变成全零矩阵
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[0].length
 * 1 <= m <= 3
 * 1 <= n <= 3
 * mat[i][j] 是 0 或 1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param mat
     * @return
     */
    public int minFlips(int[][] mat) {
        // 生成最终状态的key
        int[][] finalStatus = new int[mat.length][mat[0].length];
        String finalStatusString = getKey(finalStatus);

        // 两个队列 同步修改 记录 当时矩阵的反转次数
        Queue<int[][]> matQueue = new LinkedList<>();
        Queue<Integer> matTimes = new LinkedList<>();
        matQueue.add(mat);
        matTimes.add(0);
        Map<String, Integer> matStatusMap = new HashMap<>();
        matStatusMap.put(getKey(mat), 1);
        // bfs
        while (!matQueue.isEmpty()) {
            int[][] currentMat = matQueue.poll();
            Integer currentFlipTime = matTimes.poll();
            String currentKey = getKey(currentMat);
            if (finalStatusString.equals(currentKey)) {
                return currentFlipTime;
            }
            // 遍历各个状态进行翻转
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    // 翻转
                    int[][] nextMat = filpMat(currentMat, i, j);
                    String key = getKey(nextMat);
                    if (finalStatusString.equals(key)) {
                        return currentFlipTime + 1;
                    }
                    Integer status = matStatusMap.getOrDefault(key, 0);
                    if (status > 0) {
                        continue;
                    }
                    // 没有访问过
                    matQueue.add(nextMat);
                    matTimes.add(currentFlipTime + 1);
                    matStatusMap.put(key, 1);
                }
            }
            matStatusMap.put(currentKey, 2);
        }
        // map key 矩阵序列化之后的key value 是 这个矩阵目前遍历的状态 0未遍历，1遍历过，2遍历完了 没有终态
        return -1;
    }


    // 翻转矩阵的某个位置
    private int[][] filpMat(int[][] mat, int i, int j) {
        int[][] newMat = new int[mat.length][mat[0].length];
        for (int k = 0; k < mat.length; k++) {
            for (int l = 0; l < mat[0].length; l++) {
                if ((k == i && l == j)
                        || (k == i-1 && l == j)
                        || (k == i+1 && l == j)
                        || (k == i && l == j-1)
                        || (k == i && l == j+1)) {
                    newMat[k][l] = 1 - mat[k][l];
                } else {
                    newMat[k][l] = mat[k][l];
                }
            }
        }
        return newMat;
    }

    // 生成key
    private String getKey(int[][] mat) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                builder.append(mat[i][j]);
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat = new int[][] {
                {0, 0},
                {0, 1}
        };
        int i = solution.minFlips(mat);
        System.out.println(i);
        Assert.assertEquals(3, i);


        mat = new int[][] {
                {0},
                {1},
                {1}
        };
        i = solution.minFlips(mat);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }


}
