package com.potato.study.leetcodecn.p02385.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 * 2385. 感染二叉树需要的总时间
 *
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 *
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 *
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,5,3,null,4,10,6,9,2], start = 3
 * 输出：4
 * 解释：节点按以下过程被感染：
 * - 第 0 分钟：节点 3
 * - 第 1 分钟：节点 1、10、6
 * - 第 2 分钟：节点5
 * - 第 3 分钟：节点 4
 * - 第 4 分钟：节点 9 和 2
 * 感染整棵树需要 4 分钟，所以返回 4 。
 * 示例 2：
 *
 *
 * 输入：root = [1], start = 1
 * 输出：0
 * 解释：第 0 分钟，树中唯一一个节点处于感染状态，返回 0 。
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 105] 内
 * 1 <= Node.val <= 105
 * 每个节点的值 互不相同
 * 树中必定存在值为 start 的节点
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int amountOfTime(TreeNode root, int start) {
        // 从root开始遍历树，使用一个 map list 存 parent关系
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        dfsBuildMap(root, parentMap, queue, start);
        if (queue.isEmpty()) {
            return 0;
        }
        int level = -1;
        Set<TreeNode> visit = new HashSet<>();
        visit.addAll(queue);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                // 传染
                TreeNode treeNode = parentMap.get(poll);
                // 父亲
                if (parentMap.containsKey(poll) && !visit.contains(treeNode)) {
                    queue.add(treeNode);
                    visit.add(treeNode);
                }
                // 两个孩子
                if (poll.left != null && !visit.contains(poll.left)) {
                    queue.add(poll.left);
                    visit.add(poll.left);
                }
                if (poll.right != null && !visit.contains(poll.right)) {
                    queue.add(poll.right);
                    visit.add(poll.right);
                }
            }
            level++;
        }
        return level;
    }

    private void dfsBuildMap(TreeNode root, Map<TreeNode, TreeNode> parentMap, Queue<TreeNode> queue, int start) {
        if (root == null) {
            return;
        }

        if (root.val == start) {
            queue.add(root);
        }

        if (root.left != null) {
            parentMap.put(root.left, root);
            dfsBuildMap(root.left, parentMap, queue, start);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            dfsBuildMap(root.right, parentMap, queue, start);
        }
    }

}
