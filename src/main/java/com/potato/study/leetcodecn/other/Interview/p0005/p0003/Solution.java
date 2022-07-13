package com.potato.study.leetcodecn.other.Interview.p0005.p0003;


import org.junit.Assert;

/**
 * 面试题 05.03. 翻转数位
 *
 * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 *
 * 示例 1：
 *
 * 输入: num = 1775(110111011112)
 * 输出: 8
 * 示例 2：
 *
 * 输入: num = 7(01112)
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-bits-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 05.03
    public int reverseBits(int num) {
        String binaryString = Integer.toBinaryString(num);
        // 滑动窗口 计算1的个数
        int zeroCount = 0;
        int oneCount = 0;
        int left = 0;
        char[] chars = binaryString.toCharArray();
        int maxLength = 0;
        boolean hasZero = false;
        for (int right = 0; right < binaryString.length(); right++) {
            if (chars[right] == '1') {
                oneCount++;
            } else {
                hasZero = true;
                zeroCount++;
                // 不到2计数
                if (zeroCount < 2) {
                    oneCount++;
                } else {
                    // 到了2
                    oneCount++;
                    while (left <= right && chars[left] == '1') {
                        oneCount--;
                        left++;
                    }
                    if (left <= right && chars[left] == '0') {
                        oneCount--;
                        zeroCount--;
                        left++;
                    }
                }
            }
            maxLength = Math.max(maxLength, oneCount);
        }
        if (!hasZero && maxLength < 32) {
            return maxLength + 1;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.reverseBits(2147483647);
        System.out.println(i);
        Assert.assertEquals(32, i);


        i = solution.reverseBits(2147482622);
        System.out.println(i);
        Assert.assertEquals(30, i);
    }
}
