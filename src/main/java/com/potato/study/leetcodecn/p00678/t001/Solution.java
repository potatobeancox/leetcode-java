package com.potato.study.leetcodecn.p00678.t001;

import org.junit.Assert;

/**
 * 678. 有效的括号字符串
 *
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 *
 * 输入: "()"
 * 输出: True
 * 示例 2:
 *
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 *
 * 输入: "(*))"
 * 输出: True
 * 注意:
 *
 * 字符串大小将在 [1，100] 范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 678 (*)
    public boolean checkValidString(String s) {
        return isValidString(s, 0, 0);
    }

    /**
     *
     * @param s
     * @param index
     * @param state 状态，大于0 说明是 （ 多一些
     * @return
     */
    private boolean isValidString(String s, int index, int state) {
        // 终止条件
        if (index >= s.length()) {
            return state == 0;
        }
        if (state < 0) {
            return false;
        }
        char ch = s.charAt(index++);
        if (ch == '(') {
            state++;
            return isValidString(s, index, state);
        }
        if (ch == ')') {
            state--;
            if (state < 0) {
                return false;
            }
            return isValidString(s, index, state);
        }
        // * 处理
        return isValidString(s, index, state + 1)
                || isValidString(s, index, state - 1) || isValidString(s, index, state);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "()";
        boolean b = solution.checkValidString(s);
        System.out.println(b);
        Assert.assertEquals(true, b);


        s = "(*))";
        b = solution.checkValidString(s);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }

}
