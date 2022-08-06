package com.potato.study.leetcodecn.p00320.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 320. 列举单词的全部缩写
 *
 * 单词的 广义缩写词 可以通过下述步骤构造：先取任意数量的 不重叠、不相邻 的子字符串，再用它们各自的长度进行替换。

 例如，"abcde" 可以缩写为：
 "a3e"（"bcd" 变为 "3" ）
 "1bcd1"（"a" 和 "e" 都变为 "1"）
 "5" ("abcde" 变为 "5")
 "abcde" (没有子字符串被代替)
 然而，这些缩写是 无效的 ：
 "23"（"ab" 变为 "2" ，"cde" 变为 "3" ）是无效的，因为被选择的字符串是相邻的
 "22de" ("ab" 变为 "2" ， "bc" 变为 "2")  是无效的，因为被选择的字符串是重叠的
 给你一个字符串 word ，返回 一个由 word 的所有可能 广义缩写词 组成的列表 。按 任意顺序 返回答案。

  

 示例 1：

 输入：word = "word"
 输出：["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
 示例 2：

 输入：word = "a"
 输出：["1","a"]
  

 提示：

 1 <= word.length <= 15
 word 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/generalized-abbreviation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<String> generateAbbreviations(String word) {

        int index = 0;
        List<String> resultList = new ArrayList<>();
        backtracking(word, index, resultList, "", 0);
        return resultList;
    }

    private void backtracking(String word, int index, List<String> resultList, String currentStr, int currentNum) {
        if (index >= word.length()) {
            if (currentNum > 0) {
                resultList.add(currentStr + currentNum);
            } else {
                resultList.add(currentStr);
            }
            return;
        }
        // 2种选择 继续用数字或者直接用字母
        // 直接用数字
        backtracking(word, index+1, resultList, currentStr, currentNum + 1);
        // 直接用字母
        if (currentNum > 0) {
            currentStr += currentNum;
        }
        currentStr += String.valueOf(word.charAt(index));
        backtracking(word, index+1, resultList, currentStr, 0);


    }

}
