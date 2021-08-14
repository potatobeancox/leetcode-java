package com.potato.study.leetcodecn.p01668.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;
import org.junit.Assert;

/**
 * 1668. 最大重复子字符串
 *
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。

 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。

  

 示例 1：

 输入：sequence = "ababc", word = "ab"
 输出：2
 解释："abab" 是 "ababc" 的子字符串。
 示例 2：

 输入：sequence = "ababc", word = "ba"
 输出：1
 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
 示例 3：

 输入：sequence = "ababc", word = "ac"
 输出：0
 解释："ac" 不是 "ababc" 的子字符串。
  

 提示：

 1 <= sequence.length <= 100
 1 <= word.length <= 100
 sequence 和 word 都只包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-repeating-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 美剧 se的每个位置 开始与word比较
     * @param sequence
     * @param word
     * @return
     */
    public int maxRepeating(String sequence, String word) {
        int maxTime = 0;
        for (int i = 0; i < sequence.length(); i++) {
            char ch = sequence.charAt(i);
            if (ch != word.charAt(0)) {
                continue;
            }
            int index = i;
            boolean isMath = true;
            int times = 0;

            while (isMath && index < sequence.length()) {

                for (int j = 0; j < word.length(); j++) {

                    if (index >= sequence.length()) {
                        isMath = false;
                        break;
                    }

                    if (word.charAt(j) != sequence.charAt(index)) {
                        isMath = false;
                        break;
                    }
                    index++;
                }
                if (isMath) {
                    times++;
                }
            }
            maxTime = Math.max(maxTime, times);
        }
        return maxTime;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String sequence = "ababc";
        String word = "ab";
        int i = solution.maxRepeating(sequence, word);
        System.out.println(i);
        Assert.assertEquals(2, i);


        sequence = "ababc";
        word = "ba";
        i = solution.maxRepeating(sequence, word);
        System.out.println(i);
        Assert.assertEquals(1, i);


        sequence = "a";
        word = "aa";
        i = solution.maxRepeating(sequence, word);
        System.out.println(i);
        Assert.assertEquals(0, i);


        sequence = "cacaaca";
        word = "aa";
        i = solution.maxRepeating(sequence, word);
        System.out.println(i);
        Assert.assertEquals(1, i);

    }
}
