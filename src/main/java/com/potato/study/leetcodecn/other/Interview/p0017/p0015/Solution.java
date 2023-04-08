package com.potato.study.leetcodecn.other.Interview.p0017.p0015;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 17.15. 最长单词
 *
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 *
 * 示例：
 *
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 *
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-word-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 17.15
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        // 先找最长的 再找 字典序最小的
        Arrays.sort(words, (o1, o2) -> {
            int compare = Integer.compare(o2.length(), o1.length());
            if (compare != 0) {
                return compare;
            }
            // 字典序
            return o1.compareTo(o2);
        });
        for (String word : words) {
            // 每个位置 substring
            for (int i = 0; i < word.length(); i++) {
                String part1 = word.substring(0, i+1);;
                String part2 = word.substring(i+1);

                if ((set.contains(part1) && set.contains(part2))
                        || (set.contains(part1) && "".equals(part2))
                        || (set.contains(part2) && "".equals(part1))) {
                    return word;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strings = LeetcodeInputUtils.inputString2StringArray("[\"llllcccl\",\"clclll\",\"ccc\",\"llccllccl\",\"lcclccclcl\",\"c\"]");
        String s = solution.longestWord(strings);
        System.out.println(s);
        Assert.assertEquals("ccc", s);
    }
}
