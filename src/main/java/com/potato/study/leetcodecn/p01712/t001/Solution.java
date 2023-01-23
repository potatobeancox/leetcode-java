package com.potato.study.leetcodecn.p01712.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * 1712. 将数组分成三个子数组的方案数
 *
 * 我们称一个分割整数数组的方案是 好的 ，当它满足：
 *
 * 数组被分成三个 非空 连续子数组，从左至右分别命名为 left ， mid ， right 。
 * left 中元素和小于等于 mid 中元素和，mid 中元素和小于等于 right 中元素和。
 * 给你一个 非负 整数数组 nums ，请你返回 好的 分割 nums 方案数目。由于答案可能会很大，请你将结果对 109 + 7 取余后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1]
 * 输出：1
 * 解释：唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,2,2,5,0]
 * 输出：3
 * 解释：nums 总共有 3 种好的分割方案：
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * 示例 3：
 *
 * 输入：nums = [3,2,1]
 * 输出：0
 * 解释：没有好的分割方案。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 105
 * 0 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int waysToSplit(int[] nums) {
        // 先求一个前缀和 带0的那种
        int n = nums.length;
        int[] prefix = new int[n+1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        // 确定 i位置 第一段截止为止 再求 jk 中间结尾最小位置 和最大位置
        int j = 2;
        int k = 1;
        long res = 0;
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i <= n && i < n- 1 && prefix[i] * 3 <= prefix[n]; i++) {
            // mid 开始位置 和 mid 结束位置 i包括 都在left
            long left = prefix[i];
            j = Math.max(i+1, j);
            while (j+1 < n && prefix[j] - prefix[i] < left) {
                j++;
            }
            while (k+1 < n && prefix[k+1] - prefix[i] <= prefix[n] - prefix[k+1]) {
                k++;
            }
            // mid 结束为止 从最早是j 最晚是 k 中间有多少 两边都得包括
            long count = (long)k - j + 1;
            res += count;
            res %= mod;
        }
        return (int)(res % mod);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,1,1
        };
        int i = solution.waysToSplit(nums);
        System.out.println(i);
        Assert.assertEquals(1, i);


        nums = new int[] {
                1,2,2,2,5,0
        };
        i = solution.waysToSplit(nums);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
