package com.potato.study.leetcodecn.p01353.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1353. 最多可以参加的会议数目
 *
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。

 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。

 请你返回你可以参加的 最大 会议数目。

  

 示例 1：



 输入：events = [[1,2],[2,3],[3,4]]
 输出：3
 解释：你可以参加所有的三个会议。
 安排会议的一种方案如上图。
 第 1 天参加第一个会议。
 第 2 天参加第二个会议。
 第 3 天参加第三个会议。
 示例 2：

 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 输出：4
  

 提示：​​​​​​

 1 <= events.length <= 105
 events[i].length == 2
 1 <= startDayi <= endDayi <= 105


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxEvents(int[][] events) {
        // 按照结束时间升序排序
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        // visit 数组 维护 一个 1_00000 bool 是否访问过
        boolean[] visited = new boolean[1_000_00 + 1];
        int count = 0;
        for (int i = 0; i < events.length; i++) {
            for (int j = events[i][0]; j <= events[i][1]; j++) {
                if (!visited[j]) {
                    visited[j] = true;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
