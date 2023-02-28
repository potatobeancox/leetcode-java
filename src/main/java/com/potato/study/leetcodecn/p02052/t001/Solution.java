package com.potato.study.leetcodecn.p02052.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 2052. 将句子分隔成行的最低成本
 *
 * 给定一个由空格分隔的单词组成的字符串 sentence 和一个整数 k。你的任务是将 sentence 分成多行，每行中的字符数最多为 k。你可以假设 sentence 不以空格开头或结尾，并且 sentence 中的单词由单个空格分隔。

 你可以通过在 sentence 中的单词间插入换行来分隔 sentence 。一个单词不能被分成两行。每个单词只能使用一次，并且单词顺序不能重排。同一行中的相邻单词应该由单个空格分隔，并且每行都不应该以空格开头或结尾。

 一行长度为 n 的字符串的分隔成本是 (k - n)2 ，总成本就是除开最后一行以外的其它所有行的分隔成本之和。

 以 sentence = "i love leetcode" 和k = 12为例：
 将sentence 分成 "i", "love", 和"leetcode" 的成本为 (12 - 1)2 + (12 - 4)2 = 185。
 将sentence 分成 "i love", 和"leetcode" 的成本为 (12 - 6)2 = 36。
 将sentence 分成 "i", 和"love leetcode" 是不可能的，因为 "love leetcode" 的长度大于 k。
 返回将sentence分隔成行的最低的可能总成本。

  

 示例 1:

 输入: sentence = "i love leetcode", k = 12
 输出: 36
 解释:
 将 sentence 分成"i", "love", 和"leetcode" 的成本为 (12 - 1)2 + (12 - 4)2 = 185.
 将 sentence 分成"i love", 和"leetcode" 的成本为 (12 - 6)2 = 36.
 将 sentence 分成"i", "love leetcode" 是不可能的，因为 "love leetcode" 的长度为 13.
 36是最低的可能总成本，因此返回它
 示例 2:

 输入: sentence = "apples and bananas taste great", k = 7
 输出: 21
 解释:
 将 sentence 分成"apples", "and", "bananas", "taste", 和"great" 的成本为 (7 - 6)2 + (7 - 3)2 + (7 - 7)2 + (7 - 5)2 = 21.
 21是最低的可能总成本，因此返回它
 示例 3:

 输入: sentence = "a", k = 5
 输出: 0
 解释:
 最后一行的成本不包括在总成本中，而sentence只有一行，所以返回0
  

 提示:

 1 <= sentence.length <= 5000
 1 <= k <= 5000
 sentence 中每个单词长度最大为 k.
 sentence 只包含小写字母和空格.
 sentence 不会以空格开头或结尾.
 sentence 中的单词以单个空格分隔.

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-cost-to-separate-sentence-into-rows
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumCost(String sentence, int k) {
        // 有多少个单词
        String[] split = sentence.split(" ");
        int n = split.length;
        // dp i 将第i个单词作为最后一个单词 最小花费
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            // 最后一行只有一个单词
            int lastLen = split[i].length();
            if (i == 0) {
                dp[0] = (k - lastLen) * (k - lastLen);
                continue;
            }
            lastLen = 0;
            // 往前看看 有多少个可以作为一行
            for (int j = i; j >= 0; j--) {
                // 看看当前 总共累计了多少
                lastLen += split[j].length();
                if (j != i) {
                    lastLen += 1;
                }
                // 超过了每行k的限制
                if (lastLen > k) {
                    break;
                }
                // 没超过 最后一行花费
                int cost = (k - lastLen) * (k - lastLen);
                if (j > 0) {
                    dp[i] = Math.min(dp[i], dp[j-1] + cost);
                } else {
                    dp[i] = Math.min(dp[i], cost);
                }
            }
        }

        // 枚举 split 从后往前 让尽量多的单词作为最后 一行不计算
        int lastLine = 0;
        int cost = dp[split.length - 1];
        for (int i = split.length-1; i >= 0; i--) {
            if (lastLine + split[i].length() <= k) {
                lastLine += split[i].length();
                lastLine += 1;
                cost = Math.min(cost, dp[i]);
            } else {
                return Math.min(cost, dp[i]);
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String sentence = "i love leetcode";
        int k = 12;
        int i = solution.minimumCost(sentence, k);
        System.out.println(i);
        Assert.assertEquals(36, i);



        sentence = "ke lskd ks";
        k = 6;
        i = solution.minimumCost(sentence, k);
        System.out.println(i);
        Assert.assertEquals(20, i);
    }


}
