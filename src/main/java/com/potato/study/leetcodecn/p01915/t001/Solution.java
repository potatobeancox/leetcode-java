package com.potato.study.leetcodecn.p01915.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.*;

/**
 * 1915. 最美子字符串的数目
 *
 * 如果某个字符串中 至多一个 字母出现 奇数 次，则称其为 最美 字符串。

 例如，"ccjjc" 和 "abab" 都是最美字符串，但 "ab" 不是。
 给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。

 子字符串 是字符串中的一个连续字符序列。

  

 示例 1：

 输入：word = "aba"
 输出：4
 解释：4 个最美子字符串如下所示：
 - "aba" -> "a"
 - "aba" -> "b"
 - "aba" -> "a"
 - "aba" -> "aba"
 示例 2：

 输入：word = "aabb"
 输出：9
 解释：9 个最美子字符串如下所示：
 - "aabb" -> "a"
 - "aabb" -> "aa"
 - "aabb" -> "aab"
 - "aabb" -> "aabb"
 - "aabb" -> "a"
 - "aabb" -> "abb"
 - "aabb" -> "b"
 - "aabb" -> "bb"
 - "aabb" -> "b"
 示例 3：

 输入：word = "he"
 输出：2
 解释：2 个最美子字符串如下所示：
 - "he" -> "h"
 - "he" -> "e"
  

 提示：

 1 <= word.length <= 105
 word 由从 'a' 到 'j' 的小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-wonderful-substrings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long wonderfulSubstrings(String word) {
        // 用一个 10bit 数字 计算 状态 有多少个 奇数次数的串
        Map<Integer, Integer> statusCountMap = new HashMap<>();
        int status = 0;
        char[] chars = word.toCharArray();
        long total = 0;
        for (char ch : chars) {
            status = status ^ (1 << (ch - 'a'));
            // 之前也是 偶数的情况
            Integer count = statusCountMap.getOrDefault(status, 0);
            // 如果是 直接全部的情况
            statusCountMap.put(status, count + 1);
            total += count;
            // 只有一个 位置不相同的 情况
            for (int i = 0; i < 10; i++) {
                int newStatus = (1 << i) ^ status;
                if (statusCountMap.containsKey(newStatus)) {
                    total += statusCountMap.get(newStatus);
                }
            }
            // 如果当前这个合法 还有一种
            if (status == 0 || Integer.bitCount(status) == 1) {
                total += 1;
            }
        }
        return total;
    }

}
