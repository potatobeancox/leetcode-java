package com.potato.study.leetcodecn.p01118.t001;


/**
 * 1118. 一月有多少天
 *
 * 指定年份 year 和月份 month，返回 该月天数 。

  

 示例 1：

 输入：year = 1992, month = 7
 输出：31
 示例 2：

 输入：year = 2000, month = 2
 输出：29
 示例 3：

 输入：year = 1900, month = 2
 输出：28
  

 提示：

 1583 <= year <= 2100
 1 <= month <= 12

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-days-in-a-month
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numberOfDays(int year, int month) {
        // 判断是不是 31天的月
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return 31;
        }
        if (month != 2) {
            return 30;
        }
        // 判断 年是否是 闰年
        if (year % 400 == 0
                || (year % 100 != 0 && year % 4 == 0)) {
            return 29;
        }
        return 28;
    }
}
