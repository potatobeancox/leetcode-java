package com.potato.study.leetcodecn.p00958.t001;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 958. 二叉树的完全性检验
 *
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

 （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

 返回区域的数目。

  

 示例 1：

 输入：
 [
   " /",
   "/ "
 ]
 输出：2
 解释：2x2 网格如下：

 示例 2：

 输入：
 [
   " /",
   "  "
 ]
 输出：1
 解释：2x2 网格如下：

 示例 3：给定一个二叉树，确定它是否是一个完全二叉树。

 百度百科中对完全二叉树的定义如下：

 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）

  

 示例 1：



 输入：[1,2,3,4,5,6]
 输出：true
 解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 示例 2：



 输入：[1,2,3,4,5,null,7]
 输出：false
 解释：值为 7 的结点没有尽可能靠向左侧。
  

 提示：

 树中将会有 1 到 100 个结点。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 958
    public boolean isCompleteTree(TreeNode root) {
        // bfs 判断
        if (null == root) {
            // 空树是
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 当前层 index
        int layerIndex = 0;
        while (!queue.isEmpty()) {
            // 当前层要求的个数
            int layerLimit = (1 << layerIndex);
            int layerSize = queue.size();
            int nextNum = 0;
            for (int i = 0; i < layerSize; i++) {
                TreeNode poll = queue.poll();
                // 如果当前行 不是最后一行，且个数 小于 2 ^ (index) 个返回false
                if (layerSize < layerLimit && poll.left != null) {
                    return false;
                }
                // 如果当前 节点 有右孩子但没有左孩子 false
                if (poll.left == null && poll.right != null) {
                    return false;
                }
                // 如果 当前 节点 index i 之前的个数 小于 2 * i 返回 false
                if (poll.left != null && 2 * i > nextNum) {
                    return false;
                }
                // 左右孩子
                if (poll.left != null) {
                    queue.add(poll.left);
                    nextNum++;
                }
                if (poll.right != null && 2 * i + 1 > nextNum) {
                    return false;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    nextNum++;
                }
            }
            layerIndex++;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        node.left.right = new TreeNode(4);

        boolean completeTree = solution.isCompleteTree(node);
        System.out.println(completeTree);
        Assert.assertEquals(false, completeTree);


        // [1,2,3,null,null,7,8]
        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(7);
        node.right.right = new TreeNode(8);


        completeTree = solution.isCompleteTree(node);
        System.out.println(completeTree);
        Assert.assertEquals(false, completeTree);


        // [1,2,3,null,null,7,8]
        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);


        completeTree = solution.isCompleteTree(node);
        System.out.println(completeTree);
        Assert.assertEquals(true, completeTree);
    }
}
