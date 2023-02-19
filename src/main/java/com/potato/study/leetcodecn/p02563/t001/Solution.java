package com.potato.study.leetcodecn.p02563.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 2563. 统计公平数对的数目
 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。

 如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：

 0 <= i < j < n，且
 lower <= nums[i] + nums[j] <= upper
  

 示例 1：

 输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
 输出：6
 解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
 示例 2：

 输入：nums = [1,7,9,2,5], lower = 11, upper = 11
 输出：1
 解释：只有单个公平数对：(2,3) 。
  

 提示：

 1 <= nums.length <= 105
 nums.length == n
 -109 <= nums[i] <= 109
 -109 <= lower <= upper <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-the-number-of-fair-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2563
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        // 锁定每个位置 i
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            int right = i;
            // 再 i 之前找 找位置 定位两端的位置 计算 总的个数
            int leftIndex = getLeftEndpoint(lower - nums[right], right, nums);
            int rightIndex = getRightEndpoint(upper - nums[right], right, nums);
            if (leftIndex == -1 || rightIndex == -1) {
                continue;
            }
            if (rightIndex >= leftIndex) {
                count += (rightIndex - leftIndex + 1);
            }
        }
        return count;
    }

    /**
     * bi-search from nums to find blow than target include equals
     * 找到 大于等于 target 的第一个位置
     * @param target
     * @param right
     * @param nums
     * @return
     */
    private int getLeftEndpoint(int target, int right, int[] nums) {
        int left = 0;
        right--;
        int resIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                resIndex = mid;
                // mid 还能不能小
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return resIndex;
    }


    /**
     * bi-search from nums to find blow than target include equals
     * 找到 小于于等于 target 的第一个位置 而且要比right小
     * @param target
     * @param right
     * @param nums
     * @return
     */
    private int getRightEndpoint(int target, int right, int[] nums) {
        int left = 0;
        right--;
        int resIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                resIndex = mid;
                // mid 还能不能大
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return resIndex;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = LeetcodeInputUtils.inputString2IntArray("[0,1,7,4,4,5]");
        int lower = 3;
        int upper = 6;
        long l = solution.countFairPairs(nums, lower, upper);
        System.out.println(l);
        Assert.assertEquals(6, l);
    }



}
