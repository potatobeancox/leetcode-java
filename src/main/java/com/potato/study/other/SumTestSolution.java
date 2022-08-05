package com.potato.study.other;

import org.junit.Assert;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

/**
 * @author liuzhao03 <liuzhao03@kuaishou.com>
 * Created on 2022-08-02
 */
public class SumTestSolution {


    /**
     * 整数相加
     * @param num1
     * @param num2
     * @return
     */
    public String getSum(String num1, String num2) {
        // 处理下空情况
        if (num1 == null || num2 == null) {
            return "0";
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        // 从后往前加 记录进位情况 string builder 存储
        int index1 = num1.length() -1;
        int index2 = num2.length() -1;
        StringBuilder builder = new StringBuilder();
        boolean isProcess = false;
        while (index1 >= 0 || index2 >= 0) {
            // 第一个num 消耗完了
            int num;
            if (index1 < 0) {
                num = num2.charAt(index2) - '0';
                index2--;
            } else if (index2 < 0) {
                num = num1.charAt(index1) - '0';
                index1--;
            } else {
                num = (num2.charAt(index2) - '0') + (num1.charAt(index1) - '0');
                index1--;
                index2--;
            }
            // 处理进位符号
            if (isProcess) {
                num++;
            }
            // 处理数字
            if (num > 9) {
                num %= 10;
                isProcess = true;
            }
            builder.append(num);
        }
        if (isProcess) {
            builder.append("1");
        }
        return builder.reverse().toString();
    }



    public static void main(String[] args) {
        SumTestSolution sumTestSolution = new SumTestSolution();
        String sum = sumTestSolution.getSum("1", "99");
        System.out.println(sum);
        Assert.assertEquals("100", sum);
    }

}
