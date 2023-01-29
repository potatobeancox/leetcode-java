package com.potato.study.leetcodecn.p01885.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1885. 统计数对
 *
 * 给你两个长度为 n 的整数数组 nums1 和 nums2 ，找出所有满足 i < j 且 nums1[i] + nums1[j] > nums2[i] + nums2[j] 的数对 (i, j) 。

 返回满足条件数对的 个数 。

  

 示例 1：

 输入：nums1 = [2,1,2,1], nums2 = [1,2,1,2]
 输出：1
 解释：满足条件的数对有 1 个：(0, 2) ，因为 nums1[0] + nums1[2] = 2 + 2 > nums2[0] + nums2[2] = 1 + 1
 示例 2：

 输入：nums1 = [1,10,6,2], nums2 = [1,4,1,5]
 输出：5
 解释：以下数对满足条件：
 - (0, 1) 因为 nums1[0] + nums1[1] = 1 + 10 > nums2[0] + nums2[1] = 1 + 4
 - (0, 2) 因为 nums1[0] + nums1[2] = 1 + 6 > nums2[0] + nums2[2] = 1 + 1
 - (1, 2) 因为 nums1[1] + nums1[2] = 10 + 6 > nums2[1] + nums2[2] = 4 + 1
 - (1, 3) 因为 nums1[1] + nums1[3] = 10 + 2 > nums2[1] + nums2[3] = 4 + 5
 - (2, 3) 因为 nums1[2] + nums1[3] = 6 + 2 > nums2[2] + nums2[3] = 1 + 5
  

 提示：

 n == nums1.length == nums2.length
 1 <= n <= 105
 1 <= nums1[i], nums2[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-pairs-in-two-arrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long countPairs(int[] nums1, int[] nums2) {
        // nums1[i] + nums1[j] > nums2[i] + nums2[j]
        // nums1[i] - nums2[i] + nums1[j] + nums2[j] > 0 且 i < j
        int n = nums1.length;
        long[] diff = new long[n];
        for (int i = 0; i < n; i++) {
            diff[i] = nums1[i] - nums2[i];
        }
        // 求 nums1 - nums2 给个位置的差 n2 计算每个位置看能不能达到目的
        Arrays.sort(diff);
        long total = 0;
        for (int i = 0; i < diff.length; i++) {
            // 对于每个位置 i 找到后面一个位置j 满足 nums1[i] - nums2[i] + nums1[j] + nums2[j] > 0
            int j = findIndexAfterI(diff, -1 * diff[i] + 1, i + 1);
            if (j != -1) {
                int count = diff.length - j;
                total += count;
            }

        }
        return total;
    }

    /**
     * 二分法找到 大于等于 target的第一个位置
     * @param diff
     * @param target
     * @return
     */
    private int findIndexAfterI(long[] diff, long target, int i) {
        int left = i;
        int right = diff.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (diff[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                // mid 小 需要大一些
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[] {
                2,1,2,1
        };
        int[] nums2 = new int[] {
                1,2,1,2
        };
        long l = solution.countPairs(nums1, nums2);
        System.out.println(l);
        Assert.assertEquals(1, l);
    }

}
