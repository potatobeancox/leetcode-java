package com.potato.study.leetcodecn.p00360.t001;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 360. 有序转化数组
 *
 * 给你一个已经 排好序 的整数数组 nums 和整数 a 、 b 、 c 。对于数组中的每一个元素 nums[i] ，计算函数值 f(x) = ax2 + bx + c ，请 按升序返回数组 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * 输出: [3,9,15,33]
 * 示例 2：
 *
 * 输入: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * 输出: [-23,-5,1,7]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -100 <= nums[i], a, b, c <= 100
 * nums 按照 升序排列
 *  
 *
 * 进阶：你可以在时间复杂度为 O(n) 的情况下解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-transformed-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        // f(x) = ax2 + bx + c
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = fx(nums[i], a, b, c);
        }
        Arrays.sort(res);
        return res;
    }

    private int fx(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}

