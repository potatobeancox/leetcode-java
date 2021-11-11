package com.potato.study.leetcodecn.p01218.t001;


import java.util.HashMap;
import java.util.Map;

/**
 * 1218. 最长定差子序列
 *
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 *
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 *
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 *
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 105
 * -104 <= arr[i], difference <= 104
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1218
    public int longestSubsequence(int[] arr, int difference) {
        // 从前往后 遍历 用map 记录 每个数字出现时的长长度 dpi = map get arri - diff  + 1
        Map<Integer, Integer> maxCountMap = new HashMap<>();
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i] - difference;
            Integer maxCount = maxCountMap.getOrDefault(current, 0);
            maxCount++;
            maxCountMap.put(arr[i], maxCount);
            maxLength = Math.max(maxLength, maxCount);
        }
        return maxLength;
    }
}
