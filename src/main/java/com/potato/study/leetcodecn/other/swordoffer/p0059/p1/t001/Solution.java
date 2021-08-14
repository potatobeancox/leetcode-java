package com.potato.study.leetcodecn.other.swordoffer.p0059.p1.t001;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 *
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

 示例:

 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 输出: [3,3,5,5,6,7]
 解释:

 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
  

 提示：

 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 滑动窗口内 使用一个 大跟堆记录
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (null == nums || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        // 大根堆
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                maxPriorityQueue.add(nums[i]);
            } else {
                maxPriorityQueue.remove(nums[i - k]);
                maxPriorityQueue.add(nums[i]);
            }
            if (i >= k - 1) {
                res[i - k + 1] = maxPriorityQueue.peek();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] ints = solution.maxSlidingWindow(nums, k);
        // [3,3,5,5,6,7]
        System.out.println(Arrays.toString(ints));


        nums = new int[] {1, -1};
        k = 1;
        ints = solution.maxSlidingWindow(nums, k);
        // [1,-1]
        System.out.println(Arrays.toString(ints));

        nums = new int[] {9,11};
        k = 2;
        ints = solution.maxSlidingWindow(nums, k);
        // [11]
        System.out.println(Arrays.toString(ints));

        nums = new int[] {4, -2};
        k = 2;
        ints = solution.maxSlidingWindow(nums, k);
        // [4]
        System.out.println(Arrays.toString(ints));
    }


}
