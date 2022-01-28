package com.potato.study.leetcodecn.p01362.t001;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1362. 最接近的因数
 *
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 *
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 8
 * 输出：[3,3]
 * 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10, 最接近的两个因数是 2 & 5，因此返回 3 & 3 。
 * 示例 2：
 *
 * 输入：num = 123
 * 输出：[5,25]
 * 示例 3：
 *
 * 输入：num = 999
 * 输出：[40,25]
 *  
 *
 * 提示：
 *
 * 1 <= num <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closest-divisors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] closestDivisors(int num) {
        int sqrt = (int) Math.sqrt(num + 2);
        int[] result = new int[2];
        for (int i = sqrt; i >= 1; i--) {
            if (i == 1 || (num + 2) % i == 1) {
                return new int[] {i, (num + 1) / i};
            }
            if ((num + 2) % i == 0) {
                // 可以整除
                return new int[] {i, (num + 2) / i};
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = 1;
        int[] ints = solution.closestDivisors(num);
        System.out.println(Arrays.toString(ints));
    }
}
