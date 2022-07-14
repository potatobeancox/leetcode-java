package com.potato.study.leetcodecn.p02333.t001;

import java.util.Arrays;

/**
 * 2333. 最小差值平方和
 *
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。
 *
 * 数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])2 之和。
 *
 * 同时给你两个正整数 k1 和 k2 。你可以将 nums1 中的任意元素 +1 或者 -1 至多 k1 次。类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。
 *
 * 请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。
 *
 * 注意：你可以将数组中的元素变成 负 整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
 * 输出：579
 * 解释：nums1 和 nums2 中的元素不能修改，因为 k1 = 0 和 k2 = 0 。
 * 差值平方和为：(1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579 。
 * 示例 2：
 *
 * 输入：nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
 * 输出：43
 * 解释：一种得到最小差值平方和的方式为：
 * - 将 nums1[0] 增加一次。
 * - 将 nums2[2] 增加一次。
 * 最小差值平方和为：
 * (2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43 。
 * 注意，也有其他方式可以得到最小差值平方和，但没有得到比 43 更小答案的方案。
 *  
 *
 * 提示：
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 0 <= nums1[i], nums2[i] <= 105
 * 0 <= k1, k2 <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-sum-of-squared-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2333
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
        }
        int changeTime = k1 + k2;
        Arrays.sort(diff);
        int sameCount = 1;
        long current = diff[diff.length - 1];
        int i = diff.length - 2;
        for (; i >= 0; i--) {
            // 一样就往后移动 一样的都可以被减少
            if (diff[i] == current) {
                sameCount++;
                continue;
            }
            long d = diff[i] - current;
            long totalCount = d * sameCount;
            if (changeTime >= totalCount) {
                // 超过了 总个数
                changeTime -= totalCount;
                sameCount++;
                current = diff[i];
            } else {
                // 没超过 总个数
                break;
            }
        }
        // 还可以减一些
        int remindCount = 0;
        if (changeTime > 0) {
            int minusCount = changeTime / sameCount;
            current -= minusCount;
            // 还剩下 没用的
            remindCount = changeTime % sameCount;
        }
        // 最终计算diff
        long res = 0;
        if (remindCount == 0) {
            res = current * current * sameCount;
        } else {
            res = current * current * (sameCount - remindCount)
                    + (current - 1) * (current - 1) * remindCount;
        }
        for (int j = 0; j < diff.length - sameCount; j++) {
            res += diff[j] * diff[j];
        }
        return res;
    }

}
