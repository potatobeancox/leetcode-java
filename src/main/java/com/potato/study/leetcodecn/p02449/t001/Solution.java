package com.potato.study.leetcodecn.p02449.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2449. 使数组相似的最少操作次数
 *
 * 给你两个正整数数组 nums 和 target ，两个数组长度相等。

 在一次操作中，你可以选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < nums.length ，并且：

 令 nums[i] = nums[i] + 2 且
 令 nums[j] = nums[j] - 2 。
 如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。

 请你返回将 nums 变得与 target 相似的最少操作次数。测试数据保证 nums 一定能变得与 target 相似。

  

 示例 1：

 输入：nums = [8,12,6], target = [2,14,10]
 输出：2
 解释：可以用两步操作将 nums 变得与 target 相似：
 - 选择 i = 0 和 j = 2 ，nums = [10,12,4] 。
 - 选择 i = 1 和 j = 2 ，nums = [10,14,2] 。
 2 次操作是最少需要的操作次数。
 示例 2：

 输入：nums = [1,2,5], target = [4,1,3]
 输出：1
 解释：一步操作可以使 nums 变得与 target 相似：
 - 选择 i = 1 和 j = 2 ，nums = [1,4,3] 。
 示例 3：

 输入：nums = [1,1,1,1,1], target = [1,1,1,1,1]
 输出：0
 解释：数组 nums 已经与 target 相似。
  

 提示：

 n == nums.length == target.length
 1 <= n <= 105
 1 <= nums[i], target[i] <= 106
 nums 一定可以变得与 target 相似。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2449
    public long makeSimilar(int[] nums, int[] target) {
        // 排序 按照 nums 和 target 奇偶性进行分割
        Arrays.sort(nums);
        Arrays.sort(target);
        // 分别遍历 奇数数组和偶数数组 求距离累加
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();

        List<Integer> target1 = new ArrayList<>();
        List<Integer> target2 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                nums1.add(nums[i]);
            } else {
                nums2.add(nums[i]);
            }

            if (target[i] % 2 == 1) {
                target1.add(target[i]);
            } else {
                target2.add(target[i]);
            }
        }
        // 如果个数都不一样那完成不了
        long dis = 0;
        for (int i = 0; i < nums1.size(); i++) {
            dis += Math.abs(nums1.get(i) - target1.get(i));
        }
        for (int i = 0; i < nums2.size(); i++) {
            dis += Math.abs(nums2.get(i) - target2.get(i));
        }
        // 遍历 累计 距离 / 4
        return dis / 4;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{8,12,6};
        int[] target = new int[]{2,14,10};
        long l = solution.makeSimilar(nums, target);
        System.out.println(l);
        Assert.assertEquals(2, l);
    }

}
