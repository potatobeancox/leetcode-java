package com.potato.study.leetcodecn.p02232.t001;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2232. 向表达式添加括号后的最小结果
 *
 * 给你一个下标从 0 开始的字符串 expression ，格式为 "<num1>+<num2>" ，其中 <num1> 和 <num2> 表示正整数。

 请你向 expression 中添加一对括号，使得在添加之后， expression 仍然是一个有效的数学表达式，并且计算后可以得到 最小 可能值。左括号 必须 添加在 '+' 的左侧，而右括号必须添加在 '+' 的右侧。

 返回添加一对括号后形成的表达式 expression ，且满足 expression 计算得到 最小 可能值。如果存在多个答案都能产生相同结果，返回任意一个答案。

 生成的输入满足：expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围。

  

 示例 1：

 输入：expression = "247+38"
 输出："2(47+38)"
 解释：表达式计算得到 2 * (47 + 38) = 2 * 85 = 170 。
 注意 "2(4)7+38" 不是有效的结果，因为右括号必须添加在 '+' 的右侧。
 可以证明 170 是最小可能值。
 示例 2：

 输入：expression = "12+34"
 输出："1(2+3)4"
 解释：表达式计算得到 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20 。
 示例 3：

 输入：expression = "999+999"
 输出："(999+999)"
 解释：表达式计算得到 999 + 999 = 1998 。
  

 提示：

 3 <= expression.length <= 10
 expression 仅由数字 '1' 到 '9' 和 '+' 组成
 expression 由数字开始和结束
 expression 恰好仅含有一个 '+'.
 expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimize-result-by-adding-parentheses-to-expression
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String minimizeResult(String expression) {
        // 定位 + 位置
        int plusIndex = expression.indexOf('+');
        // 枚举 插入位置
        long min = Long.MAX_VALUE;
        String result = null;
        for (int i = 0; i < plusIndex; i++) {
            // ( 插入位置
            for (int j = plusIndex + 2; j <= expression.length(); j++) {
                String part1 = expression.substring(0, i);
                String part2 = expression.substring(i, j);
                String part3 = expression.substring(j);
                long res = getResult(part1, part2, part3);
                if (res < min) {
                    min = res;
                    result = part1 + "(" + part2 + ")" + part3;
                }
            }
        }
        return result;
    }

    private long getResult(String part1, String part2, String part3) {
        long p1 = 1;
        if (part1.length() > 0) {
            p1 = Long.parseLong(part1);
        }
        long p3 = 1;
        if (part3.length() > 0) {
            p3 = Long.parseLong(part3);
        }
        long p2 = 0;
        String[] split = part2.split("\\+");
        p2 += Long.parseLong(split[0]);
        p2 += Long.parseLong(split[1]);
        return p1 * p3 * p2;
    }


}
