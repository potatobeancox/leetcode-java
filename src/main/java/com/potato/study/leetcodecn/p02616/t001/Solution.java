package com.potato.study.leetcodecn.p02616.t001;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * 2616. 最小化数对的最大差值
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 p 。请你从 nums 中找到 p 个下标对，每个下标对对应数值取差值，你需要使得这 p 个差值的 最大值 最小。同时，你需要确保每个下标在这 p 个下标对中最多出现一次。

 对于一个下标对 i 和 j ，这一对的差值为 |nums[i] - nums[j]| ，其中 |x| 表示 x 的 绝对值 。

 请你返回 p 个下标对对应数值 最大差值 的 最小值 。

  

 示例 1：

 输入：nums = [10,1,2,7,1,3], p = 2
 输出：1
 解释：第一个下标对选择 1 和 4 ，第二个下标对选择 2 和 5 。
 最大差值为 max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1 。所以我们返回 1 。
 示例 2：

 输入：nums = [4,2,1,2], p = 1
 输出：0
 解释：选择下标 1 和 3 构成下标对。差值为 |2 - 2| = 0 ，这是最大差值的最小值。
  

 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 109
 0 <= p <= (nums.length)/2

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimize-the-maximum-difference-of-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimizeMax(int[] nums, int p) {
        // 二分法 一次枚举 差 看是不是超过了 p个
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        int res = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(nums, mid, p)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 小于等于 target 一共有多少个是否超多p个
     * @param nums
     * @param target
     * @param p
     * @param target
     * @return
     */
    private boolean check(int[] nums, int target, int p) {
        int count = 0;
        // 不能选同样的下标
        int i = 0;
        while (i < nums.length) {
            // 到了最后
            if (i + 1 >= nums.length) {
                break;
            }
            // 超过了
            if (nums[i+1] - nums[i] > target) {
                i++;
                continue;
            }
            // 没超过
            count++;
            i += 2;
        }
        return count >= p;
    }



}
