package com.potato.study.leetcodecn.p01722.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 1722. 执行交换操作后的最小汉明距离
 *
 * 给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i] = [ai, bi] 表示你可以交换数组 source 中下标为 ai 和
 * bi（下标从 0 开始）的两个元素。注意，你可以按 任意 顺序 多次 交换一对特定下标指向的元素。
 *
 * 相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。形式上，其值等于满足 source[i] != target[i] （下标从 0 开始）的下标 i（0 <= i <= n-1）的数量。
 *
 * 在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * 输出：1
 * 解释：source 可以按下述方式转换：
 * - 交换下标 0 和 1 指向的元素：source = [2,1,3,4]
 * - 交换下标 2 和 3 指向的元素：source = [2,1,4,3]
 * source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
 * 示例 2：
 *
 * 输入：source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
 * 输出：2
 * 解释：不能对 source 执行交换操作。
 * source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。
 * 示例 3：
 *
 * 输入：source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * n == source.length == target.length
 * 1 <= n <= 105
 * 1 <= source[i], target[i] <= 105
 * 0 <= allowedSwaps.length <= 105
 * allowedSwaps[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimize-hamming-distance-after-swap-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        // 对于 allowedSwaps 进行并查集
        int n = source.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < allowedSwaps.length; i++) {
            int a = allowedSwaps[i][0];
            int b = allowedSwaps[i][1];
            unionFind.union(a, b);
        }
        // 遍历 source 对于每个 位置对应的连通分量 使用 一个 map map<Integer, Interg> 最终value 是个数
        Map<Integer, Map<Integer, Integer>> sourceParentNodeCountMap = getParentCountMap(source, unionFind);
        Map<Integer, Map<Integer, Integer>> targetParentNodeCountMap = getParentCountMap(target, unionFind);

        // 遍历 每个 map 找到对应连通分量
        int distance = 0;
        for (int parentNode : sourceParentNodeCountMap.keySet()) {
            Map<Integer, Integer> sourceCountMap = sourceParentNodeCountMap.get(parentNode);
            Map<Integer, Integer> targetCountMap = targetParentNodeCountMap.get(parentNode);
            for (int key : sourceCountMap.keySet()) {
                if (targetCountMap.containsKey(key)) {
                    // target 有 比较是不是多了 多了就无处安放
                    if (targetCountMap.get(key) < sourceCountMap.get(key)) {
                        distance += (sourceCountMap.get(key) - targetCountMap.get(key));
                    }
                } else {
                    // 不存在就都是 问题
                    distance += sourceCountMap.get(key);
                }
            }
        }
        return distance;
    }

    private Map<Integer, Map<Integer, Integer>> getParentCountMap(int[] nums, UnionFind unionFind) {
        Map<Integer, Map<Integer, Integer>> parentNodeCountMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int parentKey = unionFind.find(i);
            Map<Integer, Integer> parentMap = parentNodeCountMap.getOrDefault(parentKey, new HashMap<>());
            Integer count = parentMap.getOrDefault(nums[i], 0);
            count++;
            parentMap.put(nums[i], count);
            parentNodeCountMap.put(parentKey, parentMap);
        }
        return parentNodeCountMap;
    }

    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

    }
}
