package com.potato.study.leetcodecn.other.swordoffer.p0010.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

 F(0) = 0,   F(1) = 1
 F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

  

 示例 1：

 输入：n = 2
 输出：1
 示例 2：

 输入：n = 5
 输出：5
  

 提示：

 0 <= n <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long f1 = 0;
        long f2 = 1;
        long f = -1;
        for (int i = 2; i <= n; i++) {
            f = f1 % 1000000007 + f2 % 1000000007;
            f1 = f2 % 1000000007;
            f2 = f % 1000000007;
            f %= 1000000007;
        }
        return (int)f;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int fib = solution.fib(2);
        System.out.println(fib);
        Assert.assertEquals(1,fib);
        fib = solution.fib(5);
        System.out.println(fib);
        Assert.assertEquals(5,fib);

//        fib = solution.fib();
//        System.out.println(fib);
//        Assert.assertEquals(5,fib);
        System.out.println("---");

        for (int i = 0; i < 50; i++) {
            System.out.println(solution.fib(i));
        }
    }

}
