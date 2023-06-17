package com.potato.study.leetcodecn.p01439.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 1439. 有序矩阵中的第 k 个最小数组和
 *
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。

 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。

  

 示例 1：

 输入：mat = [[1,3,11],[2,4,6]], k = 5
 输出：7
 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
 示例 2：

 输入：mat = [[1,3,11],[2,4,6]], k = 9
 输出：17
 示例 3：

 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 输出：9
 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
 示例 4：

 输入：mat = [[1,1,10],[2,2,9]], k = 7
 输出：12
  

 提示：

 m == mat.length
 n == mat.length[i]
 1 <= m, n <= 40
 1 <= k <= min(200, n ^ m)
 1 <= mat[i][j] <= 5000
 mat[i] 是一个非递减数组

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/solution/san-chong-suan-fa-bao-li-er-fen-da-an-du-k1vd/
     * @param mat
     * @param k
     * @return
     */
    public int kthSmallest(int[][] mat, int k) {
        // 用一个 list 记录目前最小的k个 升序
        List<Integer> sumList = new ArrayList<>();
        sumList.add(0);
        for (int[] array : mat) {
            // 遍历 mat 每一行 用每行跟 当前sumList 长度生成新的sum 并进行截取
            List<Integer> targetSumList = new ArrayList<>();
            for (int prefixSum : sumList) {
                for (int element : array) {
                    targetSumList.add(element + prefixSum);
                }
            }
            // 排序
            Collections.sort(targetSumList);
            if (targetSumList.size() <= k) {
                sumList = targetSumList;
            } else {
                sumList = targetSumList.subList(0, k);
            }
        }
        return sumList.get(k-1);
    }
}
