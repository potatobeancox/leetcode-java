package com.potato.study.leetcodecn.p01353.t001;


import java.util.*;

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

    /**
     * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/solution/zui-duo-ke-yi-can-jia-de-hui-yi-shu-mu-by-leetcode/
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {
        // 按照开始时间升序排序
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int current = 1;
        int index = 0;
        // 参加会议数量
        int count = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        while (index < events.length || !priorityQueue.isEmpty()) {
            // 遍历 events 维护一个 数组 将 开始时间 等于 current 的 的结束时间 放入小堆
            while (index < events.length && events[index][0] == current) {
                priorityQueue.add(events[index][1]);
                index++;
            }
            // 放完了 判定 小根堆 中结束时间小于当前时间的 丢弃 参加不了
            while (!priorityQueue.isEmpty() && priorityQueue.peek() < current) {
                priorityQueue.poll();
            }
            // 选择一个最小的参加 时间往后推一天
            if (!priorityQueue.isEmpty()) {
                priorityQueue.poll();
                count++;
            }
            current++;
        }
        return count;
    }
}
