package com.potato.study.leetcodecn.p02261.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2261. 含最多 K 个可整除元素的子数组
 *
 * 给你一个整数数组 nums 和两个整数 k 和 p ，找出并返回满足要求的不同的子数组数，要求子数组中最多 k 个可被 p 整除的元素。

 如果满足下述条件之一，则认为数组 nums1 和 nums2 是 不同 数组：

 两数组长度 不同 ，或者
 存在 至少 一个下标 i 满足 nums1[i] != nums2[i] 。
 子数组 定义为：数组中的连续元素组成的一个 非空 序列。

  

 示例 1：

 输入：nums = [2,3,3,2,2], k = 2, p = 2
 输出：11
 解释：
 位于下标 0、3 和 4 的元素都可以被 p = 2 整除。
 共计 11 个不同子数组都满足最多含 k = 2 个可以被 2 整除的元素：
 [2]、[2,3]、[2,3,3]、[2,3,3,2]、[3]、[3,3]、[3,3,2]、[3,3,2,2]、[3,2]、[3,2,2] 和 [2,2] 。
 注意，尽管子数组 [2] 和 [3] 在 nums 中出现不止一次，但统计时只计数一次。
 子数组 [2,3,3,2,2] 不满足条件，因为其中有 3 个元素可以被 2 整除。
 示例 2：

 输入：nums = [1,2,3,4], k = 4, p = 1
 输出：10
 解释：
 nums 中的所有元素都可以被 p = 1 整除。
 此外，nums 中的每个子数组都满足最多 4 个元素可以被 1 整除。
 因为所有子数组互不相同，因此满足所有限制条件的子数组总数为 10 。
  

 提示：

 1 <= nums.length <= 200
 1 <= nums[i], p <= 200
 1 <= k <= nums.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/k-divisible-elements-subarrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/k-divisible-elements-subarrays/solution/by-shou-hu-zhe-t-moiy/
     * @param nums
     * @param k
     * @param p
     * @return
     */
    public int countDistinct(int[] nums, int k, int p) {
        // 枚举 每个开始 位置 找结束位置 使用 Set 记录 字串的大小
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            StringBuilder builder = new StringBuilder();
            int cnt = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % p == 0) {
                    cnt++;

                }
                // 最多k个被整除
                if (cnt <= k) {
                    builder.append(nums[j]).append("_");
                    set.add(builder.toString());
                } else {
                    break;
                }
            }
        }
        return set.size();
    }
}
