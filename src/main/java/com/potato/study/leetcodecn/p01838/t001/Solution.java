package com.potato.study.leetcodecn.p01838.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 1838. 最高频元素的频数
 *
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 *
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 *
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        // 升序排列 维护一个窗口 left 和 right
        Arrays.sort(nums);
        // 每次往后移动right ，计算当前增加了 多少个 （right - left） * right 和 right-1 值 求乘积 对窗口内部值进行--计算
        int max = 1;
        int total = 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            // 窗口内布值需要进行修改
            int newValue = nums[right];
            int oldValue = nums[right - 1];
            // 最后一个不用 -1
            total += (newValue - oldValue) * (right - left);
            // 如果计算后的窗口内部值
            while (total > k) {
                total -= (newValue - nums[left]);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1,2,4};
        int k = 5;
        int i = solution.maxFrequency(nums, k);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
