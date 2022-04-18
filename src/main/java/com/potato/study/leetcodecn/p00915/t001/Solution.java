package com.potato.study.leetcodecn.p00915.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 915. 分割数组
 *
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：

 left 中的每个元素都小于或等于 right 中的每个元素。
 left 和 right 都是非空的。
 left 的长度要尽可能小。
 在完成这样的分组后返回 left 的 长度 。

 用例可以保证存在这样的划分方法。

  

 示例 1：

 输入：nums = [5,0,3,8,6]
 输出：3
 解释：left = [5,0,3]，right = [8,6]
 示例 2：

 输入：nums = [1,1,1,0,6,12]
 输出：4
 解释：left = [1,1,1,0]，right = [6,12]
  

 提示：

 2 <= nums.length <= 105
 0 <= nums[i] <= 106
 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/partition-array-into-disjoint-intervals
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/partition-array-into-disjoint-intervals/solution/fen-ge-shu-zu-by-leetcode/
     * @param nums
     * @return
     */
    public int partitionDisjoint(int[] nums) {
        // left 数组 的 max right数组的 min
        int[] left = new int[nums.length];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            left[i] = max;
        }
        int[] right = new int[nums.length];
        int min = nums[nums.length-1];
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            right[i] = min;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (left[i] <= right[i+1]) {
                return i + 1;
            }
        }
        return -1;
    }


}
