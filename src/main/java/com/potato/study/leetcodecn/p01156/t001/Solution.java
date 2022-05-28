package com.potato.study.leetcodecn.p01156.t001;


import org.junit.Assert;

/**
 * 1156. 单字符重复子串的最大长度
 *
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 *
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：text = "ababa"
 * 输出：3
 * 示例 2：
 *
 * 输入：text = "aaabaaa"
 * 输出：6
 * 示例 3：
 *
 * 输入：text = "aaabbaaa"
 * 输出：4
 * 示例 4：
 *
 * 输入：text = "aaaaa"
 * 输出：5
 * 示例 5：
 *
 * 输入：text = "abcdef"
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= text.length <= 20000
 * text 仅由小写英文字母组成。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-for-longest-repeated-character-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/solution/java-tong-ji-ge-zi-mu-chu-xian-ci-shu-zai-shun-xu-/
     * @param text
     * @return
     */
    public int maxRepOpt1(String text) {
        // 遍历 text 记录所有字母出现次数
        int[] totalCount = new int[26];
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            totalCount[chars[i]-'a']++;
        }
        // 遍历 text 使用数组 windowCount 记录窗口内部的出现次数
        int[] windowCount = new int[26];
        int maxCount = 0;
        int maxCharIndex = 0;
        // 如果当前记录长度已经大于 最大长度了，说明当前出现次数就是 最大长度 修改最大长度 最大长度 + 1等
        int startIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = chars[i];
            int charIndex = ch - 'a';
            // 窗口内 计数
            windowCount[charIndex]++;
            // 如果当前窗口长度 比最大窗口 + 1还要大 说明 需要移动遍 一次
            if (windowCount[charIndex] > maxCount) {
                maxCount = windowCount[charIndex];
                maxCharIndex = charIndex;
            } else {
                // 窗口是否需要缩小
                if (i - startIndex + 1 > maxCount + 1) {
                    windowCount[chars[startIndex] - 'a']--;
                    startIndex++;
                }
            }
        }
        // 返回最大字符出现次数 和 maxCount + 1 的最小值
        return Math.min(maxCount + 1, totalCount[maxCharIndex]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String text = "ababa";
        int i = solution.maxRepOpt1(text);
        System.out.println(i);
        Assert.assertEquals(3, i);

        text = "aaabaaa";
        i = solution.maxRepOpt1(text);
        System.out.println(i);
        Assert.assertEquals(6, i);

        text = "aaabbaaa";
        i = solution.maxRepOpt1(text);
        System.out.println(i);
        Assert.assertEquals(4, i);

        text = "abbccbcaaa";
        i = solution.maxRepOpt1(text);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }

}
