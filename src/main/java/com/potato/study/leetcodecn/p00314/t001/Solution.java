package com.potato.study.leetcodecn.p00314.t001;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.TreeNodeUtil;

/**
 * 314. 二叉树的垂直遍历
 *
 * 给你一个二叉树的根结点，返回其结点按 垂直方向（从上到下，逐列）遍历的结果。
 *
 * 如果两个结点在同一行和列，那么顺序则为 从左到右。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 示例 2：
 *
 *
 * 输入：root = [3,9,8,4,0,1,7]
 * 输出：[[4],[9],[3,0,1],[8],[7]]
 * 示例 3：
 *
 *
 * 输入：root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * 输出：[[4],[9,5],[3,0,1],[8,2],[7]]
 *  
 *
 * 提示：
 *
 * 树中结点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-vertical-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, List<Integer>> listMap = new TreeMap<>();
        Queue<TreeNodeContext> queue = new LinkedList<>();
        TreeNodeContext nodeContext = new TreeNodeContext();
        nodeContext.treeNode = root;
        nodeContext.key = 0;
        queue.add(nodeContext);
        while (!queue.isEmpty()) {
            TreeNodeContext poll = queue.poll();
            int key = poll.key;
            TreeNode current = poll.treeNode;
            List<Integer> orDefault = listMap.getOrDefault(key, new ArrayList<>());
            orDefault.add(current.val);
            listMap.put(key, orDefault);
            // 孩子
            if (current.left != null) {
                TreeNodeContext node = new TreeNodeContext();
                node.treeNode = current.left;
                node.key = key - 1;
                queue.add(node);
            }
            if (current.right != null) {
                TreeNodeContext node = new TreeNodeContext();
                node.treeNode = current.right;
                node.key = key + 1;
                queue.add(node);
            }
        }
        return new ArrayList<>(listMap.values());
    }

    class TreeNodeContext {
        public TreeNode treeNode;
        public int key;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[3,9,20,null,null,15,7]";
        TreeNode root = TreeNodeUtil.generateTreeByArrayString(input);
        List<List<Integer>> list = solution.verticalOrder(root);
        System.out.println(list);
    }
}
