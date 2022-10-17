package com.potato.study.leetcodecn.p02439.t001;

import java.util.Stack;

/**
 * 2439. 最小化数组中的最大值
 *
 * 给你一个下标从 0 开始的数组 nums ，它含有 n 个非负整数。

 每一步操作中，你需要：

 选择一个满足 1 <= i < n 的整数 i ，且 nums[i] > 0 。
 将 nums[i] 减 1 。
 将 nums[i - 1] 加 1 。
 你可以对数组执行 任意 次上述操作，请你返回可以得到的 nums 数组中 最大值 最小 为多少。

  

 示例 1：

 输入：nums = [3,7,1,6]
 输出：5
 解释：
 一串最优操作是：
 1. 选择 i = 1 ，nums 变为 [4,6,1,6] 。
 2. 选择 i = 3 ，nums 变为 [4,6,2,5] 。
 3. 选择 i = 1 ，nums 变为 [5,5,2,5] 。
 nums 中最大值为 5 。无法得到比 5 更小的最大值。
 所以我们返回 5 。
 示例 2：

 输入：nums = [10,1]
 输出：10
 解释：
 最优解是不改动 nums ，10 是最大值，所以返回 10 。
  

 提示：

 n == nums.length
 2 <= n <= 105
 0 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimize-maximum-of-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/minimize-maximum-of-array/solution/cppjava-you-shi-yi-dao-jing-dian-de-er-f-w3i6/
     * @param nums
     * @return
     */
    public int minimizeArrayValue(int[] nums) {
        // 二分法 枚举 最大值
        long left = 0;
        long right = 1_000_000_000;
        // 对于每个位置的最大值进行check
        long res = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(mid, nums)) {
                res = mid;
                // 再往小试试
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // check 逻辑 从前往后累计可以负担的个数，如果某个位置出现了 当前位置的多余量超过了负担量 就gg
        return (int)res;
    }

    private boolean check(long max, int[] nums) {
        // 还有能承载的空位
        long hasBlank = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= max) {
                hasBlank += (max - nums[i]);
            } else {
                // 比max大 看看能不能调整
                if (nums[i] - max > hasBlank) {
                    return false;
                }
                hasBlank -= (nums[i] - max);
            }
        }
        return true;
    }

}
