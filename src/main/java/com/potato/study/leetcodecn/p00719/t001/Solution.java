package com.potato.study.leetcodecn.p00719.t001;

import java.util.Arrays;

/**
 * 719. 找出第 K 小的数对距离
 *
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 *
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,1], k = 1
 * 输出：0
 * 解释：数对和对应的距离如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 距离第 1 小的数对是 (1,1) ，距离为 0 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1,6,1], k = 3
 * 输出：5
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 2 <= n <= 104
 * 0 <= nums[i] <= 106
 * 1 <= k <= n * (n - 1) / 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-th-smallest-pair-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 719
    public int smallestDistancePair(int[] nums, int k) {
        // 先对 nums 进行排序
        Arrays.sort(nums);
        // 二分法 找到 left = 0  right 等于 最后的数字减去第一个数字 每次检测 距离小于等于 mid 的个数有多少个
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        // 如果个数大于等于k 往小的找 并记录 否则往打了找
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int distanceBelowCount = getDistanceBelowCount(nums, mid);
            if (distanceBelowCount >= k) {
                res = distanceBelowCount;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }


    /**
     * 获取生序数组中差距小于等于mid的 对数量
     * @param nums 生序数组
     * @param mid
     * @return
     */
    private int getDistanceBelowCount(int[] nums, int mid) {
        int right = 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            while (right < nums.length && nums[right] - nums[i] <= mid) {
                right++;
            }
            if (right - 1 <= i) {
                continue;
            }
            // right - 1 是小于等于 个数就是 从 i+1 到
            count += (right - 1 - i);
        }
        return count;
    }
}
