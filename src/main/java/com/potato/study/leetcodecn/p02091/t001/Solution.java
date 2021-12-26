package com.potato.study.leetcodecn.p02091.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2091. 从数组中移除最大值和最小值
 *
 * 给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。

 nums 中有一个值最小的元素和一个值最大的元素。分别称为 最小值 和 最大值 。你的目标是从数组中移除这两个元素。

 一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。

 返回将数组中最小值和最大值 都 移除需要的最小删除次数。

  

 示例 1：

 输入：nums = [2,10,7,5,4,1,8,6]
 输出：5
 解释：
 数组中的最小元素是 nums[5] ，值为 1 。
 数组中的最大元素是 nums[1] ，值为 10 。
 将最大值和最小值都移除需要从数组前面移除 2 个元素，从数组后面移除 3 个元素。
 结果是 2 + 3 = 5 ，这是所有可能情况中的最小删除次数。
 示例 2：

 输入：nums = [0,-4,19,1,8,-2,-3,5]
 输出：3
 解释：
 数组中的最小元素是 nums[1] ，值为 -4 。
 数组中的最大元素是 nums[2] ，值为 19 。
 将最大值和最小值都移除需要从数组前面移除 3 个元素。
 结果是 3 ，这是所有可能情况中的最小删除次数。
 示例 3：

 输入：nums = [101]
 输出：1
 解释：
 数组中只有这一个元素，那么它既是数组中的最小值又是数组中的最大值。
 移除它只需要 1 次删除操作。
  

 提示：

 1 <= nums.length <= 105
 -105 <= nums[i] <= 105
 nums 中的整数 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/removing-minimum-and-maximum-from-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumDeletions(int[] nums) {
        // 找到 最大maxIndex 找到最小minIndex
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        int[] tmp = new int[2];
        if (maxIndex >= minIndex) {
            tmp[0] = minIndex;
            tmp[1] = maxIndex;
        } else {
            tmp[0] = maxIndex;
            tmp[1] = minIndex;
        }
        // 判断left和right 离最大值最小值 多远，取最近的一个 删除 然后再判断依次
        int left = 0;
        int right = nums.length - 1;
        // 相当于只有一个
        if (tmp[0] == tmp[1]) {
            return Math.min(tmp[0] - left, right - tmp[0]) + 1;
        }
        // 两个不同
        int min = right - tmp[1] + tmp[0] - left + 2;
        min = Math.min(min, tmp[1] - left + 1);
        min = Math.min(min, right - tmp[0] + 1);
        return min;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2,10,7,5,4,1,8,6};
        int i = solution.minimumDeletions(nums);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
