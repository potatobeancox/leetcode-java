package com.potato.study.leetcodecn.p02134.t001;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;

/**
 * 2134. 最少交换次数来组合所有的 1 II
 *
 * 交换 定义为选中一个数组中的两个 互不相同 的位置并交换二者的值。
 *
 * 环形 数组是一个数组，可以认为 第一个 元素和 最后一个 元素 相邻 。
 *
 * 给你一个 二进制环形 数组 nums ，返回在 任意位置 将数组中的所有 1 聚集在一起需要的最少交换次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,0,1,1,0,0]
 * 输出：1
 * 解释：这里列出一些能够将所有 1 聚集在一起的方案：
 * [0,0,1,1,1,0,0] 交换 1 次。
 * [0,1,1,1,0,0,0] 交换 1 次。
 * [1,1,0,0,0,0,1] 交换 2 次（利用数组的环形特性）。
 * 无法在交换 0 次的情况下将数组中的所有 1 聚集在一起。
 * 因此，需要的最少交换次数为 1 。
 * 示例 2：
 *
 * 输入：nums = [0,1,1,1,0,0,1,1,0]
 * 输出：2
 * 解释：这里列出一些能够将所有 1 聚集在一起的方案：
 * [1,1,1,0,0,0,0,1,1] 交换 2 次（利用数组的环形特性）。
 * [1,1,1,1,1,0,0,0,0] 交换 2 次。
 * 无法在交换 0 次或 1 次的情况下将数组中的所有 1 聚集在一起。
 * 因此，需要的最少交换次数为 2 。
 * 示例 3：
 *
 * 输入：nums = [1,1,0,0,1]
 * 输出：0
 * 解释：得益于数组的环形特性，所有的 1 已经聚集在一起。
 * 因此，需要的最少交换次数为 0 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 为 0 或者 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-swaps-to-group-all-1s-together-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
    public int minSwaps(int[] nums) {
        // 统计1的个数
        int oneCount = 0;
        for (int num : nums) {
            if (num == 1) {
                oneCount++;
            }
        }
        // 开一个 窗口 1的个数 计算窗口中的 0的个数 就是需要交换的个数 注意便利的时候 要比那里两次
        int left = 0;
        int zeroCount = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length * 2; right++) {
            int index = right % nums.length;
            if (nums[index] == 0) {
                zeroCount++;
            }
            if (right - left + 1 <= oneCount) {
                continue;
            }
            if (nums[left % nums.length] == 0) {
                zeroCount--;
            }
            left++;
            // 计算最小值
            min = Math.min(min, zeroCount);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                0,1,0,1,1,0,0
        };
        int i = solution.minSwaps(nums);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
