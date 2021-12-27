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

    private int mod = 1_000_000_007;
    // 372
    public int superPow(int a, int[] b) {
        int current = 1;
        for (int i = 0; i < b.length; i++) {
            if (i > 0) {
                current = pow(current, 10);
            }
            current *= pow(a, b[i]);
        }
        return current;
    }

    public int pow(int a, int b) {
        int tmp = a % mod;
        for (int i = 0; i < b; i++) {
            tmp *= (a % mod);
        }
        return tmp;
    }

}
