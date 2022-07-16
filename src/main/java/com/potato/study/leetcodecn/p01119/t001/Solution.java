package com.potato.study.leetcodecn.p01119.t001;


import java.util.concurrent.Semaphore;

/**
 * 1119. 删去字符串中的元音
 *
 * 给你一个字符串 s ，请你删去其中的所有元音字母 'a'，'e'，'i'，'o'，'u'，并返回这个新字符串。

  

 示例 1：

 输入：s = "leetcodeisacommunityforcoders"
 输出："ltcdscmmntyfrcdrs"
 示例 2：

 输入：s = "aeiou"
 输出：""
  

 提示：

 1 <= S.length <= 1000
 s 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/remove-vowels-from-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String removeVowels(String s) {
        StringBuilder builder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if ('a' == ch || 'e' == ch
                    || 'i' == ch || 'o' == ch
                    || 'u' == ch) {
                continue;
            }
            builder.append(ch);
        }
        return builder.toString();
    }
}
