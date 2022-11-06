package com.potato.study.leetcodecn.p01573.t001;

import org.junit.Assert;

/**
 * 1573. 分割字符串的方案数
 *
 * 给你一个二进制串 s  （一个只包含 0 和 1 的字符串），我们可以将 s 分割成 3 个 非空 字符串 s1, s2, s3 （s1 + s2 + s3 = s）。

 请你返回分割 s 的方案数，满足 s1，s2 和 s3 中字符 '1' 的数目相同。

 由于答案可能很大，请将它对 10^9 + 7 取余后返回。

  

 示例 1：

 输入：s = "10101"
 输出：4
 解释：总共有 4 种方法将 s 分割成含有 '1' 数目相同的三个子字符串。
 "1|010|1"
 "1|01|01"
 "10|10|1"
 "10|1|01"
 示例 2：

 输入：s = "1001"
 输出：0
 示例 3：

 输入：s = "0000"
 输出：3
 解释：总共有 3 种分割 s 的方法。
 "0|0|00"
 "0|00|0"
 "00|0|0"
 示例 4：

 输入：s = "100100010100110"
 输出：12
  

 提示：

 s[i] == '0' 或者 s[i] == '1'
 3 <= s.length <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-ways-to-split-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numWays(String s) {
        // 统计1的个数 看看能不能被3 整除
        int oneCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                oneCount++;
            }
        }
        if (oneCount % 3 != 0) {
            return 0;
        }
        // 都是 0
        if (oneCount == 0) {
            // 中间空当数 s.len -1 中间取2个
            if (s.length() - 1 < 2) {
                return 0;
            }
            int mod = 1_000_000_000 + 7;
            return (int) (((long)s.length() - 1) * (s.length() - 2) / 2 % mod);
        }
        long target = oneCount / 3;
        // 找到第一段结尾位置 第二段开始位置 ，第二段结尾位置 第三段开始位置
        char[] chars = s.toCharArray();
        long finishIndex2 = -1;
        long finishIndex3 = -1;

        long startIndex2 = -1;
        long startIndex3 = -1;

        long currentOneCount = 0;
        // 能被整除的时候 遍历 s 计数 分别计算2次分割点的开始位置和结束位置 根据这两个位置 找到第一段可能的种类数 * 第二段
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '1') {
                currentOneCount++;
                if (currentOneCount == target) {
                    startIndex2 = i + 1;
                } else if (currentOneCount == target * 2) {
                    startIndex3 = i + 1;
                }

                if (currentOneCount == target + 1) {
                    finishIndex2 = i;
                } else if (currentOneCount == target * 2 + 1) {
                    finishIndex3 = i;
                }
            }
        }
        // 计算可能的次数
        long a = finishIndex2 - startIndex2 + 1;
        long b = finishIndex3 - startIndex3 + 1;
        int mod = 1_000_000_000 + 7;
        return (int) ((a % mod) * (b % mod) % mod);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.numWays("10101");
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
