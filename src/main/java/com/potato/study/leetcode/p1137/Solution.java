package com.potato.study.leetcode.p1137;


/**
 * 
 * @author liuzhao11
 * 
 * 	1137. N-th Tribonacci Number
 *  
 *
 * The Tribonacci sequence Tn is defined as follows:

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.



Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537


Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 *         
 *      思路：
 *
 *
 *      https://leetcode-cn.com/problems/n-th-tribonacci-number/solution/di-n-ge-tai-bo-na-qi-shu-by-leetcode/
 *
 *
 */
public class Solution {

    public int tribonacci(int n) {
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }

        int tmp, x = 0, y = 1, z = 1;
        for (int i = 3; i <= n; ++i) {
            tmp = x + y + z;
            x = y;
            y = z;
            z = tmp;
        }
        return z;
    }


	
	public static void main(String[] args) {
//		Solution solution = new Solution();
//
//        int[] arr = new int[]{6,2,4};
//        int count = solution.mctFromLeafValues(arr);
//        System.out.println(count);
//        Assert.assertEquals(32, count);
    }
}
