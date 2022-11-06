package com.potato.study.leetcodecn.p01852.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1852. 每个子数组的数字种类数
 *
 * 给你一个整数数组 nums与一个整数 k，请你构造一个长度 n-k+1 的数组 ans，这个数组第i个元素 ans[i] 是每个长度为k的子数组 nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]]中数字的种类数。
 *
 * 返回这个数组 ans。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,2,2,1,3], k = 3
 * 输出: [3,2,2,2,3]
 * 解释：每个子数组的数字种类计算方法如下：
 * - nums[0:2] = [1,2,3] 所以 ans[0] = 3
 * - nums[1:3] = [2,3,2] 所以 ans[1] = 2
 * - nums[2:4] = [3,2,2] 所以 ans[2] = 2
 * - nums[3:5] = [2,2,1] 所以 ans[3] = 2
 * - nums[4:6] = [2,1,3] 所以 ans[4] = 3
 * 示例 2:
 *
 * 输入: nums = [1,1,1,1,2,3,4], k = 4
 * 输出: [1,2,3,4]
 * 解释: 每个子数组的数字种类计算方法如下：
 * - nums[0:3] = [1,1,1,1] 所以 ans[0] = 1
 * - nums[1:4] = [1,1,1,2] 所以 ans[1] = 2
 * - nums[2:5] = [1,1,2,3] 所以 ans[2] = 3
 * - nums[3:6] = [1,2,3,4] 所以 ans[3] = 4
 *  
 *
 * 提示:
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/distinct-numbers-in-each-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] distinctNumbers(int[] nums, int k) {
        int resultLen = nums.length - k + 1;
        int[] countResult = new int[resultLen];
        // 遍历 nums 统计次数
        Map<Integer, Integer> countMap = new HashMap<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            // 移除之前的
            if (i == k-1) {
                countResult[index] = countMap.size();
                index++;
                continue;
            }
            if (i >= k) {
                Integer count = countMap.remove(nums[i-k]);
                count--;
                if (count > 0) {
                    countMap.put(nums[i-k], count);
                }
                countResult[index] = countMap.size();
                index++;
            }
        }
        return countResult;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[1,2,3,2,2,1,3]";
        int[] nums = LeetcodeInputUtils.inputString2IntArray(input);
        int k = 3;
        int[] ints = solution.distinctNumbers(nums, k);
        // [3,2,2,2,3]
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                3,2,2,2,3
        }, ints);
    }
}
