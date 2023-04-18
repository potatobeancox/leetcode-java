package com.potato.study.leetcodecn.p02641.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 *
 * 2641. 二叉树的堂兄弟节点 II
 *
 * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。

 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。

 请你返回修改值之后，树的根 root 。

 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。

  

 示例 1：



 输入：root = [5,4,9,1,10,null,7]
 输出：[0,0,0,7,7,null,11]
 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 - 值为 5 的节点没有堂兄弟，所以值修改为 0 。
 - 值为 4 的节点没有堂兄弟，所以值修改为 0 。
 - 值为 9 的节点没有堂兄弟，所以值修改为 0 。
 - 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 - 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 - 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
 示例 2：



 输入：root = [3,1,2]
 输出：[0,0,0]
 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 - 值为 3 的节点没有堂兄弟，所以值修改为 0 。
 - 值为 1 的节点没有堂兄弟，所以值修改为 0 。
 - 值为 2 的节点没有堂兄弟，所以值修改为 0 。
  

 提示：

 树中节点数目的范围是 [1, 105] 。
 1 <= Node.val <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/cousins-in-binary-tree-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 2641
    public TreeNode replaceValueInTree(TreeNode root) {
        // 层序遍历 每层需要记录sum 和父亲对应的孩子的和 最终进行替换
        Queue<TreeNode[]> queue = new LinkedList<>();
        // 最开始入队 root 和他的父亲
        queue.add(new TreeNode[] {
                root, null
        });

        while (!queue.isEmpty()) {
            // 这层有多少个节点
            int size = queue.size();
            long layerSum = 0;
            Map<TreeNode, Long> fatherSumMap = new HashMap<>();
            List<TreeNode> parentNodeList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode[] poll = queue.poll();
                TreeNode treeNode = poll[0];
                TreeNode parent = poll[1];

                layerSum += treeNode.val;
                // 处理第一层的节点
                if (parent != null) {
                    long parentSum = fatherSumMap.getOrDefault(parent, 0L);
                    parentSum += treeNode.val;
                    fatherSumMap.put(parent, parentSum);
                    // 用于之后替换
                    parentNodeList.add(parent);
                }

                // 当前节点的左右孩子放入节点
                if (treeNode.left != null) {
                    queue.add(new TreeNode[] {
                            treeNode.left, treeNode
                    });
                }
                if (treeNode.right != null) {
                    queue.add(new TreeNode[] {
                            treeNode.right, treeNode
                    });
                }

            }
            // 替换
            for (TreeNode parentNode : parentNodeList) {
                long needMinusSum = fatherSumMap.getOrDefault(parentNode, 0L);
                long targetValue = layerSum - needMinusSum;

                if (parentNode.left != null) {
                    parentNode.left.val = (int) targetValue;
                }
                if (parentNode.right != null) {
                    parentNode.right.val = (int) targetValue;
                }
            }
        }

        // 改根
        root.val = 0;
        return root;
    }



}
