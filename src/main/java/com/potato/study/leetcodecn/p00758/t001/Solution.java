package com.potato.study.leetcodecn.p00758.t001;

import com.potato.study.leetcode.domain.Interval;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 758. 字符串中的加粗单词
 *
 * 给定一个关键词集合 words 和一个字符串 s，将所有 s 中出现的关键词 words[i] 加粗。所有在标签 <b> 和 <b> 中的字母都会加粗。

 加粗后返回 s 。返回的字符串需要使用尽可能少的标签，当然标签应形成有效的组合。

  

 示例 1:

 输入: words = ["ab","bc"], s = "aabcd"
 输出: "a<b>abc</b>d"
 解释: 注意返回 "a<b>a<b>b</b>c</b>d" 会使用更多的标签，因此是错误的。
 示例 2:

 输入: words = ["ab","cb"], s = "aabcd"
 输出: "a<b>ab</b>cd"
  

 提示:

 1 <= s.length <= 500
 0 <= words.length <= 50
 1 <= words[i].length <= 10
 s 和 words[i] 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/bold-words-in-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String boldWords(String[] words, String s) {
        // true 是加粗
        boolean[] blod = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (String word : words) {
                // 看下剩下的够不够
                if (s.length() - i < word.length()) {
                    continue;
                }
                String substring = s.substring(i, i + word.length());
                if (substring.equals(word)) {
                    for (int j = 0; j < word.length(); j++) {
                        blod[j + i] = true;
                    }
                }
            }   
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < blod.length; i++) {
            // 开始的 b
            if ((i == 0 && blod[i])) {
                builder.append("<b>");
            } else if (i > 0 && blod[i] && !blod[i-1]) {
                builder.append("<b>");
            }
            builder.append(s.charAt(i));
            // 结束的b
            if (i == blod.length - 1 && blod[i]) {
                builder.append("</b>");
            } else if (i < blod.length - 1 && blod[i] && !blod[i+1]) {
                builder.append("</b>");
            }
        }
        return builder.toString();
    }


    /**
     * ["ab","bc"]
     "aabcd"
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "ab","bc"
        };
        String s = "aabcd";
        String str = solution.boldWords(words, s);
        System.out.println(str);
        Assert.assertEquals("a<b>abc</b>d", str);
    }
}

