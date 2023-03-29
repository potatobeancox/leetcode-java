package com.potato.study.leetcodecn.other.Interview.p0005.p0004;


import org.junit.Assert;

/**
 * 面试题 05.04. 下一个数
 *
 * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 *
 * 示例1:
 *
 *  输入：num = 2（或者0b10）
 *  输出：[4, 1] 或者（[0b100, 0b1]）
 * 示例2:
 *
 *  输入：num = 1
 *  输出：[2, -1]
 * 提示:
 *
 * num的范围在[1, 2147483647]之间；
 * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/closed-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // mainshiti 05.04
    public int[] findClosedNumbers(int num) {
        // 将num 转成2进制 大的最近的数字 从右往左 找到第一个 01 -》 10 然后将 这个右边的1 都放在最右边
        int small = getSmall(num);
        // 将num 转成2进制 small的最近的数字 从右往左 找到第一个 10 -》 01 然后将 这个右边的1 都放在最左边边
        int big = getBig(num);
        return new int[] {big, small};
    }

    private int getBig(int num) {
        char[] chars = Integer.toBinaryString(num).toCharArray();
        int index = -1;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i-1] == '0' && chars[i] == '1') {
                index = i-1;
                break;
            }
        }
        // 没找到 01 变不了更大了
        if (index == -1) {
            return -1;
        }
        // 反转
        chars[index] = '1';
        chars[index+1] = '0';
        // 将右边所有1都往右边移动
        int left = index + 2;
        int right = chars.length - 1;
        while (left < right) {
            // 找到left的第一个1
            while (left < right && chars[left] == '0') {
                left++;
            }
            while (left < right && chars[right] == '1') {
                right--;
            }
            // 交换 left 和right
            if (left < right) {
                chars[left] = '0';
                chars[right] = '1';

                left++;
                right--;
            }
        }
        String numStr = new String(chars);
        return Integer.parseInt(numStr);
    }

    private int getSmall(int num) {
        char[] chars = Integer.toBinaryString(num).toCharArray();
        int index = -1;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i-1] == '1' && chars[i] == '0') {
                index = i-1;
                break;
            }
        }
        // 没找到 01 变不了更大了
        if (index == -1) {
            return -1;
        }
        // 反转
        chars[index] = '0';
        chars[index+1] = '1';
        // 将右边所有1都往右边移动
        int left = index + 2;
        int right = chars.length - 1;
        while (left < right) {
            // 找到left的第一个1
            while (left < right && chars[left] == '1') {
                left++;
            }
            while (left < right && chars[right] == '0') {
                right--;
            }
            // 交换 left 和right
            if (left < right) {
                chars[left] = '1';
                chars[right] = '0';

                left++;
                right--;
            }
        }
        String numStr = new String(chars);
        return Integer.parseInt(numStr);
    }
}
