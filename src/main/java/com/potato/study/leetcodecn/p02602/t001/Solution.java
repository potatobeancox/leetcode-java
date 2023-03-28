package com.potato.study.leetcodecn.p02602.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 2602. 使数组元素全部相等的最少操作次数
 *
 * 给你一个正整数数组 nums 。
 *
 * 同时给你一个长度为 m 的整数数组 queries 。第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：
 *
 * 将数组里一个元素 增大 或者 减小 1 。
 * 请你返回一个长度为 m 的数组 answer ，其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。
 *
 * 注意，每次查询后，数组变回最开始的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,1,6,8], queries = [1,5]
 * 输出：[14,10]
 * 解释：第一个查询，我们可以执行以下操作：
 * - 将 nums[0] 减小 2 次，nums = [1,1,6,8] 。
 * - 将 nums[2] 减小 5 次，nums = [1,1,1,8] 。
 * - 将 nums[3] 减小 7 次，nums = [1,1,1,1] 。
 * 第一个查询的总操作次数为 2 + 5 + 7 = 14 。
 * 第二个查询，我们可以执行以下操作：
 * - 将 nums[0] 增大 2 次，nums = [5,1,6,8] 。
 * - 将 nums[1] 增大 4 次，nums = [5,5,6,8] 。
 * - 将 nums[2] 减小 1 次，nums = [5,5,5,8] 。
 * - 将 nums[3] 减小 3 次，nums = [5,5,5,5] 。
 * 第二个查询的总操作次数为 2 + 4 + 1 + 3 = 10 。
 * 示例 2：
 *
 * 输入：nums = [2,9,6,3], queries = [10]
 * 输出：[20]
 * 解释：我们可以将数组中所有元素都增大到 10 ，总操作次数为 8 + 1 + 4 + 7 = 20 。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 105
 * 1 <= nums[i], queries[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2602
    public List<Long> minOperations(int[] nums, int[] queries) {
        // 排序 计算前缀和 每次 二分法找到 第一个大于的节点 计算
        Arrays.sort(nums);
        long[] prefix = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                prefix[i] = nums[0];
            } else {
                prefix[i] = nums[i] + prefix[i-1];
            }
        }
        // 找到前缀和 之后比那里 querry 找到第一个大于 当前 query的下标
        int n = nums.length;
        List<Long> result = new ArrayList<>();
        for (int q : queries) {
            int index = findIndex(q, nums);
            if (index == -1) {
                // 左右的index 都是小于等于
                result.add(1L * n * q - prefix[n-1]);
            } else if (index == 0){
                // 所有的都 大于 q
                result.add(1L * prefix[n-1] - n * q);
            } else {
                result.add(1L * index * q - prefix[index-1] + prefix[n-1] - prefix[index-1] - (n - index) * q);
            }
        }
        return result;
    }

    /**
     * 二分查找第一个大于 target的 index
     * @param target
     * @param nums
     * @return
     */
    private int findIndex(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                // 大于 看看 还能不能找一个小一点的
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }


}
