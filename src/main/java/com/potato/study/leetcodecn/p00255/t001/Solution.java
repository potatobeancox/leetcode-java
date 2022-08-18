package com.potato.study.leetcodecn.p00255.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * 255. 验证前序遍历序列二叉搜索树
 *
 * 给定一个 无重复元素 的整数数组 preorder ， 如果它是以二叉搜索树的先序遍历排列 ，返回 true 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: preorder = [5,2,1,3,6]
 * 输出: true
 * 示例 2：
 *
 * 输入: preorder = [5,2,6,1,3]
 * 输出: false
 *  
 *
 * 提示:
 *
 * 1 <= preorder.length <= 104
 * 1 <= preorder[i] <= 104
 * preorder 中 无重复元素
 *  
 *
 * 进阶：您能否使用恒定的空间复杂度来完成此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/verify-preorder-sequence-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean verifyPreorder(int[] preorder) {
        // 每一个位置作为 根
        for (int i = 0; i < preorder.length; i++) {
            int rootVal = preorder[i];
            boolean findBigger = false;
            for (int j = i + 1; j < preorder.length; j++) {
                // 对之后的位置 都进行判断是否都大于 root
                if (findBigger) {
                    // 已经找到了 比root大的节点
                    if (preorder[j] < rootVal) {
                        return false;
                    }
                } else {
                    // 从这个位置开始 找到第一个大于他的位置
                    if (preorder[j] > rootVal) {
                        findBigger = true;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                5,2,6,1,3
        };
        boolean b = solution.verifyPreorder(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);
    }

}
