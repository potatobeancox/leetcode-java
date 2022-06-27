package com.potato.study.leetcodecn.p01390.t001;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1390. 四因数
 *
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。如果数组中不存在满足题意的整数，则返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 * 示例 2:
 *
 * 输入: nums = [21,21]
 * 输出: 64
 * 示例 3:
 *
 * 输入: nums = [1,2,3,4,5]
 * 输出: 0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * 1 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/four-divisors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 1390
    public int sumFourDivisors(int[] nums) {
        // 直接遍历 nums 对每个 num 找到因数的个数 和因数和
        int allSum = 0;
        Map<Integer, Integer> numSumMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numSumMap.containsKey(num)) {
                allSum += numSumMap.get(num);
                continue;
            }
            int count = 0;
            int sum = 0;
            for (int j = 1; j * j <= num; j++) {
                if (num % j != 0) {
                    continue;
                }
                count++;
                sum += j;
                // 不相等对的时候 一次找到2个
                if (j * j != num) {
                    count++;
                    sum += (num / j);
                }
            }
            if (count == 4) {
                numSumMap.put(num, sum);
                allSum += sum;
            }
        }
        return allSum;
    }

}
