package com.potato.study.leetcodecn.p00995.t001;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 995. K 连续位的最小翻转次数
 *
 * 给定一个二进制数组 nums 和一个整数 k 。
 *
 * k位翻转 就是从 nums 中选择一个长度为 k 的 子数组 ，同时把子数组中的每一个 0 都改成 1 ，把子数组中的每一个 1 都改成 0 。
 *
 * 返回数组中不存在 0 所需的最小 k位翻转 次数。如果不可能，则返回 -1 。
 *
 * 子数组 是数组的 连续 部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,0], K = 1
 * 输出：2
 * 解释：先翻转 A[0]，然后翻转 A[2]。
 * 示例 2：
 *
 * 输入：nums = [1,1,0], K = 2
 * 输出：-1
 * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
 * 示例 3：
 *
 * 输入：nums = [0,0,0,1,0,1,1,0], K = 3
 * 输出：3
 * 解释：
 * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
 * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
 * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-k-consecutive-bit-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 995
     * https://leetcode.cn/problems/minimum-number-of-k-consecutive-bit-flips/solution/k-lian-xu-wei-de-zui-xiao-fan-zhuan-ci-s-bikk/
     * @param nums
     * @param k
     * @return
     */
    public int minKBitFlips(int[] nums, int k) {
        // 数组长度 + 1 为了防御
        int[] diff = new int[nums.length + 1];
        // 遍历 nums 用一个查分数组记录 diff i 记录 i-1 和i 位置反转次数差 用一个 当前反转次数记录当前位置 i的 翻转次数
        int currentFlipTime = 0;
        int minCount = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当前位置之前翻转了多少次
            currentFlipTime += diff[i];
            int value = nums[i] + currentFlipTime;
            if (value % 2 == 0) {
                // 当前位置 经历过翻转之后 还需要继续翻转 【i， i+k-1】
                if (i + k > nums.length) {
                    // 没有机会了
                    return -1;
                }
                // 每次 i 都 根据当前值 和 需要翻转的次数看看 之后 的状态 过程中 如果超过
                diff[i]++;
                diff[i+k]--;
                // 最少反转多少次 能使得 nums 为全是1的数组
                currentFlipTime++;
                minCount++;
            }
        }
        return minCount;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {
                0,1,0
        };
        int k = 1;
        Solution solution = new Solution();
        int i = solution.minKBitFlips(nums, k);
        System.out.println(i);
        // 2
        Assert.assertEquals(2, i);
    }
}
