package com.potato.study.leetcodecn.p02475.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2475. 数组中不等三元组的数目
 *
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：

 0 <= i < j < k < nums.length
 nums[i]、nums[j] 和 nums[k] 两两不同 。
 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 返回满足上述条件三元组的数目。

  

 示例 1：

 输入：nums = [4,4,2,4,3]
 输出：3
 解释：下面列出的三元组均满足题目条件：
 - (0, 2, 4) 因为 4 != 2 != 3
 - (1, 2, 4) 因为 4 != 2 != 3
 - (2, 3, 4) 因为 2 != 4 != 3
 共计 3 个三元组，返回 3 。
 注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
 示例 2：

 输入：nums = [1,1,1,1,1]
 输出：0
 解释：不存在满足条件的三元组，所以返回 0 。
  

 提示：

 3 <= nums.length <= 100
 1 <= nums[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-unequal-triplets-in-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-unequal-triplets-in-array/solution/by-longluo-kg4a/
     * @param nums
     * @return
     */
    public int unequalTriplets(int[] nums) {
        // 计算左边有多少个 用treemap 计数
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }
        // 维护一个小于 当前value 的数量的和 和 大于 value的和
        int leftCount = 0;
        int rightCount = nums.length;
        int totalCount = 0;
        for (int key : countMap.keySet()) {
            int count = countMap.get(key);
            totalCount += (leftCount * count * (rightCount - count));

            leftCount += count;
            rightCount -= count;
        }
        return totalCount;
    }
}
