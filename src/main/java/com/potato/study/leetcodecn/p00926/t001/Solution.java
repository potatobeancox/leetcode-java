package com.potato.study.leetcodecn.p00926.t001;

import org.junit.Assert;

/**
 * 926. 将字符串翻转到单调递增
 *
 * 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是单调递增的。

 我们给出一个由字符 '0' 和 '1' 组成的字符串 S，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。

 返回使 S 单调递增的最小翻转次数。

  

 示例 1：

 输入："00110"
 输出：1
 解释：我们翻转最后一位得到 00111.
 示例 2：

 输入："010110"
 输出：2
 解释：我们翻转得到 011111，或者是 000111。
 示例 3：

 输入："00011000"
 输出：2
 解释：我们翻转得到 00000000。
  

 提示：

 1 <= S.length <= 20000
 S 中只包含字符 '0' 和 '1'

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/flip-string-to-monotone-increasing
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minFlipsMonoIncr(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 找到第一个1
        int index = 0;
        while (index < s.length() && s.charAt(index) == '0') {
            index++;
        }
        if (index >= s.length()) {
            return 0;
        }
        // 计算第一部分1 多少个
        int oneCount = 0;
        while (index < s.length() && s.charAt(index) == '1') {
            index++;
            oneCount++;
        }
        if (index >= s.length()) {
            return 0;
        }
        if (oneCount == 0) {
            // 没有1
            return 0;
        }
        // 计算 0 多少个
        int zeroCount = 0;
        while (index < s.length() && s.charAt(index) == '0') {
            index++;
            zeroCount++;
        }
        // 选小的
        if (zeroCount == 0) {
            return 0;
        }
        int convertCount = Math.min(oneCount, zeroCount);
        while (index < s.length()) {
            if (s.charAt(index) == '0') {
                convertCount++;
            }
            index++;
        }
        return convertCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0101100011";
        int i = solution.minFlipsMonoIncr(s);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }


}
