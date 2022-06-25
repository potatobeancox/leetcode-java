package com.potato.study.leetcodecn.p01253.t001;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * 1253. 重构 2 行二进制矩阵
 *
 * 给你一个 2 行 n 列的二进制数组：
 *
 * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。
 * 第 0 行的元素之和为 upper。
 * 第 1 行的元素之和为 lower。
 * 第 i 列（从 0 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。
 * 你需要利用 upper，lower 和 colsum 来重构这个矩阵，并以二维整数数组的形式返回它。
 *
 * 如果有多个不同的答案，那么任意一个都可以通过本题。
 *
 * 如果不存在符合要求的答案，就请返回一个空的二维数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：upper = 2, lower = 1, colsum = [1,1,1]
 * 输出：[[1,1,0],[0,0,1]]
 * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
 * 示例 2：
 *
 * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
 * 输出：[]
 * 示例 3：
 *
 * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 *  
 *
 * 提示：
 *
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1253
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        // 统计 colsum 中 2的个数
        int twoTime = 0;
        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 2) {
                twoTime++;
            }
        }
        if (twoTime > upper || twoTime > lower) {
            return new ArrayList<>();
        }
        // 计算 upper 存在 可以肚子独自承担的数量
        int upperTime = upper - twoTime;
        int lowerTime = lower - twoTime;
        // 统计 有多少 个 2的 情况 如果 2的个数 大于 upper 或者 lower 直接 返回
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> upperList = new ArrayList<>();
        List<Integer> lowerList = new ArrayList<>();
        list.add(upperList);
        list.add(lowerList);
        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 2) {
                upperList.add(1);
                lowerList.add(1);
            } else if (colsum[i] == 1) {
                if (upperTime > 0) {
                    upperTime--;
                    upperList.add(1);
                    lowerList.add(0);
                } else {
                    if (lowerTime <= 0) {
                        return new ArrayList<>();
                    }
                    lowerTime--;
                    upperList.add(0);
                    lowerList.add(1);
                }
            } else {
                upperList.add(0);
                lowerList.add(0);
            }
        }
        if (upperTime != 0 || lowerTime != 0) {
            return new ArrayList<>();
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int upper = 4;
        int lower = 7;
        int[] colsum = new int[] {
                2,1,2,2,1,1,1
        };
        List<List<Integer>> list = solution.reconstructMatrix(upper, lower, colsum);
        System.out.println(list);
    }
}
