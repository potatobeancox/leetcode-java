package com.potato.study.leetcodecn.p02857.t001;


import java.util.List;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 *
 * 2857. 统计距离为 k 的点对
 *
 * 给你一个 二维 整数数组 coordinates 和一个整数 k ，其中 coordinates[i] = [xi, yi] 是第 i 个点在二维平面里的坐标。
 *
 * 我们定义两个点 (x1, y1) 和 (x2, y2) 的 距离 为 (x1 XOR x2) + (y1 XOR y2) ，XOR 指的是按位异或运算。
 *
 * 请你返回满足 i < j 且点 i 和点 j之间距离为 k 的点对数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
 * 输出：2
 * 解释：以下点对距离为 k ：
 * - (0, 1)：(1 XOR 4) + (2 XOR 2) = 5 。
 * - (2, 3)：(1 XOR 5) + (3 XOR 2) = 5 。
 * 示例 2：
 *
 * 输入：coordinates = [[1,3],[1,3],[1,3],[1,3],[1,3]], k = 0
 * 输出：10
 * 解释：任何两个点之间的距离都为 0 ，所以总共有 10 组点对。
 *
 *
 * 提示：
 *
 * 2 <= coordinates.length <= 50000
 * 0 <= xi, yi <= 106
 * 0 <= k <= 100
 *
 */
public class Solution {


    public int countPairs(List<List<Integer>> coordinates, int k) {
        // 正常一遍循环枚举两个点 计算抑或的和是k的个数
        int limit = coordinates.size();
        int count = 0;
        for (int i = 0; i < limit; i++) {
            for (int j = i+1; j < limit; j++) {
                if (k == getValue(coordinates.get(i), coordinates.get(j))) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     *
     * @param point1
     * @param point2
     * @return
     */
    private int getValue(List<Integer> point1, List<Integer> point2) {
        //  (x1 XOR x2) + (y1 XOR y2)
        return (point1.get(0) ^ point2.get(0)) + (point1.get(1) ^ point2.get(1));
    }
}
