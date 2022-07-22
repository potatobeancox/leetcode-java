package com.potato.study.leetcodecn.p00589.t001;


import com.potato.study.leetcode.domain.node.val.children.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历。

 例如，给定一个 3叉树 :

  



  

 返回其前序遍历: [1,3,5,6,2,4]。

  

 说明: 递归法很简单，你可以使用迭代法完成此题吗?

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preOrderTraverse(root, list);
        return list;
    }

    /**
     *
     * @param root
     * @param list
     */
    private void preOrderTraverse(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        // 叶子了
        if (root.children == null || root.children.size() == 0) {
            list.add(root.val);
            return;
        }
        // 处理当前节点
        list.add(root.val);
        // 先进行子结点的遍历
        for (Node child : root.children) {
            preOrderTraverse(child, list);
        }
        return;
    }

}
