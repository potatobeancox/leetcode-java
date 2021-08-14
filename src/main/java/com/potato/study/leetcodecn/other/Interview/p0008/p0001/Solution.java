package com.potato.study.leetcodecn.other.Interview.p0008.p0001;


import org.junit.Assert;

/**
 * 面试题 08.01. 三步问题
 *
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。

 示例1:

 输入：n = 3
 输出：4
 说明: 有四种走法
 示例2:

 输入：n = 5
 输出：13
 提示:

 n范围在[1, 1000000]之间

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/three-steps-problem-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 可以压缩状态
     * step i 走到i 有多少种走法
     * step 0，1 = 1；
     * step 2 = 2；
     * step 3 = sum of step2（走1步伐），step1（走2步），step0 （走3步）
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int step0 = 1;
        int step1 = 1;
        int step2 = 2;
        int step3 = 0;

        for (int i = 3; i <= n; i++) {

            step3 = step0 + step1;
            step3 %= 1000000007;
            step3 += step2;
            step3 %= 1000000007;

            step0 = step1;
            step1 = step2;
            step2 = step3;
        }
        return step3;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int res = solution.waysToStep(n);
        System.out.println(res);
        Assert.assertEquals(4, res);

        n = 5;
        res = solution.waysToStep(n);
        System.out.println(res);
        Assert.assertEquals(13, res);
    }
}
