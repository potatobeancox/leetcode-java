package com.potato.study.leetcodecn.p02601.t001;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 2601. 质数减法运算
 *
 * 给你一个下标从 0 开始的整数数组 nums ，数组长度为 n 。

 你可以执行无限次下述运算：

 选择一个之前未选过的下标 i ，并选择一个 严格小于 nums[i] 的质数 p ，从 nums[i] 中减去 p 。
 如果你能通过上述运算使得 nums 成为严格递增数组，则返回 true ；否则返回 false 。

 严格递增数组 中的每个元素都严格大于其前面的元素。

  

 示例 1：

 输入：nums = [4,9,6,10]
 输出：true
 解释：
 在第一次运算中：选择 i = 0 和 p = 3 ，然后从 nums[0] 减去 3 ，nums 变为 [1,9,6,10] 。
 在第二次运算中：选择 i = 1 和 p = 7 ，然后从 nums[1] 减去 7 ，nums 变为 [1,2,6,10] 。
 第二次运算后，nums 按严格递增顺序排序，因此答案为 true 。
 示例 2：

 输入：nums = [6,8,11,12]
 输出：true
 解释：nums 从一开始就按严格递增顺序排序，因此不需要执行任何运算。
 示例 3：

 输入：nums = [5,8,3]
 输出：false
 解释：可以证明，执行运算无法使 nums 按严格递增顺序排序，因此答案是 false 。
  

 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 1000
 nums.length == n

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/prime-subtraction-operation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean primeSubOperation(int[] nums) {
        // 先记录 1000 以内的质数
        List<Integer> list = new ArrayList<>();
        list.add(2);
        for (int i = 3; i < 1000; i++) {
            boolean isPrime = true;
            for (int ele : list) {
                if (i % ele == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                list.add(i);
            }
        }

        // 从后往前 记录 之后已经变成的最小值
        int bigLimit = nums[nums.length-1];
        for (int i = nums.length - 2; i >= 0; i--) {
            // 对于 当前的位置 从2开始找 直到当前减去小于 末尾的数字即可
            if (nums[i] < bigLimit) {
                bigLimit = nums[i];
                continue;
            }
            // 从2 开始找质数
            boolean hasFond = false;
            for (int prime : list) {
                if (prime >= nums[i]) {
                    break;
                }
                if (nums[i] - prime < bigLimit) {
                    bigLimit = nums[i] - prime;
                    hasFond = true;
                    break;
                }
            }
            // 没找到这样的质数
            if (!hasFond) {
                return false;
            }

        }
        return true;
    }


}
