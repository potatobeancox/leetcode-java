package com.potato.study.leetcodecn.p00966.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 966. 元音拼写检查器
 *
 * 在给定单词列表 wordlist 的情况下，我们希望实现一个拼写检查器，将查询单词转换为正确的单词。

 对于给定的查询单词 query，拼写检查器将会处理两类拼写错误：

 大小写：如果查询匹配单词列表中的某个单词（不区分大小写），则返回的正确单词与单词列表中的大小写相同。
 例如：wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 例如：wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 例如：wordlist = ["yellow"], query = "yellow": correct = "yellow"
 元音错误：如果在将查询单词中的元音 ('a', 'e', 'i', 'o', 'u')  分别替换为任何元音后，能与单词列表中的单词匹配（不区分大小写），则返回的正确单词与单词列表中的匹配项大小写相同。
 例如：wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 例如：wordlist = ["YellOw"], query = "yeellow": correct = "" （无匹配项）
 例如：wordlist = ["YellOw"], query = "yllw": correct = "" （无匹配项）
 此外，拼写检查器还按照以下优先级规则操作：

 当查询完全匹配单词列表中的某个单词（区分大小写）时，应返回相同的单词。
 当查询匹配到大小写问题的单词时，您应该返回单词列表中的第一个这样的匹配项。
 当查询匹配到元音错误的单词时，您应该返回单词列表中的第一个这样的匹配项。
 如果该查询在单词列表中没有匹配项，则应返回空字符串。
 给出一些查询 queries，返回一个单词列表 answer，其中 answer[i] 是由查询 query = queries[i] 得到的正确单词。

  

 示例 1：

 输入：wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 输出：["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 示例 2:

 输入：wordlist = ["yellow"], queries = ["YellOw"]
 输出：["yellow"]
  

 提示：

 1 <= wordlist.length, queries.length <= 5000
 1 <= wordlist[i].length, queries[i].length <= 7
 wordlist[i] 和 queries[i] 只包含英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/vowel-spellchecker
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 966
    public String[] spellchecker(String[] wordlist, String[] queries) {
        // 一个 set 两个map key 是替换之后 的 残次 value 是 第一个出现的 单词
        Set<String> originalWordSet = new HashSet<>();
        Map<String,String> lowerMap = new HashMap<>();
        Map<String,String> vowelMap = new HashMap<>();
        // set 是记录 完全相同的单词 另外 其他key 使用 小写 和 。 替代
        for (String word : wordlist) {
            originalWordSet.add(word);
            String lowerCase = word.toLowerCase();
            if (!lowerMap.containsKey(lowerCase)) {
                lowerMap.put(lowerCase, word);
            }
            String vowelReplaceKey = getVowelReplaceKey(word);
            if (!vowelMap.containsKey(vowelReplaceKey)) {
                vowelMap.put(vowelReplaceKey, word);
            }
        }
        // queries 遍历
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (originalWordSet.contains(queries[i])) {
                result[i] = queries[i];
                continue;
            }
            String lowerCase = queries[i].toLowerCase();
            if (lowerMap.containsKey(lowerCase)) {
                result[i] = lowerMap.get(lowerCase);
                continue;
            }
            String vowelReplaceKey = getVowelReplaceKey(queries[i]);
            if (vowelMap.containsKey(vowelReplaceKey)) {
                result[i] = vowelMap.get(vowelReplaceKey);
                continue;
            }
            result[i] = "";
        }
        return result;
    }

    private String getVowelReplaceKey(String word) {
        char[] chars = word.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'a'
                    || chars[i] == 'e'
                    || chars[i] == 'i'
                    || chars[i] == 'o'
                    || chars[i] == 'u') {
                chars[i] = '-';
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] wordlist = new String[] {
                "KiTe","kite","hare","Hare"
        };
        String[] queries = new String[] {
                "kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"
        };
        String[] spellchecker = solution.spellchecker(wordlist, queries);
        System.out.println(Arrays.toString(spellchecker));
        // ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
        Assert.assertArrayEquals(new String[] {
                "kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"
        }, spellchecker);
    }


}
