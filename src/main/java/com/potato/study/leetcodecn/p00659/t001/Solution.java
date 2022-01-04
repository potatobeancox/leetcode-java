package com.potato.study.leetcodecn.p00659.t001;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 659. 分割数组为连续子序列
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isPossible(int[] nums) {
        // 用一个map key 最后一个结束的 num ， value 优先级队列，按照list大小顺序 升序排列的
        Map<Integer, PriorityQueue<List<Integer>>> lengthMap = new HashMap<>();
        // 遍历 map 每次 从 其中 找到最小的那个 新增，并插入之后map
        for (int num : nums) {
            int key = num - 1;
            PriorityQueue<List<Integer>> lastPriorityQueue = lengthMap.get(key);
            List<Integer> list;
            if (null == lastPriorityQueue || lastPriorityQueue.isEmpty()) {
                list = new ArrayList<>();
            } else {
                list = lastPriorityQueue.poll();
            }
            list.add(num);
            PriorityQueue<List<Integer>> priorityQueue = lengthMap.getOrDefault(num, new PriorityQueue<>(
                    new Comparator<List<Integer>>() {
                        @Override
                        public int compare(List<Integer> o1, List<Integer> o2) {
                            return Integer.compare(o1.size(), o2.size());
                        }
                    }
            ));
            priorityQueue.add(list);
            lengthMap.put(num, priorityQueue);
        }
        // 遍历 map 看 是否每个 list 元素都是 大于等于3个的
        for (PriorityQueue<List<Integer>> queue : lengthMap.values()) {
            if (null == queue || queue.isEmpty()) {
                continue;
            }
            while (!queue.isEmpty()) {
                List<Integer> poll = queue.poll();
                if (poll.size() < 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,2,3,3,4,5
        };
        boolean possible = solution.isPossible(nums);
        System.out.println(possible);
        Assert.assertEquals(true, possible);
    }
}
