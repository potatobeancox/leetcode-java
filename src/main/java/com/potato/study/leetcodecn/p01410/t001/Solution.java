package com.potato.study.leetcodecn.p01410.t001;

import org.junit.Assert;

/**
 * 1410. HTML 实体解析器
 *
 * HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。

 HTML 里这些特殊字符和它们对应的字符实体包括：

 双引号：字符实体为 &quot; ，对应的字符是 " 。
 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 大于号：字符实体为 &gt; ，对应的字符是 > 。
 小于号：字符实体为 &lt; ，对应的字符是 < 。
 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。

  

 示例 1：

 输入：text = "&amp; is an HTML entity but &ambassador; is not."
 输出："& is an HTML entity but &ambassador; is not."
 解释：解析器把字符实体 &amp; 用 & 替换
 示例 2：

 输入：text = "and I quote: &quot;...&quot;"
 输出："and I quote: \"...\""
 示例 3：

 输入：text = "Stay home! Practice on Leetcode :)"
 输出："Stay home! Practice on Leetcode :)"
 示例 4：

 输入：text = "x &gt; y &amp;&amp; x &lt; y is always false"
 输出："x > y && x < y is always false"
 示例 5：

 输入：text = "leetcode.com&frasl;problemset&frasl;all"
 输出："leetcode.com/problemset/all"
  

 提示：

 1 <= text.length <= 10^5
 字符串可能包含 256 个ASCII 字符中的任意字符。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/html-entity-parser
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String entityParser(String text) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        char[] chars = text.toCharArray();
        while (index < text.length()) {
            // 找到&
            while (index < text.length() && chars[index] != '&') {
                builder.append(chars[index]);
                index++;
            }
            if (index >= text.length()) {
                break;
            }
            // 找到对应的 ;
            int nextIndex = index + 1;
            while (nextIndex < text.length() && chars[nextIndex] != ';') {
                if (chars[nextIndex] == '&') {
                    builder.append(text.substring(index, nextIndex));
                    index = nextIndex;
                }
                nextIndex++;
            }
            // 找到最后都没有找到
            if (nextIndex == text.length()) {
                builder.append(text.substring(index));
                break;
            }
            String html = text.substring(index, nextIndex + 1);
            switch (html) {
                case "&quot;" :
                    builder.append("\"");
                    break;
                case "&apos;" :
                    builder.append("'");
                    break;
                case "&amp;" :
                    builder.append("&");
                    break;
                case "&gt;" :
                    builder.append(">");
                    break;
                case "&lt;" :
                    builder.append("<");
                    break;
                case "&frasl;" :
                    builder.append("/");
                    break;
                default:
                    builder.append(html);
            }
            index = nextIndex + 1;

        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "&amp; is an HTML entity but &ambassador; is not.";
        String s = solution.entityParser(input);
        System.out.println(s);
        Assert.assertEquals("& is an HTML entity but &ambassador; is not.", s);


        input = "&&gt;";
        s = solution.entityParser(input);
        System.out.println(s);
        Assert.assertEquals("&>", s);
    }
}
