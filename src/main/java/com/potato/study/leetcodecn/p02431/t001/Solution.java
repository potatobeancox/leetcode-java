package com.potato.study.leetcodecn.p02431.t001;

import org.junit.Assert;

/**
 * 2431. Maximize Total Tastiness of Purchased Fruits
 *
 * You are given two non-negative integer arrays price and tastiness, both arrays have the same length n. You are also given two non-negative integers maxAmount and maxCoupons.

 For every integer i in range [0, n - 1]:

 price[i] describes the price of ith fruit.
 tastiness[i] describes the tastiness of ith fruit.
 You want to purchase some fruits such that total tastiness is maximized and the total price does not exceed maxAmount.

 Additionally, you can use a coupon to purchase fruit for half of its price (rounded down to the closest integer). You can use at most maxCoupons of such coupons.

 Return the maximum total tastiness that can be purchased.

 Note that:

 You can purchase each fruit at most once.
 You can use coupons on some fruit at most once.
  

 Example 1:

 Input: price = [10,20,20], tastiness = [5,8,8], maxAmount = 20, maxCoupons = 1
 Output: 13
 Explanation: It is possible to make total tastiness 13 in following way:
 - Buy first fruit without coupon, so that total price = 0 + 10 and total tastiness = 0 + 5.
 - Buy second fruit with coupon, so that total price = 10 + 10 and total tastiness = 5 + 8.
 - Do not buy third fruit, so that total price = 20 and total tastiness = 13.
 It can be proven that 13 is the maximum total tastiness that can be obtained.
 Example 2:

 Input: price = [10,15,7], tastiness = [5,8,20], maxAmount = 10, maxCoupons = 2
 Output: 28
 Explanation: It is possible to make total tastiness 20 in following way:
 - Do not buy first fruit, so that total price = 0 and total tastiness = 0.
 - Buy second fruit with coupon, so that total price = 0 + 7 and total tastiness = 0 + 8.
 - Buy third fruit with coupon, so that total price = 7 + 3 and total tastiness = 8 + 20.
 It can be proven that 28 is the maximum total tastiness that can be obtained.
  

 Constraints:

 n == price.length == tastiness.length
 1 <= n <= 100
 0 <= price[i], tastiness[i], maxAmount <= 1000
 0 <= maxCoupons <= 5

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximize-total-tastiness-of-purchased-fruits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxTastiness(int[] price, int[] tastiness,
                            int maxAmount, int maxCoupons) {
        // 1.dp ij 使用了  元 用了 j 能够获得的最大 tastiness
        int[][] dp = new int[maxAmount+1][maxCoupons+1];
        // dp 0 0 = 0
        dp[0][0] = 0;
        // 2.遍历每一种水果 都有买 不买 用券买
        for (int i = 0; i < price.length; i++) {
            // 遍历没一种 ij情况 求用了这个水果之后的情况
            int[][] temp = new int[maxAmount+1][maxCoupons+1];
            for (int j = 0; j < maxAmount + 1; j++) {
                for (int k = 0; k < maxCoupons + 1; k++) {
                    // 不买的时候
                    temp[j][k] = Math.max(temp[j][k], dp[j][k]);
                    // 买了这个水果 不用优惠券
                    if (j + price[i] <= maxAmount) {
                        temp[j+price[i]][k] = Math.max(temp[j+price[i]][k], dp[j][k]+tastiness[i]);
                    }
                    // 使用优惠券 买这个水果
                    if (j + price[i] / 2 <= maxAmount
                            && k + 1 <= maxCoupons) {
                        temp[j + price[i]/2][k+1] =
                                Math.max(temp[j + price[i]/2][k+1], dp[j][k]+tastiness[i]);
                    }
                }
            }
            dp = temp;
        }
        // 3. 遍历结果获取最大值
        int maxTaste = 0;
        for (int i = 0; i < maxAmount+1; i++) {
            for (int j = 0; j < maxCoupons+1; j++) {
                maxTaste = Math.max(dp[i][j], maxTaste);
            }
        }
        return maxTaste;
    }

}
