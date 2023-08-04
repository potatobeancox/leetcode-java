package com.potato.study.leetcodecn.other.lcr.p0002.t001;

/**
 * LCR 002. 二进制求和
 *
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *  
 *
 * 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/JFETK5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    /**
     * 相加
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int len = Math.max(a.length(), b.length()) + 1;
        int[] res = new int[len];
        int index1 = a.length() - 1;
        int index2 = b.length() - 1;
        for (int i = len - 1; i >= 0; i--) {
            if (index1 >= 0 && index2 >= 0) {
                res[i] = (a.charAt(index1) - '0') + (b.charAt(index2) - '0');
                index1--;
                index2--;
            } else if (index1 >= 0){
                res[i] = (a.charAt(index1) - '0');
                index1--;
            } else if (index2 >= 0) {
                res[i] = (b.charAt(index2) - '0');
                index2--;
            } else {
                break;
            }
        }
        // 遍历 res 如果是 2 往前进位
        for (int i = len - 1; i >= 0; i--) {
            if (res[i] > 1) {
                res[i-1]++;
                res[i] -= 2;
            }
        }
        StringBuilder builder = new StringBuilder();
        if (res[0] == 1) {
            builder.append(1);
        }
        for (int i = 1; i < len; i++) {
            builder.append(res[i]);
        }
        return builder.toString();
    }
}
