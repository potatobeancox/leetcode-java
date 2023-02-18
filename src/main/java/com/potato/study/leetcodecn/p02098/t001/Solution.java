package com.potato.study.leetcodecn.p02098.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

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
        // 两个大根堆
        PriorityQueue<Long> oddPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> evenPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (long n : nums) {
            if (n % 2 == 1) {
                oddPriorityQueue.add(n);
            } else {
                evenPriorityQueue.add(n);
            }
        }
        // 将大根队 转换成 奇偶数字 前缀和
        long[] prefix1 = new long[oddPriorityQueue.size()];
        buildPrefixSum(oddPriorityQueue, 0, prefix1);
        long[] prefix2 = new long[evenPriorityQueue.size()];
        buildPrefixSum(evenPriorityQueue, 0, prefix2);
        // 分别 枚举 从 奇数堆中获取的个数 再从偶数中补充 看看 最大
        long max = -1;
        for (int i = 0; i <= k; i+=2) {
            // 1 已经获取到最大
            if (i > prefix1.length) {
                break;
            }
            long current;
            if (i == 0) {
                current = 0;
            } else {
                current = prefix1[i-1];
            }
            // even 中获取个数
            int num = k - i;
            // 偶数 如果不够了 还可以试试 用奇数
            if (num > prefix2.length) {
                continue;
            }
            if (num != 0) {
                current += prefix2[num-1];
            }
            max = Math.max(max, current);
        }
        return max;
    }

    private void buildPrefixSum(PriorityQueue<Long> evenPriorityQueue, int index, long[] prefix) {
        while (!evenPriorityQueue.isEmpty()) {
            long cur = evenPriorityQueue.poll();
            if (index > 0) {
                cur += prefix[index-1];
            }
            prefix[index] = cur;
            index++;
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
