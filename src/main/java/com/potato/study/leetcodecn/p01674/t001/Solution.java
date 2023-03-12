package com.potato.study.leetcodecn.p01674.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1674. 使数组互补的最少操作次数
 *
 * 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。

 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。

 返回使数组 互补 的 最少 操作次数。

  

 示例 1：

 输入：nums = [1,2,4,3], limit = 4
 输出：1
 解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
 nums[0] + nums[3] = 1 + 3 = 4.
 nums[1] + nums[2] = 2 + 2 = 4.
 nums[2] + nums[1] = 2 + 2 = 4.
 nums[3] + nums[0] = 3 + 1 = 4.
 对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
 示例 2：

 输入：nums = [1,2,2,1], limit = 2
 输出：2
 解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
 示例 3：

 输入：nums = [1,2,1,2], limit = 2
 输出：0
 解释：nums 已经是互补的。
  

 提示：

 n == nums.length
 2 <= n <= 105
 1 <= nums[i] <= limit <= 105
 n 是偶数。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-moves-to-make-array-complementary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minMoves(int[] nums, int limit) {
        int sumLimit = limit * 2;
        // 查分数组 2 * limit + 2
        int[] diff = new int[sumLimit + 1];
        // 遍历nums 求 最大值 和最小值 针对 2 最小值-1 该2个
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {

            int num1 = nums[i];
            int num2 = nums[nums.length - 1- i];

            int sum = num1 + num2;
            // 两个数替换之后的最小值 将最大的替换成1 留下 最小的
            int min = Math.min(num1, num2) + 1;
            // 两个数字替换的最大值 留下最大值，将最小值替换成limit
            int max = Math.max(num1, num2) + limit;
            // 如果最终 替换后的值在 【2，min】 说明替换了一个


            // 如果最终两个数字 在 【2， min】 之内说明 2个数字都需要改变
            diff[min]--;
            diff[sum]--;
            // 【min+1， max + limit + 1】用一个就行
            if (sum < sumLimit) {
                diff[sum + 1]++;
            }
            if (max < sumLimit) {
                diff[max + 1]++;
            }

            i++;
            j--;
        }
        int min = nums.length;
        // 先移动n次
        int status = nums.length;
        for (int k = 1; k <= 2 * limit; k++) {
            status += diff[k];
            min = Math.min(min, status);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,2,4,3
        };
        int limit = 4;
        int i = solution.minMoves(nums, limit);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
