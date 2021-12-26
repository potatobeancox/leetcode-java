package com.potato.study.leetcodecn.p01262.t001;


import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * 1262. 可被三整除的最大和
 *
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。

  

 示例 1：

 输入：nums = [3,6,5,1,8]
 输出：18
 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 示例 2：

 输入：nums = [4]
 输出：0
 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 示例 3：

 输入：nums = [1,2,3,4,4]
 输出：12
 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
  

 提示：

 1 <= nums.length <= 4 * 10^4
 1 <= nums[i] <= 10^4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/greatest-sum-divisible-by-three
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 求和 记录最小的连个 1 的余数 和 2的余数
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        PriorityQueue<Integer> ones = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> twos = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int num : nums) {
            sum += num;
            if (num % 3 == 1) {
                ones.add(num);
            } else if (num % 3 == 2) {
                twos.add(num);
            }

            if (ones.size() > 2) {
                ones.poll();
            }

            if (twos.size() > 2) {
                twos.poll();
            }
        }
        // 判断最终结果
        int result = sum % 3;
        if (result == 0) {
            return sum;
        }

        int max = 0;
        if (result == 1) {
            if (ones.size() >= 1) {
                while (ones.size() > 1) {
                    ones.poll();
                }
                max = Math.max(max, sum - ones.poll());
            }

            if (twos.size() == 2) {
                max = Math.max(max, sum - twos.poll() - twos.poll());
            }
        } else if (result == 2) {
            if (twos.size() >= 1) {
                while (twos.size() > 1) {
                    twos.poll();
                }
                max = Math.max(max, sum - twos.poll());
            }
            if (ones.size() == 2) {
                max = Math.max(max, sum - ones.poll() - ones.poll());
            }
        }
        return max;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                2,6,2,2,7
        };
        int i = solution.maxSumDivThree(arr);
        System.out.println(i);
        Assert.assertEquals(15, i);
    }
}
