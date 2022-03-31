package com.potato.study.leetcodecn.p02192.t001;

import java.util.*;

/**
 * 2192. 有向无环图中一个节点的所有祖先
 *
 * 给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。

 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。

 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。

 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。

  

 示例 1：



 输入：n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
 输出：[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
 解释：
 上图为输入所对应的图。
 - 节点 0 ，1 和 2 没有任何祖先。
 - 节点 3 有 2 个祖先 0 和 1 。
 - 节点 4 有 2 个祖先 0 和 2 。
 - 节点 5 有 3 个祖先 0 ，1 和 3 。
 - 节点 6 有 5 个祖先 0 ，1 ，2 ，3 和 4 。
 - 节点 7 有 4 个祖先 0 ，1 ，2 和 3 。
 示例 2：



 输入：n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 输出：[[],[0],[0,1],[0,1,2],[0,1,2,3]]
 解释：
 上图为输入所对应的图。
 - 节点 0 没有任何祖先。
 - 节点 1 有 1 个祖先 0 。
 - 节点 2 有 2 个祖先 0 和 1 。
 - 节点 3 有 3 个祖先 0 ，1 和 2 。
 - 节点 4 有 4 个祖先 0 ，1 ，2 和 3 。
  

 提示：

 1 <= n <= 1000
 0 <= edges.length <= min(2000, n * (n - 1) / 2)
 edges[i].length == 2
 0 <= fromi, toi <= n - 1
 fromi != toi
 图中不会有重边。
 图是 有向 且 无环 的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2192 找到每个节点的祖先
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // 遍历 edges 用map int list int 存 每个点的直接祖先
        Map<Integer, List<Integer>> directParentMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            // [fromi, toi]
            int from = edges[i][0];
            int to = edges[i][1];
            List<Integer> orDefault = directParentMap.getOrDefault(to, new ArrayList<>());
            orDefault.add(from);
            directParentMap.put(to, orDefault);
        }
        // 从 i 开始生成 每个节点的所有祖先
        List<Set<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            getParents(i, result, directParentMap);
        }

        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            resultList.add(new ArrayList<>(result.get(i)));
        }
        return resultList;
    }

    private void getParents(int i, List<Set<Integer>> result,
                            Map<Integer, List<Integer>> directParentMap) {
        if (result.get(i).size() > 0) {
            return;
        }
        Set<Integer> set = result.get(i);
        // 找到set 所有直接的 节点 往里加
        List<Integer> parentList = directParentMap.get(i);
        if (parentList != null) {
            set.addAll(parentList);
            for (int parent : parentList) {
                getParents(parent, result, directParentMap);
                set.addAll(result.get(parent));
            }
        }
    }

}
