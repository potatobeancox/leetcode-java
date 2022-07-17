package com.potato.study.leetcodecn.p01180.t001;


/**
 * 1180. 统计只含单一字母的子串
 *
 * 给你一个字符串 s，返回 只含 单一字母 的子串个数 。

 示例 1：

 输入： s = "aaaba"
 输出： 8
 解释： 只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
 "aaa" 出现 1 次。
 "aa" 出现 2 次。
 "a" 出现 4 次。
 "b" 出现 1 次。
 所以答案是 1 + 2 + 4 + 1 = 8。
 示例 2:

 输入： s = "aaaaaaaaaa"
 输出： 55
  

 提示：

 1 <= s.length <= 1000
 s[i] 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int countLetters(String s) {
        // 随便弄一个字符只要不是字母 就可以让第一次不用写if
        char lastCh = '#';
        int chCount = 0;
        int totalCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != lastCh) {
                chCount = 1;
                lastCh = ch;
            } else {
                chCount++;
            }
            // 以ch 结尾的 子串个数
            totalCount += chCount;

        }
        return totalCount;
    }



}
