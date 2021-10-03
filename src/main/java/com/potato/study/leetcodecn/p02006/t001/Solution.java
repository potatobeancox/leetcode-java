package com.potato.study.leetcodecn.p02006.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 2006. 差的绝对值为 K 的数对数目
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 *
 * |x| 的值定义为：
 *
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * 示例 2：
 *
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 * 示例 3：
 *
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为 2 的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countKDifference(int[] nums, int k) {
        // map 统计次数
        Map<Integer, Integer> countMap = new HashMap<>();
        // 从1开始 找到 100 只往后找，次数等于个数相乘
        for (int i = 0; i < nums.length; i++) {
            Integer count = countMap.getOrDefault(nums[i], 0);
            count++;
            countMap.put(nums[i], count);
        }
        int total = 0;
        for (int i = 1; i <= 100; i++) {
            if (!countMap.containsKey(i)) {
                continue;
            }
            if (!countMap.containsKey(i + k)) {
                continue;
            }
            // 计算总对数
            total += (countMap.get(i) * countMap.get(i+k));
        }
        return total;
    }
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//
//        String word = "abcdefd";
//        char ch = 'd';
//        String s = solution.reversePrefix(word, ch);
//        System.out.println(s);
//        Assert.assertEquals("dcbaefd", s);
//    }

}

