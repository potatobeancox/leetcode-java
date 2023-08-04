package com.potato.study.leetcodecn.other.lcr.p0009.t001;

/**
 * LCR 009. 乘积小于 K 的子数组
 *
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 示例 2:
 *
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 *  
 *
 * 提示: 
 *
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 *  
 *
 * 注意：本题与主站 713 题相同：https://leetcode-cn.com/problems/subarray-product-less-than-k/ 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ZVAVXX
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    // 009 滑动窗口 固定右边 滑动左边
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int left = 0;
        long window = 1;
        int num = 0;
        for (int right = 0; right < nums.length; right++) {
            window *= nums[right];
            // 保证 窗口内部小于 k
            while (window >= k && left <= right) {
                window /= nums[left];
                left++;
            }
            int len = right - left + 1;
            if (len > 0) {
                num += len;
            }
        }
        return num;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
//        [7,6,8,4,9,3,2,10,7,9,9,6,3]
//        236
//        int i = solution.numSubarrayProductLessThanK();
//        System.out.println(i);
//        Assert.assertEquals(, i);
    }


}
