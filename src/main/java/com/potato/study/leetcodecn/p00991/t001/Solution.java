package com.potato.study.leetcodecn.p00991.t001;

import org.junit.Assert;

/**
 * 991. 坏了的计算器
 *
 * 在显示着数字的坏计算器上，我们可以执行以下两种操作：

 双倍（Double）：将显示屏上的数字乘 2；
 递减（Decrement）：将显示屏上的数字减 1 。
 最初，计算器显示数字 X。

 返回显示数字 Y 所需的最小操作数。

  

 示例 1：

 输入：X = 2, Y = 3
 输出：2
 解释：先进行双倍运算，然后再进行递减运算 {2 -> 4 -> 3}.
 示例 2：

 输入：X = 5, Y = 8
 输出：2
 解释：先递减，再双倍 {5 -> 4 -> 8}.
 示例 3：

 输入：X = 3, Y = 10
 输出：3
 解释：先双倍，然后递减，再双倍 {3 -> 6 -> 5 -> 10}.
 示例 4：

 输入：X = 1024, Y = 1
 输出：1023
 解释：执行递减运算 1023 次
  

 提示：

 1 <= X <= 10^9
 1 <= Y <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/broken-calculator
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     *
     * https://leetcode-cn.com/problems/broken-calculator/solution/wu-xu-ni-xiang-zheng-xiang-ji-suan-jian-ji-zheng-m/
     * @param startValue
     * @param target
     * @return
     */
    public int brokenCalc(int startValue, int target) {
        // 正向计算 直到 大于等于 target 记录 次数 cnt1
        int cnt1 = 0;
        // 从cnt1 开始计算要减去多少次
        while (startValue < target) {
            startValue *= 2;
            cnt1++;
        }
        int diff = startValue - target;
        // 计算减去 cnt2的次数
        int cnt2 = 0;
        for (int i = cnt1; i >= 0; i--) {
            // 2 ^ n 次幂
            int tmp = (int) Math.pow(2, i);
            int times = diff / tmp;
            // 减去 定量之后的值
            diff -= times * tmp;
            cnt2 += times;
            // 已经剪光了 剪枝
            if (diff == 0) {
                break;
            }
        }
        return cnt1 + cnt2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int startValue = 2;
        int target = 3;
        int i = solution.brokenCalc(startValue, target);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }


}
