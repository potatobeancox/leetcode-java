package com.potato.study.leetcodecn.p00555.t001;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 555. 分割连接字符串
 *
 * 给定一个字符串列表 strs，你可以将这些字符串连接成一个循环字符串，对于每个字符串，你可以选择是否翻转它。在所有可能的循环字符串中，你需要分割循环字符串（这将使循环字符串变成一个常规的字符串），然后找到字典序最大的字符串。

 具体来说，要找到字典序最大的字符串，你需要经历两个阶段：

 将所有字符串连接成一个循环字符串，你可以选择是否翻转某些字符串，并按照给定的顺序连接它们。
 在循环字符串的某个位置分割它，这将使循环字符串从分割点变成一个常规的字符串。
 你的工作是在所有可能的常规字符串中找到字典序最大的一个。

  

 示例 1:

 输入: strs = ["abc","xyz"]
 输出: "zyxcba"
 解释: 你可以得到循环字符串 "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-"，其中 '-' 代表循环状态。
 答案字符串来自第四个循环字符串， 你可以从中间字符 'a' 分割开然后得到 "zyxcba"。
 示例 2:

 输入: strs = ["abc"]
 输出: "cba"
  

 提示:

 1 <= strs.length <= 1000
 1 <= strs[i].length <= 1000
 1 <= sum(strs[i].length) <= 1000
 strs[i] 只包含小写英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/split-concatenated-strings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/split-concatenated-strings/solution/555-fen-ge-lian-jie-zi-fu-chuan-by-klb/
     * @param strs
     * @return
     */
    public String splitLoopedString(String[] strs) {
        // 处理 strs 全调整到 字母降序
        for (int i = 0; i < strs.length; i++) {
            StringBuilder builder = new StringBuilder(strs[i]);

            String reverse = builder.reverse().toString();
            if (reverse.compareTo(strs[i]) > 0) {
                strs[i] = reverse;
            }
        }
        // 遍历每一个位置 str 其他单词拼接到一块 ，对于每个str的位置 i 分分一下看看 最大的是哪个
        String res = null;
        for (int i = 0; i < strs.length; i++) {
            // 其他字符连接在一起，先添加后面的字符再添加前面的
            StringBuilder others = new StringBuilder();
            for (int j = i+1; j < strs.length; j++) {
                others.append(strs[j]);
            }
            // i前面的
            for (int j = 0; j < i; j++) {
                others.append(strs[j]);
            }
            // i 如何拆
            String target = strs[i];
            res = getTargetString(res, others, target);

            StringBuilder builder = new StringBuilder(strs[i]);
            String reverse = builder.reverse().toString();
            res = getTargetString(res, others, reverse);
        }
        return res;
    }

    /**
     * 遍历 target 找到 字母表顺序 最大的
     * @param res
     * @param others
     * @param target
     * @return
     */
    private String getTargetString(String res, StringBuilder others, String target) {
        for (int j = 0; j <= target.length(); j++) {
            // 前面
            String end = target.substring(0, j);
            String start = target.substring(j);

            String finalWord = start + others.toString() + end;

            if (res == null || finalWord.compareTo(res) > 0) {
                res = finalWord;
            }
        }
        return res;
    }

}
