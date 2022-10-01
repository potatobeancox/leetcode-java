package com.potato.study.leetcodecn.p00418.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 418. 屏幕可显示句子的数量
 *
 * 给你一个 rows x cols 的屏幕和一个用 非空 的单词列表组成的句子，请你计算出给定句子可以在屏幕上完整显示的次数。

 注意：

 一个单词不能拆分成两行。
 单词在句子中的顺序必须保持不变。
 在一行中 的两个连续单词必须用一个空格符分隔。
 句子中的单词总量不会超过 100。
 每个单词的长度大于 0 且不会超过 10。
 1 ≤ rows, cols ≤ 20,000.
  

 示例 1：

 输入：
 rows = 2, cols = 8, 句子 sentence = ["hello", "world"]

 输出：
 1

 解释：
 hello---
 world---

 字符 '-' 表示屏幕上的一个空白位置。
  

 示例 2：

 输入：
 rows = 3, cols = 6, 句子 sentence = ["a", "bcd", "e"]

 输出：
 2

 解释：
 a-bcd-
 e-a---
 bcd-e-

 字符 '-' 表示屏幕上的一个空白位置。
  

 示例 3：

 输入：
 rows = 4, cols = 5, 句子 sentence = ["I", "had", "apple", "pie"]

 输出：
 1

 解释：
 I-had
 apple
 pie-I
 had--

 字符 '-' 表示屏幕上的一个空白位置。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sentence-screen-fitting
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Solution {



    public int wordsTyping(String[] sentence, int rows, int cols) {
        // 拼接成 一个整体
        StringBuilder builder = new StringBuilder();
        for (String word : sentence) {
            builder.append(word);
            builder.append(" ");
        }
        if (builder.charAt(builder.length() - 1) == ' ') {
            builder.deleteCharAt(builder.length() - 1);
        }
        // 从开始第一行往后找 一直找到句子的末尾，再重头开始找
        int times = 0;
        // 从第一行开始计数
        int builderIndex = 0;
        for (int i = 0; i < rows; i++) {
            // 每次往后找 col 看一下现在停在哪里
            builderIndex += cols;
            // 如果已经到了最后
            if (builderIndex >= builder.length()) {
                times += (builderIndex / builder.length());
                builderIndex %= builder.length();
            }
            // 如果当前不是 空格就需要往前移动 builderIndex 至空格
            // 不是空格需要往前移动到空格
            while (builderIndex > 0
                    && builder.charAt(builderIndex) != ' ') {
                builderIndex--;
            }
            if (builder.charAt(builderIndex) == ' ') {
                builderIndex++;
            }
        }

        if (builderIndex >= builder.length()) {
            times++;
        }

        return times;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] strings = LeetcodeInputUtils.inputString2StringArray("[\"a\", \"bcd\", \"e\"]");
        int i = solution.wordsTyping(strings,3,6);
        System.out.println(i);
        Assert.assertEquals(2, i);


        strings = LeetcodeInputUtils.inputString2StringArray("[\"i\",\"had\",\"apple\",\"pie\"]");
        i = solution.wordsTyping(strings,4,5);
        System.out.println(i);
        Assert.assertEquals(1, i);


        strings = LeetcodeInputUtils.inputString2StringArray("[\"f\",\"p\",\"a\"]");
        i = solution.wordsTyping(strings,8,7);
        System.out.println(i);
        Assert.assertEquals(10, i);
    }
}
