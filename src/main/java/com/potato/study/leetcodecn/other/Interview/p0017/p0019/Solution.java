package com.potato.study.leetcodecn.other.Interview.p0017.p0019;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.19. 消失的两个数字
 *
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？

 以任意顺序返回这两个数字均可。

 示例 1:

 输入: [1]
 输出: [2,3]
 示例 2:

 输入: [2,3]
 输出: [1,4]
 提示：

 nums.length <= 30000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/missing-two-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] missingTwo(int[] nums) {
        // 先对 1-n 进行异或
        int n = nums.length + 2;
        int xor = 0;
        // 异或到 nums 得到缺的2个数字的 异或值
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        for (int num : nums) {
            xor ^= num;
        }
        // 根据异或值某一个1的bit 对 nums 进行分割 n 也分割 最终就能得到结果了
        int bit = 1;
        while ((bit & xor) == 0) {
            bit <<= 1;
        }
        int num1 = 0;
        int num2 = 0;
        for (int i = 1; i <= n; i++) {
            if ((bit & i) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if ((bit & num) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        return new int[] {num1, num2};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1};
        int[] ints = solution.missingTwo(nums);
        System.out.println(Arrays.toString(ints));
    }
}
