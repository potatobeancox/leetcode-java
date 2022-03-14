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

    /**
     * https://leetcode-cn.com/problems/valid-parenthesis-string/solution/gong-shui-san-xie-yi-ti-shuang-jie-dong-801rq/
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        // （ ++ ） --  * 记录最小值和最大值
        int min = 0;
        int max = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                min++;
                max++;
            } else if (ch == ')') {
                min--;
                max--;
            } else {
                // *
                min--;
                max++;
            }

            if (min < 0) {
                min = 0;
            }
            // 判断 是否已经不行了
            if (min > max) {
                // 隐含 max < 0
                return false;
            }
        }
        return min == 0;
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
