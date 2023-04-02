package com.potato.study.leetcodecn.other.swordoffer2.p0065.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 剑指 Offer II 065. 最短的单词编码
 *
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：

 words.length == indices.length
 助记字符串 s 以 '#' 字符结尾
 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。

  

 示例 1：

 输入：words = ["time", "me", "bell"]
 输出：10
 解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
 words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 示例 2：

 输入：words = ["t"]
 输出：2
 解释：一组有效编码为 s = "t#" 和 indices = [0] 。
  

 提示：

 1 <= words.length <= 2000
 1 <= words[i].length <= 7
 words[i] 仅由小写字母组成
  

 注意：本题与主站 820 题相同： https://leetcode-cn.com/problems/short-encoding-of-words/



 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/iSwD2y
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 065 不想写字典树了
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        Set<String> dictionarySet = new HashSet<>();
        for (String word : words) {
            // 找到后缀 并从 dic 中删除
            if (dictionarySet.isEmpty()) {
                dictionarySet.add(word);
                continue;
            }
            for (int i = 0; i < word.length(); i++) {
                String sub = word.substring(i);
                if (dictionarySet.contains(sub)) {
                    dictionarySet.remove(sub);
                }
            }
            dictionarySet.add(word);
        }
        // 找到 set 中的单词长度的和
        int totalLength = 0;
        for (String dic : dictionarySet) {
            totalLength += dic.length();
            totalLength += 1;
        }
        return totalLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "time","me","bell"
        };
        int i = solution.minimumLengthEncoding(words);
        System.out.println(i);
        Assert.assertEquals(10, i);
    }

}
