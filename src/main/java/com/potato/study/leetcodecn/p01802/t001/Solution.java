package com.potato.study.leetcodecn.p01802.t001;

import org.junit.Assert;

/**
 * 1802. 有界数组中指定下标处的最大值
 *
 *给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 *
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 * 示例 2：
 *
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1802
    public int maxValue(int n, int index, int maxSum) {
        int left = index;
        int right = index;
        // 先垫个底
        int temp = maxSum - n;
        int height = 1;
        while ((left >= 0 || right < n ) && temp >= right - left + 1) {
            height++;
            temp -= (right - left + 1);
            if (left > 0) {
                left--;
            }
            if (right < n - 1) {
                right++;
            }
            if (right - left + 1 == n) {
                break;
            }
        }
        if (temp >= n) {
            height += temp / n;
        }
        return height;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        int index = 2;
        int maxSum = 6;
        int i = solution.maxValue(n, index, maxSum);
        System.out.println(i);
        Assert.assertEquals(2, i);


        n = 6;
        index = 1;
        maxSum = 10;
        i = solution.maxValue(n, index, maxSum);
        System.out.println(i);
        Assert.assertEquals(3, i);


        n = 3;
        index = 2;
        maxSum = 18;
        i = solution.maxValue(n, index, maxSum);
        System.out.println(i);
        Assert.assertEquals(7, i);


        n = 4;
        index = 0;
        maxSum = 4;
        i = solution.maxValue(n, index, maxSum);
        System.out.println(i);
        Assert.assertEquals(1, i);


        n = 10;
        index = 5;
        maxSum = 420121961;
        i = solution.maxValue(n, index, maxSum);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }

}
