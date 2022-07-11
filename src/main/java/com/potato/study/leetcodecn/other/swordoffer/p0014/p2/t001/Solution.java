package com.potato.study.leetcodecn.other.swordoffer.p0014.p2.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 14- II. 剪绳子 II
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *  
 *
 * 提示：
 *
 * 2 <= n <= 1000
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int cuttingRope(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int i = n / 3;
        int remind = n % 3;
        int mod = 1_000_000_000 + 7;
        if (remind == 0) {
            return (int) pow(3, i);
        } else if (remind == 2) {
            return (int) ((pow(3, i) * 2) % mod);
        } else {
            // remind == 1
            return (int) (((pow(3, i-1) % mod) * (4 % mod)) % mod);
        }
    }


    /**
     * 快速求幂法
     * @param num
     * @param times
     * @return
     */
    private long pow(long num, long times) {
        if (times == 0) {
            return 1;
        }
        if (times == 1) {
            return num;
        }
        int mod = 1_000_000_000 + 7;
        if (times % 2 == 1) {
            long res = pow(num, times / 2);
            return ((res % mod) * (res % mod) % mod * num) % mod;
        } else {
            long res = pow(num, times / 2);
            return ((res % mod) * (res % mod) % mod);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int i = solution.cuttingRope(n);
        System.out.println(i);
        Assert.assertEquals(1, i);

        n = 10;
        i = solution.cuttingRope(n);
        System.out.println(i);
        Assert.assertEquals(36, i);

        n = 120;
        i = solution.cuttingRope(n);
        System.out.println(i);
        Assert.assertEquals(953271190, i);


        n = 127;
        i = solution.cuttingRope(n);
        System.out.println(i);
        Assert.assertEquals(439254203, i);
    }

}
