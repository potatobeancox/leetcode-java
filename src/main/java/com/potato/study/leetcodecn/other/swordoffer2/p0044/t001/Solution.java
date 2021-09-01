package com.potato.study.leetcodecn.other.swordoffer2.p0044.t001;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值
 *
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 *  
 *
 * 示例1：
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 * 示例2：
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 *           1
 *          / \
 *         2   3
 * 示例3：
 *
 * 输入: root = [1]
 * 输出: [1]
 * 示例4：
 *
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 *            1
 *             \
 *              2
 * 示例5：
 *
 * 输入: root = []
 * 输出: []
 *  
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 *  
 *
 * 注意：本题与主站 515 题相同： https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hPov7L
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // offer II 044
    public List<Integer> largestValues(TreeNode root) {
        // 层序遍历 记录每个层第一个单元 至少有一个 节点
        int max = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> maxList = new ArrayList<>();
        if (root == null) {
            return maxList;
        }
        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            max = Integer.MIN_VALUE;
            for (int i = 0; i < levelLength; i++) {
                TreeNode poll = queue.poll();
                max = Math.max(max, poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            maxList.add(max);
        }
        return maxList;
    }
}
