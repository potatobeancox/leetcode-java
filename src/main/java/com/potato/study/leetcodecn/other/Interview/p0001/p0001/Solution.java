package com.potato.study.leetcodecn.other.Interview.p0001.p0001;


import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 01.01. 判定字符是否唯一
 *
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

 示例 1：

 输入: s = "leetcode"
 输出: false
 示例 2：

 输入: s = "abc"
 输出: true
 限制：

 0 <= len(s) <= 100
 如果你不使用额外的数据结构，会很加分。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/is-unique-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 判断是不是 没有重复的
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        // 判断 set 中是不是有 ch
        for (char ch : astr.toCharArray()) {
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }
}
