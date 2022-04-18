package com.potato.study.leetcodecn.p01297.t001;


import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1297. 子串的最大出现次数
 *
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：

 子串中不同字母的数目必须小于等于 maxLetters 。
 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
  

 示例 1：

 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 输出：2
 解释：子串 "aab" 在原字符串中出现了 2 次。
 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 示例 2：

 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 输出：2
 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 示例 3：

 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 输出：3
 示例 4：

 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 输出：0
  

 提示：

 1 <= s.length <= 10^5
 1 <= maxLetters <= 26
 1 <= minSize <= maxSize <= min(26, s.length)
 s 只包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-number-of-occurrences-of-a-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-occurrences-of-a-substring/solution/hua-dong-chuang-kou-ha-xi-biao-by-dvuszk-le9n/
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // 滑动庄口 找到 窗口大小维持在 minSize
        Map<String, Integer> appearCountMap = new HashMap<>();
        int windowLen = 0;
        int left = 0;
        // 记录窗口中的字符种类数
        int kindCount = 0;
        // window 中每个字母出现了多少次
        int[] count = new int[26];
        // 枚举每一个 right
        char[] chars = s.toCharArray();


        for (int right = 0; right < s.length(); right++) {
            // 当前窗口操作
            char ch = chars[right];
            if (count[ch-'a'] == 0) {
                kindCount++;
            }
            count[ch-'a']++;
            windowLen++;

            // 看看是不是符合结算条件
            while (left <= right && ((kindCount > maxLetters) || (windowLen > minSize))) {
                ch = chars[left];
                count[ch-'a']--;
                if (count[ch-'a'] == 0) {
                    kindCount--;
                }
                windowLen--;
                left++;
            }

            // 结算
            if (windowLen == minSize) {
                String key = s.substring(left, right+1);
                Integer appearCount = appearCountMap.getOrDefault(key, 0);
                appearCount++;
                appearCountMap.put(key, appearCount);
            }
        }
        int max = 0;
        for (int appearCount : appearCountMap.values()) {
            max = Math.max(appearCount, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aababcaab";
        int maxLetters = 2;
        int minSize = 3;
        int maxSize = 4;
        int i = solution.maxFreq(s, maxLetters, minSize, maxSize);
        System.out.println(i);
        Assert.assertEquals(2, i);


        solution = new Solution();
        s = "aabcabcab";
        maxLetters = 2;
        minSize = 2;
        maxSize = 3;
        i = solution.maxFreq(s, maxLetters, minSize, maxSize);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
