package com.potato.study.leetcodecn.p01632.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1632. 矩阵转换后的秩
 *
 * 给你一个 m x n 的矩阵 matrix ，请你返回一个新的矩阵 answer ，其中 answer[row][col] 是 matrix[row][col] 的秩。

 每个元素的 秩 是一个整数，表示这个元素相对于其他元素的大小关系，它按照如下规则计算：

 秩是从 1 开始的一个整数。
 如果两个元素 p 和 q 在 同一行 或者 同一列 ，那么：
 如果 p < q ，那么 rank(p) < rank(q)
 如果 p == q ，那么 rank(p) == rank(q)
 如果 p > q ，那么 rank(p) > rank(q)
 秩 需要越 小 越好。
 题目保证按照上面规则 answer 数组是唯一的。

  

 示例 1：


 输入：matrix = [[1,2],[3,4]]
 输出：[[1,2],[2,3]]
 解释：
 matrix[0][0] 的秩为 1 ，因为它是所在行和列的最小整数。
 matrix[0][1] 的秩为 2 ，因为 matrix[0][1] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
 matrix[1][0] 的秩为 2 ，因为 matrix[1][0] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
 matrix[1][1] 的秩为 3 ，因为 matrix[1][1] > matrix[0][1]， matrix[1][1] > matrix[1][0] 且 matrix[0][1] 和 matrix[1][0] 的秩都为 2 。
 示例 2：


 输入：matrix = [[7,7],[7,7]]
 输出：[[1,1],[1,1]]
 示例 3：


 输入：matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 输出：[[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 示例 4：


 输入：matrix = [[7,3,6],[1,4,5],[9,8,2]]
 输出：[[5,1,4],[1,2,3],[6,3,1]]
  

 提示：

 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 500
 -109 <= matrix[row][col] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/rank-transform-of-a-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[][] matrixRankTransform(int[][] matrix) {
        // 首先统计 每个值 对应出现次数和出现的位置
        TreeMap<Integer, List<int[]>> valueIndexMap = new TreeMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j];

                List<int[]> indexList = valueIndexMap.getOrDefault(val, new ArrayList<>());
                indexList.add(new int[] {i, j});

                valueIndexMap.put(val, indexList);
            }
        }
        // 按照统计出现从小到大开始遍历 用两个数据 粉笔记录 历史某行和某列已经有的最大值
        // 某一行最大值
        int[] maxLine = new int[matrix.length];
        // 某一列的最大
        int[] maxCol = new int[matrix[0].length];
        int[] rank = new int[matrix.length + matrix[0].length];
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int key : valueIndexMap.keySet()) {
            // 这些节点是一样的秩
            List<int[]> sameValueList = valueIndexMap.get(key);
            // 将 sameValueList 放入union 中 中间记录 每次每行每列的最大值
            UnionFind unionFind = new UnionFind(matrix.length + matrix[0].length);
            for (int[] index : sameValueList) {
                unionFind.union(index[0], matrix.length + index[1]);
            }
            // 这些行列已经连接到一起看看 他们其中的最大值
            for (int[] index : sameValueList) {
                int p = unionFind.find(index[0]);
                int max = Math.max(Math.max(maxLine[index[0]], maxCol[index[1]]), rank[p]);

                rank[p] = max;
            }
            // 重新遍历 生成结果 并修改 每行没列最大使用
            for (int[] index : sameValueList) {
                int x = index[0];
                int y = index[1];

                int p = unionFind.find(x);

                res[x][y] = rank[p] + 1;
                // 修改max
                maxLine[x] = rank[p] + 1;
                maxCol[y] = rank[p] + 1;
            }
        }
        return res;
    }


    class UnionFind {

        public int[] parent;
        public int n;

        public UnionFind(int n) {
            this.n = n;
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
            // p1 和 p2 连接一下
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
