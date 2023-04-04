package com.potato.study.leetcodecn.other.swordoffer2.p0009.t001;

import org.junit.Assert;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组
 *
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 示例 2:
 *
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 *  
 *
 * 提示: 
 *
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 *  
 *
 * 注意：本题与主站 713 题相同：https://leetcode-cn.com/problems/subarray-product-less-than-k/ 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ZVAVXX
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    // 009
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // nums 正整数 乘积小于 k 子数组个数
        // prefix1 / prefix2 < k  ==》 prefix1 / k < prefix2 找到第一个大于 prefix1 / k的 index
        // 前缀* 一定是递增的 treeSet 中找到
        int n = nums.length;
        long[] product = new long[n+1];
        product[0] = 1;
        int totalCount = 0;
        for (int i = 0; i < nums.length; i++) {
            product[i+1] = product[i] * nums[i];
            long target = product[i+1] / k;
            int biggerIndex = findBiggerIndex(product, target, 0, i);
            // 没找到
            if (biggerIndex == -1) {
                continue;
            }
            totalCount += (i+1 - biggerIndex);
        }
        return totalCount;
    }

    /**
     * 在 product 中 index 位于 【from, to】 位置找到大于 target的 index
     * @param product
     * @param target
     * @param from from的坐标
     * @param to to坐标
     * @return
     */
    private int findBiggerIndex(long[] product, long target, int from, int to) {
        int left = from;
        int right = to;

        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (product[mid] > target) {
                res = mid;
                right = mid - 1;
            } else {
                // mid 值小了
                left = mid + 1;
            }
        }
        return res;
    }


}
