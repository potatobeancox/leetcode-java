package com.potato.study.leetcodecn.other.Interview.p0004.p0002;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 面试题 04.02. 最小高度树
 *
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。

 示例:
 给定有序数组: [-10,-3,0,5,9],

 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

 0
 / \
 -3   9
 /   /
 -10  5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 递归生成 bst树
     * @param nums
     * @return      当前对应 nums 的 bst
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 终止条件 当前 数组 nums 长度如果为 1 那么 直接返回 树节点
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        // 数组长度 len 取 len / 2 节点 将数组 分成2部分 然后 递归生成每个部分对应的 子树
        int currentIndex = nums.length / 2;
        TreeNode node = new TreeNode(nums[currentIndex]);
        // 设置 head 的 left 和right 节点 对应的子树
        int[][] arr = getSplitArrayByIndex(nums, currentIndex);
        node.left = sortedArrayToBST(arr[0]);
        node.right = sortedArrayToBST(arr[1]);
        return node;
    }

    /**
     * 将 nums 数组 按照 index 分成2个 子数组
     * @param nums
     * @param index
     * @return
     */
    private int[][] getSplitArrayByIndex (int[] nums, int index) {
        int[][] res = new int[2][];
        int[] start = new int[index];
        int[] end = new int[nums.length - index - 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < index) {
                start[i] = nums[i];
            } else if (i > index) {
                end[i - index - 1] = nums[i];
            }
        }
        res[0] = start;
        res[1] = end;
        return res;
    }
}
