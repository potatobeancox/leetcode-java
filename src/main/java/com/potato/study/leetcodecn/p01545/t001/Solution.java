package com.potato.study.leetcodecn.p01545.t001;

import org.junit.Assert;

/**
 * 1545. 找出第 N 个二进制字符串中的第 K 位
 *
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：

 S1 = "0"
 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。

 例如，符合上述描述的序列的前 4 个字符串依次是：

 S1 = "0"
 S2 = "011"
 S3 = "0111001"
 S4 = "011100110110001"
 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。

  

 示例 1：

 输入：n = 3, k = 1
 输出："0"
 解释：S3 为 "0111001"，其第 1 位为 "0" 。
 示例 2：

 输入：n = 4, k = 11
 输出："1"
 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
 示例 3：

 输入：n = 1, k = 1
 输出："0"
 示例 4：

 输入：n = 2, k = 3
 输出："1"
  

 提示：

 1 <= n <= 20
 1 <= k <= 2n - 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-kth-bit-in-nth-binary-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public char findKthBit(int n, int k) {
        String s = "0";
        for (int i = 1; i < n; i++) {
            s = s + "1" + reverse(invert(s));
        }
        // 找到第k和字母
        return s.charAt(k-1);
    }

    private String invert(String bit) {
        char[] chars = bit.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '1') {
                chars[i] = '0';
            } else {
                chars[i] = '1';
            }
        }
        return new String(chars);
    }

    private String reverse(String word) {
        StringBuilder builder = new StringBuilder();
        builder.append(word);
        return builder.reverse().toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int k = 1;
        char kthBit = solution.findKthBit(n, k);
        System.out.println(kthBit);
        Assert.assertEquals('0', kthBit);
    }
}

