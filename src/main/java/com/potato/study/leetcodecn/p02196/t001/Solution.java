package com.potato.study.leetcodecn.p02196.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2196. 根据描述创建二叉树
 *
 * 给你一个二维整数数组 descriptions ，其中 descriptions[i] = [parenti, childi, isLefti] 表示 parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。此外：

 如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
 如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
 请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。

 测试用例会保证可以构造出 有效 的二叉树。

  

 示例 1：



 输入：descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 输出：[50,20,80,15,17,19]
 解释：根节点是值为 50 的节点，因为它没有父节点。
 结果二叉树如上图所示。
 示例 2：



 输入：descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 输出：[1,2,null,null,3,4]
 解释：根节点是值为 1 的节点，因为它没有父节点。
 结果二叉树如上图所示。
  

 提示：

 1 <= descriptions.length <= 104
 descriptions[i].length == 3
 1 <= parenti, childi <= 105
 0 <= isLefti <= 1
 descriptions 所描述的二叉树是一棵有效二叉树

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/create-binary-tree-from-descriptions
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public TreeNode createBinaryTree(int[][] descriptions) {
        // 存可能的父亲节点
        Set<TreeNode> parentSet = new HashSet<>();
        Set<TreeNode> childSet = new HashSet<>();
        // 存当前 value 对应节点
        Map<Integer, TreeNode> valueNodeMap = new HashMap<>();
        for (int i = 0; i < descriptions.length; i++) {
            int parentValue = descriptions[i][0];
            int childValue = descriptions[i][1];
            boolean isLeft = descriptions[i][2] == 1;

            TreeNode parent = valueNodeMap.getOrDefault(parentValue, new TreeNode(parentValue));
            TreeNode child = valueNodeMap.getOrDefault(childValue, new TreeNode(childValue));

            if (isLeft) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            parentSet.add(parent);
            childSet.add(child);

            valueNodeMap.put(parentValue, parent);
            valueNodeMap.put(childValue, child);

        }
        parentSet.removeAll(childSet);
        return parentSet.iterator().next();
    }

}
