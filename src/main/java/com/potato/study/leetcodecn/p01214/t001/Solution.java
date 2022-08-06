package com.potato.study.leetcodecn.p01214.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1214. 查找两棵二叉搜索树之和
 *
 * 给出两棵二叉搜索树的根节点 root1 和 root2 ，请你从两棵树中各找出一个节点，使得这两个节点的值之和等于目标值 Target。

 如果可以找到返回 True，否则返回 False。

  

 示例 1：



 输入：root1 = [2,1,4], root2 = [1,0,3], target = 5
 输出：true
 解释：2 加 3 和为 5 。
 示例 2：



 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 输出：false
  

 提示：

 每棵树上节点数在 [1, 5000] 范围内。
 -109 <= Node.val, target <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/two-sum-bsts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        // 中序遍历 root1 root2
        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        dfs(root2, list2);
        // 双指针 查找 对应 target
        int index1 = 0;
        int index2 = list2.size() - 1;
        while (index1 < list1.size() && index2 >= 0) {
            int temp = list1.get(index1) + list2.get(index2);
            if (temp == target) {
                return true;
            } else if (temp > target) {
                index2--;
            } else {
                index1++;
            }
        }
        return false;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            dfs(root.right, list);
        }
    }


}
