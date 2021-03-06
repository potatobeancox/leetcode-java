package com.potato.study.leetcodecn.p00653.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 653. 两数之和 IV - 输入 BST
 *
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

  

 示例 1：


 输入: root = [5,3,6,2,4,null,7], k = 9
 输出: true
 示例 2：


 输入: root = [5,3,6,2,4,null,7], k = 28
 输出: false
 示例 3：

 输入: root = [2,1,3], k = 4
 输出: true
 示例 4：

 输入: root = [2,1,3], k = 1
 输出: false
 示例 5：

 输入: root = [2,1,3], k = 3
 输出: true
  

 提示:

 二叉树的节点个数的范围是  [1, 104].
 -104 <= Node.val <= 104
 root 为二叉搜索树
 -105 <= k <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 我们说一个假定 如果两个数的和 中的一部分是当前节点 的val，那么另一部分val2肯定在set里边
     * 如果不在说明当前没有遍历到，就将当前 val 放set 中，等到遍历到val2时，自然就返回 true了
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> wholeSet = new HashSet<>();
        return hasFound(root, k , wholeSet);
    }


    /**
     *
     * @param root 当前节点
     * @param k 目标值
     * @param wholeSet 目前遍历到的值
     * @return
     */
    private boolean hasFound(TreeNode root, int k, Set<Integer> wholeSet) {
        if (root == null) {
            return false;
        }
        if (wholeSet.contains(k - root.val)) {
            return true;
        }
        wholeSet.add(root.val);
        return hasFound(root.left, k, wholeSet)
                || hasFound(root.right, k, wholeSet);
    }




}
