package com.potato.study.leetcodecn.p00592.t001;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 592. 分数加减运算

 *
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 

 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。

  

 示例 1:

 输入: expression = "-1/2+1/2"
 输出: "0/1"
  示例 2:

 输入: expression = "-1/2+1/2+1/3"
 输出: "1/3"
 示例 3:

 输入: expression = "1/3-1/2"
 输出: "-1/6"
  

 提示:

 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 输入的分数个数范围是 [1,10]。
 最终结果的分子与分母保证是 32 位整数范围内的有效整数

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/fraction-addition-and-subtraction
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param expression
     * @return
     */
    public String fractionAddition(String expression) {
        Queue<String> fractionQueue = new LinkedList<>();
        Queue<Character> signQueue = new LinkedList<>();
        int start = 0;
        if (expression.charAt(start) == '-') {
            signQueue.add('-');
            start++;
        } else {
            signQueue.add('+');
        }

        for (int i = start; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '/' || Character.isDigit(ch)) {
                continue;
            }
            //  + -
            fractionQueue.add(expression.substring(start, i));
            start = i + 1;
            signQueue.add(ch);
        }
        if (start < expression.length()) {
            fractionQueue.add(expression.substring(start, expression.length()));
        }
        // 计算分数
        int molecular = 0;
        int denominator = 0;
        while (!fractionQueue.isEmpty()) {
            String fractionPoll = fractionQueue.poll();
            Character signPoll = signQueue.poll();

            String[] split = fractionPoll.split("/");
            int otherMolecular = Integer.parseInt(split[0]);
            int otherDenominator = Integer.parseInt(split[1]);

            if (signPoll == '-') {
                otherMolecular *= -1;
            }
            if (molecular == 0 && denominator == 0) {
                molecular = otherMolecular;
                denominator = otherDenominator;
                continue;
            }
            // 计算
            molecular = molecular * otherDenominator + denominator * otherMolecular;
            denominator = denominator * otherDenominator;

            int gcd = getGcd(molecular, denominator);

            molecular /= gcd;
            denominator /= gcd;

        }
        return molecular + "/" + denominator;
    }

    private int getGcd(int a, int b) {
        if (a < 0) {
            a *= -1;
        }
        if (b < 0) {
            b *= -1;
        }
        if (a % b == 0) {
            return b;
        }
        return getGcd(b, a%b);
    }

}
