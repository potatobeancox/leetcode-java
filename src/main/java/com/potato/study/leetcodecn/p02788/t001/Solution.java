package com.potato.study.leetcodecn.p02788.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 2788. 按分隔符拆分字符串
 *
 * 给你一个字符串数组 words 和一个字符 separator ，请你按 separator 拆分 words 中的每个字符串。
 *
 * 返回一个由拆分后的新字符串组成的字符串数组，不包括空字符串 。
 *
 * 注意
 *
 * separator 用于决定拆分发生的位置，但它不包含在结果字符串中。
 * 拆分可能形成两个以上的字符串。
 * 结果字符串必须保持初始相同的先后顺序。
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["one.two.three","four.five","six"], separator = "."
 * 输出：["one","two","three","four","five","six"]
 * 解释：在本示例中，我们进行下述拆分：
 *
 * "one.two.three" 拆分为 "one", "two", "three"
 * "four.five" 拆分为 "four", "five"
 * "six" 拆分为 "six"
 *
 * 因此，结果数组为 ["one","two","three","four","five","six"] 。
 * 示例 2：
 *
 * 输入：words = ["$easy$","$problem$"], separator = "$"
 * 输出：["easy","problem"]
 * 解释：在本示例中，我们进行下述拆分：
 *
 * "$easy$" 拆分为 "easy"（不包括空字符串）
 * "$problem$" 拆分为 "problem"（不包括空字符串）
 *
 * 因此，结果数组为 ["easy","problem"] 。
 * 示例 3：
 *
 * 输入：words = ["|||"], separator = "|"
 * 输出：[]
 * 解释：在本示例中，"|||" 的拆分结果将只包含一些空字符串，所以我们返回一个空数组 [] 。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * words[i] 中的字符要么是小写英文字母，要么就是字符串 ".,|$#@" 中的字符（不包括引号）
 * separator 是字符串 ".,|$#@" 中的某个字符（不包括引号）
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/split-strings-by-separator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> list = new ArrayList<>();
        // 遍历 words 将每个 word 按照 separator split
        String separatorStr;
        if (separator == '.') {
            separatorStr = "\\.";
        } else if (separator == '$') {
            separatorStr = "\\$";
        } else if (separator == '|') {
            separatorStr = "\\|";
        } else {
            separatorStr = String.valueOf(separator);
        }
        for (String word : words) {
            String[] split = word.split(separatorStr);
            if (split == null) {
                continue;
            }
            for (String s : split) {
                if (null == s || s.length() == 0) {
                    continue;
                }
                list.add(s);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> words = LeetcodeInputUtils.inputString2StringList("[\"one.two.three\",\"four.five\",\"six\"]");
        char separator = '.';
        List<String> strings = solution.splitWordsBySeparator(words, separator);
        System.out.println(strings);

        words = LeetcodeInputUtils.inputString2StringList("[\"$easy$\",\"$problem$\"]");
        separator = '$';
        strings = solution.splitWordsBySeparator(words, separator);
        System.out.println(strings);
    }


}
