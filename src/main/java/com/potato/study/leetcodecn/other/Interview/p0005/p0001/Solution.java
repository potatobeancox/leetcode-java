package com.potato.study.leetcodecn.other.Interview.p0005.p0001;


import org.junit.Assert;

import java.util.Arrays;

/**
 * 面试题 05.01. 插入
 *
 * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。

 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。



 题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。

  

 示例1:

 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 输出：N = 1100(10001001100)
 示例2:

 输入： N = 0, M = 31(11111), i = 0, j = 4
 输出：N = 31(11111)

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/insert-into-bits-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int insertBits(int n, int m, int i, int j) {
        String s = Integer.toBinaryString(n);
        char[] chars = s.toCharArray();
        if (chars.length != 32) {
            chars = new char[32];
            int index = 31;
            for (int k = s.length() - 1; k >= 0; k--) {
                chars[index--] = s.charAt(k);
            }
            for (int k = index; k >=0 ; k--) {
                chars[k] = '0';
            }
        }
        String replace = Integer.toBinaryString(m);
        // 替换 s 中的 i到j
        char[] charArray = replace.toCharArray();
        int index = charArray.length - 1;
        for (int k = chars.length - 1 - i; k >= chars.length - 1 - j && k >= 0; k--) {
            if (index >= 0) {
                chars[k] = charArray[index];

                index--;
            } else {
                chars[k] = '0';
            }
        }
        return Integer.parseInt(new String(chars), 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 0;
        int m = 31;
        int i = 0;
        int j = 4;
        int res = solution.insertBits(n,m,i,j);
        System.out.println(res);
        Assert.assertEquals(31, res);
    }
}
