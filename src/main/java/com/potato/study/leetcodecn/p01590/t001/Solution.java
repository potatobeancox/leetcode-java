package com.potato.study.leetcodecn.p01590.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1590. 使数组和能被 P 整除
 *
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。

 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。

 子数组 定义为原数组中连续的一组元素。

  

 示例 1：

 输入：nums = [3,1,4,2], p = 6
 输出：1
 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
 示例 2：

 输入：nums = [6,3,5,2], p = 9
 输出：2
 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
 示例 3：

 输入：nums = [1,2,3], p = 3
 输出：0
 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
 示例  4：

 输入：nums = [1,2,3], p = 7
 输出：-1
 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
 示例 5：

 输入：nums = [1000000000,1000000000,1000000000], p = 3
 输出：0
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 109
 1 <= p <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/make-sum-divisible-by-p
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/make-sum-divisible-by-p/solution/java-by-kaslanla-t5ak/
     * 前缀和
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        // 先求 总和
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 余数
        long remind = sum % p;
        if (remind == 0) {
            return 0;
        }
        // 遍历 nums 用map 记录前缀和 更新为最新的位置 key 是余数
        long tempSum = 0;
        // key 是 %p的余数 value 是 index
        Map<Long, Integer> remind2IndexMap = new HashMap<>();
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            tempSum += num;
            // 找到 子串 子串的sum 是 remind + k * p
            long tempRemind = tempSum % p;
            // tempRemind 需要大于 remind
            if (tempRemind < remind) {
                tempRemind += p;
            }
            // 前缀应该有的余数
            long targetRemind = tempRemind - remind;
            // （tempSum - 前缀） % p = remind
            if (targetRemind == 0 && i != nums.length - 1) {
                // 特殊情况
                minLen = Math.min(minLen, i + 1);
            } else if (remind2IndexMap.containsKey(targetRemind)) {
                // 有这个余数 需要求出前缀位置
                int prefixIndex = remind2IndexMap.get(targetRemind);
                // prefixIndex 是包含关系
                int len = i - prefixIndex;
                minLen = Math.min(minLen, len);
            }
            // 有或者没有 都要记录当前前缀
            remind2IndexMap.put(tempSum % p, i);
        }
        if (minLen == Integer.MAX_VALUE) {
            return -1;
        }
        return minLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                6,3,5,2
        };
        int p = 9;
        int i = solution.minSubarray(nums, p);
        System.out.println(i);
        Assert.assertEquals(2, i);


        nums = new int[] {
                1000000000,1000000000,1000000000
        };
        p = 3;
        i = solution.minSubarray(nums, p);
        System.out.println(i);
        Assert.assertEquals(0, i);


        nums = new int[] {
                8,32,31,18,34,20,21,13,1,27,23,22,11,15,30,4,2
        };
        p = 148;
        i = solution.minSubarray(nums, p);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }
}
