package com.potato.study.leetcode.p0559;

import com.potato.study.leetcode.domain.node.val.children.Node;

/**
 * 
 * @author liuzhao11
 * 
 *         559. Maximum Depth of N-ary Tree
 * 
 *         Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example, given a 3-ary tree:






We should return its max depth, which is 3.



Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
 * 
 * 
 *         思路：
 *
 *       
 *          
 */
public class Solution {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.children == null || root.children.size() == 0) {
            return 1;
        }
        int maxHeight = 0;
        for (Node ele : root.children) {
            int h = maxDepth(ele);
            if (maxHeight < h) {
                maxHeight = h;
            }
        }
        return maxHeight + 1;
    }
	
	
	public static void main(String[] args) {
		Solution solution = new Solution();

    }
}
