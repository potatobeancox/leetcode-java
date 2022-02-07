package com.potato.study.leetcodecn.p01679.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 1679. K 和数对的最大数目
 *
 * 给你一个整数数组 nums 和一个整数 k 。
 *
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 *
 * 返回你可以对数组执行的最大操作数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：2
 * 解释：开始时 nums = [1,2,3,4]：
 * - 移出 1 和 4 ，之后 nums = [2,3]
 * - 移出 2 和 3 ，之后 nums = []
 * 不再有和为 5 的数对，因此最多执行 2 次操作。
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,3], k = 6
 * 输出：1
 * 解释：开始时 nums = [3,1,3,4,3]：
 * - 移出前两个 3 ，之后nums = [1,4,3]
 * 不再有和为 6 的数对，因此最多执行 1 次操作。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-number-of-k-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1679
    public int maxOperations(int[] nums, int k) {
        //  统计 nums value 出现次数 遍历 countMap 计算map 修改的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = countMap.getOrDefault(nums[i], 0);
            count++;
            countMap.put(nums[i], count);
        }
        int total = 0;
        for (int key : countMap.keySet()) {
            int count = countMap.get(key);
            if (count == 0) {
                continue;
            }
            int target = k - key;
            if (key == target) {
                total += (count / 2);
                countMap.put(key, count - count / 2 * 2);
            } else {
                if (!countMap.containsKey(target)) {
                    continue;
                }
                Integer targetCount = countMap.get(target);
                if (targetCount == 0) {
                    continue;
                }
                int minCount = Math.min(targetCount, count);
                total += minCount;
                countMap.put(key, count - minCount);
                countMap.put(target, targetCount - minCount);
            }
        }
        return total;
    }
}
