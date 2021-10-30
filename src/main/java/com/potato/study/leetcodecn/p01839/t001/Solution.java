package com.potato.study.leetcodecn.p01839.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1839. 所有元音按顺序排布的最长子字符串
 *
 * 当一个字符串满足如下条件时，我们称它是 美丽的 ：

 所有 5 个英文元音字母（'a' ，'e' ，'i' ，'o' ，'u'）都必须 至少 出现一次。
 这些元音字母的顺序都必须按照 字典序 升序排布（也就是说所有的 'a' 都在 'e' 前面，所有的 'e' 都在 'i' 前面，以此类推）
 比方说，字符串 "aeiou" 和 "aaaaaaeiiiioou" 都是 美丽的 ，但是 "uaeio" ，"aeoiu" 和 "aaaeeeooo" 不是美丽的 。

 给你一个只包含英文元音字母的字符串 word ，请你返回 word 中 最长美丽子字符串的长度 。如果不存在这样的子字符串，请返回 0 。

 子字符串 是字符串中一个连续的字符序列。

  

 示例 1：

 输入：word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
 输出：13
 解释：最长子字符串是 "aaaaeiiiiouuu" ，长度为 13 。
 示例 2：

 输入：word = "aeeeiiiioooauuuaeiou"
 输出：5
 解释：最长子字符串是 "aeiou" ，长度为 5 。
 示例 3：

 输入：word = "a"
 输出：0
 解释：没有美丽子字符串，所以返回 0 。
  

 提示：

 1 <= word.length <= 5 * 105
 word 只包含字符 'a'，'e'，'i'，'o' 和 'u' 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-substring-of-all-vowels-in-order
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param word
     * @return
     */
    public int longestBeautifulSubstring(String word) {
        int maxLength = 0;
        int type = 1;
        int currentLen = 1;
        char[] chars = word.toCharArray();
        for (int i = 1; i < word.length(); i++) {
            // 遍历 word 记录 max 和当前字符串 type  如果字符之前满足递增 那么本次 长度 ++ 更换type ++
            if (chars[i] >= chars[i-1]) {
                if (chars[i] > chars[i-1]) {
                    type++;
                }
                currentLen++;
            } else {
                // 不满足递增 时，计算当前最大值，并判定 type 是不是已经满足 不满足不计算 计算之后 重重各个标志
                // judge type == 5
                if (type == 5) {
                    maxLength = Math.max(maxLength, currentLen);
                }
                type = 1;
                currentLen = 1;
            }
        }

        // 判断最后一个
        if (type == 5) {
            maxLength = Math.max(maxLength, currentLen);
        }

        return maxLength;
    }
}
