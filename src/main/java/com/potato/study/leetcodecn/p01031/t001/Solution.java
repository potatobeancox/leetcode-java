package com.potato.study.leetcodecn.p01031.t001;

import org.junit.Assert;


/**
 * 1031. 两个非重叠子数组的最大和
 *
 * 给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 L 和 M。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）

 从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) 并满足下列条件之一：

  

 0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或
 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
  

 示例 1：

 输入：A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 输出：20
 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
 示例 2：

 输入：A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 输出：29
 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
 示例 3：

 输入：A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 输出：31
 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
  

 提示：

 L >= 1
 M >= 1
 L + M <= A.length <= 1000
 0 <= A[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-sum-of-two-non-overlapping-subarrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // 计算前缀和
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        // 从 L + M位置开始 遍历前缀和 记录L+M中 最大的 L和最大的M 计算L在最后 和M在最后 的最大和的值
        int firstMax = prefixSum[firstLen-1];
        int secondMax = prefixSum[secondLen-1];
        int max = prefixSum[firstLen + secondLen - 1];
        int step = 1;
        for (int i = firstLen + secondLen; i < nums.length; i++) {
            firstMax = Math.max(firstMax, prefixSum[firstLen - 1 + step] - prefixSum[step-1]);
            secondMax = Math.max(secondMax, prefixSum[secondLen - 1 + step] - prefixSum[step-1]);
            max = Math.max(max, Math.max(
                    firstMax + prefixSum[i] - prefixSum[i-secondLen],
                    secondMax + prefixSum[i] - prefixSum[i-firstLen]));
            step++;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                0,6,5,2,2,5,1,9,4
        };
        int l = 1;
        int r = 2;
        int i = solution.maxSumTwoNoOverlap(arr, l, r);
        System.out.println(i);
        Assert.assertEquals(20, i);
    }
}
