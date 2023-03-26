package com.potato.study.leetcodecn.other.swordoffer2.p0111.t001;

import java.util.*;

/**
 * 剑指 Offer II 111. 计算除法
 *
 * 给定一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。

 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

 注意：输入总是有效的。可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

  

 示例 1：

 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 解释：
 条件：a / b = 2.0, b / c = 3.0
 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 示例 2：

 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 输出：[3.75000,0.40000,5.00000,0.20000]
 示例 3：

 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 输出：[0.50000,2.00000,-1.00000,-1.00000]
  

 提示：

 1 <= equations.length <= 20
 equations[i].length == 2
 1 <= Ai.length, Bi.length <= 5
 values.length == equations.length
 0.0 < values[i] <= 20.0
 1 <= queries.length <= 20
 queries[i].length == 2
 1 <= Cj.length, Dj.length <= 5
 Ai, Bi, Cj, Dj 由小写英文字母与数字组成
  

 注意：本题与主站 399 题相同： https://leetcode-cn.com/problems/evaluate-division/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/vlzXQL
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {
        // equations 遍历 用map 记录 每个string 对应 index
        int index = 0;
        Map<String, Integer> valueIndexMap = new HashMap<>();
        for (List<String> equation : equations) {
            String a = equation.get(0);
            String b = equation.get(1);

            if (!valueIndexMap.containsKey(a)) {
                valueIndexMap.put(a, index);
                index++;
            }

            if (!valueIndexMap.containsKey(b)) {
                valueIndexMap.put(b, index);
                index++;
            }
        }
        // 将 equations 按照临界矩阵方式构造 map string map string doule 构造
        int n = valueIndexMap.size();
        double[][] grid = new double[n][n];
        for (int i = 0; i < n; i++) {
            grid[i][i] = 1.0;
        }
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);

            int index1 = valueIndexMap.get(a);
            int index2 = valueIndexMap.get(b);

            double val = values[i];

            grid[index1][index2] = val;
            grid[index2][index1] = 1.0 / val;
        }
        double[] result = new double[queries.size()];
        // 遍历 query bfs
        for (int i = 0; i < queries.size(); i++) {
            List<String> strings = queries.get(i);
            String a = strings.get(0);
            String b = strings.get(1);

            if (!valueIndexMap.containsKey(a) || !valueIndexMap.containsKey(b)) {
                result[i] = -1.0;
                continue;
            }

            int fromIndex = valueIndexMap.get(a);
            int toIndex = valueIndexMap.get(b);

            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(fromIndex, 1.0));
            Map<Integer, Double> path = new HashMap<>();
            path.put(fromIndex, 1.0);

            result[i] = getCost(n, grid, result, i, toIndex, queue, path);

        }
        return result;
    }

    private double getCost(int n, double[][] grid, double[] result, int i, int toIndex,
                         Queue<Node> queue, Map<Integer, Double> path) {
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int currentIndex = poll.index;
            double currentCost = poll.cost;

            // 是否已经到了 终点
            if (currentIndex == toIndex) {
                return currentCost;
            }
            // 没到终点 找下一个节点
            for (int j = 0; j < n; j++) {
                if (path.containsKey(j)) {
                    continue;
                }
                if (grid[currentIndex][j] == 0) {
                    continue;
                }
                if (j == currentIndex) {
                    continue;
                }
                double nextCost = currentCost * grid[currentIndex][j];
                if (j == toIndex) {
                    return nextCost;
                }
                path.put(j, nextCost);
                queue.add(new Node(j, currentCost * grid[currentIndex][j]));
            }

        }
        return -1;
    }

    class Node {
        public int index;
        public double cost;

        public Node(int index, double cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
