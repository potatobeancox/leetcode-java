package com.potato.study.leetcodecn.p00471.t001;

import org.junit.Assert;

import java.util.List;

/**
 * 471. 编码最短长度的字符串
 *
 * 给定一个 非空 字符串，将其编码为具有最短长度的字符串。

 编码规则是：k[encoded_string]，其中在方括号 encoded_string 中的内容重复 k 次。

 注：

 k 为正整数
 如果编码的过程不能使字符串缩短，则不要对其进行编码。如果有多种编码方式，返回 任意一种 即可
  

 示例 1：

 输入：s = "aaa"
 输出："aaa"
 解释：无法将其编码为更短的字符串，因此不进行编码。
 示例 2：

 输入：s = "aaaaa"
 输出："5[a]"
 解释："5[a]" 比 "aaaaa" 短 1 个字符。
 示例 3：

 输入：s = "aaaaaaaaaa"
 输出："10[a]"
 解释："a9[a]" 或 "9[a]a" 都是合法的编码，和 "10[a]" 一样长度都为 5。
 示例 4：

 输入：s = "aabcaabcd"
 输出："2[aabc]d"
 解释："aabc" 出现两次，因此一种答案可以是 "2[aabc]d"。
 示例 5：

 输入：s = "abbbabbbcabbbabbbc"
 输出："2[2[abbb]c]"
 解释："abbbabbbc" 出现两次，但是 "abbbabbbc" 可以编码为 "2[abbb]c"，因此一种答案可以是 "2[2[abbb]c]"。
  

 提示：

 1 <= s.length <= 150
 s 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/encode-string-with-shortest-length
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 471
    public String encode(String s) {
        // dp ij 从i位置到j位置 最短的简化表示是什么字符
        int n = s.length();
        String[][] dp = new String[n][n];
        // 外部循环遍历 len 表示字串的长度
        for (int len = 0; len < n; len++) {
            // 内部循环 枚举开始位置 根据 len计算结束位置
            for (int i = 0; i + len < n; i++) {
                // 右边节点位置 如果 len 小于等于 4 那么还维持原来样子
                int j = i + len - 1;
                if (j < i) {
                    continue;
                }
                String current = s.substring(i, j + 1);
                if (current.length() >= 4) {
                    // 得到开始和结束位置 找到循环节 根据循环节就能估计出来能被 省略多少 并且看看分割的话 能有多少
                    String tmp = current + current;
                    int index = tmp.indexOf(current, 1);
                    // 说明找到了
                    if (index <= current.length()) {
                        int size = index;
                        int count = current.length() / size;
                        current = count + "[" + current.substring(0, index) + "]";
                    }

                }
                // 从中间的某一个点断开看看行不行
                for (int k = i+1; k +1 < j; k++) {
                    if (current.length() > dp[i][k].length() + dp[k+1][j].length()) {
                        current = dp[i][k] + dp[k+1][j];
                    }
                }
                dp[i][j] = current;
            }
        }
        return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String encode = solution.encode("aabcaabcd");
        System.out.println(encode);
        Assert.assertEquals("2[aabc]d", encode);
    }

}
