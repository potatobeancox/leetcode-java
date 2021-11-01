package com.potato.study.leetcodecn.p01360.t001;

import org.junit.Assert;

/**
 * 1360. 日期之间隔几天
 *
 * 请你编写一个程序来计算两个日期之间隔了多少天。

 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。

  

 示例 1：

 输入：date1 = "2019-06-29", date2 = "2019-06-30"
 输出：1
 示例 2：

 输入：date1 = "2020-01-15", date2 = "2019-12-31"
 输出：15
  

 提示：

 给定的日期是 1971 年到 2100 年之间的有效日期。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-days-between-two-dates
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param date1
     * @param date2
     * @return
     */
    public int daysBetweenDates(String date1, String date2) {
        // 将 date 1 放在小的位置
        int dateNum1 = getDateNum(date1);
        int dateNum2 = getDateNum(date2);
        return Math.abs(dateNum1 - dateNum2);
    }


    private boolean isYearNeedPlus(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }


    /**
     * 当前时间 到 1971.1.1 差了几天
     * @param date
     * @return
     */
    private int getDateNum(String date) {

        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);


        int distance = 0;
        for (int i = 1971; i < year; i++) {
            if (isYearNeedPlus(i)) {
                distance += 366;
            } else {
                distance += 365;
            }
        }

        int[] monthBase = new int[] {
                31,28,31,30,31,30,31,31,30,31,30,31
        };
        // 计算 月差距
        for (int i = 0; i < month - 1; i++) {
            distance += monthBase[i];
            if (isYearNeedPlus(year) && i == 1) {
                distance++;
            }
        }
        // / 计算 日差距
        distance += day;

        return distance;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String date1 = "2019-06-29";
        String date2 = "2019-06-30";
        int i = solution.daysBetweenDates(date1, date2);
        System.out.println(i);
        Assert.assertEquals(1, i);


        date1 = "1971-06-29";
        date2 = "2010-09-23";
        i = solution.daysBetweenDates(date1, date2);
        System.out.println(i);
        Assert.assertEquals(14331, i);
    }
}
