package com.potato.study.leetcodecn.p01986.t001;

import java.util.Arrays;

/**
 * 1986. 完成任务的最少工作时间段
 *
 * 你被安排了 n 个任务。任务需要花费的时间用长度为 n 的整数数组 tasks 表示，第 i 个任务需要花费 tasks[i] 小时完成。一个 工作时间段 中，你可以 至多 连续工作 sessionTime 个小时，然后休息一会儿。

 你需要按照如下条件完成给定任务：

 如果你在某一个时间段开始一个任务，你需要在 同一个 时间段完成它。
 完成一个任务后，你可以 立马 开始一个新的任务。
 你可以按 任意顺序 完成任务。
 给你 tasks 和 sessionTime ，请你按照上述要求，返回完成所有任务所需要的 最少 数目的 工作时间段 。

 测试数据保证 sessionTime 大于等于 tasks[i] 中的 最大值 。

  

 示例 1：

 输入：tasks = [1,2,3], sessionTime = 3
 输出：2
 解释：你可以在两个工作时间段内完成所有任务。
 - 第一个工作时间段：完成第一和第二个任务，花费 1 + 2 = 3 小时。
 - 第二个工作时间段：完成第三个任务，花费 3 小时。
 示例 2：

 输入：tasks = [3,1,3,1,1], sessionTime = 8
 输出：2
 解释：你可以在两个工作时间段内完成所有任务。
 - 第一个工作时间段：完成除了最后一个任务以外的所有任务，花费 3 + 1 + 3 + 1 = 8 小时。
 - 第二个工作时间段，完成最后一个任务，花费 1 小时。
 示例 3：

 输入：tasks = [1,2,3,4,5], sessionTime = 15
 输出：1
 解释：你可以在一个工作时间段以内完成所有任务。
  

 提示：

 n == tasks.length
 1 <= n <= 14
 1 <= tasks[i] <= 10
 max(tasks[i]) <= sessionTime <= 15

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    private int minCost;
    /**
     * https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks/solution/da-lao-du-zai-xiu-zhuang-ya-cai-ji-yong-xflrt/
     * @param tasks
     * @param sessionTime
     * @return
     */
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        this.minCost = n;
        // 回溯 使用一个 记录当前时间使用了多少的task
        int index = 0;
        int currentCost = 0;
        // 记录之前的任务用了多少
        int[] usedSessionTime = new int[n];
        backtrack(tasks, sessionTime, index, currentCost, usedSessionTime);
        return minCost;
    }

    private void backtrack(int[] tasks, int sessionTime, int index,
                           int currentCost, int[] usedSessionTime) {
        // 剪枝逻辑 如果 当前使用的时间已经 大于之前最小值就继续本次了
        if (currentCost >= this.minCost) {
            return;
        }
        // 如果当前index 已经到最后了 那么直接比较返回 min
        if (index == tasks.length) {
            this.minCost = Math.min(minCost, currentCost);
            return;
        }
        // index 还没有到最后 看看 可以用那个 每一个 使用一个标记 记录是否已经给新增过 如果新增逻辑那么也剪枝
        int n = usedSessionTime.length;
        // 是否已经新分配过 剪枝使用 分配过 可以直接跳过了
        boolean isUseNewSession = false;
        for (int i = 0; i < n; i++) {
            // 当前i位置的情况 之前已经处理过
            if (isUseNewSession && usedSessionTime[i] == 0) {
                break;
            }
            // 当前这个 用了也没用
            if (usedSessionTime[i] + tasks[index] > sessionTime) {
                // 用一个新的
                continue;
            }
            // 如果当前是个 0 看看
            if (0 == usedSessionTime[i]) {
                isUseNewSession = true;
                currentCost++;
            }
            usedSessionTime[i] += tasks[index];
            // 递归找下一个位置
            backtrack(tasks, sessionTime, index+1, currentCost, usedSessionTime);
            usedSessionTime[i] -= tasks[index];
        }
    }

}
