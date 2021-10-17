package com.potato.study.leetcodecn.p00919.t001;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import com.potato.study.leetcode.domain.TreeNode;
import com.sun.jmx.snmp.SnmpNull;

/**
 * 919. 完全二叉树插入器
 *
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *  
 *
 * 示例 1：
 *
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 * 示例 2：
 *
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *  
 *
 * 提示：
 *
 * 最初给定的树是完全二叉树，且包含 1 到 1000 个节点。
 * 每个测试用例最多调用 CBTInserter.insert  操作 10000 次。
 * 给定节点或插入节点的每个值都在 0 到 5000 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/complete-binary-tree-inserter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CBTInserter {

    private TreeNode root;

    private Queue<TreeNode> deque;

    public CBTInserter(TreeNode root) {
        this.root = root;
        // 队列记录 还可以添加的点 层序遍历 获取最终点
        this.deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left == null || poll.right == null) {
                deque.add(poll);
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    /**
     * 每次插入就是往后进行插入
     * @param val
     * @return
     */
    public int insert(int val) {
        // 之前是空的
        if (deque.isEmpty()) {
            TreeNode root = new TreeNode(val);
            deque.add(root);
            this.root = root;
            return -1;
        }
        TreeNode peek = deque.peek();
        TreeNode node = new TreeNode(val);
        if (peek.left == null) {
            peek.left = node;
        } else {
            peek.right = node;
        }
        if (peek.left != null && peek.right != null) {
            deque.poll();
        }
        deque.add(node);
        return peek.val;
    }

    public TreeNode get_root() {
        return root;
    }

}
