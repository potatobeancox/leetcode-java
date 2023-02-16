package com.potato.study.leetcodecn.p02098.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2098. 长度为 K 的最大偶数和子序列
 *
 * 给你一个整数数组 nums 和一个整数 k 。找出 nums 长度为 k 的所有子序列中的 最大偶数和 。
 * 返回此总和，如果此总和不存在，则返回 -1。
 * 子序列 是一个数组，可以通过删除一些元素或不删除任何元素而从另一个数组派生，而不改变其余元素的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: nums = [4,1,5,3,1], k = 3
 * 输出: 12
 * 解释:
 * 具有最大可能偶数和的子序列是[4,5,3]。它的和为 4 + 5 + 3 = 12
 * 示例 2:
 *
 * 输入: nums = [4,6,2], k = 3
 * 输出: 12
 * 解释:
 * 具有最大可能偶数和的子序列是[4,6,2]。它的和为 4 + 6 + 2 = 12
 * 示例 3:
 *
 * 输入: nums = [1,3,5], k = 1
 * 输出: -1
 * 解释:
 * 长度为 1 的 NUM 的子序列没有偶数和。
 *  
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * 1 <= k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subsequence-of-size-k-with-the-largest-even-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2098
    public long largestEvenSum(int[] nums, int k) {
        // 排序 如果 nums 最大是偶数直接返回返回如果是找到 最小的奇数 替换成 偶数
        Arrays.sort(nums);
        // 最后 k个 求sum 并且记录 最小的odd 的index
        int smallestOddIndex = -1;
        int smallestEvenIndex = -1;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int index = nums.length - 1 - i;
            sum += nums[index];
            if (nums[index] % 2 == 1) {
                smallestOddIndex = index;
            } else {
                smallestEvenIndex = index;
            }
        }
        if (sum % 2 == 0) {
            return sum;
        }
        // 往前找 到 第一个偶数
        long sum1 = -1;
        long sum2 = -1;
        for (int i = nums.length - 1 - k; i >= 0; i--) {
            if (sum1 != -1 && sum2 != -1) {
                return Math.min(sum1, sum2);
            }
            if (sum1 == -1 && nums[i] % 2 == 1 && smallestEvenIndex != -1) {
                sum1 = sum - nums[smallestEvenIndex] + nums[i];
            }
            if (sum2 == -1 && nums[i] % 2 == 0 && smallestOddIndex != -1) {
                sum2 = sum - nums[smallestOddIndex] + nums[i];
            }
        }
        // 都找到了 返回小的哪个 否则找到哪个就用哪个
        if (sum1 == -1 && sum2 == -1) {
            return -1;
        } else if (sum1 == -1) {
            return sum2;
        } else if (sum2 == -1) {
            return sum1;
        } else {
            return Math.min(sum1, sum2);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                4,1,5,3,1
        };
        int k = 3;
        long l = solution.largestEvenSum(nums, k);
        System.out.println(l);
        Assert.assertEquals(12, l);


        nums = new int[] {
                1,5,5,5,4
        };
        k = 4;
        l = solution.largestEvenSum(nums, k);
        System.out.println(l);
        Assert.assertEquals(16, l);
    }


}
