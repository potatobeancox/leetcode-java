package com.potato.study.leetcodecn.p00616.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 616. 给字符串添加加粗标签
 *
 * 给你一个字符串 s 和一个字符串列表 words ，你需要将在字符串列表中出现过的 s 的子串添加加粗闭合标签 <b> 和 </b> 。

 如果两个子串有重叠部分，你需要把它们一起用一对闭合标签包围起来。同理，如果两个子字符串连续被加粗，那么你也需要把它们合起来用一对加粗标签包围。

 返回添加加粗标签后的字符串 s 。

  

 示例 1：

 输入： s = "abcxyz123", words = ["abc","123"]
 输出："<b>abc</b>xyz<b>123</b>"
 示例 2：

 输入：s = "aaabbcc", words = ["aaa","aab","bc"]
 输出："<b>aaabbc</b>c"
  

 提示：

 1 <= s.length <= 1000
 0 <= words.length <= 100
 1 <= words[i].length <= 1000
 s 和 words[i] 由英文字母和数字组成
 words 中的所有值 互不相同
  

 注：此题与「758 - 字符串中的加粗单词」相同 - https://leetcode-cn.com/problems/bold-words-in-string

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/add-bold-tag-in-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String addBoldTag(String s, String[] words) {
        boolean[] status = new boolean[s.length()];
        for (String word : words) {
            // s 中循环找
            int fromIndex = s.indexOf(word);
            while (fromIndex >= 0) {
                // 更改自按照
                for (int i = 0; i < word.length(); i++) {
                    status[fromIndex + i] = true;
                }
                fromIndex = s.indexOf(word, fromIndex + 1);
            }
        }
        // 遍历 status 对于 切换成 高亮之前和之后 加了字符
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < status.length; i++) {
            if (status[i] && (i == 0 || !status[i-1])) {
                builder.append("<b>");
            }
            builder.append(chars[i]);
            // 是不是需要终止符号
            if (status[i] && (i == status.length - 1 || !status[i+1])) {
                builder.append("</b>");
            }
        }
        return builder.toString();
    }
}
