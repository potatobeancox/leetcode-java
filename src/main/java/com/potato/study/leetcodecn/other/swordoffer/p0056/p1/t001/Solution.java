package com.potato.study.leetcodecn.other.swordoffer.p0056.p1.t001;


import java.util.Arrays;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 *
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

  

 示例 1：

 输入：nums = [4,1,4,6]
 输出：[1,6] 或 [6,1]
 示例 2：

 输入：nums = [1,2,10,4,1,4,3,3]
 输出：[2,10] 或 [10,2]
  

 限制：

 2 <= nums.length <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        // 先全部做一次异或 拿到
        int bit = 0;
        for (int num : nums) {
            bit ^= num;
        }
        // 获取当前第一个不为0的位置生成的数字
        int spiltBit = 1;
        while ((spiltBit & bit) == 0) {
            spiltBit <<= 1;
        }
        // 利用这个位置将数组分割成两个不同数组
        int bit1 = 0;
        int bit2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((spiltBit & nums[i]) == 0) {
                bit1 ^= nums[i];
            } else {
                bit2 ^= nums[i];
            }
        }
        // 分别异或得到结果
        return new int[] {bit1, bit2};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                4,1,4,6
        };
        int[] ints = solution.singleNumbers(nums);
        System.out.println(Arrays.toString(ints));
    }

}
