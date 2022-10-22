package com.potato.study.leetcodecn.p02401.t001;

import org.junit.Assert;

/**
 * 2401. 最长优雅子数组
 *
 * 给你一个由 正 整数组成的数组 nums 。
 *
 * 如果 nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。
 *
 * 返回 最长 的优雅子数组的长度。
 *
 * 子数组 是数组中的一个 连续 部分。
 *
 * 注意：长度为 1 的子数组始终视作优雅子数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,8,48,10]
 * 输出：3
 * 解释：最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
 * - 3 AND 8 = 0
 * - 3 AND 48 = 0
 * - 8 AND 48 = 0
 * 可以证明不存在更长的优雅子数组，所以返回 3 。
 * 示例 2：
 *
 * 输入：nums = [3,1,5,11,13]
 * 输出：1
 * 解释：最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-nice-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestNiceSubarray(int[] nums) {
        // 统计 32个位置 每个 1对应出现的个数
        int[] count = new int[32];
        // 如果某个位置超过了1 对 开始位置 循环出栈 直到为0
        int left = 0;
        int max = 0;
        for (int right = 0; right < nums.length; right++) {
            String s = Integer.toBinaryString(nums[right]);
            char[] chars = s.toCharArray();
            int countIndex = 0;
            for (int i = chars.length - 1; i >= 0; i--) {
                if ('1' == chars[i]) {
                    count[countIndex]++;
                }
                // 如果当前count i 大于 1 需要一直pop知道 等于1
                while (left < right && count[countIndex] > 1) {
                    char[] temp = Integer.toBinaryString(nums[left]).toCharArray();
                    int tempCountIndex = 0;
                    for (int j = temp.length - 1; j >= 0; j--) {
                        if ('1' == temp[j]) {
                            count[tempCountIndex]--;
                        }
                        tempCountIndex++;
                    }
                    left++;
                }
                countIndex++;
            }
            // 计算长度 right - left + 1
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{
                1,3,8,48,10
        };
        int i = solution.longestNiceSubarray(nums);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }

}
