package com.potato.study.leetcodecn.p00291.t001;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 291. 单词规律 II
 *
 * 给你一种规律 pattern 和一个字符串 s，请你判断 s 是否和 pattern 的规律相匹配。
 *
 * 如果存在单个字符到字符串的 双射映射 ，那么字符串 s 匹配 pattern ，即：如果pattern 中的每个字符都被它映射到的字符串替换，那么最终的字符串则为 s 。双射
 * 意味着映射双方一一对应，不会存在两个字符映射到同一个字符串，也不会存在一个字符分别映射到两个不同的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pattern = "abab", s = "redblueredblue"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "red"
 * 'b' -> "blue"
 * 示例 2：
 *
 * 输入：pattern = "aaaa", s = "asdasdasdasd"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "asd"
 * 示例 3：
 *
 * 输入：pattern = "aabb", s = "xyzabcxzyabc"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= pattern.length, s.length <= 20
 * pattern 和 s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-pattern-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/word-pattern-ii/solution/291-dan-ci-gui-lu-ii-by-klb/
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPatternMatch(String pattern, String s) {
        // 回溯 使用 map 记录 char word 关系 注意双射性质
        Map<Character, String> mapping = new HashMap<>();
        return backtrack(pattern, s, mapping);
    }

    private boolean backtrack(String pattern, String s, Map<Character, String> mapping) {
        // 终止条件 pattern 找完了 看下 s 是不是也找完了
        if (pattern == null || pattern.length() == 0) {
            return s == null || s.length() == 0;
        }
        // 如果当前 s 剩余长度超过 patteren 那么直接赶回false 剪枝
        if (pattern.length() > s.length()) {
            return false;
        }
        // 没有终止 对于 pattern 0 依次找到 s 中可能的子串
        char keyChar = pattern.charAt(0);
        // i 控制子串长度 最长不能超过 s
        for (int i = 1; i <= s.length(); i++) {
            String subWord = s.substring(0, i);
            // 去map 中看 有没有 没有的话 保证 value 也没有 ，有的话 看看 value 是不是一致
            if (!mapping.containsKey(keyChar)) {
                if (mapping.containsValue(subWord)) {
                    continue;
                }
                // map 中没有 key 也没有 word
                mapping.put(keyChar, subWord);
                // 递归查找
                boolean backtrack = backtrack(pattern.substring(1), s.substring(i), mapping);
                if (backtrack) {
                    return true;
                }
                // 重置状态
                mapping.remove(keyChar);
            } else {
                // 已经包含了 看下是否可以匹配
                if (subWord.equals(mapping.get(keyChar))) {
                    // 子串能匹配上 递归找
                    boolean backtrack = backtrack(pattern.substring(1), s.substring(i), mapping);
                    if (backtrack) {
                        return true;
                    }
                } else {
                    // 子串匹配不上直接 continue
                    continue;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        "abab"
//        "redblueredblue"
        String pattern = "abab";
        String s = "redblueredblue";
        boolean b = solution.wordPatternMatch(pattern, s);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
