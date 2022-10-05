package com.potato.study.leetcodecn.p02427.t001;

/**
 * 2427. 公因子的数目
 *
 * 给你两个正整数 a 和 b ，返回 a 和 b 的 公 因子的数目。
 *
 * 如果 x 可以同时整除 a 和 b ，则认为 x 是 a 和 b 的一个 公因子 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = 12, b = 6
 * 输出：4
 * 解释：12 和 6 的公因子是 1、2、3、6 。
 * 示例 2：
 *
 * 输入：a = 25, b = 30
 * 输出：2
 * 解释：25 和 30 的公因子是 1、5 。
 *  
 *
 * 提示：
 *
 * 1 <= a, b <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-common-factors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int commonFactors(int a, int b) {
        int min = Math.min(a, b);
        int count = 1;
        for (int i = 2; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                count++;
            }
        }
        return count;
    }

}
