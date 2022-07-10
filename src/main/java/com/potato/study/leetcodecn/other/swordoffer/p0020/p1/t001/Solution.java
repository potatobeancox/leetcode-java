package com.potato.study.leetcodecn.other.swordoffer.p0020.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 20. 表示数值的字符串
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 *
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *  
 *
 * 示例 1：
 *
 * 输入：s = "0"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 *
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "    .1  "
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isNumber(String s) {
        // 去掉 若干空格
        s = s.trim();
        s = s.toLowerCase();
        // 是否存在e
        int index = s.indexOf("e");
        int lastIndex = s.lastIndexOf("e");
        if (index != lastIndex) {
            return false;
        }
        String[] split = s.split("e");
        if (index < 0) {
            // 不存在判断是否 小数 或者 整数
            return isInteger(split[0]) || isDecimal(split[0]);
        } else {
            if (split.length != 2) {
                return false;
            }
            return (isInteger(split[0]) || isDecimal(split[0])) && isInteger(split[1]);
        }
    }

    /**
     * 是否小数
     *
     * @param numStr
     * @return
     */
    private boolean isDecimal(String numStr) {
        if (numStr.length() == 0) {
            return false;
        }
        // （可选）一个符号字符（'+' 或 '-'）
        char charAt = numStr.charAt(0);
        int numStartIndex = 0;
        if (charAt == '+' || charAt == '-') {
            numStartIndex = 1;
        }
        String substring = numStr.substring(numStartIndex);
        // 下述格式之一：
        int index = substring.indexOf('.');
        int lastIndex = substring.lastIndexOf('.');
        // 没有.
        if (index < 0 || lastIndex < 0) {
            return false;
        }
        if (index != lastIndex) {
            return false;
        }
        if (index == 0) {
            // 一个点 '.' ，后面跟着至少一位数字
            int digitCount = 0;
            for (int i = 1; i < substring.length(); i++) {
                if (!Character.isDigit(substring.charAt(i))) {
                    return false;
                }
                digitCount++;
            }
            return digitCount > 0;
        } else {
            if (lastIndex == substring.length() - 1) {
                // 至少一位数字，后面跟着一个点 '.'
                int digitCount = 0;
                for (int i = 0; i < lastIndex; i++) {
                    if (!Character.isDigit(substring.charAt(i))) {
                        return false;
                    }
                    digitCount++;
                }
                return digitCount > 0;
            } else {
                // 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
                String[] split = substring.split("\\.");
                int digitCount = 0;
                for (int i = 0; i < split[0].length(); i++) {
                    if (!Character.isDigit(split[0].charAt(i))) {
                        return false;
                    }
                    digitCount++;
                }
                if (digitCount == 0) {
                    return false;
                }
                digitCount = 0;
                for (int i = 0; i < split[1].length(); i++) {
                    if (!Character.isDigit(split[1].charAt(i))) {
                        return false;
                    }
                    digitCount++;
                }
                if (digitCount == 0) {
                    return false;
                }
                return true;
            }
        }
    }

    /**
     * 判断是否整数
     * @param numStr
     * @return
     */
    private boolean isInteger(String numStr) {
        if (numStr.length() == 0) {
            return false;
        }
        // （可选）一个符号字符（'+' 或 '-'）
        char charAt = numStr.charAt(0);
        int numStartIndex = 0;
        if (charAt == '+' || charAt == '-') {
            numStartIndex = 1;
        }
        String substring = numStr.substring(numStartIndex);
        // 至少一位数字
        for (int i = 0; i < substring.length(); i++) {
            if (!Character.isDigit(substring.charAt(i))) {
                return false;
            }
        }
        return substring.length() > 0;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = ".";
        boolean number = solution.isNumber(s);
        System.out.println(number);
        Assert.assertEquals(false, number);


        s = "0e";
        number = solution.isNumber(s);
        System.out.println(number);
        Assert.assertEquals(false, number);
    }
}
