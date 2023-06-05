package com.potato.study.leetcodecn.p02638.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 2638. 统计 K-Free 子集的总数
 *
 * 给定一个包含 无重复 元素的整数数组 nums 和一个整数 k 。
 *
 * 如果一个子集中 不 存在两个差的绝对值等于 k 的元素，则称其为 k-Free 子集。注意，空集是一个 k-Free 子集。
 *
 * 返回 nums 中 k-Free 子集的数量。
 *
 * 一个数组的 子集 是该数组中的元素的选择（可能为零个）。
 *
 *  
 *
 * 示例 1 ：
 *
 * 输入：nums = [5,4,6], k = 1
 * 输出：5
 * 解释：有 5 个合法子集：{}, {5}, {4}, {6} 和 {4, 6} 。
 * 示例 2 ：
 *
 * 输入：nums = [2,3,5,8], k = 5
 * 输出：12
 * 解释：有12个合法子集：{}, {2}, {3}, {5}, {8}, {2, 3}, {2, 3, 5}, {2, 5}, {2, 5, 8}, {2, 8}, {3, 5} 和 {5, 8} 。
 * 示例 3 ：
 *
 * 输入：nums = [10,5,9,11], k = 20
 * 输出：16
 * 解释：所有的子集都是有效的。由于子集的总数为 24 = 16，因此答案为 16 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 1000
 * 1 <= k <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-the-number-of-k-free-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {







    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        // 将 nums 按照对k取余数进行分组
        List<Integer>[] group = new List[k];
        for (int i = 0; i < k; i++) {
            group[i] = new ArrayList<>();
        }
        for (int num : nums) {
            int index = num % k;
            group[index].add(num);
        }
        // 遍历每个数组 求能购达成 kFreeSubset的个数 用乘法定理计算总个数 空集也是1个 k free
        long res = 1;
        for (List<Integer> eachGroup : group) {
            if (eachGroup.size() > 0) {
                res *= getCount(eachGroup, k);
            }
        }
        return res;
    }

    /**
     * subList 中可以构成多少个 k free set
     * @param subList
     * @return
     */
    private long getCount(List<Integer> subList, int k) {
        // 先进行排序
        Collections.sort(subList);
        int size = subList.size();
        long[] dp = new long[size];
        // dp i 到i位置 为止，能组成多少个 k-free-set
        dp[0] = 2;
        for (int i = 1; i < size; i++) {
            // 两个之间差了 k 不能在同一个组里边
            if (subList.get(i) - subList.get(i-1) == k) {
                // 如果选择这个数字 那就只能选择 i-2 的情况 or 不选择
                if (i>=2) {
                    dp[i] = dp[i-2] + dp[i-1];
                } else {
                    dp[i] = 1 + dp[i-1];
                }
            } else {
                // 选择i 或者不选i
                dp[i] = dp[i-1] * 2;
            }
        }
        return dp[size-1];
    }



}
