package com.potato.study.leetcodecn.other.swordoffer.p0003.p1.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 03. 数组中重复的数字
 *
 * 找出数组中重复的数字。


 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

 示例 1：

 输入：
 [2, 3, 1, 0, 2, 5, 3]
 输出：2 或 3
  

 限制：

 2 <= n <= 100000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 直接 计数，然后当计数器达到2时 返回即可
     * 通过 t002 解法减少空间消耗
     * 解法有个问题 会额外耗费空间
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if (nums.length < 2 || nums.length > 100000) {
            throw new RuntimeException("数组过大或者过小，不满足题目含义");
        }
        // 计数器
        Map<Integer, Integer> countMap = new HashMap<>();
        // 遍历计数 当计数次数大于2时返回
        for (int num : nums) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            if (count >= 2) {
                return num;
            }
            countMap.put(num, count);
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int num = solution.findRepeatNumber(nums);
        System.out.println(num);
        Assert.assertTrue( num == 2 || num == 3);


    }

}
