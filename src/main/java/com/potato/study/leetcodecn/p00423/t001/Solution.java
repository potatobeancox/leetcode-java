package com.potato.study.leetcodecn.p00423.t001;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.domain.duplicate.name.Node;

/**
 * 423. 从英文中重建数字
 *
 *
 * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
 *
 * 注意:
 *
 * 输入只包含小写英文字母。
 * 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
 * 输入字符串的长度小于 50,000。
 * 示例 1:
 *
 * 输入: "owoztneoer"
 *
 * 输出: "012" (zeroonetwo)
 * 示例 2:
 *
 * 输入: "fviefuro"
 *
 * 输出: "45" (fourfive)
 *
 */
public class Solution {

    /**
     * 统计s 中 每个字母出现次数
     *
     * nine
     *
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        int[] digit = new int[10];
        if (count[25] > 0) {
            digit[0] = count[25];
            //z e r o
            count['e' - 'a'] -= count[25];
            count['r' - 'a'] -= count[25];
            count['o' - 'a'] -= count[25];
            count['z' - 'a'] = 0;
        }
        if (count['w' - 'a'] > 0) {
            digit[2] = count['w' - 'a'];
            // two
            count['t' - 'a'] -= count['w' - 'a'];
            count['o' - 'a'] -= count['w' - 'a'];
            count['w' - 'a'] = 0;
        }
        if (count['u' - 'a'] > 0) {
            digit[4] = count['u' - 'a'];
            // four
            count['f' - 'a'] -= count['u' - 'a'];
            count['o' - 'a'] -= count['u' - 'a'];
            count['r' - 'a'] -= count['u' - 'a'];
            count['u' - 'a'] = 0;
        }
        if (count['f' - 'a'] > 0) {
            digit[5] = count['f' - 'a'];
            // five
            count['i' - 'a'] -= count['f' - 'a'];
            count['v' - 'a'] -= count['f' - 'a'];
            count['e' - 'a'] -= count['f' - 'a'];
            count['f' - 'a'] = 0;
        }
        if (count['x' - 'a'] > 0) {
            digit[6] = count['x' - 'a'];
            // six
            count['s' - 'a'] -= count['x' - 'a'];
            count['i' - 'a'] -= count['x' - 'a'];
            count['x' - 'a'] = 0;
        }
        if (count['o' - 'a'] > 0) {
            digit[1] = count['o' - 'a'];
            // one
            count['n' - 'a'] -= count['o' - 'a'];
            count['e' - 'a'] -= count['o' - 'a'];
            count['o' - 'a'] = 0;
        }
        if (count['r' - 'a'] > 0) {
            digit[3] = count['r' - 'a'];
            // three
            count['t' - 'a'] -= count['r' - 'a'];
            count['h' - 'a'] -= count['r' - 'a'];
            count['e' - 'a'] -= count['r' - 'a'];
            count['e' - 'a'] -= count['r' - 'a'];
            count['r' - 'a'] = 0;
        }
        if (count['v' - 'a'] > 0) {
            digit[7] = count['v' - 'a'];
            // seven
            count['s' - 'a'] -= count['v' - 'a'];
            count['e' - 'a'] -= count['v' - 'a'];
            count['e' - 'a'] -= count['v' - 'a'];
            count['n' - 'a'] -= count['v' - 'a'];
            count['v' - 'a'] = 0;
        }
        if (count['g' - 'a'] > 0) {
            digit[8] = count['g' - 'a'];
            // eight
            count['e' - 'a'] -= count['g' - 'a'];
            count['i' - 'a'] -= count['g' - 'a'];
            count['h' - 'a'] -= count['g' - 'a'];
            count['t' - 'a'] -= count['g' - 'a'];
            count['g' - 'a'] = 0;
        }
        if (count['i' - 'a'] > 0) {
            digit[9] = count['i' - 'a'];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < digit[i]; j++) {
                builder.append(i);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "owoztneoer";
        String s = solution.originalDigits(input);
        System.out.println(s);
        Assert.assertEquals("012", s);
    }
}
