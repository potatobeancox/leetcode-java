package com.potato.study.leetcodecn.p01940.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.bidimap.TreeBidiMap;

/**
 * 1940. 排序数组之间的最长公共子序列
 *
 * 给定一个由整数数组组成的数组arrays，其中arrays[i]是严格递增排序的，返回一个表示所有数组之间的最长公共子序列的整数数组。
 *
 * 子序列是从另一个序列派生出来的序列，删除一些元素或不删除任何元素，而不改变其余元素的顺序。
 *
 * 示例1:
 *
 * 输入: arrays = [[1,3,4],
 *                [1,4,7,9]]
 * 输出: [1,4]
 * 解释: 这两个数组中的最长子序列是[1,4]。
 * 示例 2:
 *
 * 输入: arrays = [[2,3,6,8],
 *                [1,2,3,5,6,7,10],
 *                [2,3,4,6,9]]
 * 输出: [2,3,6]
 * 解释: 这三个数组中的最长子序列是[2,3,6]。
 * 示例 3:
 *
 * 输入: arrays = [[1,2,3,4,5],
 *                [6,7,8]]
 * 输出: []
 * 解释: 这两个数组之间没有公共子序列。
 *  
 *
 * 限制条件:
 *
 * 2 <= arrays.length <= 100
 * 1 <= arrays[i].length <= 100
 * 1 <= arrays[i][j] <= 100
 * arrays[i] 是严格递增排序.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-common-subsequence-between-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                int count = countMap.getOrDefault(arrays[i][j], 0);
                count++;
                countMap.put(arrays[i][j], count);
            }
        }
        // 遍历
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == arrays.length) {
                list.add(entry.getKey());
            }
        }
        return list;
    }


}
