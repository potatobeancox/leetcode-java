package com.potato.study.leetcodecn.other.Interview.p0017.p0001;


import org.junit.Assert;

/**
 * 面试题 17.01. 不用加号的加法
 *
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-without-plus-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 面试题 17.01
    public int add(int a, int b) {
        // 异或是不进位的 加法 用有& 获取进位情况
        int i = a & b;
        i <<= 1;
        int c = a ^ b;
        if (i == 0) {
            return c;
        }
        return add(i, c);
    }
}
