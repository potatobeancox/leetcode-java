package com.potato.study.leetcodecn.p00630.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 630. 课程表 III
 *
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 *
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 *
 * 返回你最多可以修读的课程数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 * 示例 2：
 *
 * 输入：courses = [[1,2]]
 * 输出：1
 * 示例 3：
 *
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 *  
 *
 * 提示:
 *
 * 1 <= courses.length <= 104
 * 1 <= durationi, lastDayi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/course-schedule-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 630
    public int scheduleCourse(int[][] courses) {
        // 按照 courses 的 lastday 生序排序 过程中记录 能学习的课程的时间花费 大根堆
        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        PriorityQueue<Integer> costPriorityQueue = new PriorityQueue<>(
                Comparator.reverseOrder()
        );
        // 每次判断当前使用时间 + 当前课程花费是不是小于 cource 是的话 直接记录 不是的话 看看能不能替换 大根堆
        int studyCount = 0;
        int current = 0;
        for (int[] course : courses) {
            int cost = course[0];
            int ddl = course[1];

            if (current + cost <= ddl) {
                costPriorityQueue.add(cost);
                current += cost;
                studyCount++;
            } else {
                // 完成不了了 看看能不能替换
                if (!costPriorityQueue.isEmpty() && costPriorityQueue.peek() > cost) {
                    Integer poll = costPriorityQueue.poll();
                    costPriorityQueue.add(cost);
                    current = current - poll + cost;
                }
            }
        }
        return studyCount;
    }

}
