package com.potato.study.leetcodecn.other.swordoffer.p0054.p1.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点。

  

 示例 1:

 输入: root = [3,1,4,null,2], k = 1
 3
 / \
 1   4
 \
    2
 输出: 4
 示例 2:

 输入: root = [5,3,6,2,4,null,null,1], k = 3
 5
 / \
 3   6
 / \
 2   4
 /
 1
 输出: 4
  

 限制：

 1 ≤ k ≤ 二叉搜索树元素个数

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 中序排列 计算 返回len - k
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        inorderGetList(root, result);
        return result.get(result.size() - k);
    }

    /**
     * 中序 遍历 获取 最终结果
     * @param head
     * @param result
     */
    private void inorderGetList(TreeNode head, List<Integer> result) {
        if (head == null) {
            return;
        }
        inorderGetList(head.left, result);
        result.add(head.val);
        inorderGetList(head.right, result);
    }



//    public static void main(String[] args) {
//        Solution solution = new Solution();
//
//        int[] nums = new int[]{5,7,7,8,8,10};
//        int target = 8;
//        int num = solution.search(nums, target);
//        System.out.println(num);
//        Assert.assertEquals(2, num);
//
//        nums = new int[]{5,7,7,8,8,10};
//        target = 6;
//        num = solution.search(nums, target);
//        System.out.println(num);
//        Assert.assertEquals(0, num);
//    }

}
