package com.potato.study.leetcodecn.p01090.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1090. 受标签影响的最大值
 *
 * 我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。

 我们从这些项中选出一个子集 S，这样一来：

 |S| <= num_wanted
 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。
 返回子集 S 的最大可能的 和。

  

 示例 1：

 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 输出：9
 解释：选出的子集是第一项，第三项和第五项。
 示例 2：

 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 输出：12
 解释：选出的子集是第一项，第二项和第三项。
 示例 3：

 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 输出：16
 解释：选出的子集是第一项和第四项。
 示例 4：

 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 输出：24
 解释：选出的子集是第一项，第二项和第四项。
  

 提示：

 1 <= values.length == labels.length <= 20000
 0 <= values[i], labels[i] <= 20000
 1 <= num_wanted, use_limit <= values.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-values-from-labels
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        // 使用 一个 map key label id value 是优先级队列 useLimit
        Map<Integer, PriorityQueue<Integer>> labelPriorityQueueMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            int label = labels[i];
            int value = values[i];
            // 小根堆
            PriorityQueue<Integer> priorityQueue = labelPriorityQueueMap.getOrDefault(label, new PriorityQueue<>());
            if (priorityQueue.size() < useLimit) {
                priorityQueue.add(value);
                labelPriorityQueueMap.put(label, priorityQueue);
                continue;
            }
            if (priorityQueue.peek() < value) {
                priorityQueue.add(value);
            }
            while (priorityQueue.size() > useLimit) {
                priorityQueue.poll();
            }
            labelPriorityQueueMap.put(label, priorityQueue);
        }
        // 使用优先级队列 小根堆 保持 大小为 小于等于  numWanted
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : labelPriorityQueueMap.entrySet()) {
            PriorityQueue<Integer> value = entry.getValue();
            while (!value.isEmpty()) {
                Integer target = value.poll();
                if (queue.size() < numWanted) {
                    queue.add(target);
                    continue;
                }
                if (target <= queue.peek()) {
                    continue;
                }
                queue.add(target);
                if (queue.size() > numWanted) {
                    queue.poll();
                }
            }
        }
        int totalSum = 0;
        while (!queue.isEmpty()) {
            totalSum += queue.poll();
        }

        return totalSum;
    }
}
