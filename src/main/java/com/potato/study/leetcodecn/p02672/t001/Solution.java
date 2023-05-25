package com.potato.study.leetcodecn.p02672.t001;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * 2672. 有相同颜色的相邻元素数目
 *
 * 给你一个下标从 0 开始、长度为 n 的数组 nums 。一开始，所有元素都是 未染色 （值为 0 ）的。
 *
 * 给你一个二维整数数组 queries ，其中 queries[i] = [indexi, colori] 。
 *
 * 对于每个操作，你需要将数组 nums 中下标为 indexi 的格子染色为 colori 。
 *
 * 请你返回一个长度与 queries 相等的数组 answer ，其中 answer[i]是前 i 个操作 之后 ，相邻元素颜色相同的数目。
 *
 * 更正式的，answer[i] 是执行完前 i 个操作后，0 <= j < n - 1 的下标 j 中，满足 nums[j] == nums[j + 1] 且 nums[j] != 0 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
 * 输出：[0,1,1,0,2]
 * 解释：一开始数组 nums = [0,0,0,0] ，0 表示数组中还没染色的元素。
 * - 第 1 个操作后，nums = [2,0,0,0] 。相邻元素颜色相同的数目为 0 。
 * - 第 2 个操作后，nums = [2,2,0,0] 。相邻元素颜色相同的数目为 1 。
 * - 第 3 个操作后，nums = [2,2,0,1] 。相邻元素颜色相同的数目为 1 。
 * - 第 4 个操作后，nums = [2,1,0,1] 。相邻元素颜色相同的数目为 0 。
 * - 第 5 个操作后，nums = [2,1,1,1] 。相邻元素颜色相同的数目为 2 。
 * 示例 2：
 *
 * 输入：n = 1, queries = [[0,100000]]
 * 输出：[0]
 * 解释：一开始数组 nums = [0] ，0 表示数组中还没染色的元素。
 * - 第 1 个操作后，nums = [100000] 。相邻元素颜色相同的数目为 0 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= indexi <= n - 1
 * 1 <=  colori <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-adjacent-elements-with-the-same-color
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2672
    public int[] colorTheArray(int n, int[][] queries) {
        // 遍历 query 用一个 treemap 覆盖的方式记录颜色
        TreeMap<Integer, Integer> indexColorMap = new TreeMap<>();
        int length = queries.length;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            int key = queries[i][0];
            int col = queries[i][1];
            indexColorMap.put(key, col);
            // 遍历 treemap 记录上一个的颜色和位置 连续的时候更新位置 和最大值
            int len = 0;
            for (int index : indexColorMap.keySet()) {
                if (indexColorMap.get(index-1) == indexColorMap.get(index)) {
                    len++;
                }
            }
            res[i] = len;
        }
        return res;
    }
}
