package com.potato.study.leetcodecn.p02735.t001;


/**
 *
 * 2735. 收集巧克力
 *
 * 给你一个长度为 n 、下标从 0 开始的整数数组 nums ，表示收集不同巧克力的成本。每个巧克力都对应一个不同的类型，最初，位于下标 i 的巧克力就对应第 i 个类型。

 在一步操作中，你可以用成本 x 执行下述行为：

 同时修改所有巧克力的类型，将巧克力的类型 ith 修改为类型 ((i + 1) mod n)th。
 假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。

  

 示例 1：

 输入：nums = [20,1,15], x = 5
 输出：13
 解释：最开始，巧克力的类型分别是 [0,1,2] 。我们可以用成本 1 购买第 1 个类型的巧克力。
 接着，我们用成本 5 执行一次操作，巧克力的类型变更为 [1,2,0] 。我们可以用成本 1 购买第 2 个类型的巧克力。
 然后，我们用成本 5 执行一次操作，巧克力的类型变更为 [2,0,1] 。我们可以用成本 1 购买第 0 个类型的巧克力。
 因此，收集所有类型的巧克力需要的总成本是 (1 + 5 + 1 + 5 + 1) = 13 。可以证明这是一种最优方案。
 示例 2：

 输入：nums = [1,2,3], x = 4
 输出：6
 解释：我们将会按最初的成本收集全部三个类型的巧克力，而不需执行任何操作。因此，收集所有类型的巧克力需要的总成本是 1 + 2 + 3 = 6 。
  

 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 109
 1 <= x <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/collecting-chocolates
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @param x
     * @return
     */
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        // 最多旋转n此 使用一个 sum数组记录 旋转i次 每次的最小花费
        long[] sum = new long[n];
        // init sum 每次旋转需要进行的花费
        for (int i = 0; i < n; i++) {
            sum[i] = (long)i * x;
        }
        // 遍历每种类型
        for (int i = 0; i < n; i++) {
            long thisTypeMinCost = Long.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                // 内部枚举每次旋转 并比较记录最小花费 每次旋转相当于 往前移动的k个单位的花费
                int index = (i - j + n) % n;
                thisTypeMinCost = Math.min(thisTypeMinCost, nums[index]);
                // 记录到每次移动的sum中
                sum[j] += thisTypeMinCost;
            }
        }
        // 遍历 sum 计算全局最小值
        long min = Long.MAX_VALUE;
        for (long res : sum) {
            min = Math.min(min, res);
        }
        return min;
    }

}
