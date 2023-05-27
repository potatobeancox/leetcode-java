package com.potato.study.leetcodecn.p02689.t001;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 2689. Extract Kth Character From The Rope Tree
 *
 * You are given the root of a binary tree and an integer k. Besides the left and right children, every node of this tree has two other properties, a string node.val containing only lowercase English letters (possibly empty) and a non-negative integer node.len. There are two types of nodes in this tree:
 *
 * Leaf: These nodes have no children, node.len = 0, and node.val is some non-empty string.
 * Internal: These nodes have at least one child (also at most two children), node.len > 0, and node.val is an empty string.
 * The tree described above is called a Rope binary tree. Now we define S[node] recursively as follows:
 *
 * If node is some leaf node, S[node] = node.val,
 * Otherwise if node is some internal node, S[node] = concat(S[node.left], S[node.right]).
 *  Return k-th character of the string S[root].
 *
 * Note: If s and p are two strings, concat(s, p) is a string obtained by concatenating p to s. For example, concat("ab", "zz") = "abzz".
 *
 *  
 *
 * Example 1:
 *
 * Input: root = [10,4,"abcpoe","g","rta"], k = 6
 * Output: "b"
 * Explanation: In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
 * You can see that S[root] = concat(concat("g", "rta"), "abcpoe") = "grtaabcpoe". So S[root][5], which represents 6th character of it, is equal to "b".
 *
 *
 * Example 2:
 *
 * Input: root = [12,6,6,"abc","efg","hij","klm"], k = 3
 * Output: "c"
 * Explanation: In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
 * You can see that S[root] = concat(concat("abc", "efg"), concat("hij", "klm")) = "abcefghijklm". So S[root][2], which represents the 3rd character of it, is equal to "c".
 *
 *
 * Example 3:
 *
 * Input: root = ["ropetree"], k = 8
 * Output: "e"
 * Explanation: In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
 * You can see that S[root] = "ropetree". So S[root][7], which represents 8th character of it, is equal to "e".
 *
 *
 *  
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 103]
 * node.val contains only lowercase English letters
 * 0 <= node.val.length <= 50
 * 0 <= node.len <= 104
 * for leaf nodes, node.len = 0 and node.val is non-empty
 * for internal nodes, node.len > 0 and node.val is empty
 * 1 <= k <= S[root].length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/extract-kth-character-from-the-rope-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    // 2689
    public char getKthCharacter(RopeTreeNode root, int k) {
        String res = buildTotalString(root);
        return res.charAt(k-1);
    }

    private String buildTotalString(RopeTreeNode root) {
        // 叶子
        if (root.len == 0) {
            return root.val;
        }
        // 非叶子
        if (root.left == null) {
            return buildTotalString(root.right);
        } else if (root.right == null) {
            return buildTotalString(root.left);
        }
        return buildTotalString(root.left) + buildTotalString(root.right);
    }
}


class RopeTreeNode {
    int len;
    String val;
    RopeTreeNode left;
    RopeTreeNode right;
    RopeTreeNode() {}
    RopeTreeNode(String val) {
        this.len = 0;
        this.val = val;
    }
    RopeTreeNode(int len) {
        this.len = len;
        this.val = "";
    }
    RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
        this.len = len;
        this.val = "";
        this.left = left;
        this.right = right;
    }
}
