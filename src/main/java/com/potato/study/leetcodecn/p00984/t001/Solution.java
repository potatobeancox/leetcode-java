package com.potato.study.leetcodecn.p00984.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 984. 不含 AAA 或 BBB 的字符串
 *
 * 给定两个整数 A 和 B，返回任意字符串 S，要求满足：
 *
 * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
 * 子串 'aaa' 没有出现在 S 中；
 * 子串 'bbb' 没有出现在 S 中。
 *  
 *
 * 示例 1：
 *
 * 输入：A = 1, B = 2
 * 输出："abb"
 * 解释："abb", "bab" 和 "bba" 都是正确答案。
 * 示例 2：
 *
 * 输入：A = 4, B = 1
 * 输出："aabaa"
 *  
 *
 * 提示：
 *
 * 0 <= A <= 100
 * 0 <= B <= 100
 * 对于给定的 A 和 B，保证存在满足要求的 S。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-without-aaa-or-bbb
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 如果 ab 相等 生成策略就是 ab 相间
     * 否则 每次去多的那个 2个 少的那个1 个拼接，知道相等
     * @param a
     * @param b
     * @return
     */
    public String strWithout3a3b(int a, int b) {
        // 如果 ab 相同 直接拼接 依次拼接ab 即可
        StringBuilder builder = new StringBuilder();
        if (a == 0) {
            for (int i = 0; i < b; i++) {
                builder.append("b");
            }
            return builder.toString();
        }
        if (b == 0) {
            for (int i = 0; i < a; i++) {
                builder.append("a");
            }
            return builder.toString();
        }
        while (a != b) {
            // 每次选多的那个选2个 再看看能不能选少的那个
            if (a > b) {
                builder.append("a");
                builder.append("a");
                a -= 2;
                if (a != b && b > 0) {
                    builder.append("b");
                    b--;
                }
            } else {
                // a < b
                builder.append("b");
                builder.append("b");
                b -= 2;
                if (a != b && a > 0) {
                    builder.append("a");
                    a--;
                }
            }
        }
        // 否则 选择 ab 中多的那个 每次消耗2个 少的 消耗一个 或者不消耗
        if (builder.length() == 0) {
            return getSameCountStr(a, true);
        }
        if (builder.charAt(builder.length() - 1) == 'a') {
            builder.append(getSameCountStr(a, false));
        } else {
            builder.append(getSameCountStr(a, true));
        }
        return builder.toString();
    }

    public String getSameCountStr(int n, boolean startWithA) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (startWithA) {
                builder.append("ab");
            } else {
                builder.append("ba");
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = 1;
        int b = 2;
        String str = solution.strWithout3a3b(a, b);
        System.out.println(str);
        Assert.assertEquals("bba", str);


        a = 4;
        b = 1;
        str = solution.strWithout3a3b(a, b);
        System.out.println(str);
        Assert.assertEquals("aabaa", str);


    }

}
