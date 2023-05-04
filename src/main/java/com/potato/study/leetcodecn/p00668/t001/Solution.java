package com.potato.study.leetcodecn.p00668.t001;

import org.junit.Assert;

/**
 *
 * 668. 乘法表中第k小的数
 *
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第 k 小的数字吗？
 *
 * 乘法表是大小为 m x n 的一个整数矩阵，其中 mat[i][j] == i * j（下标从 1 开始）。
 *
 * 给你三个整数 m、n 和 k，请你在大小为 m x n 的乘法表中，找出并返回第 k 小的数字。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 3, k = 5
 * 输出：3
 * 解释：第 5 小的数字是 3 。
 * 示例 2：
 *
 *
 * 输入：m = 2, n = 3, k = 6
 * 输出：6
 * 解释：第 6 小的数字是 6 。
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 3 * 104
 * 1 <= k <= m * n
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 668
     * https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/solution/by-fuxuemingzhu-8eq4/
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int m, int n, int k) {
        // 二分法枚举 对应有多少个数字 小于等于 target 再跟k比较
        int left = 1;
        int right = m * n;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int below = belowCount(m, n, mid);
            if (below >= k) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 有多少个数字 小于等于 target
     * @param m
     * @param n
     * @param target
     * @return
     */
    private int belowCount(int m, int n, int target) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += Math.min(target / i, m);
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int kthNumber = solution.findKthNumber(3, 3, 5);
        System.out.println(kthNumber);
        Assert.assertEquals(3, kthNumber);
    }
}
