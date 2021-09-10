package com.potato.study.leetcodecn.p00337.t001;

import java.util.*;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 337. 打家劫舍 III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private Map<TreeNode, Integer> selectMap = new HashMap();
    private Map<TreeNode, Integer> unSelectMap = new HashMap();
    private int max;


    public int rob(TreeNode root) {
        this.selectMap = new HashMap();
        this.unSelectMap = new HashMap();
        this.max = 0;
        robEach(root);
        return max;
    }


    public void robEach(TreeNode root) {
        // 后续遍历孩子
        if (root == null) {
            return;
        }
        if (root.left != null) {
            robEach(root.left);
        }
        if (root.right != null) {
            robEach(root.right);
        }
        // 对于当前节点 选择当前节点 等于 当前值 + 不选两个孩子的值的和
        int selectValue = root.val + unSelectMap.getOrDefault(root.left, 0)
                + unSelectMap.getOrDefault(root.right, 0);
        selectMap.put(root, selectValue);
        // 不选当前节点 等于 max孩子选不选的和
        int unSelectValue = Math.max(selectMap.getOrDefault(root.left, 0), unSelectMap.getOrDefault(root.left, 0))
                + Math.max(selectMap.getOrDefault(root.right, 0), unSelectMap.getOrDefault(root.right, 0));
        unSelectMap.put(root, unSelectValue);
        max = Math.max(max, selectValue);
        max = Math.max(max, unSelectValue);
    }


//    class RobTreeNode {
//        public RobTreeNode left;
//        public RobTreeNode right;
//        public int robThisVal;
//        public int notRobThisVal;
//        public TreeNode root;
//    }
//
//    // 337
//    public int rob(TreeNode root) {
//        int max = 0;
//        if (root == null) {
//            return max;
//        }
//        Queue<RobTreeNode> queue = new LinkedList<>();
//        RobTreeNode robRoot = new RobTreeNode();
//        robRoot.robThisVal = root.val;
//        robRoot.robThisVal = 0;
//        robRoot.root = root;
//        max = root.val;
//        queue.add(robRoot);
//        while (!queue.isEmpty()) {
//            RobTreeNode poll = queue.poll();
//            max = Math.max(max, poll.robThisVal);
//            max = Math.max(max, poll.notRobThisVal);
//
//            TreeNode pollRoot = poll.root;
//            if (pollRoot.left != null) {
//                RobTreeNode robRootLeft = new RobTreeNode();
//                robRootLeft.root = pollRoot.left;
//                poll.left = robRootLeft;
//                robRootLeft.robThisVal = poll.notRobThisVal + pollRoot.left.val;
//                robRootLeft.notRobThisVal = Math.max(poll.notRobThisVal, poll.robThisVal);
//                max = Math.max(max, robRootLeft.robThisVal);
//                max = Math.max(max, robRootLeft.notRobThisVal);
//            }
//
//            if (pollRoot.right != null) {
//                RobTreeNode robRootRight = new RobTreeNode();
//                poll.right = robRootRight;
//                robRootRight.root = pollRoot.right;
//                robRootRight.robThisVal = poll.notRobThisVal + pollRoot.right.val;
//                robRootRight.notRobThisVal = Math.max(poll.notRobThisVal, poll.robThisVal);
//                max = Math.max(max, robRootRight.robThisVal);
//                max = Math.max(max, robRootRight.notRobThisVal);
//            }
//        }
//        return max;
//    }
}
