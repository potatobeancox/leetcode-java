package com.potato.study.leetcodecn.other.swordoffer.p0056.p2.t001;


import java.util.*;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 *
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

  

 示例 1：

 输入：nums = [3,4,3,3]
 输出：4
 示例 2：

 输入：nums = [9,1,7,9,7,9,7]
 输出：1
  

 限制：

 1 <= nums.length <= 10000
 1 <= nums[i] < 2^31

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历 nums set 记录出现过没加
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Set<Integer> hasAppear = new HashSet<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int num : nums) {
            if (!hasAppear.contains(num)) {
                sum1 += num;
            }
            hasAppear.add(num);
            sum2 += num;
        }
        sum1 *= 3;
        long target = (sum1 - sum2) / 2;
        return (int)target;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int target = 9;
//        int[][] continuousSequence = solution.findContinuousSequence(target);
//        System.out.println(Arrays.deepToString(continuousSequence));
//
//        target = 15;
//        continuousSequence = solution.findContinuousSequence(target);
//        System.out.println(Arrays.deepToString(continuousSequence));
//    }

}
