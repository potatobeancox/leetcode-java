package com.potato.study.leetcodecn.p02653.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 *
 * 2653. 滑动子数组的美丽值
 *
 * 给你一个长度为 n 的整数数组 nums ，请你求出每个长度为 k 的子数组的 美丽值 。

 一个子数组的 美丽值 定义为：如果子数组中第 x 小整数 是 负数 ，那么美丽值为第 x 小的数，否则美丽值为 0 。

 请你返回一个包含 n - k + 1 个整数的数组，依次 表示数组中从第一个下标开始，每个长度为 k 的子数组的 美丽值 。

 子数组指的是数组中一段连续 非空 的元素序列。

  

 示例 1：

 输入：nums = [1,-1,-3,-2,3], k = 3, x = 2
 输出：[-1,-2,-2]
 解释：总共有 3 个 k = 3 的子数组。
 第一个子数组是 [1, -1, -3] ，第二小的数是负数 -1 。
 第二个子数组是 [-1, -3, -2] ，第二小的数是负数 -2 。
 第三个子数组是 [-3, -2, 3] ，第二小的数是负数 -2 。
 示例 2：

 输入：nums = [-1,-2,-3,-4,-5], k = 2, x = 2
 输出：[-1,-2,-3,-4]
 解释：总共有 4 个 k = 2 的子数组。
 [-1, -2] 中第二小的数是负数 -1 。
 [-2, -3] 中第二小的数是负数 -2 。
 [-3, -4] 中第二小的数是负数 -3 。
 [-4, -5] 中第二小的数是负数 -4 。
 示例 3：

 输入：nums = [-3,1,2,-3,0,-3], k = 2, x = 1
 输出：[-3,0,-3,-3,-3]
 解释：总共有 5 个 k = 2 的子数组。
 [-3, 1] 中最小的数是负数 -3 。
 [1, 2] 中最小的数不是负数，所以美丽值为 0 。
 [2, -3] 中最小的数是负数 -3 。
 [-3, 0] 中最小的数是负数 -3 。
 [0, -3] 中最小的数是负数 -3 。
  

 提示：

 n == nums.length 
 1 <= n <= 105
 1 <= k <= n
 1 <= x <= k 
 -50 <= nums[i] <= 50 

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sliding-subarray-beauty
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        // 遍历 数组 用一个计数器 记录 -50 <= nums[i] <= 50 出现个数
        int[] count = new int[101]; // -50 => 0 50->100
        // 先计数k-1个
        for (int i = 0; i < k - 1; i++) {
            count[nums[i]+50]++;
        }
        // 对于每个 窗口 通过遍历计数器 得到第x个是哪个
        int[] res = new int[nums.length - k + 1];
        for (int i = k-1; i < nums.length; i++) {
            count[nums[i]+50]++;
            // 开始结算
            int tmp = x;
            for (int j = 0; j <= 100; j++) {
                tmp -= count[j];
                if (tmp <= 0) {
                    if (j > 50) {
                        res[i - k + 1] = 0;
                    } else {
                        res[i - k + 1] = j - 50;
                    }
                    break;
                }
            }
            count[nums[i- (k-1)]+50]--;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                -3,1,2,-3,0,-3
        };
        int k = 2;
        int x = 1;
        int[] subarrayBeauty = solution.getSubarrayBeauty(nums, k, x);
        System.out.println(Arrays.toString(subarrayBeauty));
        Assert.assertArrayEquals(new int[]{
                -3,0,-3,-3,-3
        }, subarrayBeauty);
    }



}
