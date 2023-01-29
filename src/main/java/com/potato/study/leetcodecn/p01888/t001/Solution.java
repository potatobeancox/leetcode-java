package com.potato.study.leetcodecn.p01888.t001;

import org.junit.Assert;

/**
 * 1888. 使二进制字符串字符交替的最少反转次数
 *
 * 给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：

 类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
 类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
 请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。

 我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。

 比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。
  

 示例 1：

 输入：s = "111000"
 输出：2
 解释：执行第一种操作两次，得到 s = "100011" 。
 然后对第三个和第六个字符执行第二种操作，得到 s = "101010" 。
 示例 2：

 输入：s = "010"
 输出：0
 解释：字符串已经是交替的。
 示例 3：

 输入：s = "1110"
 输出：1
 解释：对第二个字符执行第二种操作，得到 s = "1010" 。
  

 提示：

 1 <= s.length <= 105
 s[i] 要么是 '0' ，要么是 '1' 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minFlips(String s) {
        // 01010 101010 两种字符串构造
        int n = s.length();
        // 0 开头
        StringBuilder builder1 = new StringBuilder();
        // 1 开头
        StringBuilder builder2 = new StringBuilder();
        // 用数组 分别记录并统计 每个位置 的差
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                builder1.append('0');
                builder2.append('1');
            } else {
                builder1.append('1');
                builder2.append('0');
            }
        }
        // 偶数 直接和翻转一次的 + 1 比 奇数 的话 按照每个位置翻转 + 之前位置的个数进行比较计算最小值
        int[] misCount1 = new int[n];
        int[] misCount2 = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                misCount1[i] += misCount1[i-1];
                misCount2[i] += misCount2[i-1];
            }
            if (s.charAt(i) != builder1.charAt(i)) {
                misCount1[i]++;
            }
            if (s.charAt(i) != builder2.charAt(i)) {
                misCount2[i]++;
            }
        }
        int min = Math.min(misCount1[n-1], misCount2[n-1]);
        if (n % 2 == 0) {
            return min;
        }
        // 奇数还需要选择 旋转点旋转 类型2的次数
        for (int i = 0; i < n; i++) {
            int current = misCount1[i] + misCount2[n-1] - misCount2[i];
            min = Math.min(min, current);

            current = misCount2[i] + misCount1[n-1] - misCount1[i];
            min = Math.min(min, current);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "01001001101";
        int i = solution.minFlips(s);
        System.out.println(i);
        Assert.assertEquals(2, i);


        s = "111000";
        i = solution.minFlips(s);
        System.out.println(i);
        Assert.assertEquals(2, i);


        s = "010";
        i = solution.minFlips(s);
        System.out.println(i);
        Assert.assertEquals(0, i);


        s = "1110";
        i = solution.minFlips(s);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }



}
