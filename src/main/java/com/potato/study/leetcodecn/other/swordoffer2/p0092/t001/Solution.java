package com.potato.study.leetcodecn.other.swordoffer2.p0092.t001;

import org.junit.Assert;

/**
 * 剑指 Offer II 092. 翻转字符
 *
 * 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是 单调递增 的。
 *
 * 我们给出一个由字符 '0' 和 '1' 组成的字符串 s，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
 *
 * 返回使 s 单调递增 的最小翻转次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "00110"
 * 输出：1
 * 解释：我们翻转最后一位得到 00111.
 * 示例 2：
 *
 * 输入：s = "010110"
 * 输出：2
 * 解释：我们翻转得到 011111，或者是 000111。
 * 示例 3：
 *
 * 输入：s = "00011000"
 * 输出：2
 * 解释：我们翻转得到 00000000。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 20000
 * s 中只包含字符 '0' 和 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cyJERH
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minFlipsMonoIncr(String s) {
        // 第一个1 之后 0的个数 和 1的个数 （包括第一个1）
        char[] chars = s.toCharArray();
        // 将1反转成0的个数 i位置就反转
        int n = s.length();
        int[] oneCounts = new int[n+1];
        for (int i = 1; i <= n; i++) {
            // 如果 i-1是 1 进行反转
            if (chars[i-1] == '1') {
                oneCounts[i] = oneCounts[i-1] + 1;
            } else {
                oneCounts[i] = oneCounts[i-1];
            }
        }
        // 第一个1之后 0的个数 和1的个数
        int[] zeroCounts = new int[n+1];
        for (int i = n-1; i >= 0; i--) {
            if (chars[i] == '0') {
                zeroCounts[i] = zeroCounts[i+1] + 1;
            } else {
                zeroCounts[i] = zeroCounts[i+1];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            min = Math.min(min, zeroCounts[i] + oneCounts[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0101100011";
        int i = solution.minFlipsMonoIncr(s);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
