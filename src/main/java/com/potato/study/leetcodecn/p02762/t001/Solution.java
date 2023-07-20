package com.potato.study.leetcodecn.p02762.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * 2762. 不间断子数组
 *
 * 给你一个下标从 0 开始的整数数组 nums 。nums 的一个子数组如果满足以下条件，那么它是 不间断 的：
 *
 * i，i + 1 ，...，j  表示子数组中的下标。对于所有满足 i <= i1, i2 <= j 的下标对，都有 0 <= |nums[i1] - nums[i2]| <= 2 。
 * 请你返回 不间断 子数组的总数目。
 *
 * 子数组是一个数组中一段连续 非空 的元素序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [5,4,2,4]
 * 输出：8
 * 解释：
 * 大小为 1 的不间断子数组：[5], [4], [2], [4] 。
 * 大小为 2 的不间断子数组：[5,4], [4,2], [2,4] 。
 * 大小为 3 的不间断子数组：[4,2,4] 。
 * 没有大小为 4 的不间断子数组。
 * 不间断子数组的总数目为 4 + 3 + 1 = 8 。
 * 除了这些以外，没有别的不间断子数组。
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：6
 * 解释：
 * 大小为 1 的不间断子数组：[1], [2], [3] 。
 * 大小为 2 的不间断子数组：[1,2], [2,3] 。
 * 大小为 3 的不间断子数组：[1,2,3] 。
 * 不间断子数组的总数目为 3 + 2 + 1 = 6 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/continuous-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Solution {


    public long continuousSubarrays(int[] nums) {
        // treeMap 记录 最大最小值 没个结点 进入 map 往右移动left 直到满足条件 累加以这个结尾的个数
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        int left = 0;
        // 枚举最后一个位置
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            // 移动左边
            while (left <= i) {
                int small = countMap.firstKey();
                int big = countMap.lastKey();

                if (Math.abs(big - small) <= 2) {
                    break;
                }
                int count = countMap.get(nums[left]);
                count--;
                if (count <= 0) {
                    countMap.remove(nums[left]);
                } else {
                    countMap.put(nums[left], count);
                }
                left++;
            }
            total += (i - left + 1);
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                5,4,2,4
        };
        long l = solution.continuousSubarrays(nums);
        System.out.println(l);
        Assert.assertEquals(8, l);

    }


}
