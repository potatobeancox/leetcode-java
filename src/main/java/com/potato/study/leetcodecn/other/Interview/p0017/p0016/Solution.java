package com.potato.study.leetcodecn.other.Interview.p0017.p0016;


import org.junit.Assert;

/**
 * 面试题 17.16. 按摩师
 *
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

 注意：本题相对原题稍作改动

  

 示例 1：

 输入： [1,2,3,1]
 输出： 4
 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 示例 2：

 输入： [2,7,9,3,1]
 输出： 12
 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 示例 3：

 输入： [2,1,4,5,3,1,1,3]
 输出： 12
 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/the-masseuse-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * maxTime i 记录 选到i时，最大时间
     * 状态转移 方程
     * maxTime i = max {time[i-1] + maxTime[i-2], maxTime[i-1]}
     * maxTime 0 = 0
     * maxTime 1 = time 0
     * i 从 小 -> 大 遍历
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int[] maxTime = new int[nums.length + 1];
        maxTime[0] = 0;
        maxTime[1] = nums[0];

        for (int i = 2; i < maxTime.length; i++) {
            maxTime[i] = Math.max(nums[i-1] + maxTime[i-2], maxTime[i-1]);
        }
        return maxTime[maxTime.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,1};
        int sum = solution.massage(nums);
        System.out.println(sum);

        Assert.assertEquals(4, sum);

    }
}
