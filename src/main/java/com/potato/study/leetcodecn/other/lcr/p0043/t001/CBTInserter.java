package com.potato.study.leetcodecn.other.lcr.p0043.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer II 043. 往完全二叉树添加节点
 *
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的根节点。
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
 *
 * 注意：本题与主站 919 题相同： https://leetcode-cn.com/problems/complete-binary-tree-inserter/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/NaqhDT
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CBTInserter {

    // 043
    private TreeNode root;
    private Deque<TreeNode> deque;


    // 双端队列 先bfs 如果少一个或者都少 放入队列中
    public CBTInserter(TreeNode root) {
        this.root = root;
        this.deque = new LinkedList<>();
        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            if (poll.left != null) {
                queue.add(poll.left);
            }

            if (poll.right != null) {
                queue.add(poll.right);
            }

            if (poll.left == null || poll.right ==null) {
                deque.add(poll);
            }
        }
    }

    /**
     * 找到 双端队列 head 里边 孩子不满的第一个节点 挂孩子 然后将新插入的放入队列中
     * @param v
     * @return
     */
    public int insert(int v) {
        // 说明 这个树就是空
        TreeNode treeNode = new TreeNode(v);
        this.deque.add(treeNode);
        if (deque.isEmpty()) {
            this.root = treeNode;
            // 没有父亲节点
            return -1;
        }
        TreeNode first = deque.peekFirst();
        if (first.left == null) {
            first.left = treeNode;
        } else if (first.right == null) {
            first.right = treeNode;
        } else {
            throw new RuntimeException("不可能到达的分支");
        }
        // 安置之后 看看 是否需要pop
        if (first.left != null && first.right != null) {
            // 都占满了
            deque.pollFirst();
        }
        return first.val;
    }


    /**
     * 返回 树的根节点
     * @return
     */
    public TreeNode get_root() {
        return this.root;
    }
}
