package com.potato.study.leetcodecn.p00891.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 891. 子序列宽度之和
 *
 * 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
 *
 * 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。由于答案可能非常大，请返回对 109 + 7 取余 后的结果。
 *
 * 子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,1,3]
 * 输出：6
 * 解释：子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
 * 相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
 * 宽度之和是 6 。
 * 示例 2：
 *
 * 输入：nums = [2]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-subsequence-widths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/sum-of-subsequence-widths/solution/by-endlesscheng-upd1/
     * @param nums
     * @return
     */
    public int sumSubseqWidths(int[] nums) {
        // 事先计算 2的n次方
        long[] power = new long[nums.length];
        power[0] = 1;
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < nums.length; i++) {
            power[i] = 2 * power[i-1];
            power[i] %= mod;
        }
        // 排序 nums 升序
        Arrays.sort(nums);
        // 对于每个位置i 对应的值num 他有 2^len - index - 1  次机会成为最小值 2^index 次机会成为最大值
        long res = 0;

        for (int i = 0; i < nums.length; i++) {
            long target = nums[i] * (power[i] - power[nums.length-i-1]);
            // 累加贡献值
            res += target;
            res %= mod;
        }
        return (int) res;
    }
}
