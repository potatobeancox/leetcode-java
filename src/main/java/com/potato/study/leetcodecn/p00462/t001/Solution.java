package com.potato.study.leetcodecn.p00462.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 462. 最少移动次数使数组元素相等 II
 *
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
 *
 * 例如:
 *
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 2
 *
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 *
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 462
    public int minMoves2(int[] nums) {
        // 数学上证明 找到中位数即可
        Arrays.sort(nums);
        long middle = 0;
        if ((nums.length & 1) == 1) {
            middle = nums[nums.length / 2];
        } else {
            middle = ((long) nums[nums.length / 2] + (long) nums[nums.length / 2 - 1])/ 2;;
        }
        // 然后计算 每个数与中位数的差
        long total = 0;
        for (int num : nums) {
            total += (Math.abs(num - middle));
        }
        return (int) total;
    }
}
