package com.potato.study.leetcodecn.p02979.t001;


import java.util.Arrays;

/**
 *
 * 2979. 最贵的无法购买的商品
 *
 * 给定两个 不同的质数 primeOne 和 primeTwo。
 *
 * Alice 和 Bob 正在逛市场。该市场有 无数种 商品，对于 任何 正整数 x，都存在一个价格为 x 的物品。Alice 想从市场里买一些东西送给 Bob。她有 无数个 面值为 primeOne 和 primeTwo 的硬币。她想知道她 无法 用她拥有的硬币购买的 最贵 商品的价格。
 *
 * 返回 Alice 无法买给 Bob 的 最贵 商品的价格。
 *
 *
 *
 * 示例 1:
 *
 * 输入：primeOne = 2, primeTwo = 5
 * 输出：3
 * 解释：无法购买的商品的价格有 [1,3]。所有价格大于 3 的商品都可以通过组合使用面额为 2 和 5 的硬币购买。
 * 示例 2:
 *
 * 输入：primeOne = 5, primeTwo = 7
 * 输出：23
 * 解释：无法购买的商品的价格有 [1,2,3,4,6,8,9,11,13,16,18,23]。所有价格大于 23 的商品都可以购买。
 *
 *
 * 提示：
 *
 * 1 < primeOne, primeTwo < 104
 * primeOne, primeTwo 都是质数。
 * primeOne * primeTwo < 105
 *
 */
public class Solution {


    public int mostExpensiveItem(int primeOne, int primeTwo) {
        int prime1 = Math.min(primeOne, primeTwo);
        int prime2 = Math.max(primeOne, primeTwo);

        return prime2 * (prime1 - 1) - prime1;
    }


}
