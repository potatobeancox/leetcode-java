package com.potato.study.leetcodecn.p02000.t001;

import org.junit.Assert;

/**
 * 2000. 反转单词前缀
 *
 * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch
 * ，则无需进行任何操作。
 *
 * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
 * 返回 结果字符串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：word = "abcdefd", ch = "d"
 * 输出："dcbaefd"
 * 解释："d" 第一次出现在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
 * 示例 2：
 *
 * 输入：word = "xyxzxe", ch = "z"
 * 输出："zxyxxe"
 * 解释："z" 第一次也是唯一一次出现是在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "zxyxxe" 。
 * 示例 3：
 *
 * 输入：word = "abcd", ch = "z"
 * 输出："abcd"
 * 解释："z" 不存在于 word 中。
 * 无需执行反转操作，结果字符串是 "abcd" 。
 *  
 *
 * 提示：
 *
 * 1 <= word.length <= 250
 * word 由小写英文字母组成
 * ch 是一个小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-prefix-of-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2000
    public String reversePrefix(String word, char ch) {
        char[] chars = word.toCharArray();
        int tail = word.length();
        for (int i = 0; i < word.length(); i++) {
            if (ch == word.charAt(i)) {
                tail = i;
                break;
            }
        }
        // 判断是够已经到了 末尾 不用反转
        if (tail == word.length()) {
            return word;
        }
        // 需要进行反转
        int left = 0;
        int right = tail;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            // 移动
            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String word = "abcdefd";
        char ch = 'd';
        String s = solution.reversePrefix(word, ch);
        System.out.println(s);
        Assert.assertEquals("dcbaefd", s);
    }

}
