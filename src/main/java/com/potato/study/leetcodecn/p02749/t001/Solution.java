package com.potato.study.leetcodecn.p02749.t001;


import org.junit.Assert;

/**
 *
 * 2749. 得到整数零需要执行的最少操作数
 *
 * 给你两个整数：num1 和 num2 。
 *
 * 在一步操作中，你需要从范围 [0, 60] 中选出一个整数 i ，并从 num1 减去 2i + num2 。
 *
 * 请你计算，要想使 num1 等于 0 需要执行的最少操作数，并以整数形式返回。
 *
 * 如果无法使 num1 等于 0 ，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num1 = 3, num2 = -2
 * 输出：3
 * 解释：可以执行下述步骤使 3 等于 0 ：
 * - 选择 i = 2 ，并从 3 减去 22 + (-2) ，num1 = 3 - (4 + (-2)) = 1 。
 * - 选择 i = 2 ，并从 1 减去 22 + (-2) ，num1 = 1 - (4 + (-2)) = -1 。
 * - 选择 i = 0 ，并从 -1 减去 20 + (-2) ，num1 = (-1) - (1 + (-2)) = 0 。
 * 可以证明 3 是需要执行的最少操作数。
 * 示例 2：
 *
 * 输入：num1 = 5, num2 = 7
 * 输出：-1
 * 解释：可以证明，执行操作无法使 5 等于 0 。
 *  
 *
 * 提示：
 *
 * 1 <= num1 <= 109
 * -109 <= num2 <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-operations-to-make-the-integer-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {





    public int makeTheIntegerZero(int num1, int num2) {
        // 从 1开始枚举i num1 - num2*i 如果 结果中二进制1的个数 小于等于 i 说明找到了 次数
        long n1 = num1;
        long n2 = num2;
        int i = 1;
        while (n1 > i * n2) {
            // 计算中间的差值
            long res = n1 - i * n2;
            // 二进制 位数量
            int binaryCount = Long.bitCount(res);
            // 如果太小了 分不出n个1就不行了
            if (binaryCount <= i && res >= i) {
                return i;
            }
            i++;
        }
        // 没有这种关系
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.makeTheIntegerZero(5, 7);
        System.out.println(i);
        Assert.assertEquals(-1, i);


        i = solution.makeTheIntegerZero(3, -2);
        System.out.println(i);
        Assert.assertEquals(3, i);


        //112577768
        //-501662198
        i = solution.makeTheIntegerZero(112577768, -501662198);
        System.out.println(i);
        Assert.assertEquals(16, i);


        i = solution.makeTheIntegerZero(85, 42);
        System.out.println(i);
        Assert.assertEquals(-1, i);

    }
}
