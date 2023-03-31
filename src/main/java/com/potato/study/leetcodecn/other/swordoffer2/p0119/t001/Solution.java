package com.potato.study.leetcodecn.other.swordoffer2.p0119.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 119. 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *  
 *
 * 进阶：可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 *
 *  
 *
 * 注意：本题与主站 128 题相同： https://leetcode-cn.com/problems/longest-consecutive-sequence/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/WhsWhI
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 119
    public int longestConsecutive(int[] nums) {
        // 用set 缓存一下
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // 遍历 nums 找到 set 中出现连续序列的开始位置 （上一个节点不再set中）
        int max = 0;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            // 当前num 是起点
            int length = 1;
            int target = num + 1;
            while (set.contains(target)) {
                target++;
                length++;
            }
            max = Math.max(max, length);
        }
        return max;
    }
}
