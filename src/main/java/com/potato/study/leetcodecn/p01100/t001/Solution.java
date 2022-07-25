package com.potato.study.leetcodecn.p01100.t001;


import java.util.Arrays;

import org.junit.Assert;

/**
 * 1100. 长度为 K 的无重复字符子串
 *
 * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "havefunonleetcode", K = 5
 * 输出：6
 * 解释：
 * 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
 * 示例 2：
 *
 * 输入：S = "home", K = 5
 * 输出：0
 * 解释：
 * 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 10^4
 * S 中的所有字符均为小写英文字母
 * 1 <= K <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numKLenSubstrNoRepeats(String s, int k) {
        int noRepeatCount = 0;
        // 遍历 s 的每一个位置 先找到没有重复的k个 再往后移动
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        int windowCount = 0;
        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            // 先判断重复没有 重复了 先插入再缩小
            int index = chars[i] - 'a';
            count[index]++;
            windowCount++;
            while (count[index] == 2 || windowCount > k) {
                count[chars[left] - 'a']--;
                left++;
                windowCount--;
            }
            // 判断 window 大小
            if (windowCount == k) {
                noRepeatCount++;
            }
        }
        return noRepeatCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "havefunonleetcode";
        int k = 5;
        int i = solution.numKLenSubstrNoRepeats(s, k);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }
}
