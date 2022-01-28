package com.potato.study.leetcodecn.p01781.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1781. 所有子字符串美丽值之和
 *
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 *
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 * 示例 2：
 *
 * 输入：s = "aabcbaa"
 * 输出：17
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-beauty-of-all-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int beautySum(String s) {
        char[] chars = s.toCharArray();
        int totalSum = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // 单词从i到j
                int sum = getBeautySum(chars, i, j);
                totalSum += sum;
            }
        }
        return totalSum;
    }

    private int getBeautySum(char[] words, int i, int j) {
        int[] count = new int[26];
        for (int k = i; k <= j; k++) {
            count[words[k] - 'a']++;
        }
        // 出现频率最高字符与出现频率最低字符的出现次数之差。
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < 26; k++) {
            if (count[k] == 0) {
                continue;
            }
            max = Math.max(max, count[k]);
            min = Math.min(min, count[k]);
        }
        return max - min;
    }


}
