package com.potato.study.leetcodecn.other.Interview.p0001.p0005;


import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 01.05. 一次编辑
 *
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。

  

 示例 1:

 输入:
 first = "pale"
 second = "ple"
 输出: True
  

 示例 2:

 输入:
 first = "pales"
 second = "pal"
 输出: False

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/one-away-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    return first.substring(i+1).equals(second.substring(i+1));
                }
            }
        } else if (first.length() > second.length()) {
            for (int i = 0; i < first.length(); i++) {
                if (i == second.length()) {
                    return true;
                } else if (first.charAt(i) != second.charAt(i)) {
                    return first.substring(i+1).equals(second.substring(i));
                }
            }
        } else {
            for (int i = 0; i < second.length(); i++) {
                if (i == first.length()) {
                    return true;
                } else if (first.charAt(i) != second.charAt(i)) {
                    return first.substring(i).equals(second.substring(i+1));
                }
            }
        }
        return true;
    }

}
