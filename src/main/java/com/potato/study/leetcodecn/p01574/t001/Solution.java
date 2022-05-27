package com.potato.study.leetcodecn.p01574.t001;

import org.junit.Assert;

/**
 * 1574. 删除最短的子数组使剩余数组有序
 *
 * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
 *
 * 一个子数组指的是原数组中连续的一个子序列。
 *
 * 请你返回满足题目要求的最短子数组的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,10,4,2,3,5]
 * 输出：3
 * 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
 * 另一个正确的解为删除子数组 [3,10,4] 。
 * 示例 2：
 *
 * 输入：arr = [5,4,3,2,1]
 * 输出：4
 * 解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
 * 示例 3：
 *
 * 输入：arr = [1,2,3]
 * 输出：0
 * 解释：数组已经是非递减的了，我们不需要删除任何元素。
 * 示例 4：
 *
 * 输入：arr = [1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/solution/by-dylsini4yh-z1k4/
     * @param arr
     * @return
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        // 首先从左边开始 找到 第一个 下降位置 记录为 leftEnd leftEnd 》 leftEnd+ 1
        int leftEndIndex = 0;
        while (leftEndIndex < arr.length - 1 && arr[leftEndIndex] <= arr[leftEndIndex+1]) {
            leftEndIndex++;
        }
        // 从右边开始 找到第一个 下降位置 记录为 rightEnd rightEnd -1 》 rightEnd
        int rightEndIndex = arr.length - 1;
        while (rightEndIndex > 0 && arr[rightEndIndex-1] <= arr[rightEndIndex]) {
            rightEndIndex--;
        }
        // 不用删除 全员有序
        if (leftEndIndex >= rightEndIndex) {
            return 0;
        }
        // 枚举每个 leftEnd 开始位置 往0 找 二分法找到 第一个 大于等于 arr i 的右边位置 【rightEnd， n-1】 每次找到 记录 间距最小值
        int minDeleteLength = Integer.MAX_VALUE;
        minDeleteLength = Math.min(arr.length - leftEndIndex - 1, rightEndIndex);
        for (int i = leftEndIndex; i >= 0; i--) {
            int target = arr[i];
            int left = rightEndIndex;
            int right = arr.length - 1;
            int ans = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] >= target) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (ans == -1) {
                minDeleteLength = Math.min(minDeleteLength, arr.length - i - 1);
            } else {
                minDeleteLength = Math.min(minDeleteLength, ans - i - 1);
            }
        }
        return minDeleteLength;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,2,3,10,4,2,3,5
        };
        int len = solution.findLengthOfShortestSubarray(arr);
        System.out.println(len);
        Assert.assertEquals(3, len);


        arr = new int[] {
                16,10,0,3,22,1,14,7,1,12,15
        };
        len = solution.findLengthOfShortestSubarray(arr);
        System.out.println(len);
        Assert.assertEquals(8, len);
    }
}
