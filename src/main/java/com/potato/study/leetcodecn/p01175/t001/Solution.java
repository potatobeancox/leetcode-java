package com.potato.study.leetcodecn.p01175.t001;


import org.junit.Assert;

/**
 * 1175. 质数排列
 *
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 *
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 *
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 * 示例 2：
 *
 * 输入：n = 100
 * 输出：682289015
 *  
 *
 * 提示：
 *
 * 1 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/prime-arrangements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param n
     * @return
     */
    public int numPrimeArrangements(int n) {
        if (n == 1) {
            return 1;
        }
        // 计算 小于等于 n的有多少个 k质数 有多少个非质数 n-k 那么就是一种排列
        int[] primes = new int[] {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,
                73,79,83,89,97};
        int len = primes.length;
        for (int i = 0; i < primes.length; i++) {
            if (n < primes[i]) {
                len = i;
                break;
            }
        }
        int otherLen = n - len;
        // (a×b) mod c=(a mod c * b mod c) mod c
        long mod1 = getRemind(len);
        long mod2 = getRemind(otherLen);
        // k! * (n-k)!
        int mod = (int)Math.pow(10, 9) + 7;
        return (int) ((mod1 % mod * mod2 % mod) % mod);
    }

    private long getRemind(int n) {
        long tmp = 1;
        long mod = (long)Math.pow(10, 9) + 7;
        for (long i = 2; i <= n; i++) {
            tmp = (tmp % mod * i % mod) % mod;
        }
        return tmp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 100;
        int i = solution.numPrimeArrangements(n);
        System.out.println(i);
        Assert.assertEquals(682289015, i);



        n = 2;
        i = solution.numPrimeArrangements(n);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}

