package com.potato.study.leetcodecn.p01874.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1874. 两个数组的最小乘积和
 *
 * 给定两个长度相等的数组a和b，它们的乘积和为数组中所有的a[i] * b[i]之和，其中0 <= i < a.length。

 比如a = [1,2,3,4]，b = [5,2,3,1]时，它们的乘积和为1*5 + 2*2 + 3*3 + 4*1 = 22
 现有两个长度都为n的数组nums1和nums2，你可以以任意顺序排序nums1，请返回它们的最小乘积和。

 示例 1:

 输入: nums1 = [5,3,4,2], nums2 = [4,2,2,5]
 输出: 40
 解释: 将 num1 重新排列为 [3,5,4,2] 后，可由 [3,5,4,2] 和 [4,2,2,5] 得到最小乘积和 3*4 + 5*2 + 4*2 + 2*5 = 40。
 示例 2:

 输入: nums1 = [2,1,4,5,7], nums2 = [3,2,4,8,6]
 输出: 65
 解释: 将 num1 重新排列为 [5,7,4,1,2] 后，可由 [5,7,4,1,2] 和 [3,2,4,8,6] 得到最小乘积和 5*3 + 7*2 + 4*4 + 1*8 + 2*6 = 65。
  

 提示:

 n == nums1.length == nums2.length
 1 <= n <= 105
 1 <= nums1[i], nums2[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimize-product-sum-of-two-arrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minProductSum(int[] nums1, int[] nums2) {
        // 是不是 最小 * 最大就行
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int sum = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            sum += (nums1[i] * nums2[n-i-1]);
        }
        return sum;
    }
}
