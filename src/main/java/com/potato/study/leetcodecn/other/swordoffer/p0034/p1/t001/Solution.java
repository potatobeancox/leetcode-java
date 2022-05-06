package com.potato.study.leetcodecn.other.swordoffer.p0034.p1.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

 叶子节点 是指没有子节点的节点。

  

 示例 1：



 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 输出：[[5,4,11,2],[5,8,4,5]]
 示例 2：



 输入：root = [1,2,3], targetSum = 5
 输出：[]
 示例 3：

 输入：root = [1,2], targetSum = 0
 输出：[]
  

 提示：

 树中节点总数在范围 [0, 5000] 内
 -1000 <= Node.val <= 1000
 -1000 <= targetSum <= 1000
 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root, 0, target, new ArrayList<>());
        return result;
    }

    private void dfs(List<List<Integer>> result, TreeNode current, int currentSum, int target,
                     List<Integer> currentResult) {
        if (current == null) {
            return;
        }
        int temp = currentSum + current.val;
        List<Integer> list = new ArrayList<>(currentResult);
        list.add(current.val);

        if (current.left == null && current.right == null) {
            if (temp == target) {
                result.add(list);
            }
            return;
        }

        if (current.left != null) {
            dfs(result, current.left, temp, target, list);
        }

        if (current.right != null) {
            dfs(result, current.right, temp, target, list);
        }

    }
}
