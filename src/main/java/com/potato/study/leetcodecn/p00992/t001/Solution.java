package com.potato.study.leetcodecn.p00992.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. K 个不同整数的子数组
 *
 * 给定一个正整数数组 nums和一个整数 k ，返回 num 中 「好子数组」 的数目。
 *
 * 如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
 *
 * 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
 * 子数组 是数组的 连续 部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,2,3], k = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 *
 * 输入：nums = [1,2,1,3,4], k = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/subarrays-with-k-different-integers/solution/k-ge-bu-tong-zheng-shu-de-zi-shu-zu-by-l-ud34/
     * @param nums
     * @param k
     * @return
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        // 最多k中字符的子数组个数 - 最多k-1个数
        return subarraysWithKMostDistinct(nums, k) - subarraysWithKMostDistinct(nums, k-1);
    }

    /**
     * 找到字符种类最多为 targetCount 的子数组个数
     * @param nums
     * @param targetCount
     * @return
     */
    private int subarraysWithKMostDistinct(int[] nums, int targetCount) {
        // 直接往右遍历 计算个数 注意题目有一个简化条件  1 <= nums[i], k <= nums.length
        int n = nums.length;
        int[] freq = new int[n+1];
        // 可以直接用 数组进行计数

        // 用一个 map key 是出现的数字 value 是该数字出现的次数 基础窗口的左边界 计算左边界到当前位置有多少个数字 就是多少个子数组
        int left = 0;
        int subarrayCount = 0;
        int windowCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];

            if (freq[val] == 0) {
                windowCount++;
            }
            freq[val]++;

            while (windowCount > targetCount) {
                int removeVal = nums[left];
                freq[removeVal]--;
                if (freq[removeVal] == 0) {
                    windowCount--;
                }

                left++;
            }
            // windowCount <= targetCount
            subarrayCount += (i - left + 1);

        }
        return subarrayCount;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,2,1,2,3
        };
        int k = 2;
        int i = solution.subarraysWithKDistinct(nums, k);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }


}
