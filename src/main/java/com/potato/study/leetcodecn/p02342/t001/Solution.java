package com.potato.study.leetcodecn.p02342.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2342. 数位和相等数对的最大和
 *
 * 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。

 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。

  

 示例 1：

 输入：nums = [18,43,36,13,7]
 输出：54
 解释：满足条件的数对 (i, j) 为：
 - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
 - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
 所以可以获得的最大和是 54 。
 示例 2：

 输入：nums = [10,12,19,14]
 输出：-1
 解释：不存在满足条件的数对，返回 -1 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maximumSum(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> key2maxNumMap = new HashMap<>();
        for (int num : nums) {
            // 计算数位和
            int tmp = num;
            int bitSum = 0;
            while (tmp > 0) {
                bitSum += (tmp % 10);
                tmp /= 10;
            }
            // key2maxNumMap
            PriorityQueue<Integer> priorityQueue =
                    key2maxNumMap.getOrDefault(bitSum, new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)));
            priorityQueue.add(num);
            key2maxNumMap.put(bitSum, priorityQueue);
        }
        // 遍历 找到最大的两个数字
        int max = -1;
        for (PriorityQueue<Integer> heap : key2maxNumMap.values()) {

            if (heap.size() <= 1) {
                continue;
            }

            int a = heap.poll();
            int b = heap.poll();


            max = Math.max(a+b, max);
        }
        return max;
    }


}
