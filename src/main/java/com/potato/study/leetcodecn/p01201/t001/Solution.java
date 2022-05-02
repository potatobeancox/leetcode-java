package com.potato.study.leetcodecn.p01201.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

/**
 * 1201. 丑数 III
 *
 * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数。
 *
 * 丑数是可以被 a 或 b 或 c 整除的 正整数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3, a = 2, b = 3, c = 5
 * 输出：4
 * 解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。
 * 示例 2：
 *
 * 输入：n = 4, a = 2, b = 3, c = 4
 * 输出：6
 * 解释：丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。
 * 示例 3：
 *
 * 输入：n = 5, a = 2, b = 11, c = 13
 * 输出：10
 * 解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
 * 示例 4：
 *
 * 输入：n = 1000000000, a = 2, b = 217983653, c = 336916467
 * 输出：1999999984
 *  
 *
 * 提示：
 *
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * 本题结果在 [1, 2 * 10^9] 的范围内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int nthUglyNumber(int n, int a, int b, int c) {
        // 利用二分查找 到 小于 当前值 中有多少个 被abc 整除的 有n个数
        long left = 1;
        long right = 2 * 1_000_000_000;

        // 计算 ab ac bc abc 的最小公倍数
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm(a, bc);
        long res = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            // 计算 小于等于 mid的数字中有多少可以被 abc 整除的数字
            long count = mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc;
            // count 等于n
            if (count >= n) {
                // mid 有可能还稍微大一点
                if (count == n) {
                    res = mid;
                }
                right = mid - 1;
            } else {
                // count < n 说明 mid 小了
                left = mid + 1;
            }
        }
        return (int) res;
    }


    /**
     * 最小公倍数
     * @param a
     * @param b
     * @return
     */
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    /**
     * 最大公约数
     * @param a
     * @param b
     * @return
     */
    private long gcd(long a, long b) {
        while (a % b != 0) {
            long remind = a % b;
            a = b;
            b = remind;
        }
        return b;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int a = 2;
        int b = 3;
        int c = 5;
        int i = solution.nthUglyNumber(n, a, b, c);
        System.out.println(i);
        Assert.assertEquals(4, i);


        n = 4;
        a = 2;
        b = 3;
        c = 4;
        i = solution.nthUglyNumber(n, a, b, c);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }
}



