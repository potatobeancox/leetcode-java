package com.potato.study.leetcodecn.p01738.t001;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 1738. 找出第 K 大的异或坐标值
 *
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 106
 * 1 <= k <= m * n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        // 使用 新矩阵机选 使用 堆维护 前k个 大小根堆
        int[][] xorValue = new int[matrix.length][matrix[0].length];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        xorValue[0][0] = matrix[0][0];
        priorityQueue.add(xorValue[0][0]);
        for (int i = 1; i < matrix[0].length; i++) {
            xorValue[0][i] = (xorValue[0][i-1] ^ matrix[0][i]);
            priorityQueue.add(xorValue[0][i]);
        }
        for (int i = 1; i < matrix.length; i++) {
            xorValue[i][0] = (xorValue[i-1][0] ^ matrix[i][0]);
            priorityQueue.add(xorValue[i][0]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                xorValue[i][j] = xorValue[i][j-1] ^ xorValue[i-1][j] ^ xorValue[i-1][j-1] ^ matrix[i][j];
                priorityQueue.add(xorValue[i][j]);
            }
        }
        while (priorityQueue.size() > k) {
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{5,2},{1,6}};
        int k = 1;
        int i = solution.kthLargestValue(matrix, k);
        System.out.println(i);
        Assert.assertEquals(7, i);


        matrix = new int[][]{{5,2},{1,6}};
        k = 2;
        i = solution.kthLargestValue(matrix, k);
        System.out.println(i);
        Assert.assertEquals(5, i);

        matrix = new int[][]{{5,2},{1,6}};
        k = 4;
        i = solution.kthLargestValue(matrix, k);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
