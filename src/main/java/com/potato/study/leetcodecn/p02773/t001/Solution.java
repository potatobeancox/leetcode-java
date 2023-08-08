package com.potato.study.leetcodecn.p02773.t001;


import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 2773. 特殊二叉树的高度
 *
 * 给定一棵具有 n 个节点的 特殊 二叉树的根节点 root 。特殊二叉树的节点编号从 1 到 n 。假设这棵树有 k 个叶子，顺序如下：b1 < b2 < ... < bk 。
 *
 * 这棵树的叶子节点有一个 特殊 属性 ！对于每个叶子节点 bi ，满足以下条件：
 *
 * 如果 i < k ，则 bi 的右子节点为 bi + 1 ；否则为 b1 。
 * 如果 i > 1 ，则 bi 的左子节点为 bi - 1 ；否则为 bk 。
 * 返回给定树的高度。
 *
 * 注意：二叉树的高度是指从根节点到任何其他节点的 最长路径 的长度。
 *
 *  
 *
 * 示例 1;
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：2
 * 解释：给定树如下图所示。每个叶子节点的左子节点是它左边的叶子节点（用蓝色边表示）。每个叶子节点的右子节点是它右边的叶子节点（用红色边表示）。我们可以看出，该图的高度为2。
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2]
 * 输出：1
 * 解释：给定树如下图所示。只有一个叶子节点，所以它没有左子节点或右子节点。我们可以看出，该图的高度为 1。
 *
 *
 * 示例 3：
 *
 * 输入：root = [1,2,3,null,null,4,null,5,6]
 * 输出：3
 * 解释：给定树如下图所示。每个叶子节点的左子节点是它左边的叶子节点（用蓝色边表示）。每个叶子节点的右子节点是它右边的叶子节点（用红色边表示）。我们可以看出，该图的高度为3。
 *
 *
 *  
 *
 * 提示：
 *
 * n 为树中节点的数量
 * 2 <= n <= 104
 * 1 <= node.val <= n
 * 输入保证每个 node.val 的值是唯一的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/height-of-special-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public int heightOfTree(TreeNode root) {
        // bfs 如果 存在二元环就不添加了
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                // 找到相邻的看看是不是有效节点
                if (poll.left != null && poll.left.right != poll) {
                    queue.add(poll.left);
                }
                if (poll.right != null && poll.right.left != poll) {
                    queue.add(poll.right);
                }
            }
            height++;
        }
        return height - 1;
    }


}
