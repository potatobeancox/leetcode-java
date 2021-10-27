package com.potato.study.leetcodecn.p01371.t001;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * 1371. 每个元音包含偶数次的最长子字符串
 *
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 *
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 *
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1371
    public int findTheLongestSubstring(String s) {
        // 遍历 s 将aeiou 出现的奇偶性 压缩成状态 ^ i 并记录第一次出现这个状态对应的位置 遍历 过程中 记录 当前位置 与第一次出现位置差或者就是当前位置
        int status = 0;
        Map<Integer, Integer> firstAppearIndexMap = new HashMap<>();
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                int bit = (1 << 0);
                status ^= bit;
            } else if (ch == 'e') {
                int bit = (1 << 1);
                status ^= bit;
            } else if (ch == 'i') {
                int bit = (1 << 2);
                status ^= bit;
            } else if (ch == 'o') {
                int bit = (1 << 3);
                status ^= bit;
            } else if (ch == 'u') {
                int bit = (1 << 4);
                status ^= bit;
            }
            // 比较次数
            if (status == 0) {
                maxLength = Math.max(maxLength, i + 1);
            } else {
                Integer firstIndex = firstAppearIndexMap.getOrDefault(status, i);
                maxLength = Math.max(maxLength, i - firstIndex);
                
                if (firstIndex == i) {
                    firstAppearIndexMap.put(status, i);
                }
            }
        }
        return maxLength;
    }
}
