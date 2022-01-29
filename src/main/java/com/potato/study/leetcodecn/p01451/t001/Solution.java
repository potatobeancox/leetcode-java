package com.potato.study.leetcodecn.p01451.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1451. 重新排列句子中的单词
 *
 * 「句子」是一个用空格分隔单词的字符串。给你一个满足下述格式的句子 text :
 *
 * 句子的首字母大写
 * text 中的每个单词都用单个空格分隔。
 * 请你重新排列 text 中的单词，使所有单词按其长度的升序排列。如果两个单词的长度相同，则保留其在原句子中的相对顺序。
 *
 * 请同样按上述格式返回新的句子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：text = "Leetcode is cool"
 * 输出："Is cool leetcode"
 * 解释：句子中共有 3 个单词，长度为 8 的 "Leetcode" ，长度为 2 的 "is" 以及长度为 4 的 "cool" 。
 * 输出需要按单词的长度升序排列，新句子中的第一个单词首字母需要大写。
 * 示例 2：
 *
 * 输入：text = "Keep calm and code on"
 * 输出："On and keep calm code"
 * 解释：输出的排序情况如下：
 * "On" 2 个字母。
 * "and" 3 个字母。
 * "keep" 4 个字母，因为存在长度相同的其他单词，所以它们之间需要保留在原句子中的相对顺序。
 * "calm" 4 个字母。
 * "code" 4 个字母。
 * 示例 3：
 *
 * 输入：text = "To be or not to be"
 * 输出："To be or to be not"
 *  
 *
 * 提示：
 *
 * text 以大写字母开头，然后包含若干小写字母以及单词间的单个空格。
 * 1 <= text.length <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rearrange-words-in-a-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String arrangeWords(String text) {
        String[] split = text.split(" ");
        PriorityQueue<Word> priorityQueue = new PriorityQueue<>(
                new Comparator<Word>() {
                    @Override
                    public int compare(Word o1, Word o2) {
                        int compare = Integer.compare(o1.word.length(), o2.word.length());
                        if (0 == compare) {
                            return Integer.compare(o1.index, o2.index);
                        }
                        return compare;
                    }
                }
        );
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                Word word = new Word(split[i].toLowerCase(), i);
                priorityQueue.add(word);
            } else {
                Word word = new Word(split[i], i);
                priorityQueue.add(word);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Word poll = priorityQueue.poll();
            if (builder.length() == 0) {
                char[] chars = poll.word.toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                builder.append(new String(chars));
            } else {
                builder.append(" ").append(poll.word);
            }
        }
        return builder.toString();
    }

    class Word {
        public String word;
        public int index;

        public Word(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
