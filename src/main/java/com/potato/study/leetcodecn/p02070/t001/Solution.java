package com.potato.study.leetcodecn.p02070.t001;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2070. 每一个查询的最大美丽值
 *
 * 给你一个二维整数数组 items ，其中 items[i] = [pricei, beautyi] 分别表示每一个物品的 价格 和 美丽值 。

 同时给你一个下标从 0 开始的整数数组 queries 。对于每个查询 queries[j] ，你想求出价格小于等于 queries[j] 的物品中，最大的美丽值 是多少。如果不存在符合条件的物品，那么查询的结果为 0 。

 请你返回一个长度与 queries 相同的数组 answer，其中 answer[j]是第 j 个查询的答案。

  

 示例 1：

 输入：items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
 输出：[2,4,5,5,6,6]
 解释：
 - queries[0]=1 ，[1,2] 是唯一价格 <= 1 的物品。所以这个查询的答案为 2 。
 - queries[1]=2 ，符合条件的物品有 [1,2] 和 [2,4] 。
 它们中的最大美丽值为 4 。
 - queries[2]=3 和 queries[3]=4 ，符合条件的物品都为 [1,2] ，[3,2] ，[2,4] 和 [3,5] 。
 它们中的最大美丽值为 5 。
 - queries[4]=5 和 queries[5]=6 ，所有物品都符合条件。
 所以，答案为所有物品中的最大美丽值，为 6 。
 示例 2：

 输入：items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
 输出：[4]
 解释：
 每个物品的价格均为 1 ，所以我们选择最大美丽值 4 。
 注意，多个物品可能有相同的价格和美丽值。
 示例 3：

 输入：items = [[10,1000]], queries = [5]
 输出：[0]
 解释：
 没有物品的价格小于等于 5 ，所以没有物品可以选择。
 因此，查询的结果为 0 。
  

 提示：

 1 <= items.length, queries.length <= 105
 items[i].length == 2
 1 <= pricei, beautyi, queries[j] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/most-beautiful-item-for-each-query
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 找到 item中 价格 小于 等于 query i 中最大的美丽值
     * @param items
     * @param queries
     * @return
     */
    public int[] maximumBeauty(int[][] items, int[] queries) {
        // 做一个 queriesIndex 数组 按照 queries 升序 排序
        Integer[] queriesIndex = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            queriesIndex[i] = i;
        }
        Arrays.sort(queriesIndex, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(queries[o1], queries[o2]);
            }
        });
        // 对item 按照 价格升序排序
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int price1 = o1[0];
                int price2 = o2[0];
                return Integer.compare(price1, price2);
            }
        });
        // 遍历 queriesIndex 如果当前 item 价格小于等于 queriesIndex 价格 往前移动 维护最大美丽值
        int maxBeauty = 0;
        int priceItemIndex = 0;
        int[] result = new int[queries.length];
        for (int i = 0; i < queriesIndex.length; i++) {
            int index = queriesIndex[i];
            int targetPrice = queries[index];
            while (priceItemIndex < items.length && items[priceItemIndex][0] <= targetPrice) {
                maxBeauty = Math.max(maxBeauty, items[priceItemIndex][1]);
                priceItemIndex++;
            }
            result[index] = maxBeauty;
        }
        return result;
    }


}
