package com.potato.study.leetcodecn.p00246.t001;

/**
 * 246. 中心对称数
 *
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num = "69"
 * 输出: true
 * 示例 2:
 *
 * 输入: num = "88"
 * 输出: true
 * 示例 3:
 *
 * 输入: num = "962"
 * 输出: false
 * 示例 4：
 *
 * 输入：num = "1"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strobogrammatic-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isStrobogrammatic(String num) {
        StringBuilder builder = new StringBuilder();
        StringBuilder reverse = builder.append(num).reverse();
        char[] chars = reverse.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '3' || ch == '4' || ch == '7' || ch == '2' || ch == '5') {
                return false;
            }
            if (ch == '6') {
                chars[i] = '9';
            } else if (ch == '9') {
                chars[i] = '6';
            }
        }
        String newNum = new String(chars);
        return num.equals(newNum);
    }

}
