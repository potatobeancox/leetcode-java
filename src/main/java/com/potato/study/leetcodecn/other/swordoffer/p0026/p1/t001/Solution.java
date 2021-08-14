package com.potato.study.leetcodecn.other.swordoffer.p0026.p1.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (null == a) {
            return false;
        }
        return eachIsSubStructure(a, b);
    }

    /**
     * 递归判断 b 是否是 a 的子结构
     * 子结果区别于子树
     * @param a
     * @param b
     * @return
     */
    public boolean eachIsSubStructure(TreeNode a, TreeNode b) {
        // 终止条件 空树 直接返回false
        if (b == null) {
            return false;
        }
        if (a == null && b != null) {
            return false;
        }



        //  ab 都非空 ab 不相等
        if (a.val != b.val) {
            if (a.left != null && a.right != null) {
                return eachIsSubStructure(a.left, b) || eachIsSubStructure(a.right, b);
            } else if (a.left != null) {
                return eachIsSubStructure(a.left, b);
            } else if (a.right != null) {
                return eachIsSubStructure(a.right, b);
            } else {
                return false;
            }

        }
        // 继续进行递归判断 相等了 我擦
        return eachIsSubStructure(a.left, b.left) && eachIsSubStructure(a.right, b.right);
    }

}
