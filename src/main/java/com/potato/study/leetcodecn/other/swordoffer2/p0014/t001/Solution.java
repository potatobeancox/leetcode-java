package com.potato.study.leetcodecn.other.swordoffer2.p0014.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 014. 字符串中的变位词
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 *  
 *
 * 注意：本题与主站 567 题相同： https://leetcode-cn.com/problems/permutation-in-string/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/MPnaiL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // ii 014
    public boolean checkInclusion(String s1, String s2) {
        // 直接滑动窗口 比较窗口大小
        int[] baseCount = new int[26];
        for (char ch : s1.toCharArray()) {
            int index = ch - 'a';
            baseCount[index]++;
        }
        int[] window = new int[26];
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            int index = ch - 'a';

            window[index]++;
            if (i >= s1.length()) {
                window[s2.charAt(i - s1.length()) - 'a']--;
            }

            if (i >= s1.length() - 1) {
                boolean same = isSameArray(baseCount, window);
                if (same) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSameArray(int[] baseCount, int[] window) {
        for (int i = 0; i < 26; i++) {
            if (baseCount[i] != window[i]) {
                return false;
            }
        }
        return true;
    }
}
