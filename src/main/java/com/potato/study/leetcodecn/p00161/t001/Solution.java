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
        return false;
    }
}
