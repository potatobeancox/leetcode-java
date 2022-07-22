package com.potato.study.leetcode.p0590;

import com.potato.study.leetcode.domain.node.val.children.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liuzhao11
 * 
 *         590. N-ary Tree Postorder Traversal
 * 
 *         Given an n-ary tree, return the postorder traversal of its nodes' values.

For example, given a 3-ary tree:







Return its postorder traversal as: [5,6,3,2,4,1].


Note:

Recursive solution is trivial, could you do it iteratively?
 * 
 * 
 *         思路：
 *         后序遍历一个多叉树
 *       
 *          
 */
public class Solution {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderNAryTree(root, result);
        return result;
    }

    /**
     * 执行遍历n叉树
     * @param root
     * @param result
     */
    private void postorderNAryTree(Node root, List<Integer> result) {
        if (null == root) {
            return;
        }
        if (root.children != null) {
            for (Node node : root.children) {
                this.postorderNAryTree(node, result);
            }
        }
        result.add(root.val);
    }


    public static void main(String[] args) {
		Solution solution = new Solution();
        Node root = null;
        List<Integer> sum = solution.postorder(root);
        System.out.println(sum);
    }
}
