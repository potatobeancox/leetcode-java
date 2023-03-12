package com.potato.study.leetcodecn.p01498.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1498. 满足条件的子序列数目
 *
 * 给你一个整数数组 nums 和一个整数 target 。

 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。

 由于答案可能很大，请将结果对 109 + 7 取余后返回。

  

 示例 1：

 输入：nums = [3,5,6,7], target = 9
 输出：4
 解释：有 4 个子序列满足该条件。
 [3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
 [3,5] -> (3 + 5 <= 9)
 [3,5,6] -> (3 + 6 <= 9)
 [3,6] -> (3 + 6 <= 9)
 示例 2：

 输入：nums = [3,3,6,8], target = 10
 输出：6
 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
 [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 示例 3：

 输入：nums = [2,3,3,4,6,7], target = 12
 输出：61
 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
 有效序列总数为（63 - 2 = 61）
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 106
 1 <= target <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solution/java-shuang-zhi-zhen-by-rookiewantajob-d3fv/
     * @param nums
     * @param target
     * @return
     */
    public int numSubseq(int[] nums, int target) {
        // 对 nums 进行排序
        Arrays.sort(nums);
        // 确定某一个 最小值 位置 看一下 最大值位置 是否已经超过 target 不超过计数
        int mod = 1_000_000_000 + 7;
        // 双指针 确定当前两边作为左右子序列的中间点 有多少种序列可能
        int left = 0;
        int right = nums.length - 1;
        long total = 0;
        while (left <= right) {
            // 如果当前
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                // 满足条件
                total = (total + fastPow(2, right - left)) % mod;
                left++;
            }
        }
        return (int) total;
    }

    /**
     *
     * @param base 底
     * @param num  指数
     * @return
     */
    private long fastPow(int base, int num) {
        if (num == 0) {
            return 1;
        }
        long l = fastPow(base, num / 2);
        int mod = 1_000_000_000 + 7;
        l = (l * l) % mod;
        if (num % 2 == 1) {
            return (base * l) % mod;
        }
        return l;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{
                3,5,6,7
        };
        int target = 9;
        int i = solution.numSubseq(nums, target);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
