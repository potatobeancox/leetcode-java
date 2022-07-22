package com.potato.study.leetcodecn.p00594.t001;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 594. 最长和谐子序列
 *
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 *
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,2,2,5,2,3,7]
 * 输出：5
 * 解释：最长的和谐子序列是 [3,2,2,2,3]
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [1,1,1,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    /**
     * 需要判断是够 只有一个数字
     * 改成使用map计数
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        // -109 <= nums[i] <= 109  nums = 109 计数
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            Integer count = numCountMap.getOrDefault(num, 0);
            count++;
            numCountMap.put(num, count);
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry: numCountMap.entrySet()) {
            Integer key = entry.getKey();
            if (!numCountMap.containsKey(key+1)) {
                continue;
            }
            max = Math.max(max, numCountMap.get(key +1) + entry.getValue());
        }
        return max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,3,2,2,5,2,3,7
        };
        int lhs = solution.findLHS(arr);
        System.out.println(lhs);
        Assert.assertEquals(5, lhs);

        arr = new int[] {
                1,1
        };
        lhs = solution.findLHS(arr);
        System.out.println(lhs);
        Assert.assertEquals(0, lhs);

        arr = new int[] {
                1,3
        };
        lhs = solution.findLHS(arr);
        System.out.println(lhs);
        Assert.assertEquals(0, lhs);
    }

}
