package com.potato.study.leetcodecn.p02340.t001;

/**
 * 2340. Minimum Adjacent Swaps to Make a Valid Array
 *
 * You are given a 0-indexed integer array nums.

 Swaps of adjacent elements are able to be performed on nums.

 A valid array meets the following conditions:

 The largest element (any of the largest elements if there are multiple) is at the rightmost position in the array.
 The smallest element (any of the smallest elements if there are multiple) is at the leftmost position in the array.
 Return the minimum swaps required to make nums a valid array.

  

 Example 1:

 Input: nums = [3,4,5,5,3,1]
 Output: 6
 Explanation: Perform the following swaps:
 - Swap 1: Swap the 3rd and 4th elements, nums is then [3,4,5,3,5,1].
 - Swap 2: Swap the 4th and 5th elements, nums is then [3,4,5,3,1,5].
 - Swap 3: Swap the 3rd and 4th elements, nums is then [3,4,5,1,3,5].
 - Swap 4: Swap the 2nd and 3rd elements, nums is then [3,4,1,5,3,5].
 - Swap 5: Swap the 1st and 2nd elements, nums is then [3,1,4,5,3,5].
 - Swap 6: Swap the 0th and 1st elements, nums is then [1,3,4,5,3,5].
 It can be shown that 6 swaps is the minimum swaps required to make a valid array.
 Example 2:
 Input: nums = [9]
 Output: 0
 Explanation: The array is already valid, so we return 0.
  

 Constraints:

 1 <= nums.length <= 105
 1 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-adjacent-swaps-to-make-a-valid-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int minimumSwaps(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        // 找到 min 离左边最近的
        int leftMinIndex = 0;
        // 找到 max 离右边最近的
        int rightMaxIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[leftMinIndex]) {
                leftMinIndex = i;
            }
            if (nums[nums.length - 1 - i] > nums[rightMaxIndex]) {
                rightMaxIndex = nums.length - 1 - i;
            }

        }
        // 如果 min 位置在 max 左边 那么不用交叉 直接加和就行
        if (leftMinIndex < rightMaxIndex) {
            return leftMinIndex - 0 + nums.length - 1 - rightMaxIndex;
        }
        // 反之需要交叉一次 -1
        return leftMinIndex - 0 + nums.length - 1 - rightMaxIndex - 1;
    }


}
