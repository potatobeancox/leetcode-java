package com.potato.study.leetcodecn.other.lcr.p0035.t001;

import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 035. 最小时间差
 *
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 *
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 *  
 *
 * 注意：本题与主站 539 题相同： https://leetcode-cn.com/problems/minimum-time-difference/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/569nqc
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() <= 1) {
            return 0;
        }
        Collections.sort(timePoints);
        // 先计算第一个和最后一个
        String first = timePoints.get(0);
        String last = timePoints.get(timePoints.size() - 1);
        int min = getMinute(first) - getMinute(last) + 60 * 24;
        for (int i = 0; i < timePoints.size()-1; i++) {
            min = Math.min(getMinute(timePoints.get(i+1)) - getMinute(timePoints.get(i)), min);
        }
        return min;
    }

    private int getMinute(String minute) {
        String[] split = minute.split(":");
        int minuteNum = Integer.parseInt(split[1]);
        int hourNum = Integer.parseInt(split[0]);
        minuteNum += hourNum * 60;
        return minuteNum;
    }
}
