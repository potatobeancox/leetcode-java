package com.potato.study.leetcodecn.p01296.t001;


import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1296. 划分数组为连续数字的集合
 *
 * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
 如果可以，请返回 true；否则，返回 false。

  

 示例 1：

 输入：nums = [1,2,3,3,4,4,5,6], k = 4
 输出：true
 解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
 示例 2：

 输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 输出：true
 解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
 示例 3：

 输入：nums = [3,3,2,2,1,1], k = 3
 输出：true
 示例 4：

 输入：nums = [1,2,3,4], k = 3
 输出：false
 解释：数组不能分成几个大小为 3 的子数组。
  

 提示：

 1 <= k <= nums.length <= 105
 1 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isPossibleDivide(int[] nums, int k) {
        // 使用计数器进行计数 map value count
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        Arrays.sort(nums);
        // 将nums 排序 遍历 nums 每次往后找 k个如果找不到足量的 返回false 否则 继续找 并修改map 如果某个数字 被消耗光了 continue
        for (int num : nums) {
            if (!countMap.containsKey(num)) {
                continue;
            }
            int count = countMap.get(num);
            countMap.remove(num);
            for (int i = 1; i < k; i++) {
                if (!countMap.containsKey(num + i)) {
                    return false;
                }
                Integer otherCount = countMap.get(num + i);
                if (otherCount < count) {
                    return false;
                }
                otherCount -= count;
                if (otherCount == 0) {
                    countMap.remove(num + i);
                    continue;
                }
                countMap.put(num + i, otherCount);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{
                1,2,3,3,4,4,5,6
        };
        int k = 4;
        boolean possibleDivide = solution.isPossibleDivide(nums, k);
        System.out.println(possibleDivide);
        Assert.assertEquals(true, possibleDivide);
    }
}
