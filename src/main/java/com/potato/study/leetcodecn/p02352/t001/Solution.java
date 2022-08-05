package com.potato.study.leetcodecn.p02352.t001;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2352. 相等行列对
 *
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 *
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 *  
 *
 * 提示：
 *
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/equal-row-and-column-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int equalPairs(int[][] grid) {
        // 行个数
        Map<String, Integer> lineCountMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < grid[i].length; j++) {
                builder.append(grid[i][j]).append("#");
            }
            String key = builder.toString();
            int count = lineCountMap.getOrDefault(key, 0);
            count++;
            lineCountMap.put(key, count);
        }
        // 列个数
        Map<String, Integer> columnCountMap = new HashMap<>();
        for (int i = 0; i < grid[0].length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < grid.length; j++) {
                builder.append(grid[j][i]).append("#");
            }
            String key = builder.toString();
            int count = columnCountMap.getOrDefault(key, 0);
            count++;
            columnCountMap.put(key, count);
        }
        // 遍历行列求乘积
        long totalCount = 0;
        for (Map.Entry<String, Integer> entry : lineCountMap.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();
            if (columnCountMap.containsKey(key)) {
                totalCount += (count * columnCountMap.get(key));
            }
        }
        return (int) totalCount;
    }


}
