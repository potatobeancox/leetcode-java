package com.potato.study.leetcode.p0898;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuzhao11
 *
 * 898. Bitwise ORs of Subarrays
 *
 * We have an array A of non-negative integers.

For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].

Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)



Example 1:

Input: [0]
Output: 1
Explanation:
There is only one possible result: 0.
Example 2:

Input: [1,1,2]
Output: 3
Explanation:
The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
Example 3:

Input: [1,2,4]
Output: 6
Explanation:
The possible results are 1, 2, 3, 4, 6, and 7.


Note:

1 <= A.length <= 50000
0 <= A[i] <= 10^9

 * 题目含义：
 *
 *
 *
 * 思路：
 *
 *      https://leetcode-cn.com/problems/bitwise-ors-of-subarrays/solution/zi-shu-zu-an-wei-huo-cao-zuo-by-leetcode/
 *
 *
 */
public class Solution {

    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);
        // 遍历 arr 对于每个元素
        for (int x: arr) {
            Set<Integer> temp = new HashSet();
            // 新结果放在里边
            for (int y: cur) {
                temp.add(x | y);
            }
            // 当前数字加入集合
            temp.add(x);
            cur = temp;
            ans.addAll(cur);
        }
        return ans.size();
    }




    public static void main(String[] args) {
		Solution solution = new Solution();
        int[] arr = new int[]{1,1,2};
        int res = solution.subarrayBitwiseORs(arr);
        System.out.println(res);
        Assert.assertEquals(3, res);
    }
}
