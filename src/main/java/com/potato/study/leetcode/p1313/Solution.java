package com.potato.study.leetcode.p1313;


import java.util.Arrays;

/**
 * 
 * @author liuzhao11
 * 
 * 	1313. Decompress Run-Length Encoded List
 *  
 *
We are given a list nums of integers representing a list compressed with run-length encoding.

Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are freq elements with value val concatenated in a sublist. Concatenate all the sublists from left to right to generate the decompressed list.

Return the decompressed list.



Example 1:

Input: nums = [1,2,3,4]
Output: [2,4,4,4]
Explanation: The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
At the end the concatenation [2] + [4,4,4] is [2,4,4,4].
Example 2:

Input: nums = [1,1,2,3]
Output: [1,3,3]


Constraints:

2 <= nums.length <= 100
nums.length % 2 == 0
1 <= nums[i] <= 100
 *         
 *         思路：
 *
 *          给定 压缩后的数组
 *
 *
 *

 *
 */
public class Solution {

    public int[] decompressRLElist(int[] nums) {
        // 计算 返回数组大小
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }
        // 设置数字
        int[] res = new int[len];
        int startIndex = 0;
        for (int i = 0; i < nums.length; i += 2) {
            Arrays.fill(res, startIndex, startIndex + nums[i], nums[i+1]);
            startIndex += nums[i];
        }
        return res;
    }

	public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = new int[]{1,2,3,4};
        int[] step = solution.decompressRLElist(nums);
        System.out.println(Arrays.toString(step)); // 2,4,4,4

        nums = new int[]{1,1,2,3};
        step = solution.decompressRLElist(nums);
        System.out.println(Arrays.toString(step)); // 1,3,3
    }
}
