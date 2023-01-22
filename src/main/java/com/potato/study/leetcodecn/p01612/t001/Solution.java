package com.potato.study.leetcodecn.p01612.t001;

import java.util.LinkedList;
import java.util.Queue;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.domain.node.val.left.right.ch.Node;

/**
 * 1612. 检查两棵二叉表达式树是否等价
 *
 * 二叉表达式树是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。 叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符。在本题中，我们只考虑 '+' 运算符（即加法）。
 *
 * 给定两棵二叉表达式树的根节点 root1 和 root2 。如果两棵二叉表达式树等价，返回 true ，否则返回 false 。
 *
 * 当两棵二叉搜索树中的变量取任意值，分别求得的值都相等时，我们称这两棵二叉表达式树是等价的。
 *
 *  
 *
 * 示例 1:
 *
 * 输入： root1 = [x], root2 = [x]
 * 输出： true
 * 示例 2:
 *
 *
 *
 * 输入：root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
 * 输出：true
 * 解释：a + (b + c) == (b + c) + a
 * 示例 3:
 *
 *
 *
 * 输入： root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
 * 输出： false
 * 解释： a + (b + c) != (b + d) + a
 *  
 *
 * 提示：
 *
 * 两棵树中的节点个数相等，且节点个数为范围 [1, 4999] 内的奇数。
 * Node.val 是 '+' 或小写英文字母。
 * 给定的树保证是有效的二叉表达式树。
 *  
 *
 * 进阶：当你的答案需同时支持 '-' 运算符（减法）时，你该如何修改你的答案
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-two-expression-trees-are-equivalent
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean checkEquivalence(Node root1, Node root2) {
        // 两个数组 记录 存在 的个数 dfs 先找到 叶子 再往深处找
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        dfs(root1, count1);
        dfs(root2, count2);

        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Node root, int[] count) {
        if (null == root) {
            return;
        }
        char val = root.val;
        if ('+' == val) {
            dfs(root.left, count);
            dfs(root.right, count);
        } else {
            count[val - 'a']++;
        }
    }
}
