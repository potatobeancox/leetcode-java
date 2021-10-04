package com.potato.study.leetcodecn.p00395.t001;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;

/**
 * 395. 至少有 K 个重复字符的最长子串
 *
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 *
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 最大长度
    private int maxLen;

    /**
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        this.maxLen = 0;
        // 分治法调用子函数 获取最大值
        getMaxLengthOfLongestSubstring(s, k);
        return maxLen;
    }

    /**
     *
     * @param s
     * @param k
     */
    private void getMaxLengthOfLongestSubstring(String s, int k) {
        // 遍历 s 计算出现的字母数量是不是 都满足 大于k
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // 如果都满足需要比较变更max
        boolean needContinue = false;
        char splitChar = 'a';
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }
            // 不满足条件需要继续递归找
            if (count[i] < k) {
                splitChar = (char) ('a' + i);
                needContinue = true;
                break;
            }
        }
        if (!needContinue) {
            maxLen = Math.max(maxLen, s.length());
            return;
        }
        // 对于第一个不满足的字母，进行split 然后分治法统计
        String[] split = s.split(String.valueOf(splitChar));
        if (null == split) {
            return;
        }
        for (String each : split) {
            if (null == each || each.length() == 0) {
                continue;
            }
            getMaxLengthOfLongestSubstring(each, k);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aaabb";
        int k = 3;
        int i = solution.longestSubstring(s, k);
        System.out.println(i);
        Assert.assertEquals(3, i);

        s = "ababbc";
        k = 2;
        i = solution.longestSubstring(s, k);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
