package com.potato.study.leetcodecn.p00527.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 527. 单词缩写
 *
 * 给你一个字符串数组 words ，该数组由 互不相同 的若干字符串组成，请你找出并返回每个单词的 最小缩写 。
 *
 * 生成缩写的规则如下：
 *
 * 初始缩写由起始字母+省略字母的数量+结尾字母组成。
 * 若存在冲突，亦即多于一个单词有同样的缩写，则使用更长的前缀代替首字母，直到从单词到缩写的映射唯一。换而言之，最终的缩写必须只能映射到一个单词。
 * 若缩写并不比原单词更短，则保留原样。
 *  
 *
 * 示例 1：
 *
 * 输入: words = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * 输出: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 * 示例 2：
 *
 * 输入：words = ["aa","aaa"]
 * 输出：["aa","aaa"]
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 400
 * 2 <= words[i].length <= 400
 * words[i] 由小写英文字母组成
 * words 中的所有字符串 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-abbreviation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {














    public List<String> wordsAbbreviation(List<String> words) {
        // 遍历 words 生成缩写 用res String 数组记录
        int n = words.size();
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = getAbbreviation(words.get(i), 0);
        }
        // 一个prifix 代表每个字符串目前要保留的位置 初始为0 保留第一个字符
        int[] prefix = new int[n];
        // 遍历生成的结果 对于每个排在后面的结果找找看有没有重复的 有的话 收集到一起对于么个重复都更改 prefix +1 再看看只有还有没有重复的
        for (int i = 0; i < n; i++) {
            // 当前 每次修改都要看看有没有重复的
            while (true) {
                String target = res[i];
                // 遍历后面的看看有没有重复的
                List<Integer> dupIndexList = new ArrayList<>();
                for (int j = i+1; j < n; j++) {
                    if (target.equals(res[j])) {
                        dupIndexList.add(j);
                    }
                }
                // 没有充的就用这个
                if (dupIndexList.size() == 0) {
                    break;
                }
                // 有重的 新生成一个 都更改哪些
                dupIndexList.add(i);
                for (int changeIndex : dupIndexList) {
                    prefix[changeIndex]++;
                    res[changeIndex] = getAbbreviation(words.get(changeIndex), prefix[changeIndex]);
                }
            }
        }
        // 转list
        return Arrays.asList(res);
    }


    /**
     *
     * @param word  进行缩写的代词
     * @param prefix    保留的字符截止位置
     * @return
     */
    private String getAbbreviation(String word, int prefix) {
        int len = word.length() - 1 - (prefix + 1);
        if (len <= 1) {
            return word;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(word.substring(0, prefix + 1));
        builder.append(len);
        builder.append(word.charAt(word.length() - 1));
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        // ["like","god","internal","me","internet","interval","intension","face","intrusion"]
        List<String> words = LeetcodeInputUtils.inputString2StringList("[\"like\",\"god\",\"internal\",\"me\",\"internet\",\"interval\",\"intension\",\"face\",\"intrusion\"]");
        List<String> strings = solution.wordsAbbreviation(words);
        System.out.println(strings);
    }
}
