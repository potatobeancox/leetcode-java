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


    /**
     * https://leetcode.cn/problems/sentence-screen-fitting/solution/ping-mu-ke-xian-shi-ju-zi-de-shu-liang-by-61707667/
     * @param sentence
     * @param rows
     * @param cols
     * @return
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        // 拼接成 一个整体
        StringBuilder builder = new StringBuilder();
        for (String word : sentence) {
            builder.append(word);
            builder.append(" ");
        }
        int index = 0;
        int length = builder.length();
        // 1. 枚举 每个 rows 计算当前行停留在index的位置
        for (int i = 0; i < rows; i++) {
            // 当前index 停留的位置
            index += cols;
            // 当前index 对应在len中的位置
            int lengthIndex = index % length;
            // 2. 如果发现 停留在 空格 还是可以 往前找一找
            if (builder.charAt(lengthIndex) == ' ') {
                // 刚好遇到 空格 之后从 index + 1 开始
                index++;
            } else {
                // 往回找到上一个空格
                while (index > 0 && builder.charAt((index-1) % length) != ' ') {
                    index--;
                }
            }
        }
        return index / builder.length();
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
