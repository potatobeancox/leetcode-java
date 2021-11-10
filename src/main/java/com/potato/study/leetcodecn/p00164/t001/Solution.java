package com.potato.study.leetcodecn.p00164.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 164. 最大间距
 *
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

 如果数组元素个数小于 2，则返回 0。

 示例 1:

 输入: [3,6,9,1]
 输出: 3
 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 示例 2:

 输入: [10]
 输出: 0
 解释: 数组元素个数小于 2，因此返回 0。
 说明:

 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-gap
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 遍历一遍 max min n个数
     那么最大距离一定大于 k
     max-min /n-1

     jiang max-min 分成 多个长度为k的区间

     所以区间内部不会出现 最大距离
     求每个区间最大 最小值

     比较两个区间之间max 和min 最大差距就是max
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, nums[i+1] - nums[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {3,6,9,1};
        int gap = solution.maximumGap(nums);
        System.out.println(gap);
        Assert.assertEquals(3, gap);


        nums = new int[] {10};
        gap = solution.maximumGap(nums);
        System.out.println(gap);
        Assert.assertEquals(0, gap);


        nums = new int[] {1,1,1,1,1,5,5,5,5,5};
        gap = solution.maximumGap(nums);
        System.out.println(gap);
        Assert.assertEquals(4, gap);
    }
}
