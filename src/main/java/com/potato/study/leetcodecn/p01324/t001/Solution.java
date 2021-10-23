package com.potato.study.leetcodecn.p01324.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 1324. 竖直打印单词
 *
 * 给你一个字符串 s。请你按照单词在 s 中的出现顺序将它们全部竖直返回。
 单词应该以字符串列表的形式返回，必要时用空格补位，但输出尾部的空格需要删除（不允许尾随空格）。
 每个单词只能放在一列上，每一列中也只能有一个单词。

  

 示例 1：

 输入：s = "HOW ARE YOU"
 输出：["HAY","ORO","WEU"]
 解释：每个单词都应该竖直打印。
 "HAY"
  "ORO"
  "WEU"
 示例 2：

 输入：s = "TO BE OR NOT TO BE"
 输出：["TBONTB","OEROOE","   T"]
 解释：题目允许使用空格补位，但不允许输出末尾出现空格。
 "TBONTB"
 "OEROOE"
 "   T"
 示例 3：

 输入：s = "CONTEST IS COMING"
 输出：["CIC","OSO","N M","T I","E N","S G","T"]
  

 提示：

 1 <= s.length <= 200
 s 仅含大写英文字母。
 题目数据保证两个单词之间只有一个空格。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/print-words-vertically
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<String> printVertically(String s) {
        // 按照空格进行split
        String[] split = s.split(" ");
        // 遍历找到 最大长度
        int max = 0;
        for (String word : split) {
            max = Math.max(max, word.length());
        }
        // 遍历 split 每个位置 如果是大于 长度 加空格 否则加单词
        List<String> result = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            StringBuilder builder = new StringBuilder();
            // 每个 位置 遍历结束之后 去掉末尾的空格
            for (int j = 0; j < split.length; j++) {
                if (i < split[j].length()) {
                    builder.append(split[j].charAt(i));
                } else {
                    builder.append(" ");
                }
            }
            while (builder.charAt(builder.length() - 1) == ' ') {
                builder.deleteCharAt(builder.length() -1);
            }
            // 加入list
            result.add(builder.toString());
        }
        return result;
    }
}
