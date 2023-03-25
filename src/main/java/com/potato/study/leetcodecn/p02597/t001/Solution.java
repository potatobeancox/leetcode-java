package com.potato.study.leetcodecn.p02597.t001;

/**
 * 2597. 美丽子集的数目

 给你一个由正整数组成的数组 nums 和一个 正 整数 k 。

 如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。

 返回数组 nums 中 非空 且 美丽 的子集数目。

 nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。

  

 示例 1：

 输入：nums = [2,4,6], k = 2
 输出：4
 解释：数组 nums 中的美丽子集有：[2], [4], [6], [2, 6] 。
 可以证明数组 [2,4,6] 中只存在 4 个美丽子集。
 示例 2：

 输入：nums = [1], k = 1
 输出：1
 解释：数组 nums 中的美丽数组有：[1] 。
 可以证明数组 [1] 中只存在 1 个美丽子集。
  

 提示：

 1 <= nums.length <= 20
 1 <= nums[i], k <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/the-number-of-beautiful-subsets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int count;
    // 2597
    public int beautifulSubsets(int[] nums, int k) {
        // 去掉空集
        this.count = -1;
        // dfs 计数
        int[] select = new int[1001];
        dfs(nums, k, 0, select);
        if (this.count < 0) {
            return 0;
        }
        return this.count;
    }

    /**
     *
     * @param nums
     * @param k
     * @param index 当前判定到的index
     */
    private void dfs(int[] nums, int k, int index, int[] select) {
        // 终止条件 如果当前所有的index 都被使用过了
        if (index == nums.length) {
            count++;
            return;
        }
        // 否则不选这个数字
        dfs(nums, k, index+1, select);
        // 看看当前index 的数字 能不能选
        int target = nums[index];
        boolean hasFound = false;
        if (target - k >= 0 && select[target - k] != 0) {
            hasFound = true;
        }
        if (target + k < select.length && select[target + k] != 0) {
            hasFound = true;
        }
        // 目前已经选了别的互斥的数字 肯定不能选了
        if (hasFound) {
            return;
        }
        // 选上
        select[target]++;
        // 递归找一下
        dfs(nums, k, index+1, select);
        select[target]--;
    }

}
