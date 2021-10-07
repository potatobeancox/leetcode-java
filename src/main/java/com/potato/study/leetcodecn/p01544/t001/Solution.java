package com.potato.study.leetcodecn.p01544.t001;

import org.junit.Assert;

/**
 * 1544. 整理字符串
 *
 * 给你一个由大小写英文字母组成的字符串 s 。

 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件:

 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。
 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。
 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。

 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。

 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。

  

 示例 1：

 输入：s = "leEeetcode"
 输出："leetcode"
 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。
 示例 2：

 输入：s = "abBAcC"
 输出：""
 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如：
 "abBAcC" --> "aAcC" --> "cC" --> ""
 "abBAcC" --> "abBA" --> "aA" --> ""
 示例 3：

 输入：s = "s"
 输出："s"
  

 提示：

 1 <= s.length <= 100
 s 只包含小写和大写英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/make-the-string-great
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param s
     * @return
     */
    public String makeGood(String s) {

        // 从前往后 遍历 记录处理ok的字符串 如果当前字符与之前处理好的组成可删除组合
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (builder.length() == 0) {
                builder.append(ch);
                continue;
            }
            char lastCh = builder.charAt(builder.length() - 1);
            if (Character.isUpperCase(lastCh) && Character.isUpperCase(ch)) {
                builder.append(ch);
                continue;
            }
            if (Character.isLowerCase(ch) && Character.isLowerCase(lastCh)) {
                builder.append(ch);
                continue;
            }
            // 一个大写一个小写
            if (Character.isUpperCase(ch) && Character.toLowerCase(ch) == lastCh) {
                builder.deleteCharAt(builder.length()-1);
                continue;
            }
            // 删除这两个字符
            if (Character.isUpperCase(lastCh) && Character.toLowerCase(lastCh) == ch) {
                builder.deleteCharAt(builder.length()-1);
                continue;
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "leEeetcode";
        String s = solution.makeGood(word);
        System.out.println(s);
        Assert.assertEquals("leetcode", s);
    }
}
