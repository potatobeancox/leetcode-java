package com.potato.study.leetcodecn.p01493.t001;

import org.junit.Assert;

/**
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 *
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 *
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 *
 * 如果不存在这样的子数组，请返回 0 。
 *
 *  
 *
 * 提示 1：
 *
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * 示例 2：
 *
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * 示例 3：
 *
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 * 示例 4：
 *
 * 输入：nums = [1,1,0,0,1,1,1,0,1]
 * 输出：4
 * 示例 5：
 *
 * 输入：nums = [0,0,0]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-subarray-of-1s-after-deleting-one-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int longestSubarray(int[] nums) {
        // 1493 遍历 nums 如果
        int lastDeleteIndex = -1;
        int currentLength = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 1) {
                currentLength++;
            } else {
                // init
                if (lastDeleteIndex == -1) {
                    lastDeleteIndex = i;
                } else {
                    // has already delete
                    max = Math.max(max, currentLength);
                    currentLength = i - lastDeleteIndex - 1;
                    lastDeleteIndex = i;
                }
            }
        }
        if (currentLength > 0) {
            max = Math.max(max, currentLength);
        }
        return max == nums.length ? nums.length - 1 : max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,1,0,1
        };
        int i = solution.longestSubarray(nums);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
