package com.potato.study.leetcodecn.other.swordoffer2.p0057.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * 剑指 Offer II 057. 值和下标之差都在给定的范围内
 *
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *  
 *
 * 注意：本题与主站 220 题相同： https://leetcode-cn.com/problems/contains-duplicate-iii/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/7WqeDu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 057
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 遍历 nums 每个元素i 使用tree map 记录 窗口中的元素数量 abs(i - j) <= k 左右都k个
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        // 其实只查一面就行
        for (int i = 0; i < nums.length; i++) {
            int count = countMap.getOrDefault(nums[i], 0);
            count++;
            countMap.put(nums[i], count);
            if (i > k) {
                // 需要删掉 i-k
                count = countMap.getOrDefault(nums[i-k-1], 0);
                count--;
                countMap.put(nums[i-k-1], count);
            }
            // 窗口内部是不是有 满足的t
            // treemap 找到 小于等于 xi+t 或者大于等于 xi-t 的数字  存在就可以返回
            int target = nums[i] - t;
            Integer bigger = countMap.ceilingKey(target);
            if (bigger == null) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }
}
