package com.potato.study.leetcodecn.p00663.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 663. 均匀树划分
 *
 * 给定一棵有 n 个结点的二叉树，你的任务是检查是否可以通过去掉树上的一条边将树分成两棵，且这两棵树结点之和相等。

 样例 1:

 输入:
 5
 / \
 10 10
 /  \
 2   3

 输出: True
 解释:
 5
 /
 10

 和: 15

 10
 /  \
 2    3

 和: 15
  

 样例 2:

 输入:
 1
 / \
 2  10
 /  \
 2   20

 输出: False
 解释: 无法通过移除一条树边将这棵树划分成结点之和相等的两棵子树。
  

 注释 :

 树上结点的权值范围 [-100000, 100000]。
 1 <= n <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/equal-tree-partition
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private boolean find;

    public boolean checkEqualTree(TreeNode root) {
        // 遍历一下 求 总得sum
        int totalSum = dfs(root);
        if (totalSum % 2 == 1 || totalSum % 2 == -1) {
            return false;
        }
        // 遍历 一遍看下 某个子树是不是 有 一般
        this.find = false;
        dfs(root, totalSum / 2, true);
        return this.find;
    }


    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int totalSum = root.val;
        if (root.left != null) {
            totalSum += dfs(root.left);
        }
        if (root.right != null) {
            totalSum += dfs(root.right);
        }
        return totalSum;
    }


    public int dfs(TreeNode root, int target, boolean isRoot) {
        if (root == null) {
            return 0;
        }
        if (this.find) {
            return 0;
        }
        int totalSum = root.val;
        if (root.left != null) {
            totalSum += dfs(root.left, target, false);
        }
        if (root.right != null) {
            totalSum += dfs(root.right, target, false);
        }

        if (totalSum == target && !isRoot) {
            this.find = true;
        }
        return totalSum;
    }


}
