package com.potato.study.leetcodecn.p00533.t001;


import org.junit.Assert;

import java.util.Arrays;

/**
 * 533. 孤独像素 II
 *
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。

 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：

 0 <= i < j < nums.length
 |nums[i] - nums[j]| == k
 注意，|val| 表示 val 的绝对值。

  

 示例 1：

 输入：nums = [3, 1, 4, 1, 5], k = 2
 输出：2
 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 示例 2：

 输入：nums = [1, 2, 3, 4, 5], k = 1
 输出：4
 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 示例 3：

 输入：nums = [1, 3, 1, 5, 4], k = 0
 输出：1
 解释：数组中只有一个 0-diff 数对，(1, 1)。
  

 提示：

 1 <= nums.length <= 104
 -107 <= nums[i] <= 107
 0 <= k <= 107

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/k-diff-pairs-in-an-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                if (nums[j] - nums[i] == k) {
                    count++;
                } else if (nums[j] - nums[i] > k) {
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3, 1, 4, 1, 5
        };
        int k = 2;
        int pairs = solution.findPairs(nums, k);
        System.out.println(pairs);
        Assert.assertEquals(2, pairs);
    }


}
