package com.potato.study.leetcodecn.other.lcp.p0034.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * LCP 34. 二叉树染色
 *
 * 小扣有一个根结点为 root 的二叉树模型，初始所有结点均为白色，可以用蓝色染料给模型结点染色，模型的每个结点有一个 val 价值。小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 k 个，求所有染成蓝色的结点价值总和最大是多少？

 示例 1：

 输入：root = [5,2,3,4], k = 2

 输出：12

 解释：结点 5、3、4 染成蓝色，获得最大的价值 5+3+4=12


 示例 2：

 输入：root = [4,1,3,9,null,null,2], k = 2

 输出：16

 解释：结点 4、3、9 染成蓝色，获得最大的价值 4+3+9=16


 提示：

 1 <= k <= 10
 1 <= val <= 10000
 1 <= 结点数量 <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/er-cha-shu-ran-se-UGC
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/er-cha-shu-ran-se-UGC/solution/dp-by-hu-li-hu-wai-rr0c/
     * @param root
     * @param k
     * @return
     */
    public int maxValue(TreeNode root, int k) {
        int[] dp = getMaxValue(root, k);
        int max = Integer.MIN_VALUE;
        for (int value : dp) {
            max = Math.max(max, value);
        }
        if (max == Integer.MIN_VALUE) {
            return -1;
        }
        return max;
    }

    /**
     * 当前节点最多有 k个相邻 染色的情况下 最大能拿到多少value
     * @param root
     * @param k
     * @return
     */
    private int[] getMaxValue(TreeNode root, int k) {
        int[] dp = new int[k+1];
        if (root == null) {
            return dp;
        }
        // 孩子的
        int[] leftChild = getMaxValue(root.left, k);
        int[] rightChild = getMaxValue(root.right, k);
        // 当前 点不染色
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < k + 1; i++) {
            max1 = Math.max(max1, leftChild[i]);
            max2 = Math.max(max2, rightChild[i]);
        }
        dp[0] = max1 + max2;
        // dp 1 开始
        for (int i = 1; i <= k; i++) {
            // 左边染色 j 个 右边 i-1-j个
            for (int j = 0; j < i; j++) {
                // 当前点
                dp[i] = Math.max(dp[i], root.val + leftChild[j] + rightChild[i-1-j]);
            }
        }
        return dp;
    }


}
