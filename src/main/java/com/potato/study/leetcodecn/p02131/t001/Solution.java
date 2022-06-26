package com.potato.study.leetcodecn.p02131.t001;

import com.potato.study.leetcode.domain.ListNode;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 2131. 连接两字母单词得到的最长回文串
 *
 * 给你一个字符串数组 words 。words 中每个元素都是一个包含 两个 小写英文字母的单词。

 请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。

 请你返回你能得到的最长回文串的 长度 。如果没办法得到任何一个回文串，请你返回 0 。

 回文串 指的是从前往后和从后往前读一样的字符串。

  

 示例 1：

 输入：words = ["lc","cl","gg"]
 输出：6
 解释：一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
 "clgglc" 是另一个可以得到的最长回文串。
 示例 2：

 输入：words = ["ab","ty","yt","lc","cl","ab"]
 输出：8
 解释：最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
 "lcyttycl" 是另一个可以得到的最长回文串。
 示例 3：

 输入：words = ["cc","ll","xx"]
 输出：2
 解释：最长回文串是 "cc" ，长度为 2 。
 "ll" 是另一个可以得到的最长回文串。"xx" 也是。
  

 提示：

 1 <= words.length <= 105
 words[i].length == 2
 words[i] 仅包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestPalindrome(String[] words) {
        // 两个map 统计 回文数量和 不是回文数量
        Map<String, Integer> palindromeCountMap = new HashMap<>();
        Map<String, Integer> notPalindromeCountMap = new HashMap<>();
        int totalCount = 0;
        for (int i = 0; i < words.length; i++) {
            boolean palindrome = isPalindrome(words[i]);
            if (palindrome) {
                int count = palindromeCountMap.getOrDefault(words[i], 0);
                count++;
                palindromeCountMap.put(words[i], count);
            } else {
                // 不是回文 看看 之前有没有 反转的单词出现 有的话 计算数量，没有就加入map
                StringBuilder builder = new StringBuilder(words[i]);
                String reverseWord = builder.reverse().toString();
                // 存在就 使用并计数
                if (notPalindromeCountMap.containsKey(reverseWord)) {
                    int count = notPalindromeCountMap.get(reverseWord);
                    if (count > 0) {
                        count--;
                        totalCount += words[i].length() * 2;
                    }
                    if (count == 0) {
                        notPalindromeCountMap.remove(reverseWord);
                    } else {
                        notPalindromeCountMap.put(reverseWord, count);
                    }

                } else {
                    // 没有 reverseWord
                    notPalindromeCountMap.put(words[i],
                            notPalindromeCountMap.getOrDefault(words[i], 0) + 1);
                }
            }
        }
        // 专门针对回文计算
        int maxPalindromeLength = 0;
        for (Map.Entry<String, Integer> entry : palindromeCountMap.entrySet()) {
            int times = entry.getValue();
            String word = entry.getKey();
            if (times % 2 == 0) {
                totalCount += word.length() * times;
                continue;
            }
            totalCount += word.length() * ((times / 2) * 2);
            maxPalindromeLength = Math.max(maxPalindromeLength, word.length());
        }
        return totalCount + maxPalindromeLength;
    }

    private boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        char[] chars = word.toCharArray();
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "lc","cl","gg"
        };
        int i = solution.longestPalindrome(words);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }
}
