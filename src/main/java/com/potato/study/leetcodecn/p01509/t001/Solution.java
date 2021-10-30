package com.potato.study.leetcodecn.p01509.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1509. 三次操作后最大值与最小值的最小差
 *
 * 给你一个数组 nums ，每次操作你可以选择 nums 中的任意一个元素并将它改成任意值。

 请你返回三次操作后， nums 中最大值与最小值的差的最小值。

  

 示例 1：

 输入：nums = [5,3,2,4]
 输出：0
 解释：将数组 [5,3,2,4] 变成 [2,2,2,2].
 最大值与最小值的差为 2-2 = 0 。
 示例 2：

 输入：nums = [1,5,0,10,14]
 输出：1
 解释：将数组 [1,5,0,10,14] 变成 [1,1,0,1,1] 。
 最大值与最小值的差为 1-0 = 1 。
 示例 3：

 输入：nums = [6,6,0,1,1,4,6]
 输出：2
 示例 4：

 输入：nums = [1,5,6,14,15]
 输出：1
  

 提示：

 1 <= nums.length <= 10^5
 -10^9 <= nums[i] <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @return
     */
    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        // 找到最大的4个数字和 最小的4个数字 排序
        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>();
        // 小的在 priorityQueue2 里
        PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        // 比较小 第四大 和 最小 第四小和 最大，去掉2个大的 和一个小的，去掉2个小的和1个大的
        for (int num : nums) {
            priorityQueue1.add(num);
            priorityQueue2.add(num);

            if (priorityQueue1.size() > 4) {
                priorityQueue1.poll();
            }
            if (priorityQueue2.size() > 4) {
                priorityQueue2.poll();
            }
        }
        int[] small = new int[4];
        int[] big = new int[4];
        for (int i = 3; i >= 0; i--) {
            // 根是大的 small 升序
            small[i] = priorityQueue2.poll();
            // 根是小的 big 升序
            big[3-i] = priorityQueue1.poll();
        }
        if (big[3] < small[3] || big[0] < small[0]) {
            return 0;
        }
        int minDiff = Math.min(big[3] - small[3], big[0] - small[0]);
        if (big[1] < small[1] || big[2] < small[2]) {
            return 0;
        }
        minDiff = Math.min(minDiff, Math.min(big[1] - small[1], big[2] - small[2]));
        return minDiff;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                5,3,2,4
        };
        int i = solution.minDifference(arr);
        System.out.println(i);
        Assert.assertEquals(0, i);

        arr = new int[] {
                1,5,0,10,14
        };
        i = solution.minDifference(arr);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
