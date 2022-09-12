package com.potato.study.leetcodecn.other.swordoffer2.p0072.t001;

/**
 * 剑指 Offer II 072. 求平方根
 *
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。

 正数的平方根有两个，只输出其中的正数平方根。

 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。

  

 示例 1:

 输入: x = 4
 输出: 2
 示例 2:

 输入: x = 8
 输出: 2
 解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
  

 提示:

 0 <= x <= 231 - 1
  

 注意：本题与主站 69 题相同： https://leetcode-cn.com/problems/sqrtx/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/jJ0w9p
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        long res = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid <= x) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int)res;
    }
}
