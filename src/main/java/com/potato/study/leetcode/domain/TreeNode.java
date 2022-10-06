package com.potato.study.leetcode.domain;

/**
 * 树节点
 * @author Administrator
 *
 */
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int x) {
		val = x;
	}

    @Override
    public String toString() {
        return "[" + val + ']';
    }
}
