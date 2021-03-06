package com.potato.study.leetcodecn.p01085.t001;

import org.junit.Assert;

/**
 * 1085. 最小元素各数位之和
 *
 * 给你一个正整数的数组 A。

 然后计算 S，使其等于数组 A 当中最小的那个元素各个数位上数字之和。

 最后，假如 S 所得计算结果是 奇数 ，返回 0 ；否则请返回 1。

  

 示例 1:

 输入：[34,23,1,24,75,33,54,8]
 输出：0
 解释：
 最小元素为 1 ，该元素各个数位上的数字之和 S = 1 ，是奇数所以答案为 0 。
 示例 2：

 输入：[99,77,33,66,55]
 输出：1
 解释：
 最小元素为 33 ，该元素各个数位上的数字之和 S = 3 + 3 = 6 ，是偶数所以答案为 1 。
  

 提示：

 1 <= A.length <= 100
 1 <= A[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sum-of-digits-in-the-minimum-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int sumOfDigits(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int oddCount = 0;
        while (min > 0) {
            int i = min % 10;
            if (i % 2 == 1) {
                oddCount++;
            }
            min /= 10;
        }

        if (oddCount % 2 == 1) {
            return 0;
        } else {
            return 1;
        }
    }
}
