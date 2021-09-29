package com.potato.study.leetcodecn.p00168.t001;

import org.junit.Assert;

/**
 * 168. Excel表列名称
 *
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。

 例如，

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB
 ...
 示例 1:

 输入: 1
 输出: "A"
 示例 2:

 输入: 28
 输出: "AB"
 示例 3:

 输入: 701
 输出: "ZY"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     *
     * 将 总的column 转换成从0开始
     *
     * 这样 通过余数定位 最后一个字母
     *
     * 然后n/=26 继续找其他字母
     *
     * 列名称对应列学好
     *
     * number =sum ai*26^i
     *
     * number-1 = sum ai*26^i i 范围【1，n） + a0 - 1
     * i 范围【0，n）
     *
     * ai 1到26
     *
     * number 从1 开始
     *
     * 通过公式接一下
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder builder = new StringBuilder();
        // 计算当前位置应该是什么字符串
        while (n > 0) {
            int a = (n - 1) % 26 + 1;
            builder.append((char) ('A' + a - 1));
            n = (n-1) / 26;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.convertToTitle(1);
        System.out.println(s);
        Assert.assertEquals("A", s);

        s = solution.convertToTitle(28);
        System.out.println(s);
        Assert.assertEquals("AB", s);

        s = solution.convertToTitle(701);
        System.out.println(s);
        Assert.assertEquals("ZY", s);

        s = solution.convertToTitle(52);
        System.out.println(s);
        Assert.assertEquals("AZ", s);


        s = solution.convertToTitle(703);
        System.out.println(s);
        Assert.assertEquals("AAA", s);
    }
}
