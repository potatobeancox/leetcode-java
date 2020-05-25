package com.potato.study.leetcode.p0918;

/**
 * 
 * @author liuzhao11
 * 
 * 	918. Maximum Sum Circular Subarray
 *  
 *      Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)



Example 1:

Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1


Note:

-30000 <= A[i] <= 30000
1 <= A.length <= 30000

 *         
 *         题目含义：
 *              环形数组 子数组的 最大可能和
 *         思路：
 *
 *          https://leetcode-cn.com/problems/maximum-sum-circular-subarray/solution/huan-xing-zi-shu-zu-de-zui-da-he-by-leetcode/
 *
 *
 *
 */
public class Solution {

    public int maxSubarraySumCircular(int[] arr) {
        int len = arr.length;

        int ans = arr[0], cur = arr[0];
        for (int i = 1; i < len; ++i) {
            cur = arr[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }

        // ans is the answer for 1-interval subarrays.
        // Now, let's consider all 2-interval subarrays.
        // For each i, we want to know
        // the maximum of sum(A[j:]) with j >= i+2

        // rightsums[i] = A[i] + A[i+1] + ... + A[N-1]
        int[] rightsums = new int[len];
        rightsums[len-1] = arr[len-1];
        for (int i = len-2; i >= 0; --i)
            rightsums[i] = rightsums[i+1] + arr[i];

        // maxright[i] = max_{j >= i} rightsums[j]
        int[] maxright = new int[len];
        maxright[len-1] = arr[len-1];
        for (int i = len-2; i >= 0; --i) {
            maxright[i] = Math.max(maxright[i+1], rightsums[i]);
        }

        int leftsum = 0;
        for (int i = 0; i < len-2; ++i) {
            leftsum += arr[i];
            ans = Math.max(ans, leftsum + maxright[i+2]);
        }
        return ans;
    }
}
