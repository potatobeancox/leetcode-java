package com.potato.study.leetcodecn.p01422.t001;


import org.junit.Assert;

/**
 * 1422. 分割字符串的最大得分
 *
 * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。

 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。

  

 示例 1：

 输入：s = "011101"
 输出：5
 解释：
 将字符串 s 划分为两个非空子字符串的可行方案有：
 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
 示例 2：

 输入：s = "00111"
 输出：5
 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
 示例 3：

 输入：s = "1111"
 输出：3
  

 提示：

 2 <= s.length <= 500
 字符串 s 仅由字符 '0' 和 '1' 组成。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-score-after-splitting-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxScore(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        // 总 0的刷领和 总1的数量
        int totalOne = 0;
        int totalZero = 0;
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '0') {
                totalZero++;
            } else {
                totalOne++;
            }
        }
        int maxScore = 0;
        // 过一遍 记录当前 0的数量 和1的数量 比较一下最高得分
        int leftZero = 0;
        int leftOne = 0;
        if (chars[0] == '0') {
            leftZero++;
        } else {
            leftOne++;
        }
        // i 之前位置是 左边
        for (int i = 1; i < s.length(); i++) {
            // 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
            int currentScore = leftZero + totalOne - leftOne;
            maxScore = Math.max(maxScore, currentScore);
            if (chars[i] == '0') {
                leftZero++;
            } else {
                leftOne++;
            }
        }
        return maxScore;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "011101";
        int i = solution.maxScore(s);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
