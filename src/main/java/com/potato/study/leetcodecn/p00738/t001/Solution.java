package com.potato.study.leetcodecn.p00738.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

/**
 * 738. 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 如果这个数字本身就是单调递增的 直接返回
     * 否则找到第一个 递减的点 i  ， i变成 9 i之前的点 -- ，如果影响了 之前的点，那么这个点也变成9了 再往前搞
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        // 将 n 转换数字 string 数组
        String s = String.valueOf(n);
        if (s.length() == 1) {
            return n;
        }
        // 找到找到第一个 递减的位置 i 大于 i+ 1
        char[] numChar = s.toCharArray();
        boolean isUp = true;
        int targetIndex = -1;
        int i;
        for (i = 0; i + 1 < numChar.length; i++) {
            // 找到第一个 i位置 大于 i+ 1的位置
            if (numChar[i] > numChar[i+1]) {
                isUp = false;
                break;
            }
        }
        // 已经是升序 直接返回
        if (isUp) {
            return n;
        }
        // 从 i位置 往前 --
        numChar[i]--;
        while (i > 0 && numChar[i-1] > numChar[i]) {
            numChar[i-1]--;
            i--;
        }
        int j = i + 1;
        // 将 j 之后的字符都换成 9
        while (j < numChar.length) {
            numChar[j] = '9';
            j++;
        }
        return Integer.parseInt(new String(numChar));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        int res = solution.monotoneIncreasingDigits(n);
        System.out.println(res);
        Assert.assertEquals(9, res);
        // 1234
        n = 1234;
        res = solution.monotoneIncreasingDigits(n);
        System.out.println(res);
        Assert.assertEquals(1234, res);

        n = 332;
        res = solution.monotoneIncreasingDigits(n);
        System.out.println(res);
        Assert.assertEquals(299, res);

        n = 120;
        res = solution.monotoneIncreasingDigits(n);
        System.out.println(res);
        Assert.assertEquals(119, res);
    }
}
