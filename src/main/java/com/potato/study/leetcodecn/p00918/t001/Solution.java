package com.potato.study.leetcodecn.p00918.t001;

import org.junit.Assert;

/**
 * 918. 环形子数组的最大和
 *
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。

 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）

 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）

  

 示例 1：

 输入：[1,-2,3,-2]
 输出：3
 解释：从子数组 [3] 得到最大和 3
 示例 2：

 输入：[5,-3,5]
 输出：10
 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 示例 3：

 输入：[3,-1,2,-1]
 输出：4
 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 示例 4：

 输入：[3,-2,2,-3]
 输出：3
 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 示例 5：

 输入：[-2,-3,-1]
 输出：-1
 解释：从子数组 [-1] 得到最大和 -1
  

 提示：

 -30000 <= A[i] <= 30000
 1 <= A.length <= 30000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxSubarraySumCircular(int[] nums) {
        // 遍历 nums 2遍记录 当前sum 目前最长的max 和 min 并求和
        int sum = 0;
        int currentMax = Integer.MIN_VALUE;
        int currentMin = Integer.MAX_VALUE;
        int subarrayMax = 0;
        int subarrayMin = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = i % nums.length;
            sum += nums[index];
            subarrayMax += nums[index];
            if (subarrayMax < nums[index]) {
                subarrayMax = nums[index];
            }
            currentMax = Math.max(currentMax, subarrayMax);
            subarrayMin += nums[index];
            if (subarrayMin > nums[index]) {
                subarrayMin = nums[index];
            }
            currentMin = Math.min(currentMin, subarrayMin);
        }
        // 计算 最大值 不能减到空
        if (sum == currentMin) {
            return currentMax;
        }
        return Math.max(currentMax, sum - currentMin);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                5,-3,5
        };
        int i = solution.maxSubarraySumCircular(arr);
        System.out.println(i);
        Assert.assertEquals(10, i);


        arr = new int[] {
                1,-2,3,-2
        };
        i = solution.maxSubarraySumCircular(arr);
        System.out.println(i);
        Assert.assertEquals(3, i);


        arr = new int[] {
                -3,-2,-3
        };
        i = solution.maxSubarraySumCircular(arr);
        System.out.println(i);
        Assert.assertEquals(-2, i);
    }
}
