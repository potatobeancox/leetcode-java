package com.potato.study.leetcodecn.p00510.t001;


import org.junit.Assert;

import com.potato.study.leetcode.domain.node.val.left.right.parent.Node;

/**
 * 510. 二叉搜索树中的中序后继 II
 *
 * 给定一棵二叉搜索树和其中的一个节点 node ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * 一个节点 node 的中序后继是键值比 node.val 大所有的节点中键值最小的那个。
 *
 * 你可以直接访问结点，但无法直接访问树。每个节点都会有其父节点的引用。节点 Node 定义如下：
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：tree = [2,1,3], node = 1
 * 输出：2
 * 解析：1 的中序后继结点是 2 。注意节点和返回值都是 Node 类型的。
 * 示例 2：
 *
 *
 *
 * 输入：tree = [5,3,6,2,4,null,null,1], node = 6
 * 输出：null
 * 解析：该结点没有中序后继，因此返回 null 。
 * 示例 3：
 *
 *
 *
 * 输入：tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 15
 * 输出：17
 * 示例 4：
 *
 *
 *
 * 输入：tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 13
 * 输出：15
 * 示例 5：
 *
 * 输入：tree = [0], node = 0
 * 输出：null
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各结点的值均保证唯一。
 *  
 *
 * 进阶：你能否在不访问任何结点的值的情况下解决问题?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/inorder-successor-in-bst-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public Node inorderSuccessor(Node node) {
        // node 中序列遍历的后继
        if (node == null) {
            return null;
        }
        // 1.要么是 右边孩子的最左孩子
        if (node.right != null) {
            Node p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 2.要么 是当前节点是 pare的左孩子时的 父亲 循环往上找
        if (node.parent != null) {
            Node parent = node.parent;
            Node child = node;
            while (parent != null && parent.left != child) {
                child = parent;
                parent = parent.parent;
            }
            return parent;
        }
        // 没有后继
        return null;
    }

}
