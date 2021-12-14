package com.potato.study.leetcodecn.p00306.t001;

import org.junit.Assert;

/**
 * 306. 累加数
 *
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 *
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 示例 1:
 *
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2:
 *
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 306
    public boolean isAdditiveNumber(String num) {
        return isAdditiveNumberEach(num, 0, 0, 0, 0);
    }

    /**
     *
     * @param num  原始数字
     * @param index 当前处理到的位置
     * @param lastSum   商2个数字的和
     * @param lastNum   上一个数字
     * @param numCount  当前有多少个数字
     * @return
     */
    private boolean isAdditiveNumberEach(String num, int index, long lastSum,
            long lastNum, int numCount) {
        if (index == num.length()) {
            // 之前都满足了 找到最后 肯定ok
            return numCount > 2;
        }
        // 从index 开始往后生成当前数字
        for (int i = index; i < num.length(); i++) {
            // 生成当前数字
            String numStr = num.substring(index, i + 1);
            if (numStr.length() > 1 && numStr.charAt(0) == '0') {
                return false;
            }
            // 剩下的字符串还没有现在长 直接返回吧
            if (numCount <= 1 && numStr.length() > num.length() / 2) {
                return false;
            }
            long currentNum = Long.parseLong(numStr);
            // 如果当前数字 小于 2 进行生成逻辑
            boolean success;
            if (numCount == 0) {
                success = isAdditiveNumberEach(num, i+1, 0, currentNum, 1);
            } else if (numCount == 1) {
                success = isAdditiveNumberEach(num, i+1, currentNum + lastNum,
                        currentNum, 2);
            } else {
                // 判断有效性 无效的话直接continue
                if (currentNum < lastSum) {
                    continue;
                } else if (currentNum > lastSum) {
                    break;
                }
                // 否则计算 并判断是否可行 ，可行的话 递归计算
                success = isAdditiveNumberEach(num, i+1, currentNum + lastNum, currentNum, numCount + 1);
            }
            if (success) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String num = "11235813213455890144";
        boolean additiveNumber = solution.isAdditiveNumber(num);
        System.out.println(additiveNumber);
        Assert.assertEquals(false, additiveNumber);

        num = "112358";
        additiveNumber = solution.isAdditiveNumber(num);
        System.out.println(additiveNumber);
        Assert.assertEquals(true, additiveNumber);

        num = "199100199";
        additiveNumber = solution.isAdditiveNumber(num);
        System.out.println(additiveNumber);
        Assert.assertEquals(true, additiveNumber);
    }

}
