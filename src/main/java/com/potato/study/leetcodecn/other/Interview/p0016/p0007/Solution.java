package com.potato.study.leetcodecn.other.Interview.p0016.p0007;


/**
 * 面试题 16.07. 最大数值
 *
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。

 示例：

 输入： a = 1, b = 2
 输出： 2
 *
 */
public class Solution {


    /**
     *  c = a-b
     *  c 无符号 右移动 31 位置 得到k （1 或者 0 ）
     *  k = 1 时 返回 b
     *  k = 0 时 返回 a
     *  （1-k） * a + b * k
     *
     * @param a
     * @param b
     * @return
     */
    public int maximum(int a, int b) {
        long c = (long)a - b;
        long k = (c >>> 63);
        return (int)((1-k) * a + b * k);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = 2147483647;
        int b = -2147483648;
        int maximum = solution.maximum(a, b);
        System.out.println(maximum);


    }
}
