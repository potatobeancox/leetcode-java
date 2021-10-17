package com.potato.study.leetcodecn.p01318.t001;


import java.util.Arrays;

import org.junit.Assert;

/**
 * 1318. 或运算的最小翻转次数
 *
 * 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。
 *
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：a = 2, b = 6, c = 5
 * 输出：3
 * 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
 * 示例 2：
 *
 * 输入：a = 4, b = 2, c = 7
 * 输出：1
 * 示例 3：
 *
 * 输入：a = 1, b = 2, c = 3
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-flips-to-make-a-or-b-equal-to-c
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 直接模拟
     * int 32位
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        // 对 ab 和 c 分离 每个位置进行比较
        int operationCount = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1;
            int aBit = a & bit;
            int bBit = b & bit;
            int cBit = c & bit;
            if (cBit == 0) {
                if (aBit == 1 && bBit == 1) {
                    operationCount += 2;
                } else if (aBit == 1 || bBit == 1) {
                    operationCount++;
                }
            } else {
                // cBit == 1
                if (aBit == 0 && bBit == 0) {
                    operationCount ++;
                }
            }
            // convert
            a >>>= 1;
            b >>>= 1;
            c >>>= 1;
        }
        return operationCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = 2;
        int b = 6;
        int c = 5;
        int i = solution.minFlips(a, b, c);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }


}
