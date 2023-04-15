package com.potato.study.leetcodecn.p00689.t001;

/**
 * 689. 三个无重叠子数组的最大和
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）最大的子数组，并返回这三个子数组。

 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。

  

 示例 1：

 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 输出：[0,3,5]
 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 示例 2：

 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 输出：[0,2,4]
  

 提示：

 1 <= nums.length <= 2 * 104
 1 <= nums[i] < 216
 1 <= k <= floor(nums.length / 3)

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // 维护3个k个大小的窗口
        int max1 = 0;
        int startIndex1 = -1;
        int max2 = 0;
        int startIndex21 = -1;
        int startIndex22 = -1;

        int max3 = 0;
        int startIndex31 = -1;
        int startIndex32 = -1;
        int startIndex33 = -1;


        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;



        for (int i = 0; i < nums.length - 2 * k; i++) {
            // 直接累加
            sum1 += nums[i];
            sum2 += nums[i+k];
            sum3 += nums[i+k*2];
            // 修改窗口大小
            if (i >= k) {
                sum1 -= nums[i-k];
                sum2 -= nums[i];
                sum3 -= nums[i+k];
            }
            // 判断是否窗口已经是 k个了
            if (i >= k-1) {
                if (sum1 > max1) {
                    max1 = sum1;
                    startIndex1 = i + 1 - k;
                }

                if (max1 + sum2 > max2) {
                    max2 = sum2 + max1;
                    startIndex21 = startIndex1;
                    startIndex22 = i + k + 1 - k;
                }

                if (max2 + sum3 > max3) {
                    max3 = max2 + sum3;

                    startIndex31 = startIndex21;
                    startIndex32 = startIndex22;
                    startIndex33 = i+k*2+1 - k;
                }
            }
        }
        return new int[] {startIndex31, startIndex32, startIndex33};
    }
}
