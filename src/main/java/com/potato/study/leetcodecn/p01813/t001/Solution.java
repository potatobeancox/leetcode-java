package com.potato.study.leetcodecn.p01813.t001;

import org.junit.Assert;

/**
 * 1813. 句子相似性 III
 *
 * 一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。每个单词都
 * 只 包含大写和小写英文字母。
 *
 * 如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 相似的 。比方说，sentence1 = "Hello my name
 * is Jane" 且 sentence2 = "Hello Jane" ，我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
 *
 * 给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：sentence1 = "My name is Haley", sentence2 = "My Haley"
 * 输出：true
 * 解释：可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
 * 示例 2：
 *
 * 输入：sentence1 = "of", sentence2 = "A lot of words"
 * 输出：false
 * 解释：没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
 * 示例 3：
 *
 * 输入：sentence1 = "Eating right now", sentence2 = "Eating"
 * 输出：true
 * 解释：可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
 * 示例 4：
 *
 * 输入：sentence1 = "Luky", sentence2 = "Lucccky"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= sentence1.length, sentence2.length <= 100
 * sentence1 和 sentence2 都只包含大小写英文字母和空格。
 * sentence1 和 sentence2 中的单词都只由单个空格隔开。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sentence-similarity-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.length() < sentence2.length()) {
            String tmp = sentence2;
            sentence2 = sentence1;
            sentence1 = tmp;
        }
        // 按照 空格 split
        String[] split1 = sentence1.split(" ");
        String[] split2 = sentence2.split(" ");
        // 开始位置必须相同 对于 sentence2 的1的位置 找到
        int left1 = 0;
        int left2 = 0;
        // 三种情况 左边一致相同
        for (int i = 0; i < split2.length; i++) {
            if (split1[left1].equals(split2[left2])) {
                left1++;
                left2++;
            } else {
                break;
            }
        }
        if (left2 == split2.length) {
            return true;
        }
        // 右边一直相同
        int right1 = split1.length - 1;
        int right2 = split2.length - 1;
        for (int i = 0; i < split2.length; i++) {
            if (split1[right1].equals(split2[right2])) {
                right1--;
                right2--;
            } else {
                break;
            }
        }
        if (left2 < 0) {
            return true;
        }
        // 两头可以对上
        if (right2 <= left2  - 1) {
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String sentence1 = "My name is Haley";
        String sentence2 = "My Haley";
        boolean b = solution.areSentencesSimilar(sentence1, sentence2);
        System.out.println(b);
        Assert.assertEquals(true, b);




        sentence1 = "A A AAa";
        sentence2 = "A AAa";
        b = solution.areSentencesSimilar(sentence1, sentence2);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }


}
