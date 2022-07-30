package com.potato.study.leetcodecn.p01983.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1983. 范围和相等的最宽索引对
 *
 * 给定两个 以0为索引 的二进制数组 nums1 和 nums2 。找出 最宽 的索引对 (i, j) ，使的 i <= j 并且 nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]。

 最宽 的指标对是指在 i 和 j 之间的 距离最大 的指标对。一对指标之间的 距离 定义为 j - i + 1 。

 返回 最宽 索引对的 距离 。如果没有满足条件的索引对，则返回 0 。

  

 示例 1:

 输入: nums1 = [1,1,0,1], nums2 = [0,1,1,0]
 输出: 3
 解释:
 如果i = 1, j = 3:
 Nums1 [1] + Nums1 [2] + Nums1[3] = 1 + 0 + 1 = 2。
 Nums2 [1] + Nums2 [2] + Nums2[3] = 1 + 1 + 0 = 2。
 i和j之间的距离是j - i + 1 = 3 - 1 + 1 = 3。
 示例 2:

 输入: nums1 = [0,1], nums2 = [1,1]
 输出: 1
 解释:
 If i = 1 and j = 1:
 nums1[1] = 1。
 nums2[1] = 1。
 i和j之间的距离是j - i + 1 = 1 - 1 + 1 = 1。
 示例 3:

 输入: nums1 = [0], nums2 = [1]
 输出: 0
 解释:
 没有满足要求的索引对。
  

 提示:

 n == nums1.length == nums2.length
 1 <= n <= 105
 nums1[i] 仅为 0 或 1.
 nums2[i] 仅为 0 或 1.

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/widest-pair-of-indices-with-equal-range-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/widest-pair-of-indices-with-equal-range-sum/solution/ji-suan-shu-zu-chai-zhi-he-qian-zhui-he-d30i1/
     * @param nums1
     * @param nums2
     * @return
     */
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        // 1 分别对 nums1 nums2 做差
        int n = nums1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = nums1[i] - nums2[i];
        }
        // 2 对1求前缀和 留一个空位
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < n+1; i++) {
            prefixSum[i] = prefixSum[i-1] + diff[i-1];
        }
        // 3 遍历前缀和 用map 记录第一次出现的位置 ，如果是多次出现，更新 maxLength
        int maxLength = 0;
        Map<Integer, Integer> firstAppearIndexMap = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            if (!firstAppearIndexMap.containsKey(prefixSum[i])) {
                // 第一次出现
                firstAppearIndexMap.put(prefixSum[i], i);
            } else {
                // 如果是多次出现，更新 maxLength
                maxLength = Math.max(maxLength, i - firstAppearIndexMap.get(prefixSum[i]));
            }
        }
        return maxLength;
    }

}
