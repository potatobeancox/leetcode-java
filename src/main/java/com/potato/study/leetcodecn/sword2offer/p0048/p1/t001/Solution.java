package com.potato.study.leetcodecn.sword2offer.p0048.p1.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 *
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 *
 * 提示：
 *
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历 s 使用 map 记录 子串不重复的字符的位置 index
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (indexMap.containsKey(ch)) {
                // 计算需要删除的key
                Integer newStartIndex = indexMap.get(ch);
                int oldStart = i - indexMap.size();
                for (int j = oldStart; j <= newStartIndex; j++) {
                    indexMap.remove(s.charAt(j));
                }
            }
            indexMap.put(ch, i);
            maxLen = Math.max(maxLen, indexMap.size());
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcabcbb";
        int i = solution.lengthOfLongestSubstring(s);
        System.out.println(i);
        Assert.assertEquals(3, i);

        s = "bbbbb";
        i = solution.lengthOfLongestSubstring(s);
        System.out.println(i);
        Assert.assertEquals(1, i);
        //
    }

}
