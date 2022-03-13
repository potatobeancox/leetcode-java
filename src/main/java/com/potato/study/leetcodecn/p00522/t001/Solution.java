package com.potato.study.leetcodecn.p00522.t001;


import org.junit.Assert;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 522. 最长特殊序列 II
 *
 * 给定字符串列表 strs ，返回 它们中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。

 最长特殊序列 定义如下：该序列为某字符串 独有的最长子序列（即不能是其他字符串的子序列）。

  s 的 子序列可以通过删去字符串 s 中的某些字符实现。

 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
  

 示例 1：

 输入: strs = ["aba","cdc","eae"]
 输出: 3
 示例 2:

 输入: strs = ["aaa","aaa","aa"]
 输出: -1
  

 提示:

 2 <= strs.length <= 50
 1 <= strs[i].length <= 10
 strs[i] 只包含小写英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-uncommon-subsequence-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findLUSlength(String[] strs) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String word = strs[i];
            int length = word.length();
            for (int j = 1; j < (1 << length); j++) {
                StringBuilder builder = new StringBuilder();
                String s = Integer.toBinaryString(j);
                // 补充 前缀0
                for (int k = 0; k < s.length(); k++) {
                    if (s.charAt(s.length() - k - 1) == '1') {
                        builder.append(word.charAt(word.length() - k - 1));
                    }
                }
                String key = builder.toString();
                Integer count = wordCountMap.getOrDefault(key, 0);
                count++;
                wordCountMap.put(key, count);
            }
        }
        // 遍历 wordCountMap 找到 次数为 1的 最长的字符串
        int maxLength = 0;
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            Integer count = entry.getValue();
            if (count == 1) {
                maxLength = Math.max(maxLength, entry.getKey().length());
            }
        }
        return maxLength == 0 ? -1 : maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = new String[] {
                "aabbcc", "aabbcc","c"
        };
        int luSlength = solution.findLUSlength(strs);
        System.out.println(luSlength);
        Assert.assertEquals(-1, luSlength);


        strs = new String[] {
                "aabbcc", "aabbcc","b"
        };
        luSlength = solution.findLUSlength(strs);
        System.out.println(luSlength);
        Assert.assertEquals(-1, luSlength);

    }

}
