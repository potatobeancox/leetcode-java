package com.potato.study.leetcodecn.p00363.t001;

import org.junit.Assert;

import java.util.TreeSet;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 *
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。

 题目数据保证总会存在一个数值和不超过 k 的矩形区域。

  

 示例 1：


 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 输出：2
 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 示例 2：

 输入：matrix = [[2,2,-1]], k = 3
 输出：3
  

 提示：

 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 100
 -100 <= matrix[i][j] <= 100
 -105 <= k <= 105
  

 进阶：如果行数远大于列数，该如何设计解决方案？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/solution/ju-xing-qu-yu-bu-chao-guo-k-de-zui-da-sh-70q2/
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 两重循环 枚举 上下边界
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            // sum 为每个 开始 i 公用一个
            int[] sumCol = new int[n];
            for (int j = i; j < m; j++) {
                // 计算当前 区间范围的sum 和
                for (int l = 0; l < n; l++) {
                    sumCol[l] = sumCol[l] + matrix[j][l];
                }
                // 边界 内部 使用 前缀和 二分查找 sum - k ceil 数值  ceiling()方法用于返回等于或大于给定元素(ele)的最低元素(如果存在)，否则返回null。
                TreeSet<Integer> treeSet = new TreeSet<>();
                // treeSet 最开始 存一个 0 便是 左右列都使用
                treeSet.add(0);
                // sumCol 是每个列 在ij 中间的和 前缀和 还得加上 上一个的和
                int totalSum = 0;
                for (int s : sumCol) {
                    // 将s 放入其中 便于使用
                    totalSum += s;
                    // 每个前缀和 求 是否存在 s - k ceiling >= s - k
                    Integer ceiling = treeSet.ceiling(totalSum - k);
                    if (ceiling != null) {
                        max = Math.max(max, totalSum - ceiling);
                    }
                    // 不带这个 点 放置 出现 空档情况
                    treeSet.add(totalSum);
                }

            }
        }
        return max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = new int[][] {
                {1,0,1},
                {0,-2,3}
        };
        int i = solution.maxSumSubmatrix(arr, 2);
        System.out.println(i);
        Assert.assertEquals(2, i);


        arr = new int[][] {
                {2,2,-1}
        };
        i = solution.maxSumSubmatrix(arr, 3);
        System.out.println(i);
        Assert.assertEquals(3, i);


        arr = new int[][] {
                {2,2,-1}
        };
        i = solution.maxSumSubmatrix(arr, 0);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }
}
