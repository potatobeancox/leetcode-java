package com.potato.study.leetcodecn.p02447.t001;

import org.junit.Assert;

/**
 * 2447. 最大公因数等于 K 的子数组数目
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 nums 的子数组中元素的最大公因数等于 k 的子数组数目。
 *
 * 子数组 是数组中一个连续的非空序列。
 *
 * 数组的最大公因数 是能整除数组中所有元素的最大整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [9,3,1,2,6,3], k = 3
 * 输出：4
 * 解释：nums 的子数组中，以 3 作为最大公因数的子数组如下：
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * 示例 2：
 *
 * 输入：nums = [4], k = 7
 * 输出：0
 * 解释：不存在以 7 作为最大公因数的子数组。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k/solution/gcdbao-li-mei-ju-jian-zhi-by-ronin-f4-8zfn/
     * @param nums
     * @param k
     * @return
     */
    public int subarrayGCD(int[] nums, int k) {
        // 极端情况只有一个数字的时候 判断一下
        if (nums.length == 1) {
            if (nums[0] == k) {
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;
        // 否则遍历每个位置 ，作为起点 往后开始遍历 直到找到gcd不是 所求的
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current % k != 0) {
                continue;
            }
            for (int j = i; j < nums.length; j++) {
                current = gcd(current, nums[j]);
                if (current == k) {
                    res++;
                } else if (current < k) {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 辗转相除
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,12,9,6
        };
        int k = 3;
        int i = solution.subarrayGCD(nums, k);
        System.out.println(i);
        Assert.assertEquals(7, i);


        nums = new int[] {
                3,3,4,1,2
        };
        k = 1;
        i = solution.subarrayGCD(nums, k);
        System.out.println(i);
        Assert.assertEquals(10, i);
    }

}
