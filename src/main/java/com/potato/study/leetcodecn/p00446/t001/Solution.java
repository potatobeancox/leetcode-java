package com.potato.study.leetcodecn.p00446.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 446. 等差数列划分 II - 子序列
 *
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。

 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。

 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。

 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 题目数据保证答案是一个 32-bit 整数。

  

 示例 1：

 输入：nums = [2,4,6,8,10]
 输出：7
 解释：所有的等差子序列为：
 [2,4,6]
 [4,6,8]
 [6,8,10]
 [2,4,6,8]
 [4,6,8,10]
 [2,4,6,8,10]
 [2,6,10]
 示例 2：

 输入：nums = [7,7,7,7,7]
 输出：16
 解释：数组中的任意子序列都是等差子序列。
  

 提示：

 1  <= nums.length <= 1000
 -231 <= nums[i] <= 231 - 1

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/arithmetic-slices-ii-subsequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        // dp ij 以i 为结尾 公差是 j 的等差数列个数
        int n = nums.length;
        Map<Long, Long>[] countMap = new Map[n];
        for (int i = 0; i < n; i++) {
            countMap[i] = new HashMap<>();
        }
        // 最后一个点位置
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 倒数第二个点位置
            for (int j = 0; j < i; j++) {
                long diff = 1L * nums[i] - nums[j];
                long cnt = countMap[j].getOrDefault(diff, 0L);
                // 统计结果
                ans += cnt;
                // 在原本以 nums[j]nums[j] 为结尾的，且差值为 dd 的子序列的基础上接上 nums[i]nums[i]，
                // 再加上新的子序列 (nums[j],nums[i])，共 f[j][d]+1 个子序列。
                countMap[i].put(diff,
                        countMap[i].getOrDefault(diff, 0L) + cnt + 1);
            }
        }
        return (int)ans;
    }
}
