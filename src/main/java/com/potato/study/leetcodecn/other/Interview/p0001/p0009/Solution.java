package com.potato.study.leetcodecn.other.Interview.p0001.p0009;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 01.09. 字符串轮转
 *
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。

 示例1:

 输入：s1 = "waterbottle", s2 = "erbottlewat"
 输出：True
 示例2:

 输入：s1 = "aa", s2 = "aba"
 输出：False
 提示：

 字符串长度在[0, 100000]范围内。
 说明:

 你能只调用一次检查子串的方法吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/string-rotation-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (null == s1 || null == s2) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        // 从 s1 每个位置开始 往后遍历 s2.len 个位置
        for (int i = 0; i < s1.length(); i++) {
            boolean isValid = true;
            for (int j = 0; j < s2.length(); j++) {
                int index = (i + j) % s1.length();
                if (s1.charAt(index) != s2.charAt(j)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                return true;
            }
        }
        return false;
    }
}
