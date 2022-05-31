package com.potato.study.leetcodecn.p02288.t001;

import java.text.DecimalFormat;

import org.junit.Assert;

/**
 * 2288. 价格减免
 *
 * 句子 是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 '$' 。如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个价格。
 *
 * 例如 "$100"、"$23" 和 "$6.75" 表示价格，而 "100"、"$" 和 "2$3" 不是。
 * 注意：本题输入中的价格均为整数。
 *
 * 给你一个字符串 sentence  和一个整数 discount 。对于每个表示价格的单词，都在价格的基础上减免 discount% ，并 更新 该单词到句子中。所有更新后的价格应该表示为一个 恰好保留小数点后两位 的数字。
 *
 * 返回表示修改后句子的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：sentence = "there are $1 $2 and 5$ candies in the shop", discount = 50
 * 输出："there are $0.50 $1.00 and 5$ candies in the shop"
 * 解释：
 * 表示价格的单词是 "$1" 和 "$2" 。
 * - "$1" 减免 50% 为 "$0.50" ，所以 "$1" 替换为 "$0.50" 。
 * - "$2" 减免 50% 为 "$1" ，所以 "$1" 替换为 "$1.00" 。
 * 示例 2：
 *
 * 输入：sentence = "1 2 $3 4 $5 $6 7 8$ $9 $10$", discount = 100
 * 输出："1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$"
 * 解释：
 * 任何价格减免 100% 都会得到 0 。
 * 表示价格的单词分别是 "$3"、"$5"、"$6" 和 "$9"。
 * 每个单词都替换为 "$0.00"。
 *  
 *
 * 提示：
 *
 * 1 <= sentence.length <= 105
 * sentence 由小写英文字母、数字、' ' 和 '$' 组成
 * sentence 不含前导和尾随空格
 * sentence 的所有单词都用单个空格分隔
 * 所有价格都是 正 整数且不含前导零
 * 所有价格 最多 为  10 位数字
 * 0 <= discount <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/apply-discount-to-prices
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String discountPrices(String sentence, int discount) {
        // sentence
        String[] splits = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String split : splits) {
            if (isMoney(split)) {
                // 转换成数组
                String maybeNumStr = split.substring(1);
                double price = Double.parseDouble(maybeNumStr);
                price *= (100 - discount);
                price /= 100;

                DecimalFormat df = new DecimalFormat("0.00");
                String format = df.format(price);
                builder.append("$").append(format)
                        .append(" ");
            } else {
                builder.append(split).append(" ");
            }
        }
        if (builder.charAt(builder.length() - 1) == ' ') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }


    private boolean isMoney(String str) {
        if (str.length() < 2) {
            return false;
        }
        char[] chars = str.toCharArray();
        if (chars[0] != '$') {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(chars[i]) && chars[i] != '.') {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String string = "there are $1 $2 and 5$ candies in the shop";
        int discount = 50;
        String s = solution.discountPrices(string, discount);
        System.out.println(s);
        Assert.assertEquals("there are $0.50 $1.00 and 5$ candies in the shop", s);


        string = "1 2 $3.00 4 $5.00 $6.00 7 8$ $9.00 $10$";
        discount = 100;
        s = solution.discountPrices(string, discount);
        System.out.println(s);
        Assert.assertEquals("1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$", s);
    }
}
