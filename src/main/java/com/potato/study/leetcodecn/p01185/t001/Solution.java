package com.potato.study.leetcodecn.p01185.t001;


import org.junit.Assert;

/**
 * 1185. 一周中的第几天
 *
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。

 输入为三个整数：day、month 和 year，分别表示日、月、年。

 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。

  

 示例 1：

 输入：day = 31, month = 8, year = 2019
 输出："Saturday"
 示例 2：

 输入：day = 18, month = 7, year = 1999
 输出："Sunday"
 示例 3：

 输入：day = 15, month = 8, year = 1993
 输出："Sunday"
  

 提示：

 给出的日期一定是在 1971 到 2100 年之间的有效日期。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/day-of-the-week
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String dayOfTheWeek(int day, int month, int year) {
        String[] result = new String[]{
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        };
        // 1971年1月1日，初始化为4
        int current = 4;
        // 分别计算年 月 和日 相差多少天
        if (year > 1971) {
            for (int i = 1971; i < year; i++) {
                current += 365;
                if (isNeedPlusOneYear(i)) {
                    current++;
                }
            }
        }
        // 是否闰年标志
        boolean needPlus = isNeedPlusOneYear(year);
        // 计算月
        int[] dayOfMonth = new int[] {
                31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        // 当前是 2 只加一月的
        for (int i = 0; i < month - 1; i++) {
            current += dayOfMonth[i];
            if (i == 1 && needPlus) {
                current++;
            }
        }
        // 计算日
        current += day;
        return result[current % 7];
    }

    private boolean isNeedPlusOneYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int day = 31;
        int month = 8;
        int year = 2019;
        String s = solution.dayOfTheWeek(day, month, year);
        System.out.println(s);
        Assert.assertEquals("Saturday", s);
    }
}

