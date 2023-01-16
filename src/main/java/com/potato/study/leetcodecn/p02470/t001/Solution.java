package com.potato.study.leetcodecn.p02470.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 2470. 最小公倍数为 K 的子数组数目
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 nums 的 子数组 中满足 元素最小公倍数为 k 的子数组数目。
 *
 * 子数组 是数组中一个连续非空的元素序列。
 *
 * 数组的最小公倍数 是可被所有数组元素整除的最小正整数。
 *
 *  
 *
 * 示例 1 ：
 *
 * 输入：nums = [3,6,2,7,1], k = 6
 * 输出：4
 * 解释：以 6 为最小公倍数的子数组是：
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * 示例 2 ：
 *
 * 输入：nums = [3], k = 2
 * 输出：0
 * 解释：不存在以 2 为最小公倍数的子数组。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-subarrays-with-lcm-equal-to-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int subarrayLCM(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        int totalCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int lcm = nums[i];
            for (int j = i; j < nums.length; j++) {
                lcm = lcm(lcm, nums[j]);
                if (lcm == k) {
                    totalCount++;
                }
                // 当前这个nums 不能用了
                if (lcm > k) {
                    break;
                }
            }
        }
        return totalCount;
    }

    // 求最小公倍数
    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,6,2,7,1
        };
        int k = 6;
        int i = solution.subarrayLCM(nums, k);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }


}
