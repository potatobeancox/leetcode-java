package com.potato.study.leetcodecn.other.swordoffer.p0014.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 14- I. 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

 示例 1：

 输入: 2
 输出: 1
 解释: 2 = 1 + 1, 1 × 1 = 1
 示例 2:

 输入: 10
 输出: 36
 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 提示：

 2 <= n <= 58
 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int cuttingRope(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        return cuttingRopeOrNot(n);
    }

    /**
     * 2 3 正常就不用 cut
     * @return
     */
    private int cuttingRopeOrNot(int n) {
        if (n <= 4) {
            return n;
        }
        return 3 * cuttingRopeOrNot(n-3);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.cuttingRope(2);
        System.out.println(res);
        Assert.assertEquals(1, res);

        res = solution.cuttingRope(10);
        System.out.println(res);
        Assert.assertEquals(36, res);

        res = solution.cuttingRope(5);
        System.out.println(res);
        Assert.assertEquals(6, res);
    }

}
