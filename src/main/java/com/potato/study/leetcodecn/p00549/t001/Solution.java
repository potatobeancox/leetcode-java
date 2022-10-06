package com.potato.study.leetcodecn.p00549.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 549. 二叉树中最长的连续序列
 *
 * 给定二叉树的根 root ，返回树中最长连续路径的长度。
 * 连续路径是路径中相邻节点的值相差 1 的路径。此路径可以是增加或减少。
 *
 * 例如， [1,2,3,4] 和 [4,3,2,1] 都被认为有效，但路径 [1,2,4,3] 无效。
 * 另一方面，路径可以是子-父-子顺序，不一定是父子顺序。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [1,2,3]
 * 输出: 2
 * 解释: 最长的连续路径是 [1, 2] 或者 [2, 1]。
 *  
 *
 * 示例 2:
 *
 *
 *
 * 输入: root = [2,1,3]
 * 输出: 3
 * 解释: 最长的连续路径是 [1, 2, 3] 或者 [3, 2, 1]。
 *  
 *
 * 提示：
 *
 * 树上所有节点的值都在 [1, 3 * 104] 范围内。
 * -3 * 104 <= Node.val <= 3 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param root
     * @return
     */
    public int longestConsecutive(TreeNode root) {
        // dfs 记录 当前预期的下一个是增大还是见效
        return -1;
    }

}
