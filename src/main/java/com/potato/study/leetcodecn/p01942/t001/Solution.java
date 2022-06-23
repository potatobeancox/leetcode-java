package com.potato.study.leetcodecn.p01942.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1942. 最小未被占据椅子的编号
 *
 * 有 n 个朋友在举办一个派对，这些朋友从 0 到 n - 1 编号。派对里有 无数 张椅子，编号为 0 到 infinity 。当一个朋友到达派对时，他会占据 编号最小 且未被占据的椅子。

 比方说，当一个朋友到达时，如果椅子 0 ，1 和 5 被占据了，那么他会占据 2 号椅子。
 当一个朋友离开派对时，他的椅子会立刻变成未占据状态。如果同一时刻有另一个朋友到达，可以立即占据这张椅子。

 给你一个下标从 0 开始的二维整数数组 times ，其中 times[i] = [arrivali, leavingi] 表示第 i 个朋友到达和离开的时刻，同时给你一个整数 targetFriend 。所有到达时间 互不相同 。

 请你返回编号为 targetFriend 的朋友占据的 椅子编号 。

  

 示例 1：

 输入：times = [[1,4],[2,3],[4,6]], targetFriend = 1
 输出：1
 解释：
 - 朋友 0 时刻 1 到达，占据椅子 0 。
 - 朋友 1 时刻 2 到达，占据椅子 1 。
 - 朋友 1 时刻 3 离开，椅子 1 变成未占据。
 - 朋友 0 时刻 4 离开，椅子 0 变成未占据。
 - 朋友 2 时刻 4 到达，占据椅子 0 。
 朋友 1 占据椅子 1 ，所以返回 1 。
 示例 2：

 输入：times = [[3,10],[1,5],[2,6]], targetFriend = 0
 输出：2
 解释：
 - 朋友 1 时刻 1 到达，占据椅子 0 。
 - 朋友 2 时刻 2 到达，占据椅子 1 。
 - 朋友 0 时刻 3 到达，占据椅子 2 。
 - 朋友 1 时刻 5 离开，椅子 0 变成未占据。
 - 朋友 2 时刻 6 离开，椅子 1 变成未占据。
 - 朋友 0 时刻 10 离开，椅子 2 变成未占据。
 朋友 0 占据椅子 2 ，所以返回 2 。
  

 提示：

 n == times.length
 2 <= n <= 104
 times[i].length == 2
 1 <= arrivali < leavingi <= 105
 0 <= targetFriend <= n - 1
 每个 arrivali 时刻 互不相同 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int smallestChair(int[][] times, int targetFriend) {
        // 对 times[i] 按照 开始时间升序 结束时间升序排列 依次按照开始顺序 遍历
        int[][] timeIndex = new int[times.length][3];
        for (int i = 0; i < times.length; i++) {
            timeIndex[i][0] = times[i][0];
            timeIndex[i][1] = times[i][1];
            timeIndex[i][2] = i;
        }
        Arrays.sort(timeIndex, (timeIndex1, timeIndex2) -> {
            int compare = Integer.compare(timeIndex1[0], timeIndex2[0]);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(timeIndex1[1], timeIndex2[1]);
        });
        // 用一个 tree set 记录 空位 最开始放0 主要是针对离开的情况
        TreeSet<Integer> emptySeat = new TreeSet<>();
        emptySeat.add(0);
        // 用一个 堆记录 目前还在作为上的 人的 离开时间 和这个人的座位
        PriorityQueue<int[]> onSeatPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            int compare = Integer.compare(o1[0], o2[0]);
            return compare;
        });
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < timeIndex.length; i++) {
            // 当前节点开始时间就是 当前时间
            int current = timeIndex[i][0];
            // onSeatPriorityQueue 中 获取当前已经 到时间的出来吧
            while (!onSeatPriorityQueue.isEmpty() && onSeatPriorityQueue.peek()[0] <= current) {
                int[] poll = onSeatPriorityQueue.poll();
                emptySeat.add(poll[1]);
            }
            // 选择最小的
            Integer first = emptySeat.first();
            emptySeat.remove(first);
            if (targetFriend == timeIndex[i][2]) {
                return first;
            }
            onSeatPriorityQueue.add(new int[] {
                    timeIndex[i][1], first
            });
            used.add(first);
            int tmp = first;
            while (used.contains(tmp)) {
                tmp++;
            }
            emptySeat.add(tmp);
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,4],[2,3],[4,6]]";
        int[][] times = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int targetFriend = 1;
        int i = solution.smallestChair(times, targetFriend);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }


}
