package com.potato.study.leetcodecn.p02505.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2505. Bitwise OR of All Subsequence Sums
 *
 * Given an integer array nums, return the value of the bitwise OR of the sum of all possible subsequences in the array.

 A subsequence is a sequence that can be derived from another sequence by removing zero or more elements without changing the order of the remaining elements.

  

 Example 1:

 Input: nums = [2,1,0,3]
 Output: 7
 Explanation: All possible subsequence sums that we can have are: 0, 1, 2, 3, 4, 5, 6.
 And we have 0 OR 1 OR 2 OR 3 OR 4 OR 5 OR 6 = 7, so we return 7.
 Example 2:

 Input: nums = [0,0,0]
 Output: 0
 Explanation: 0 is the only possible subsequence sum we can have, so we return 0.
  

 Constraints:

 1 <= nums.length <= 105
 0 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/bitwise-or-of-all-subsequence-sums
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long subsequenceSumOr(int[] nums) {
        // 正常 找到所有 子序列，求和 再求 or的值
        return -1;
    }

}
