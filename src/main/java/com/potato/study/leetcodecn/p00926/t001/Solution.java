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
        // 以zero 结尾需要翻转的次数
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int tmpZeroCount;
            int tmpOneCount;
            if (s.charAt(i) == '0') {
                tmpZeroCount = zeroCount;
                tmpOneCount = Math.min(zeroCount, oneCount) + 1;
            } else {
                // 当前1结尾
                tmpZeroCount = zeroCount + 1;
                // 之前按照 01 结尾哪个短就是哪个
                tmpOneCount = Math.min(zeroCount, oneCount);

            }
            zeroCount = tmpZeroCount;
            oneCount = tmpOneCount;
        }
        return Math.min(zeroCount, oneCount);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0101100011";
        int i = solution.minFlipsMonoIncr(s);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }


}
