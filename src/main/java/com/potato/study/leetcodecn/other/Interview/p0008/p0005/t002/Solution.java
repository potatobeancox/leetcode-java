package com.potato.study.leetcodecn.other.Interview.p0008.p0005.t002;


/**
 * 面试题 08.05. 递归乘法
 *
 * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。

 示例1:

 输入：A = 1, B = 10
 输出：10
 示例2:

 输入：A = 3, B = 4
 输出：12
 提示:

 保证乘法范围不会溢出

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/recursive-mulitply-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 倍增 递归实现
     * ，
     * 内部思路就是 b变成二进制写法 比如101
     * 说明 有 4个 a + 1个a
     *
     * @param a
     * @param b 增了多少倍
     * @return
     */
    public int multiply(int a, int b) {
        return multiply(a, b, 0);
    }


    /**
     * 递归计算倍增结果
     * @param a
     * @param b
     * @param temp
     * @return
     */
    private int multiply(int a, int b, int temp) {
        if (b == 0) {
            return temp;
        }
        if ((b & 1)== 1) {
            temp += a;
        }
        b = b >>> 1;
        a = a << 1;
        return multiply(a, b, temp);
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int n = 3;
//        int res = solution.waysToStep(n);
//        System.out.println(res);
//        Assert.assertEquals(4, res);
//
//        n = 5;
//        res = solution.waysToStep(n);
//        System.out.println(res);
//        Assert.assertEquals(13, res);
    }
}
