package com.potato.study.leetcodecn.p01829.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 1829. 每个查询的最大异或值
 *
 * 给你一个 有序 数组 nums ，它由 n 个非负整数组成，同时给你一个整数 maximumBit 。你需要执行以下查询 n 次：
 *
 * 找到一个非负整数 k < 2maximumBit ，使得 nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k 的结果 最大化 。k 是第 i 个查询的答案。
 * 从当前数组 nums 删除 最后 一个元素。
 * 请你返回一个数组 answer ，其中 answer[i]是第 i 个查询的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,1,3], maximumBit = 2
 * 输出：[0,3,2,3]
 * 解释：查询的答案如下：
 * 第一个查询：nums = [0,1,1,3]，k = 0，因为 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3 。
 * 第二个查询：nums = [0,1,1]，k = 3，因为 0 XOR 1 XOR 1 XOR 3 = 3 。
 * 第三个查询：nums = [0,1]，k = 2，因为 0 XOR 1 XOR 2 = 3 。
 * 第四个查询：nums = [0]，k = 3，因为 0 XOR 3 = 3 。
 * 示例 2：
 *
 * 输入：nums = [2,3,4,7], maximumBit = 3
 * 输出：[5,2,6,5]
 * 解释：查询的答案如下：
 * 第一个查询：nums = [2,3,4,7]，k = 5，因为 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7。
 * 第二个查询：nums = [2,3,4]，k = 2，因为 2 XOR 3 XOR 4 XOR 2 = 7 。
 * 第三个查询：nums = [2,3]，k = 6，因为 2 XOR 3 XOR 6 = 7 。
 * 第四个查询：nums = [2]，k = 5，因为 2 XOR 5 = 7 。
 * 示例 3：
 *
 * 输入：nums = [0,1,2,2,5,7], maximumBit = 3
 * 输出：[4,3,6,4,6,7]
 *  
 *
 * 提示：
 *
 * nums.length == n
 * 1 <= n <= 105
 * 1 <= maximumBit <= 20
 * 0 <= nums[i] < 2maximumBit
 * nums​​​ 中的数字已经按 升序 排好序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-for-each-query
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 将 nums 变成 异或的前缀和
     * 对于每个 num for nums 从最低位开始 生成 取 与当前位置 不同的数字，知道 取到的数字 大于等于 2 maximumBit次幂等
     * @param nums
     * @param maximumBit
     * @return
     */
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        // 边界条件快速返回
        int[] result = new int[nums.length];
        if (maximumBit == 0) {
            return result;
        }
        // 将 nums 变成 异或的前缀和
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i-1] ^ nums[i];
        }
        // 对于每个 num for nums 从最低位开始 生成 取 与当前位置 不同的数字，知道 取到的数字 大于等于 2 maximumBit次幂等
        for (int i = 0; i < nums.length; i++) {
            int mask = nums[nums.length - 1 - i];
            int cur = 0;
            for (int j = 0; j < maximumBit; j++) {
                cur <<= 1;
                cur += (1 - (mask % 2));
                mask >>>= 1;
            }
            result[i] = cur;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                0,1,1,3
        };
        int maximumBit = 2;
        int[] maximumXor = solution.getMaximumXor(nums, maximumBit);
        System.out.println(Arrays.toString(maximumXor));
        Assert.assertArrayEquals(new int[] {
                0,3,2,3
        }, maximumXor);
    }
}
