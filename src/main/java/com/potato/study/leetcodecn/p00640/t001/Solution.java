package com.potato.study.leetcodecn.p00640.t001;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * 640. 求解方程
 *
 * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。

 如果方程没有解，请返回“No solution”。

 如果方程有无限解，则返回“Infinite solutions”。

 如果方程中只有一个解，要保证返回值 x 是一个整数。

 示例 1：

 输入: "x+5-3+x=6+x-2"
 输出: "x=2"
 示例 2:

 输入: "x=x"
 输出: "Infinite solutions"
 示例 3:

 输入: "2x=x"
 输出: "x=0"
 示例 4:

 输入: "2x+3x-6x=x+2"
 输出: "x=-1"
 示例 5:

 输入: "x=x+2"
 输出: "No solution"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/solve-the-equation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String solveEquation(String equation) {
        // 按照 = 分割
        if (!equation.contains("=")) {
            return "No solution";
        }
        // 根据 = 进行 split
        String[] split = equation.split("=");
        if (split.length != 2) {
            return "No solution";
        }
        // 分别对于左边和右边 按照 +- 拆分
        int x = 0;
        int num = 0;
        // 等式左边
        List<String> list = splitByOperator(split[0]);
        for (String op : list) {
            if (op.contains("x")) {
                if ("x".equals(op)) {
                    x += 1;
                } else if ("+x".equals(op)) {
                    x += 1;
                } else if ("-x".equals(op)) {
                    x -= 1;
                } else {
                    x += Integer.parseInt(op.substring(0, op.length() - 1));
                }
            } else {
                num -= Integer.parseInt(op);
            }
        }
        // 等式右边
        list = splitByOperator(split[1]);
        for (String op : list) {
            if (op.contains("x")) {
                if ("x".equals(op)) {
                    x -= 1;
                } else if ("+x".equals(op)) {
                    x -= 1;
                } else if ("-x".equals(op)) {
                    x += 1;
                } else {
                    x -= Integer.parseInt(op.substring(0, op.length() - 1));
                }
            } else {
                num += Integer.parseInt(op);
            }
        }
        if (x == 0) {
            if (num == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("x=");
        builder.append(num / x);
        return builder.toString();
    }


    private List<String> splitByOperator(String equation) {
        char[] chars = equation.toCharArray();
        int i = 0;
        StringBuilder builder = new StringBuilder();
        List<String> resultList = new ArrayList<>();
        while (i < chars.length) {
            char ch = chars[i];
            if (ch == '+' || ch == '-') {
                // 之前积累的字符
                if (builder.length() > 0) {
                    resultList.add(builder.toString());
                }
                builder = new StringBuilder();
                if (ch == '-') {
                    builder.append(ch);
                }
            } else {
                builder.append(ch);
            }
            i++;
        }
        if (builder.length() != 0) {
            resultList.add(builder.toString());
        }
        return resultList;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String equation = "x+5-3+x=6+x-2";
        String s = solution.solveEquation(equation);
        System.out.println(s);
        Assert.assertEquals("x=2", s);


        equation = "-x=-1";
        s = solution.solveEquation(equation);
        System.out.println(s);
        Assert.assertEquals("x=1", s);
    }

}
