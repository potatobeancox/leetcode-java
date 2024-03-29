package com.potato.study.leetcodecn.p01969.t001;

import org.junit.Assert;

/**
 * 1969. 数组元素的最小非零乘积
 *
 给你一个正整数 p 。你有一个下标从 1 开始的数组 nums ，这个数组包含范围 [1, 2p - 1] 内所有整数的二进制形式（两端都 包含）。你可以进行以下操作 任意 次：

 从 nums 中选择两个元素 x 和 y  。
 选择 x 中的一位与 y 对应位置的位交换。对应位置指的是两个整数 相同位置 的二进制位。
 比方说，如果 x = 1101 且 y = 0011 ，交换右边数起第 2 位后，我们得到 x = 1111 和 y = 0001 。

 请你算出进行以上操作 任意次 以后，nums 能得到的 最小非零 乘积。将乘积对 109 + 7 取余 后返回。

 注意：答案应为取余 之前 的最小值。



 示例 1：

 输入：p = 1
 输出：1
 解释：nums = [1] 。
 只有一个元素，所以乘积为该元素。
 示例 2：

 输入：p = 2
 输出：6
 解释：nums = [01, 10, 11] 。
 所有交换要么使乘积变为 0 ，要么乘积与初始乘积相同。
 所以，数组乘积 1 * 2 * 3 = 6 已经是最小值。
 示例 3：

 输入：p = 3
 输出：1512
 解释：nums = [001, 010, 011, 100, 101, 110, 111]
 - 第一次操作中，我们交换第二个和第五个元素最左边的数位。
 - 结果数组为 [001, 110, 011, 100, 001, 110, 111] 。
 - 第二次操作中，我们交换第三个和第四个元素中间的数位。
 - 结果数组为 [001, 110, 001, 110, 001, 110, 111] 。
 数组乘积 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512 是最小乘积。


 提示：

 1 <= p <= 60
 *
 */
public class Solution {

    // 1969
    public int minNonZeroProduct(int p) {
        // max 就是  2^P - 1
        int mod = 1_000_000_000 + 7;
        long max = (long) (Math.pow(2, p)) - 1;
        return (int) ((quickPow(max-1, max/2) * (max % mod)) % mod);
    }

    /**
     * 快速幂
     * @param di
     * @param x 指数
     * @return
     */
    private long quickPow(long di, long x) {
        if (x == 0) {
            return 1;
        }
        int mod = 1_000_000_000 + 7;
        long small = quickPow(di, x / 2) % mod;
        long res = (small * small) % mod;
        if (x % 2 == 1) {
            return (res * (di % mod)) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int p = 1;
        int i = solution.minNonZeroProduct(p);
        System.out.println(i);
        Assert.assertEquals(1, i);



        p = 2;
        i = solution.minNonZeroProduct(p);
        System.out.println(i);
        Assert.assertEquals(6, i);


        p = 30;
        i = solution.minNonZeroProduct(p);
        System.out.println(i);
        Assert.assertEquals(945196305, i);


        p = 34;
        i = solution.minNonZeroProduct(p);
        System.out.println(i);
        Assert.assertEquals(640964173, i);
    }

}
