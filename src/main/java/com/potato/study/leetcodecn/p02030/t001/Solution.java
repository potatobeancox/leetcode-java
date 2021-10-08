package com.potato.study.leetcodecn.p02030.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 2030. 含特定字母的最小子序列
 *
 * 给你一个字符串 s ，一个整数 k ，一个字母 letter 以及另一个整数 repetition 。

 返回 s 中长度为 k 且 字典序最小 的子序列，该子序列同时应满足字母 letter 出现 至少 repetition 次。生成的测试用例满足 letter 在 s 中出现 至少 repetition 次。

 子序列 是由原字符串删除一些（或不删除）字符且不改变剩余字符顺序得到的剩余字符串。

 字符串 a 字典序比字符串 b 小的定义为：在 a 和 b 出现不同字符的第一个位置上，字符串 a 的字符在字母表中的顺序早于字符串 b 的字符。

  

 示例 1：

 输入：s = "leet", k = 3, letter = "e", repetition = 1
 输出："eet"
 解释：存在 4 个长度为 3 ，且满足字母 'e' 出现至少 1 次的子序列：
 - "lee"（"leet"）
 - "let"（"leet"）
 - "let"（"leet"）
 - "eet"（"leet"）
 其中字典序最小的子序列是 "eet" 。
 示例 2：



 输入：s = "leetcode", k = 4, letter = "e", repetition = 2
 输出："ecde"
 解释："ecde" 是长度为 4 且满足字母 "e" 出现至少 2 次的字典序最小的子序列。
 示例 3：

 输入：s = "bb", k = 2, letter = "b", repetition = 2
 输出："bb"
 解释："bb" 是唯一一个长度为 2 且满足字母 "b" 出现至少 2 次的子序列。
  

 提示：

 1 <= repetition <= k <= s.length <= 5 * 104
 s 由小写英文字母组成
 letter 是一个小写英文字母，在 s 中至少出现 repetition 次


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/solution/cpython3java-1dan-diao-zhan-by-hanxin_ha-xwv7/
     * @param s
     * @param k
     * @param letter
     * @param repetition
     * @return
     */
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        // 统计 每个位置之后 letter 的个数
        int[] letterCount = new int[s.length()];
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (letter == ch) {
                count++;
            }
            letterCount[i] = count;
        }
        // 存放字符串
        StringBuilder builder = new StringBuilder();
        // 当前builder 中有多少个 letter；
        int builderLetterCount = 0;
        // 从前往后遍历 如果还能pop就pop
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 如果 ch 比当前builder 小，满足字典序，看看能不能pop
            while (builder.length() > 0 && ch < builder.charAt(builder.length() - 1)) {
                // 判断最后一个字符 中 letter个数
                boolean isLastBuilerLetter = false;
                if (letter == builder.charAt(builder.length() - 1)) {
                    isLastBuilerLetter = true;
                }
                // 当前 还有多少个字符串没有用
                int remindTotalLetter = s.length() - i;
                // 删除一个字符就会比k少直接不删除了
                if (builder.length() + remindTotalLetter <= k) {
                    break;
                }
                // 当前位置之后还有多少个 letter 不能继续弹出了, letter 了
                if (isLastBuilerLetter
                        && builderLetterCount + letterCount[i] <= repetition) {
                    break;
                }
                // 弹出
                builder.deleteCharAt(builder.length() - 1);
                if (isLastBuilerLetter) {
                    builderLetterCount--;
                }
            }
            // 入栈的时候进行数量控制 1.当前是 letter 或者 当前的需要的字符数，比需要的 letter多 且 数量控制
            if ((ch == letter || k - builder.length() > repetition - builderLetterCount) && builder.length() < k) {
                builder.append(ch);
                if (ch == letter) {
                    builderLetterCount++;
                }
            }

        }

        // 如何判断能否pop 当前letter 还够 当前剩余的字符串还能弥补亏空 就可以pop 否则 只能 往里放
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "leet";
        int k = 3;
        char letter = 'e';
        int repetition = 1;
        String s = solution.smallestSubsequence(str, k, letter, repetition);
        System.out.println(s);
        Assert.assertEquals("eet", s);


        str = "leetcode";
        k = 4;
        letter = 'e';
        repetition = 2;
        s = solution.smallestSubsequence(str, k, letter, repetition);
        System.out.println(s);
        Assert.assertEquals("ecde", s);


        str = "bb";
        k = 2;
        letter = 'b';
        repetition = 2;
        s = solution.smallestSubsequence(str, k, letter, repetition);
        System.out.println(s);
        Assert.assertEquals("bb", s);


        str = "aaabbbcccddd";
        k = 3;
        letter = 'b';
        repetition = 2;
        s = solution.smallestSubsequence(str, k, letter, repetition);
        System.out.println(s);
        Assert.assertEquals("abb", s);
    }

}

