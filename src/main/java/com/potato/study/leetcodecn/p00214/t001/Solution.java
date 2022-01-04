package com.potato.study.leetcodecn.p00214.t001;

import org.junit.Assert;

/**
 * 214. 最短回文串
 *
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

  

 示例 1：

 输入：s = "aacecaaa"
 输出："aaacecaaa"
 示例 2：

 输入：s = "abcd"
 输出："dcbabcd"
  

 提示：

 0 <= s.length <= 5 * 104
 s 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shortest-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 底层kmp
     * 然后发现 kmp 忘干净了 唉
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        // 添加前缀，将其变成 回文串，找到最短的回文串


        // 最长回文串 是将 s.sub 1 翻转拼在前边

        
        return null;
    }


}
