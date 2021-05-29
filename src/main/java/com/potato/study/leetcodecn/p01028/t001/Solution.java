package com.potato.study.leetcodecn.p01028.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1028. 从先序遍历还原二叉树
 *
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 *
 *
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 *
 *
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 *
 * 提示：
 *
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 递归生成
     * @param traversal
     * @return
     */
    public TreeNode recoverFromPreorder(String traversal) {
        if (null == traversal || "".equals(traversal)) {
            return null;
        }
        // 遍历 traversal 生成 一个list LevelNode
        List<LevelNode> list = new ArrayList<>();
        int index = 0;
        while (index < traversal.length()) {
            char ch = traversal.charAt(index);
            int num = 0;
            int level = 0;
            if (Character.isDigit(ch)) {
                while (index < traversal.length()
                        && Character.isDigit(traversal.charAt(index))) {
                    num *= 10;
                    num += (ch - '0');
                    index++;
                }
            } else {
                // --
                while (index < traversal.length()
                        && traversal.charAt(index) == '-') {
                    index++;
                    level++;
                }
            }
            LevelNode levelNode = new LevelNode();
            levelNode.val = num;
            levelNode.level = level;
            list.add(levelNode);
        }
        // 递归求解
        return generateTree(list);
    }

    /**
     * 使用 list 生成树
     * @param list
     * @return
     */
    private TreeNode generateTree(List<LevelNode> list) {
        // 终止条件 list 为空 或者 list 只有一个节点
        if (null == list || list.size() == 0) {
            return null;
        }
        // 使用 list get 0 生成 这个树的头部
        LevelNode levelNode = list.get(0);
        TreeNode root = new TreeNode(levelNode.val);
        int level = levelNode.level;
        Integer leftStart = null;
        Integer rightStart = null;
        for (int i = 1; i < list.size(); i++) {
            LevelNode node = list.get(i);
            if (node.level == level + 1) {
                if (leftStart == null) {
                    leftStart = i;
                } else {
                    rightStart = i;
                }
            }
        }
        // 在list 中 找到 root level + 1的两个点，利用 这两个点 生成 左右孩子
        if (leftStart != null) {
            List<LevelNode> leftList = list.subList(leftStart, rightStart);
            root.left = generateTree(leftList);
        }
        if (rightStart != null) {
            List<LevelNode> rightList = list.subList(rightStart, list.size());
            root.right = generateTree(rightList);
        }
        return root;
    }

    class LevelNode {
        // 值
        public int val;
        // 层级
        public int level;
    }


    public static void main(String[] args) {
//        Solution solution = new Solution();
//        TreeNode treeNode = solution.recoverFromPreorder();

    }
}
