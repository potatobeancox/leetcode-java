package com.potato.study.leetcodecn.p01697.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 1697. 检查边长度限制的路径是否存在
 *
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
 *
 * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
 *
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * 输出：[false,true]
 * 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
 * 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
 * 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
 * 示例 2：
 *
 *
 * 输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * 输出：[true,false]
 * 解释：上图为给定数据。
 *  
 *
 * 提示：
 *
 * 2 <= n <= 105
 * 1 <= edgeList.length, queries.length <= 105
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 109
 * 两个点之间可能有 多条 边。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    // 1697
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int queryLen = queries.length;
        boolean[] result = new boolean[queryLen];
        // 对 queries index 进行排序 按照 要求的limit 从小到大 严格小于 limitj 
        Integer[] indexes = new Integer[queryLen];
        for (int i = 0; i < queryLen; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i -> queries[i][2]));
        // 同样对于 edgeList 进行上面相同的排序  edgeList[i] = [ui, vi, disi]
        Arrays.sort(edgeList, Comparator.comparingInt(edge -> edge[2]));
        // 遍历 每个 query 对于小于 limit 的 edgeList 边进行 union find 处理 ，处理之后 判断 是否联通
        UnionFind unionFind = new UnionFind(n);
        int edgeIndex = 0;
        for (int queryIdx : indexes) {

            int limit = queries[queryIdx][2];

            while (edgeIndex < edgeList.length && edgeList[edgeIndex][2] < limit) {
                int from = edgeList[edgeIndex][0];
                int to = edgeList[edgeIndex][1];
                unionFind.union(from, to);

                edgeIndex++;
            }

            // 判断开始和技术能不能连上
            int start = queries[queryIdx][0];
            int end = queries[queryIdx][1];

            result[queryIdx] = unionFind.isConnected(start, end);
        }
        return result;
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

        public boolean isConnected(int target1, int target2) {
            return find(target1) == find(target2);
        }
    }


}
