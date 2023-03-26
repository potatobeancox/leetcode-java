package com.potato.study.leetcodecn.p02593.t001;

import java.util.PriorityQueue;

/**
 * 2593. 标记所有元素后数组的分数

 给你一个数组 nums ，它包含若干正整数。

 一开始分数 score = 0 ，请你按照下面算法求出最后分数：

 从数组中选择最小且没有被标记的整数。如果有相等元素，选择下标最小的一个。
 将选中的整数加到 score 中。
 标记 被选中元素，如果有相邻元素，则同时标记 与它相邻的两个元素 。
 重复此过程直到数组中所有元素都被标记。
 请你返回执行上述算法后最后的分数。

  

 示例 1：

 输入：nums = [2,1,3,4,5,2]
 输出：7
 解释：我们按照如下步骤标记元素：
 - 1 是最小未标记元素，所以标记它和相邻两个元素：[2,1,3,4,5,2] 。
 - 2 是最小未标记元素，所以标记它和左边相邻元素：[2,1,3,4,5,2] 。
 - 4 是仅剩唯一未标记的元素，所以我们标记它：[2,1,3,4,5,2] 。
 总得分为 1 + 2 + 4 = 7 。
 示例 2：

 输入：nums = [2,3,5,1,3,2]
 输出：5
 解释：我们按照如下步骤标记元素：
 - 1 是最小未标记元素，所以标记它和相邻两个元素：[2,3,5,1,3,2] 。
 - 2 是最小未标记元素，由于有两个 2 ，我们选择最左边的一个 2 ，也就是下标为 0 处的 2 ，以及它右边相邻的元素：[2,3,5,1,3,2] 。
 - 2 是仅剩唯一未标记的元素，所以我们标记它：[2,3,5,1,3,2] 。
 总得分为 1 + 2 + 2 = 5 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-score-of-an-array-after-marking-all-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2593
    public long findScore(int[] nums) {
        boolean[] used = new boolean[nums.length];
        // 0：值， 1：index
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int compare = Integer.compare(o1[0], o2[0]);
            if (compare == 0) {
                return Integer.compare(o1[1], o2[1]);
            }
            return compare;
        });
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(new int[] {nums[i], i});
        }
        long score = 0L;
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int index = poll[1];
            if (used[index]) {
                continue;
            }
            used[index] = true;
            score += poll[0];
            if (index + 1 < nums.length) {
                used[index+1] = true;
            }
            if (index - 1 >= 0) {
                used[index-1] = true;
            }
        }
        return score;
    }

}
