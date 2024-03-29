package com.potato.study.leetcodecn.p02409.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 2409. 统计共同度过的日子数
 *
 * Alice 和 Bob 计划分别去罗马开会。
 *
 * 给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob 。Alice 会在日期 arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），而 Bob 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。每个字符串都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
 *
 * 请你返回 Alice和 Bob 同时在罗马的天数。
 *
 * 你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
 * 输出：3
 * 解释：Alice 从 8 月 15 号到 8 月 18 号在罗马。Bob 从 8 月 16 号到 8 月 19 号在罗马，他们同时在罗马的日期为 8 月 16、17 和 18 号。所以答案为 3 。
 * 示例 2：
 *
 * 输入：arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
 * 输出：0
 * 解释：Alice 和 Bob 没有同时在罗马的日子，所以我们返回 0 。
 *  
 *
 * 提示：
 *
 * 所有日期的格式均为 "MM-DD" 。
 * Alice 和 Bob 的到达日期都 早于或等于 他们的离开日期。
 * 题目测试用例所给出的日期均为 非闰年 的有效日期。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-days-spent-together
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String start = arriveAlice;
        // bob 后到的
        if (arriveAlice.compareTo(arriveBob) < 0) {
            start = arriveBob;
        }
        String end = leaveAlice;
        // bob 先的
        if (leaveAlice.compareTo(leaveBob) > 0) {
            end = leaveBob;
        }
        if (start.compareTo(end) > 0) {
            return 0;
        }
        // 求 start 到 end 中间天数
        String[] startSplit = start.split("-");
        String[] endSplit = end.split("-");
        int[] dayCount = new int[] {
                -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        // 第一个月的开始
        int dayIntervalCount = 0;
        for (int i = Integer.parseInt(startSplit[0]); i <= Integer.parseInt(endSplit[0]); i++) {
            dayIntervalCount += dayCount[i];
        }
        // 减去开头的天数和结束的天数
        dayIntervalCount -= (Integer.parseInt(startSplit[1]) - 1);
        dayIntervalCount -= (dayCount[Integer.parseInt(endSplit[0])] - Integer.parseInt(endSplit[1]));
        return dayIntervalCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String arriveAlice = "10-01";
        String leaveAlice = "10-31";
        String arriveBob = "11-01";
        String leaveBob = "12-31";

        int i = solution.countDaysTogether(arriveAlice, leaveAlice,
                arriveBob, leaveBob);
        System.out.println(i);
        Assert.assertEquals(0, i);


        arriveAlice = "09-01";
        leaveAlice = "10-19";
        arriveBob = "06-19";
        leaveBob = "10-20";

        i = solution.countDaysTogether(arriveAlice, leaveAlice,
                arriveBob, leaveBob);
        System.out.println(i);
        Assert.assertEquals(49, i);


        arriveAlice = "08-06";
        leaveAlice = "12-08";
        arriveBob = "02-04";
        leaveBob = "09-01";


        i = solution.countDaysTogether(arriveAlice, leaveAlice,
                arriveBob, leaveBob);
        System.out.println(i);
        Assert.assertEquals(27, i);
    }




}
