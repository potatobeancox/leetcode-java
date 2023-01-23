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
        // 当前包含的左边界
        int leftIndex = 0;
        // 当前内部包含的字母的种类数，每次移动窗口的时候 要更新
        int windowCharKind = 0;
        int res = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = chars[i];
            int charIndex = ch - 'a';
            // 判断种类数量是否需要改变
            if (windowCount[charIndex] == 0) {
                windowCharKind++;
            }
            // 窗口内 计数
            windowCount[charIndex]++;
            // 判断一下当前窗口是否需要移动左边 种类是小于等于2中
            while (windowCharKind > 2 && leftIndex <= i) {
                // 因为种类数需要移动left
                int removeCharIndex = chars[leftIndex] - 'a';
                windowCount[removeCharIndex]--;
                if (windowCount[removeCharIndex] == 0) {
                    windowCharKind--;
                }
                leftIndex++;
            }
            // 第二个判断 当前最多的字母有多少个
            int mostCharIndex = getMostCharIndex(windowCount);
            // 中间有大于2个的空缺
            while (windowCount[mostCharIndex] < i - leftIndex + 1 - 1) {
                int removeCharIndex = chars[leftIndex] - 'a';
                windowCount[removeCharIndex]--;
                leftIndex++;
                // 种类数也要修改
                if (windowCount[removeCharIndex] == 0) {
                    windowCharKind--;
                }
                mostCharIndex = getMostCharIndex(windowCount);
            }
            // 只有2中字符 且其中一种大于等于 窗口长度-1
            if (windowCharKind == 1) {
                // 只有一种字母
                res = Math.max(res, i - leftIndex + 1);
            } else {
                // 2种类字母
                if (windowCount[mostCharIndex] < totalCount[mostCharIndex]) {
                    res = Math.max(res, i - leftIndex + 1);
                } else {
                    // 看看有没有可能还有一个
                    if (windowCount[mostCharIndex] * 2 == i - leftIndex + 1) {
                        // 找到另外一个
                        for (int j = 0; j < 26; j++) {
                            if (windowCount[j] == windowCount[mostCharIndex] && mostCharIndex != j) {
                                if (windowCount[j] < totalCount[j]) {
                                    res = Math.max(res, i - leftIndex + 1);
                                }
                                break;
                            }
                        }
                    }
                    res = Math.max(res, i - leftIndex + 1 - 1);
                }
            }
        }
        // 返回最大字符出现次数 和 maxCount + 1 的最小值 解决全是aaa的问题
        return res;
    }

    private int getMostCharIndex(int[] windowCount) {
        int maxIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (windowCount[i] > windowCount[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
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


        text = "cabc";
        i = solution.maxRepOpt1(text);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}
