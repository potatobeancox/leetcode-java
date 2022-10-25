package com.potato.study.leetcodecn.p01191.t001;


import org.junit.Assert;

/**
 * 1191. K 次串联后最大子数组之和
 *
 * 给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。
 *
 * 例如，如果 arr = [1, 2] ， k = 3 ，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。
 *
 * 返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
 *
 * 由于 结果可能会很大，需要返回的 109 + 7 的 模 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2], k = 3
 * 输出：9
 * 示例 2：
 *
 * 输入：arr = [1,-2,1], k = 5
 * 输出：2
 * 示例 3：
 *
 * 输入：arr = [-1,-2], k = 7
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 105
 * 1 <= k <= 105
 * -104 <= arr[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-concatenation-maximum-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int kConcatenationMaxSum(int[] arr, int k) {
        // 先 扩大2倍计算 最长有sum 有多大 (至多扩大2倍)
        long currentMax = arr[0];
        long totalMax = currentMax;
        long arrSum = arr[0];
        for (int i = 1; i < Math.min(k, 2) * arr.length; i++) {
            currentMax = Math.max(currentMax + arr[i % arr.length], arr[i % arr.length]);
            totalMax = Math.max(totalMax, currentMax);
            if (i < arr.length) {
                arrSum += arr[i];
            }
        }
        if (arrSum > 0 && k > 2) {
            totalMax += (k-2) * arrSum;
        }
        if (totalMax < 0) {
            return 0;
        }
        return (int) (totalMax % 1_000_000_007);
    }

}
