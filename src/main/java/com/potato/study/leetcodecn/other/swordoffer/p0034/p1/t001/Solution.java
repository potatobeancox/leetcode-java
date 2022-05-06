package com.potato.study.leetcodecn.other.swordoffer.p0034.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *  
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 *
 * 提示：
 *
 * 数组长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {











    /**
     * 分治 直接遍历找到左右子树并判定他们是够也合法
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        // 当前 没有节点或者当前只有一个节点 那就是合法
        return verifyPostorderEach(postorder, 0, postorder.length - 1);
    }

    /**
     *
     * @param postorder
     * @param left      左边界
     * @param right     右边界
     * @return
     */
    private boolean verifyPostorderEach(int[] postorder, int left, int right) {
        // 当前只有 1个点 或者当前 没有点那就是已经ok了 也就是终止条件
        if (left >= right) {
            return true;
        }
        int val = postorder[right];
        int leftEnd = left;
        while (leftEnd < right && postorder[leftEnd] < val) {
            leftEnd++;
        }
        int rightEnd = leftEnd;
        while (rightEnd < right && postorder[rightEnd] > val) {
            rightEnd ++;
        }
        // 没有办法 弄成 左边都比当前值小 右边都比当前值大的情况
        if (rightEnd != right) {
            return false;
        }
        return verifyPostorderEach(postorder, left, leftEnd - 1)
                && verifyPostorderEach(postorder, leftEnd, right - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,6,3,2,5
        };
        boolean b = solution.verifyPostorder(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);


        arr = new int[] {
                1,3,2,6,5
        };
        b = solution.verifyPostorder(arr);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
