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


    /**
     * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
     * @param a
     * @param b
     * @return
     */
    public boolean isSubStructure(TreeNode a, TreeNode b) {
        // a 或者 b 是空树 直接返回false
        if (a == null || b == null) {
            return false;
        }
        // 判断 b是不是 a的子树 或者，递归找 b 是不是 a的左右孩子的子树
        return eachIsSubStructure(a, b) ||
                isSubStructure (a.left, b) || isSubStructure(a.right, b);
    }

    /**
     * 递归判断 b 是否是 a 的子结构
     * 子结果区别于子树
     * @param a
     * @param b
     * @return
     */
    public boolean eachIsSubStructure(TreeNode a, TreeNode b) {
        // b 已经没有节点了 说明已经找到了
        if (b == null) {
            return true;
        }
        // 否则 a没有节点了 说明 a照完了 直接返回
        if (a == null) {
            return false;
        }
        // 不匹配 ab 都有结点
        if (a.val != b.val) {
            return false;
        }
        // 递归比较 ab的孩子
        return eachIsSubStructure(a.left, b.left) && eachIsSubStructure(a.right, b.right);
    }

}
