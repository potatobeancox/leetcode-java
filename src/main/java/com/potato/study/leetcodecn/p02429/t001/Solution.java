package com.potato.study.leetcodecn.p02429.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2429. 最小 XOR
 *
 * 给你两个正整数 num1 和 num2 ，找出满足下述条件的整数 x ：
 *
 * x 的置位数和 num2 相同，且
 * x XOR num1 的值 最小
 * 注意 XOR 是按位异或运算。
 *
 * 返回整数 x 。题目保证，对于生成的测试用例， x 是 唯一确定 的。
 *
 * 整数的 置位数 是其二进制表示中 1 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num1 = 3, num2 = 5
 * 输出：3
 * 解释：
 * num1 和 num2 的二进制表示分别是 0011 和 0101 。
 * 整数 3 的置位数与 num2 相同，且 3 XOR 3 = 0 是最小的。
 * 示例 2：
 *
 * 输入：num1 = 1, num2 = 12
 * 输出：3
 * 解释：
 * num1 和 num2 的二进制表示分别是 0001 和 1100 。
 * 整数 3 的置位数与 num2 相同，且 3 XOR 1 = 2 是最小的。
 *  
 *
 * 提示：
 *
 * 1 <= num1, num2 <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimize-xor
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimizeXor(int num1, int num2) {
        // 求 num2 里边有多少个1
        int targetCount = Integer.bitCount(num2);
        int count = Integer.bitCount(num1);
        // 相等 可以出现 0
        if (targetCount == count) {
            return num1;
        }
        char[] chars = Integer.toBinaryString(num1).toCharArray();
        char[] newChars = new char[chars.length];
        // 先确定 num1 是多少个1 其他的1 都要放在 num1 从小到大的 0的位置
        for (int i = 0; i < chars.length; i++) {
            if (targetCount == 0) {
                newChars[i] = '0';
            } else {
                // 从index 小的位置 填充 1
                if (chars[i] == '1') {
                    newChars[i] = '1';
                    targetCount --;
                } else {
                    newChars[i] = '0';
                }
            }
        }
        // 是否还剩 targetCount 从低位的 0 开始替换一致替换到末尾
        int index = newChars.length - 1;
        while (index >= 0 && targetCount > 0) {
            if (newChars[index] == '0') {
                newChars[index] = '1';
                targetCount--;
            }
            index--;
        }
        // 如果还有的话
        StringBuilder builder = new StringBuilder();
        while (targetCount > 0) {
            builder.append("1");
            targetCount--;
        }
        builder.append(new String(newChars));
        return Integer.parseInt(builder.toString(), 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minimizeXor(3, 5);
        System.out.println(i);
        Assert.assertEquals(3, i);

        i = solution.minimizeXor(1, 12);
        System.out.println(i);
        Assert.assertEquals(3, i);


        i = solution.minimizeXor(25, 72);
        System.out.println(i);
        Assert.assertEquals(24, i);
    }

}
