package com.potato.study.leetcodecn.p01198.t001;


import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;

/**
 * 1198. 找出所有行中最小公共元素
 *
 * 你一个 m x n 的矩阵 mat，其中每一行的元素均符合 严格递增 。请返回 所有行中的 最小公共元素 。
 *
 * 如果矩阵中没有这样的公共元素，就请返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * 输出：5
 * 示例 2:
 *
 * 输入：mat = [[1,2,3],[2,3,4],[2,3,5]]
 * 输出： 2
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 104
 * mat[i] 已按严格递增顺序排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-smallest-common-element-in-all-rows
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int smallestCommonElement(int[][] mat) {
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int count = countMap.getOrDefault(mat[i][j], 0);
                count++;
                countMap.put(mat[i][j], count);
            }
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == mat.length) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
