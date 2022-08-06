package com.potato.study.leetcodecn.p02083.t001;

/**
 * 2083. 求以相同字母开头和结尾的子串总数
 *
 * 给你一个仅由小写英文字母组成的，  下标从 0 开始的字符串 s 。返回 s 中以相同字符开头和结尾的子字符串总数。

 子字符串是字符串中连续的非空字符序列。

  

 示例 1：

 输入：s = "abcba"
 输出：7
 解释：
 以相同字母开头和结尾的长度为 1 的子串是："a"、"b"、"c"、"b" 和 "a" 。
 以相同字母开头和结尾的长度为 3 的子串是："bcb" 。
 以相同字母开头和结尾的长度为 5 的子串是："abcba" 。
 示例 2：

 输入：s = "abacad"
 输出：9
 解释：
 以相同字母开头和结尾的长度为 1 的子串是："a"、"b"、"a"、"c"、"a" 和 "d" 。
 以相同字母开头和结尾的长度为 3 的子串是："aba" 和 "aca" 。
 以相同字母开头和结尾的长度为 5 的子串是："abaca" 。
 示例 3：

 输入：s = "a"
 输出：1
 解释：
 只有一个，以相同字母开头和结尾的长度为 1 的子串是："a"。
  

 提示：

 1 <= s.length <= 105
 s 仅包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/substrings-that-begin-and-end-with-the-same-letter
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long numberOfSubstrings(String s) {
        long[] count = new long[26];
        long totalNum = 0;
        // 遍历每一个单词 找到之前开头可能有多少种情况
        for (char ch : s.toCharArray()) {
            // 以ch作为结尾的子串
            totalNum += count[ch - 'a'];
            count[ch - 'a']++;
            // 自身独立为串
            totalNum++;
        }
        return totalNum;
    }
}
