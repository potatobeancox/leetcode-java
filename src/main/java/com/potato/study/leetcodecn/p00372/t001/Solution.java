package com.potato.study.leetcodecn.p00372.t001;

import org.junit.Assert;

/**
 * 372. 超级次方
 *
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。

  

 示例 1：

 输入：a = 2, b = [3]
 输出：8
 示例 2：

 输入：a = 2, b = [1,0]
 输出：1024
 示例 3：

 输入：a = 1, b = [4,3,3,8,5,2]
 输出：1
 示例 4：

 输入：a = 2147483647, b = [2,0,0]
 输出：1198
  

 提示：

 1 <= a <= 231 - 1
 1 <= b.length <= 2000
 0 <= b[i] <= 9
 b 不含前导 0

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/super-pow
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int mod = 1337;

    /**
     * (a * b) % p = (a % p * b % p) % p （3）
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        long current = 1;
        for (int i = 0; i < b.length; i++) {
            if (i > 0) {
                current = pow(current, 10);
            }
            // (a * b) % p = (a % p * b % p) % p （3）
            current *= pow(a, b[i]);
        }
        return (int)(current % mod);
    }

    /**
     * 计算 a^b 对1337 取余数
     * a ^ b % p = ((a % p)^b) % p
     * @param a
     * @param b
     * @return
     */
    public int pow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long tmp = a % mod;
        for (int i = 0; i < b - 1; i++) {
            tmp *= (a % mod);
            tmp %= mod;
        }
        return (int)(tmp);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = 2;
        int[] b = new int[] {
                3
        };
        int i = solution.superPow(a, b);
        System.out.println(i);
        Assert.assertEquals(8, i);


        a = 2;
        b = new int[] {1, 0};
        i = solution.superPow(a, b);
        System.out.println(i);
        Assert.assertEquals(1024, i);

        a = 2147483647;
        b = new int[] {2, 0, 0};
        i = solution.superPow(a, b);
        System.out.println(i);
        Assert.assertEquals(1198, i);
    }
}
