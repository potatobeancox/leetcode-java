package com.potato.study.leetcode.p0971;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liuzhao11
 * 
 * 	971. Flip Binary Tree To Match Preorder Traversal
 *  
 *       Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].



Example 1:



Input: root = [1,2], voyage = [2,1]
Output: [-1]
Example 2:



Input: root = [1,2,3], voyage = [1,3,2]
Output: [1]
Example 3:



Input: root = [1,2,3], voyage = [1,2,3]
Output: []


Note:

1 <= N <= 100
 *         
 *         题目含义：
 *          https://leetcode-cn.com/problems/flip-binary-tree-to-match-preorder-traversal/solution/fan-zhuan-er-cha-shu-yi-pi-pei-xian-xu-bian-li-by-/
 *
 *
 *
 * 
 */
public class Solution {

    private List<Integer> flipped;
    private int index;
    private int[] voyage;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        flipped = new ArrayList<>();
        index = 0;
        this.voyage = voyage;

        dfs(root);
        if (!flipped.isEmpty() && flipped.get(0) == -1) {
            flipped.clear();
            flipped.add(-1);
        }

        return flipped;
    }

    private void dfs(TreeNode node) {
        if (node != null) {
            if (node.val != voyage[index++]) {
                flipped.clear();
                flipped.add(-1);
                return;
            }

            if (index < voyage.length && node.left != null &&
                    node.left.val != voyage[index]) {
                flipped.add(node.val);
                dfs(node.right);
                dfs(node.left);
            } else {
                dfs(node.left);
                dfs(node.right);
            }
        }
    }
	
	public static void main(String[] args) {
		Solution solution = new Solution();
    }
}
