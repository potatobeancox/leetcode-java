package com.potato.study.leetcodecn.p00801.t001;

import java.util.Arrays;

/**
 * 801. 使序列递增的最小交换次数
 *
 * 我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。

 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。

 数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。

 注意：

 用例保证可以实现操作。
  

 示例 1:

 输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 输出: 1
 解释:
 交换 A[3] 和 B[3] 后，两个数组如下:
 A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 两个数组均为严格递增的。
 示例 2:

 输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 输出: 1
  

 提示:

 2 <= nums1.length <= 105
 nums2.length == nums1.length
 0 <= nums1[i], nums2[i] <= 2 * 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/solution/shi-xu-lie-di-zeng-de-zui-xiao-jiao-huan-ux2y/
     * @param nums1
     * @param nums2
     * @return
     */
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // dp i 01 到位置 i 不交换或者交换 最大能 0表示 当前位置 不交换 最少多少次可以有序 1是当前i位置交换
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            // 看下当前满足的情况
            if (nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]) {
                // 当前位置不交换 一定是之前不交换
                dp[i][0] = Math.min(dp[i][0], dp[i-1][0]);
                // 当前位置必须交换 必须之前也是交换过
                dp[i][1] = Math.min(dp[i][1], dp[i-1][1] + 1);
            }

            if (nums1[i] > nums2[i-1] && nums2[i] > nums1[i-1]) {
                // i位置不进行交换 一定是 i-1交换过
                dp[i][0] = Math.min(dp[i][0], dp[i-1][1]);
                // i 位置进行交换 之前已经是没有交换过 才能对的上
                dp[i][1] = Math.min(dp[i][1], dp[i-1][0] + 1);
            }
        }
        return Math.min(dp[n-1][0], dp[n-1][1]);
    }
}
