package com.potato.study.leetcodecn.p00161.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 161. 相隔为 1 的编辑距离
 *
 * 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false 。
 *
 * 字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
 *
 * 往 s 中插入 恰好一个 字符得到 t
 * 从 s 中删除 恰好一个 字符得到 t
 * 在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
 *  
 *
 * 示例 1：
 *
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 *
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 *  
 *
 * 提示:
 *
 * 0 <= s.length, t.length <= 104
 * s 和 t 由小写字母，大写字母和数字组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/one-edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public boolean isOneEditDistance(String s, String t) {
        // 交换 是 s 一直为小的那个
        if (null == s && t == null) {
            return false;
        } else if (null == s) {
            return t.length() == 1;
        } else if (null == t) {
            return s.length() == 1;
        }
        // 都不是空 先比较 大小交换
        if (s.length() > t.length()) {
            String tmp = t;
            t = s;
            s = tmp;
        }
        if (t.length() - s.length() > 1) {
            return false;
        }
        // 找到 第一个不同的位置
        int len1 = s.length();
        int len2 = t.length();
        int diffIndex = -1;
        for (int i = 0; i < len1; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diffIndex = i;
                break;
            }
        }
        // 没有不同 判断是不是 有长度不一样 如果没有不同的地方且相同 返回 false 否则 返回 true
        if (diffIndex == -1) {
            return len1 != len2;
        }
        // 有不同的字母， 如果两个字符串 相同 和不同 两种情况
        if (len1 == len2) {
            // 之后所有位置必须都一样
            for (int i = diffIndex + 1; i < len1; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return false;
                }
            }
            return true;
        } else {
            // 长度相同情况 往后找 不同情况 短的不变 长的往后挪一个看看 能不能匹配
            int index1 = diffIndex;
            int index2 = diffIndex + 1;

            while (index1 < s.length() && index2 < t.length()) {

                if (s.charAt(index1) != t.charAt(index2)) {
                    return false;
                }

                index1++;
                index2++;
            }

            return true;
        }
    }
}
