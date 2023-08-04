package com.potato.study.leetcodecn.other.lcr.p0001.t001;

import org.junit.Assert;

/**
 * LCR 001. 两数相除
 *
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 *
 *  
 *
 * 注意：
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
 *  
 *
 * 示例 1：
 *
 * 输入：a = 15, b = 2
 * 输出：7
 * 解释：15/2 = truncate(7.5) = 7
 * 示例 2：
 *
 * 输入：a = 7, b = -3
 * 输出：-2
 * 解释：7/-3 = truncate(-2.33333..) = -2
 * 示例 3：
 *
 * 输入：a = 0, b = 1
 * 输出：0
 * 示例 4：
 *
 * 输入：a = 1, b = 1
 * 输出：1
 *  
 *
 * 提示:
 *
 * -231 <= a, b <= 231 - 1
 * b != 0
 *  
 *
 * 注意：本题与主站 29 题相同：https://leetcode-cn.com/problems/divide-two-integers/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xoh6Oh
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int divide(int a, int b) {
        // 符号位置
        long symbol = (long)a * b;
        boolean isNegtive = symbol < 0;
        long tmpA = a;
        if (a < 0) {
            tmpA *= -1;
        }
        long tmpB = b;
        if (b < 0) {
            tmpB *= -1;
        }
        long result = 0;
        // 如果 a 大于等于 b 先确定b的倍数
        while (tmpA >= tmpB) {
            // 确定 tmpB的倍数
            long tmp = tmpB;
            long current = 1;
            while (tmp + tmp <= tmpA) {
                tmp *= 2;
                current *= 2;
            }
            // 用减法 计算下次除法
            if (tmpA >= tmpB) {
                result += current;
                tmpA -= tmp;
            }
        }
        if (isNegtive) {
            result *= -1;
        }
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int divide = solution.divide(-2147483648,-1);
        System.out.println(divide);
        Assert.assertEquals(2147483647, divide);
    }
}
