package com.potato.study.leetcodecn.p00306.t001;

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

    public boolean isAdditiveNumber(String num) {

        return false;
    }




    /**
     *
     * @param num           数字字符串
     * @param index         当前使用的index
     * @param sumOfTwo      之前两个数字的和
     * @param current       当前数字
     * @param lastNum       之前那个数字是多少
     * @return
     */
    private boolean isAdditiveNumberEach(String num, int index, int sumOfTwo, int current, int lastNum) {
        // 终止条件 当前index 已经到了 num末尾 如果 current 与 sumOfTwo 相等返回 true 否则 返回false

        // index 开始往后找 直到


        return false;
    }
}
