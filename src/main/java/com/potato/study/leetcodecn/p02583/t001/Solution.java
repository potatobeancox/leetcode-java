package com.potato.study.leetcodecn.p02583.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2583. 二叉树中的第 K 大层和

 给你一棵二叉树的根节点 root 和一个正整数 k 。

 树中的 层和 是指 同一层 上节点值的总和。

 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。

 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。

  

 示例 1：



 输入：root = [5,8,9,2,1,3,7,4,6], k = 2
 输出：13
 解释：树中每一层的层和分别是：
 - Level 1: 5
 - Level 2: 8 + 9 = 17
 - Level 3: 2 + 1 + 3 + 7 = 13
 - Level 4: 4 + 6 = 10
 第 2 大的层和等于 13 。
 示例 2：



 输入：root = [1,2,null,3], k = 1
 输出：3
 解释：最大的层和是 3 。
  

 提示：

 树中的节点数为 n
 2 <= n <= 105
 1 <= Node.val <= 106
 1 <= k <= n

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long kthLargestLevelSum(TreeNode root, int k) {
        // 小跟堆 当大小 大于k时需要先aa 然后pop
        PriorityQueue<Long> layerSumHeap = new PriorityQueue<>();
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 这层开始遍历
            long currentLayerSum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                currentLayerSum += poll.val;

                // 左右孩子
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            layerSumHeap.add(currentLayerSum);
            while (layerSumHeap.size() > k) {
                layerSumHeap.poll();
            }
        }

        if (layerSumHeap.size() != k) {
            return -1;
        }
        long max = layerSumHeap.peek();
        return max;
    }




}
