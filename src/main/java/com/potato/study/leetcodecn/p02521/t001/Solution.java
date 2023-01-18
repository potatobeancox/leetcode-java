package com.potato.study.leetcodecn.p02521.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2521. 数组乘积中的不同质因数数目
 *
 * 给你一个正整数数组 nums ，对 nums 所有元素求积之后，找出并返回乘积中 不同质因数 的数目。
 *
 * 注意：
 *
 * 质数 是指大于 1 且仅能被 1 及自身整除的数字。
 * 如果 val2 / val1 是一个整数，则整数 val1 是另一个整数 val2 的一个因数。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,4,3,7,10,6]
 * 输出：4
 * 解释：
 * nums 中所有元素的乘积是：2 * 4 * 3 * 7 * 10 * 6 = 10080 = 25 * 32 * 5 * 7 。
 * 共有 4 个不同的质因数，所以返回 4 。
 * 示例 2：
 *
 * 输入：nums = [2,4,8,16]
 * 输出：1
 * 解释：
 * nums 中所有元素的乘积是：2 * 4 * 8 * 16 = 1024 = 210 。
 * 共有 1 个不同的质因数，所以返回 1 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * 2 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/distinct-prime-factors-of-product-of-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2521
    public int distinctPrimeFactors(int[] nums) {
        // 用一个 set存储目前已经出现的 质因数 对nums 如果在的话 直接下一个 否则 从2开始找这个数字的质因数 放入set中
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            int temp = num;
            for (int i = 2; i <= num; i++) {
                if (temp <= 1) {
                    break;
                }
                while (temp % i == 0) {
                    temp /= i;
                    set.add(i);
                }
            }
        }
        return set.size();
    }




}
