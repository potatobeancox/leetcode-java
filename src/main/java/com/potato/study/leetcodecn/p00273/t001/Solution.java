package com.potato.study.leetcodecn.p00273.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 273. 整数转换英文表示
 *
 * 将非负整数 num 转换为其对应的英文表示。

  

 示例 1：

 输入：num = 123
 输出："One Hundred Twenty Three"
 示例 2：

 输入：num = 12345
 输出："Twelve Thousand Three Hundred Forty Five"
 示例 3：

 输入：num = 1234567
 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 示例 4：

 输入：num = 1234567891
 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
  

 提示：

 0 <= num <= 2 31 - 1


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/integer-to-english-words
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    /**
     *
     * @param num
     * @return
     */
    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }

        int bit = 1_000_000_000;
        String[] bitString = new String[] {
          "Billion", "Million" , "Thousand", ""
        };
        // 维护一个 【Billion， Million， Thousand， ""】 串
        StringBuilder builder = new StringBuilder();
        int index = 0;
        // 按照 每次 3位数字进行获取计算并生成字符串
        while (bit >= 1 && num > 0) {
            if (num < bit) {
                bit /= 1000;
                index++;
                continue;
            }
            int bitIndex = num / bit;

            String numBlow1000 = getNumBlow1000(bitIndex);
            builder.append(numBlow1000);
            builder.append(" ");


            String str = bitString[index];
            if (!"".equals(str)) {
                builder.append(str);
                builder.append(" ");

            }
            num %= bit;
            bit /= 1000;
            index++;
        }

        if (builder.charAt(builder.length() - 1) == ' ') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }


    /**
     * 1000 以内的字符串生成
     * @return
     */
    private String getNumBlow1000(int num) {
        StringBuilder builder = new StringBuilder();
        if (num >= 100) {
            int bit = num / 100;
            String numStr = getDigitBelow20(bit);
            builder.append(numStr);
            builder.append(" ");
            builder.append("Hundred");
            builder.append(" ");
            num %= 100;
        }

        if (num >= 20) {
            int tenBit = num / 10;
            builder.append(getTenBit(tenBit));
            num %= 10;
            if (num > 0) {
                builder.append(" ");
                builder.append(getDigitBelow20(num));
            }
        } else {
            builder.append(getDigitBelow20(num));
        }

        // 删除第一个空格
        if (builder.charAt(0) == ' ') {
            builder.deleteCharAt(0);
        }

        // 删除第一个空格
        if (builder.charAt(builder.length() - 1) == ' ') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    /**
     * 获取 几十
     * @param i
     */
    private String getTenBit(int i) {
        switch (i) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "Zero";
    }




    /**
     * 生成20 一下英文
     * @param i
     * @return
     */
    private String getDigitBelow20(int i) {
        switch (i) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";

        }
        return "";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String nn = solution.numberToWords(123);
        System.out.println(nn);
        Assert.assertEquals("One Hundred Twenty Three", nn);


        nn = solution.numberToWords(12345);
        System.out.println(nn);
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", nn);


        nn = solution.numberToWords(100);
        System.out.println(nn);
        Assert.assertEquals("One Hundred", nn);


    }
}

