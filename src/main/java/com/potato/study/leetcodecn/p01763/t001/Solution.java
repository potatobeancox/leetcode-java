package com.potato.study.leetcodecn.p01763.t001;

import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 1763. 最长的美好子字符串
 *
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。

 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。

  

 示例 1：

 输入：s = "YazaAay"
 输出："aAa"
 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 "aAa" 是最长的美好子字符串。
 示例 2：

 输入：s = "Bb"
 输出："Bb"
 解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
 示例 3：

 输入：s = "c"
 输出：""
 解释：没有美好子字符串。
 示例 4：

 输入：s = "dDzeE"
 输出："dD"
 解释："dD" 和 "eE" 都是最长美好子字符串。
 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
  

 提示：

 1 <= s.length <= 100
 s 只包含大写和小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-nice-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String longestNiceSubstring(String s) {
        String longestNiceWord = "";
        // 每个长度子串找到第一个就行 枚举长度
        for (int i = 2; i <= s.length(); i++) {
            // 枚举开始位置
            for (int j = 0; j < s.length(); j++) {
                // 超长了
                if (j + i > s.length()) {
                    break;
                }
                String substring = s.substring(j, j + i);
                // 找到 i长度第一个 字符转进行修改
                if (isNiceWord(substring) && longestNiceWord.length() < substring.length()) {
                    longestNiceWord = substring;
                    break;
                }
            }
        }
        return longestNiceWord;
    }


    /**
     * 判断 word 是否nice
     * @param word
     * @return
     */
    private boolean isNiceWord(String word) {
        int[] big = new int[26];
        int[] small = new int[26];

        for (char ch : word.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                big[ch - 'A']++;
            } else {
                small[ch - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (big[i] == small[i]) {
                continue;
            }
            if (big[i] == 0 || small[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "YazaAay";
        String niceWord = solution.longestNiceSubstring(word);
        System.out.println(niceWord);
        Assert.assertEquals("aAa", niceWord);


        word = "Bb";
        niceWord = solution.longestNiceSubstring(word);
        System.out.println(niceWord);
        Assert.assertEquals("Bb", niceWord);
    }
}
