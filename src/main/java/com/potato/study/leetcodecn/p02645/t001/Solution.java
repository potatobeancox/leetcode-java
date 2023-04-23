package com.potato.study.leetcodecn.p02645.t001;

/**
 *
 * 2645. 构造有效字符串的最少插入数
 *
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。

 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。

  

 示例 1：

 输入：word = "b"
 输出：2
 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
 示例 2：

 输入：word = "aaa"
 输出：6
 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
 示例 3：

 输入：word = "abc"
 输出：0
 解释：word 已经是有效字符串，不需要进行修改。
  

 提示：

 1 <= word.length <= 50
 word 仅由字母 "a"、"b" 和 "c" 组成。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-additions-to-make-valid-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2645
    public int addMinimum(String word) {
        // 遍历 每个 word 位置
        int round = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) <= word.charAt(i-1)) {
                round++;
            }
        }
        // 如果当前位置字符 小于等于 前面的位置 说明开始了新的一个周期
        return round * 3 - word.length();
    }



}
