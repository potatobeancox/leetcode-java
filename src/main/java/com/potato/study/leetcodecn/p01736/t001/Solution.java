package com.potato.study.leetcodecn.p01736.t001;

import java.util.Arrays;

/**
 * 1736. 替换隐藏数字得到的最晚时间
 *
 * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。

 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。

 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。

  

 示例 1：

 输入：time = "2?:?0"
 输出："23:50"
 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
 示例 2：

 输入：time = "0?:3?"
 输出："09:39"
 示例 3：

 输入：time = "1?:22"
 输出："19:22"
  

 提示：

 time 的格式为 hh:mm
 题目数据保证你可以由输入的字符串生成有效的时间

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param time
     * @return
     */
    public String maximumTime(String time) {
        char[] timeChar = time.toCharArray();
        if (timeChar[4] == '?') {
            timeChar[4] = '9';
        }
        if (timeChar[3] == '?') {
            timeChar[3] = '5';
        }
        // 如果 01 都是 ？
        if (timeChar[0] == '?' && timeChar[1] == '?') {
            timeChar[0] = '2';
            timeChar[1] = '3';
        } else if (timeChar[0] == '?') {
            if (timeChar[1] - '0' > 3) {
                timeChar[0] = '1';
            } else {
                timeChar[0] = '2';
            }
        } else if (timeChar[1] == '?') {
            if (timeChar[0] - '0' == 2) {
                timeChar[1] = '3';
            } else {
                timeChar[1] = '9';
            }
        }
        return new String(timeChar);
    }
}
