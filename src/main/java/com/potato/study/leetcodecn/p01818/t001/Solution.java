package com.potato.study.leetcodecn.p01818.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 1818. 绝对差值和
 *
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。

 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。

 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。

 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。

 |x| 定义为：

 如果 x >= 0 ，值为 x ，或者
 如果 x <= 0 ，值为 -x
  

 示例 1：

 输入：nums1 = [1,7,5], nums2 = [2,3,5]
 输出：3
 解释：有两种可能的最优方案：
 - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
 - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
 示例 2：

 输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 输出：0
 解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
 示例 3：

 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 输出：20
 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
  

 提示：

 n == nums1.length
 n == nums2.length
 1 <= n <= 105
 1 <= nums1[i], nums2[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-absolute-sum-difference
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = 1_000_000_000 + 7;
        // 将 nums1 放入 tree map里边 求 sum 绝对值 总和
        TreeSet<Integer> nums1Set = new TreeSet<>();
        // 遍历 求 nums1 和 nums2 某个位置的和 查找最近的 计算差 求 最小值
        int n = nums1.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs((long)nums1[i] - nums2[i]);
            nums1Set.add(nums1[i]);
        }
        // 每个位置找到nums2 对接近的
        long min = sum;
        for (int i = 0; i < n; i++) {
            long sumWithout = sum - Math.abs((long)nums1[i] - nums2[i]);
            Integer floor = nums1Set.floor(nums2[i]);
            if (floor != null) {
                min = Math.min(min, sumWithout + Math.abs((long)floor - nums2[i]));
            }
            Integer ceiling = nums1Set.ceiling(nums2[i]);
            if (ceiling != null) {
                min = Math.min(min, sumWithout + Math.abs((long)ceiling - nums2[i]));
            }
        }
        return (int)min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[]{1,7,5};
        int[] nums2 = new int[]{2,3,5};
        int i = solution.minAbsoluteSumDiff(nums1, nums2);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
