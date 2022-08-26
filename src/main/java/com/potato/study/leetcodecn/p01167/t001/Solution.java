package com.potato.study.leetcodecn.p01167.t001;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1167. 连接棒材的最低费用
 *
 * 你有一些长度为正整数的棍子。这些长度以数组 sticks 的形式给出， sticks[i] 是 第i个 木棍的长度。

 你可以通过支付 x + y 的成本将任意两个长度为 x 和 y 的棍子连接成一个棍子。你必须连接所有的棍子，直到剩下一个棍子。

 返回以这种方式将所有给定的棍子连接成一个棍子的 最小成本 。

  

 示例 1：

 输入：sticks = [2,4,3]
 输出：14
 解释：从 sticks = [2,4,3] 开始。
 1. 连接 2 和 3 ，费用为 2 + 3 = 5 。现在 sticks = [5,4]
 2. 连接 5 和 4 ，费用为 5 + 4 = 9 。现在 sticks = [9]
 所有木棍已经连成一根，总费用 5 + 9 = 14
 示例 2：

 输入：sticks = [1,8,3,5]
 输出：30
 解释：从 sticks = [1,8,3,5] 开始。
 1. 连接 1 和 3 ，费用为 1 + 3 = 4 。现在 sticks = [4,8,5]
 2. 连接 4 和 5 ，费用为 4 + 5 = 9 。现在 sticks = [9,8]
 3. 连接 9 和 8 ，费用为 9 + 8 = 17 。现在 sticks = [17]
 所有木棍已经连成一根，总费用 4 + 9 + 17 = 30
 示例 3：

 输入：sticks = [5]
 输出：0
 解释：只有一根木棍，不必再连接。总费用 0
  

 提示：

 1 <= sticks.length <= 104
 1 <= sticks[i] <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-cost-to-connect-sticks
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int connectSticks(int[] sticks) {
        // 小根堆记录 最小的两个 stick
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int stick : sticks) {
            priorityQueue.add(stick);
        }
        // 看下 size 是不是大于 1
        int cost = 0;
        while (priorityQueue.size() > 1) {
            int s1 = priorityQueue.poll();
            int s2 = priorityQueue.poll();
            int newStick = s1 + s2;
            cost += newStick;
            priorityQueue.add(newStick);
        }
        if (priorityQueue.isEmpty()) {
            return 0;
        }
        return cost;
    }


}
