package com.potato.study.leetcodecn.p01071.t001;

import org.junit.Assert;

/**
 * 1071. 字符串的最大公因子
 *
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *  
 *
 * 提示：
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        String target = str1 + str2;
        if (!target.equals(str2 + str1)) {
            return "";
        }
        // 最大公约数
        int gcd = gcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }


    /**
     * 求最大公约数
     * @param x
     * @param y
     * @return
     */
    private int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }
        return gcd(y % x, x);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str1 = "ABCABC";
        String str2 = "ABC";
        String s = solution.gcdOfStrings(str1, str2);
        System.out.println(s);
        Assert.assertEquals("ABC", s);
    }



//    public static void main(String[] args) {
//        Solution solution = new Solution();
//
//        String str = "bcabc";
//        String s = solution.smallestSubsequence(str);
//        System.out.println(s);
//        Assert.assertEquals("abc", s);
//
//        str = "cbacdcbc";
//        s = solution.smallestSubsequence(str);
//        System.out.println(s);
//        Assert.assertEquals("acdb", s);
//
//
//        str = "cbaacabcaaccaacababa";
//        s = solution.smallestSubsequence(str);
//        System.out.println(s);
//        Assert.assertEquals("abc", s);
//
//    }
}
