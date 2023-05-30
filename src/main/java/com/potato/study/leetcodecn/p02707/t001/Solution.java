package com.potato.study.leetcodecn.p02707.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 2707. 字符串中的额外字符
 *
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
 *
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
 * 输出：1
 * 解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
 * 示例 2：
 *
 * 输入：s = "sayhelloworld", dictionary = ["hello","world"]
 * 输出：3
 * 解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] 和 s 只包含小写英文字母。
 * dictionary 中的单词互不相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/extra-characters-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public int minExtraChar(String s, String[] dictionary) {
        // dp i 以i位置作为结尾 最小的 剔除字符串
        Set<String> set = new HashSet<>(Arrays.asList(dictionary));
        int length = s.length();
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = i+1;
        }
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                // 截取当前要比较的字符串
                String sub = s.substring(i, j + 1);
                if (set.contains(sub)) {
                    if (i == 0) {
                        dp[j] = Math.min(dp[j], 0);
                    } else {
                        dp[j] = Math.min(dp[j], dp[i-1]);
                    }
                } else {
                    // i到j 字符串不能被表示出来
                    if (i == 0) {
                        dp[j] = Math.min(sub.length(), dp[j]);
                    } else {
                        dp[j] = Math.min(dp[i-1] + sub.length(), dp[j]);
                    }
                }
            }
        }
        return dp[length-1];
    }

}
