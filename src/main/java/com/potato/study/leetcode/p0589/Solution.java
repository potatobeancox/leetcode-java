package com.potato.study.leetcode.p0589;

import com.potato.study.leetcode.domain.node.val.children.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liuzhao11
 * 
 *         589. N-ary Tree Preorder Traversal
 * 
 *         Given an n-ary tree, return the preorder traversal of its nodes' values.

For example, given a 3-ary tree:







Return its preorder traversal as: [1,3,5,6,2,4].



Note:

Recursive solution is trivial, could you do it iteratively?
 * 
 * 
 *         思路：
 *         先序遍历一个多叉树
 *       
 *          
 */
public class Solution {

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        this.preorderNAryTree(root, result);
        return result;
    }

    /**
     * 执行遍历n叉树
     * @param root
     * @param result
     */
    private void preorderNAryTree(Node root, List<Integer> result) {
        if (null == root) {
            return;
        }
        result.add(root.val);
        if (root.children != null) {
            for (Node node : root.children) {
                this.preorderNAryTree(node, result);
            }
        }
    }


    public static void main(String[] args) {
		Solution solution = new Solution();
        Node root = null;
        List<Integer> sum = solution.preorder(root);
        System.out.println(sum);
    }
}
