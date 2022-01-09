package com.potato.study.leetcodecn.p01530.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1530. 好叶子节点对的数量
 *
 * 给你二叉树的根节点 root 和一个整数 distance 。

 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。

 返回树中 好叶子节点对的数量 。

  

 示例 1：

  



 输入：root = [1,2,3,null,4], distance = 3
 输出：1
 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
 示例 2：



 输入：root = [1,2,3,4,5,6,7], distance = 3
 输出：2
 解释：好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。但是叶子节点对 [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。
 示例 3：

 输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 输出：1
 解释：唯一的好叶子节点对是 [2,5] 。
 示例 4：

 输入：root = [100], distance = 1
 输出：0
 示例 5：

 输入：root = [1,1,1], distance = 2
 输出：1
  

 提示：

 tree 的节点数在 [1, 2^10] 范围内。
 每个节点的值都在 [1, 100] 之间。
 1 <= distance <= 10

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-good-leaf-nodes-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countPairs(TreeNode root, int distance) {
        // dfs 找到 当前 root 有多少个
        NodeInfo rootInfo = dfs(root, distance);
        return rootInfo.goodCount;
    }

    /**
     * dfs 找到 好叶子节点个数
     * @param root
     * @param distance
     * @return
     */
    private NodeInfo dfs(TreeNode root, int distance) {
        // 当前节点 左边孩子情况
        NodeInfo left = null;
        if (root.left != null) {
            left = dfs(root.left, distance);
        }
        // 当前节点右孩子情况
        NodeInfo right = null;
        if (root.right != null) {
            right = dfs(root.right, distance);
        }
        // 根据做孩子和右孩子 生成当前节点
        NodeInfo rootInfo = new NodeInfo(distance);
        if (left == null && right == null) {
            // 当前节点就是叶子
            rootInfo.distantCount[0] = 1;
            return rootInfo;
        } else if (left == null) {
            left = new NodeInfo(distance);
        } else if (right == null) {
            right = new NodeInfo(distance);
        }
        for (int i = 1; i < distance + 1; i++) {
            rootInfo.distantCount[i] = (left.distantCount[i-1] + right.distantCount[i-1]);
        }
        // 个数统计
        int total = left.goodCount + right.goodCount;
        for (int i = 0; i < left.distantCount.length; i++) {
            for (int j = 0; j < right.distantCount.length; j++) {
                if (i + j + 2 <= distance) {
                    total += (left.distantCount[i] * right.distantCount[j]);
                }
            }
        }
        rootInfo.goodCount = total;
        return rootInfo;
    }


    class NodeInfo {
        // 距离当前节点 i 的个数 数组最多 distance + 1个
        public int[] distantCount;
        // 这个节点好叶子的对 数量
        public int goodCount;

        public NodeInfo(int distance) {
            this.distantCount = new int[distance + 1];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);
        int distance = 3;
        int i = solution.countPairs(root, distance);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
