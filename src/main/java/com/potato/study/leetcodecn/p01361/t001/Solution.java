package com.potato.study.leetcodecn.p01361.t001;


import java.util.Arrays;

/**
 * 1361. 验证二叉树
 *
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。

 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。

 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。

 注意：节点没有值，本问题中仅仅使用节点编号。

  

 示例 1：



 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 输出：true
 示例 2：



 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 输出：false
 示例 3：



 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
 输出：false
 示例 4：



 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 输出：false
  

 提示：

 1 <= n <= 10^4
 leftChild.length == rightChild.length == n
 -1 <= leftChild[i], rightChild[i] <= n - 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/validate-binary-tree-nodes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 并查集 判断是否只有一个联通分量
        int[] indegree = new int[n];
        int edgeCount = 0;
        // 统计每个 节点的入读， 树的每个节点的入度 小于等于 1 ，遍数量等于节点数量 -1
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int leftIndex = leftChild[i];
            if (leftIndex != -1) {
                unionFind.union(i, leftIndex);
                indegree[leftIndex]++;
                edgeCount++;
            }

            int rightIndex = rightChild[i];
            if (rightIndex != -1) {
                unionFind.union(i, rightIndex);
                indegree[rightIndex]++;
                edgeCount++;
            }
        }
        // 联通分量 1
        int componentCount = unionFind.getComponentCount();
        if (componentCount != 1) {
            return false;
        }
        // 入读检查
        for (int i = 0; i < n; i++) {
            if (indegree[i] > 1) {
                return false;
            }
        }
        return edgeCount == n - 1;
    }


    class UnionFind {
        private int[] parent;
        private int componentCount;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.componentCount = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }


        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
            componentCount--;
        }

        public int getComponentCount() {
            return this.componentCount;
        }
    }
}
