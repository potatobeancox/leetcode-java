package com.potato.study.leetcodecn.p02262.t001;

import java.util.Arrays;

/**
 * 2262. 字符串的总引力
 *
 * 字符串的 引力 定义为：字符串中 不同 字符的数量。

 例如，"abbca" 的引力为 3 ，因为其中有 3 个不同字符 'a'、'b' 和 'c' 。
 给你一个字符串 s ，返回 其所有子字符串的总引力 。

 子字符串 定义为：字符串中的一个连续字符序列。

  

 示例 1：

 输入：s = "abbca"
 输出：28
 解释："abbca" 的子字符串有：
 - 长度为 1 的子字符串："a"、"b"、"b"、"c"、"a" 的引力分别为 1、1、1、1、1，总和为 5 。
 - 长度为 2 的子字符串："ab"、"bb"、"bc"、"ca" 的引力分别为 2、1、2、2 ，总和为 7 。
 - 长度为 3 的子字符串："abb"、"bbc"、"bca" 的引力分别为 2、2、3 ，总和为 7 。
 - 长度为 4 的子字符串："abbc"、"bbca" 的引力分别为 3、3 ，总和为 6 。
 - 长度为 5 的子字符串："abbca" 的引力为 3 ，总和为 3 。
 引力总和为 5 + 7 + 7 + 6 + 3 = 28 。
 示例 2：

 输入：s = "code"
 输出：20
 解释："code" 的子字符串有：
 - 长度为 1 的子字符串："c"、"o"、"d"、"e" 的引力分别为 1、1、1、1 ，总和为 4 。
 - 长度为 2 的子字符串："co"、"od"、"de" 的引力分别为 2、2、2 ，总和为 6 。
 - 长度为 3 的子字符串："cod"、"ode" 的引力分别为 3、3 ，总和为 6 。
 - 长度为 4 的子字符串："code" 的引力为 4 ，总和为 4 。
 引力总和为 4 + 6 + 6 + 4 = 20 。
  

 提示：

 1 <= s.length <= 105
 s 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/total-appeal-of-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/total-appeal-of-a-string/solution/by-endlesscheng-g405/
     * @param s
     * @return
     */
    public long appealSum(String s) {
        // 一次遍历 记录 s中每个字符出现的最后一个位置 每次更新
        int[] lastAppear = new int[26];
        Arrays.fill(lastAppear, -1);
        // 使用 一个 遍历 记录 以当前i作为最后一个 字符的字串 的引力数的和 current = current + (i - pre[这个字母出现最后位置]);
        long result = 0;
        long currentSum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int charIndex = chars[i] - 'a';
            currentSum += (i-lastAppear[charIndex]);
            // 计算结果值
            result += currentSum;
            // 记录最后一次出现的位置
            lastAppear[charIndex] = i;
        }
        return result;
    }
}
