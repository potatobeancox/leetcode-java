package com.potato.study.leetcodecn.p00539.t001;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 539. 最小时间差
 *
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。

  

 示例 1：

 输入：timePoints = ["23:59","00:00"]
 输出：1
 示例 2：

 输入：timePoints = ["00:00","23:59","00:00"]
 输出：0
  

 提示：

 2 <= timePoints <= 2 * 104
 timePoints[i] 格式为 "HH:MM"


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-time-difference
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        List<Long> minuteList = new ArrayList<>();
        for (String timePoint : timePoints) {
            long minuteCount = 0;
            String[] split = timePoint.split(":");
            long hour = Long.parseLong(split[0]);
            long minute = Long.parseLong(split[1]);
            minuteCount += minute;
            minuteCount += (60 * hour);
            minuteList.add(minuteCount);
        }
        Collections.sort(minuteList);
        long minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < minuteList.size(); i++) {
            minDiff = Math.min(minDiff, minuteList.get(i) - minuteList.get(i-1));
        }
        minDiff = Math.min(minDiff, minuteList.get(0) - minuteList.get(minuteList.size() - 1) + 24 * 60);
        return (int) minDiff;
    }

}
