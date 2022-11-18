package com.potato.study.leetcodecn.p00923.t001;

import java.util.Arrays;

/**
 * 923. 三数之和的多种可能
 *
 * 给定一个整数数组 arr ，以及一个整数 target 作为目标值，返回满足 i < j < k 且 arr[i] + arr[j] + arr[k] == target 的元组 i, j, k 的数量。
 *
 * 由于结果会非常大，请返回 109 + 7 的模。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * 输出：20
 * 解释：
 * 按值枚举(arr[i], arr[j], arr[k])：
 * (1, 2, 5) 出现 8 次；
 * (1, 3, 4) 出现 8 次；
 * (2, 2, 4) 出现 2 次；
 * (2, 3, 3) 出现 2 次。
 * 示例 2：
 *
 * 输入：arr = [1,1,2,2,2,2], target = 5
 * 输出：12
 * 解释：
 * arr[i] = 1, arr[j] = arr[k] = 2 出现 12 次：
 * 我们从 [1,1] 中选择一个 1，有 2 种情况，
 * 从 [2,2,2,2] 中选出两个 2，有 6 种情况。
 *  
 *
 * 提示：
 *
 * 3 <= arr.length <= 3000
 * 0 <= arr[i] <= 100
 * 0 <= target <= 300
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum-with-multiplicity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/3sum-with-multiplicity/solution/java-923-san-shu-zhi-he-de-duo-chong-ke-zg7wv/
     * @param arr
     * @param target
     * @return
     */
    public int threeSumMulti(int[] arr, int target) {
        // 用一个 数组计数 之前的数字，每次开始遍历 23位置的元素
        int[] count = new int[101];
        count[arr[0]]++;
        int totalCount = 0;
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int diff = target - arr[i] - arr[j];
                if (diff < 0 || diff > 100) {
                    continue;
                }
                totalCount += count[diff];
                totalCount %= mod;
            }
            count[arr[i]]++;
        }
        return totalCount % mod;
    }


}
