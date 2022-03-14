package com.potato.study.leetcodecn.p00640.t001;


import java.util.Queue;

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
        if (!equation.contains("=")) {
            return "No solution";
        }
        // 根据 = 进行 split
        String[] split = equation.split("=");
        if (split.length != 2) {
            return "No solution";
        }
        // 记录+- 和数字 通过变量记录
        for (int i = 0; i < 2; i++) {
            String eq = split[i];
            for (int j = 0; j < eq.length(); j++) {

            }
        }
        return null;
    }

}
