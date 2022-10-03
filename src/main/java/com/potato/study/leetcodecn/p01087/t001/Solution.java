package com.potato.study.leetcodecn.p01087.t001;

import java.util.*;

/**
 * 1087. 花括号展开
 *
 * 给定一个表示单词列表的字符串 s 。单词中的每个字母都有一个或多个选项。
 *
 * 如果有一个选项，则字母按原样表示。
 * 如果有多个选项，则用大括号分隔选项。例如,  "{a,b,c}"  表示选项  ["a", "b", "c"]  。
 * 例如，如果  s = "a{b,c}"  ，第一个字符总是 'a' ，但第二个字符可以是 'b' 或 'c' 。原来的列表是 ["ab", "ac"] 。
 *
 * 请你 按字典顺序 ，返回所有以这种方式形成的单词。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "{a,b}c{d,e}f"
 * 输出：["acdf","acef","bcdf","bcef"]
 * 示例 2：
 *
 * 输入：s = "abcd"
 * 输出：["abcd"]
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 50
 * s 由括号 '{}' , ',' 和小写英文字母组成。
 * s 保证是一个有效的输入。
 * 没有嵌套的大括号。
 * 在一对连续的左括号和右括号内的所有字符都是不同的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/brace-expansion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String[] expand(String s) {
        // 回溯生成
        List<String> resultList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        backtrack(s, 0, resultList, tempList);
        // list -> array
        String[] resultArray = new String[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        Arrays.sort(resultArray);
        return resultArray;
    }

    /**
     *
     * @param s
     * @param index     本次开始遍历的index
     * @param resultList 结果集
     * @param tempList 当前生成的字符串
     */
    private void backtrack(String s, int index, List<String> resultList, List<String> tempList) {
        // 终止条件 当前index 已经到了最后
        if (index >= s.length()) {
            resultList.addAll(tempList);
            return;
        }
        // 如果当前字幕 是，  {a,b,c}  往下一个字幕 如果是 字母 往结果集合中添加一下 否则 是 { 找到下一个 }
        char c = s.charAt(index);
        if (c == ',') {
            index++;
            c = s.charAt(index);
        }
        if (Character.isAlphabetic(c)) {
            // 修改 temp
            List<String> nextList = new ArrayList<>();
            if (tempList.size() == 0) {
                nextList.add(String.valueOf(c));
            } else {
                for (String temp : tempList) {
                    nextList.add(temp + c);
                }
            }
            backtrack(s, index + 1, resultList, nextList);
        } else {
            // { find }
            int leftIndex = index;
            //  对于 「」 之前字母 按照 ， split 分别组装的 currentList 上面
            int p = index;
            while (p < s.length() && s.charAt(p) != '}') {
                p++;
            }
            // 边界
            if (p >= s.length() || s.charAt(p) != '}') {
                throw new RuntimeException("{}字符串非法");
            }
            int rightIndex = p;
            String charListStr = s.substring(leftIndex + 1, rightIndex);
            String[] split = charListStr.split(",");
            // 每个元素是一个 字母
            List<String> nextList = new ArrayList<>();
            for (String charString : split) {
                // 如果是 空的话 直接添加
                if (tempList.size() == 0) {
                    nextList.add(charString);
                    continue;
                }
                // 遍历每个结果
                for (String temp : tempList) {
                    nextList.add(temp + charString);
                }
            }
            // 下一个位置的结果
            backtrack(s, rightIndex + 1, resultList, nextList);

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "{a,b}c{d,e}f";
        String[] expand = solution.expand(input);
        System.out.println(Arrays.toString(expand));
    }
}
