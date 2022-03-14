package com.potato.study.leetcodecn.other.Interview.p0005.p0006;


import org.junit.Assert;


/**
 * 面试题 05.06. 整数转换
 *
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。

 示例1:

 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 输出：2
 示例2:

 输入：A = 1，B = 2
 输出：2
 提示:

 A，B范围在[-2147483648, 2147483647]之间

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/convert-integer-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    public int convertInteger(int a, int b) {
        int i = a ^ b;
        return Integer.bitCount(i);
    }
}
