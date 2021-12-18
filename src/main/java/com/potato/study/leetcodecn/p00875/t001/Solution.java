package com.potato.study.leetcodecn.p00875.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 875. 爱吃香蕉的珂珂
 *
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

  

 示例 1：

 输入: piles = [3,6,7,11], H = 8
 输出: 4
 示例 2：

 输入: piles = [30,11,23,4,20], H = 5
 输出: 30
 示例 3：

 输入: piles = [30,11,23,4,20], H = 6
 输出: 23
  

 提示：

 1 <= piles.length <= 10^4
 piles.length <= H <= 10^9
 1 <= piles[i] <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/koko-eating-bananas
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 时间太少
        if (h < piles.length) {
            return -1;
        }
        int max = Arrays.stream(piles).max().getAsInt();
        if (h == piles.length) {
            return max;
        }
        int left = 1;
        int right = max;
        while (left < right) {
            int mid = (left + right) / 2;
            int cost = costHours(mid, piles);
            if (cost > h) {
                // 速度需要提升
                left = mid + 1;
            } else if (cost <= h) {
                right = mid;
            }
        }
        return left;
    }


    /**
     * 以 speed 速度吃完 piles 需要花费的时间
     * @param speed
     * @return
     */
    private int costHours(int speed, int[] piles) {
        int cost = 0;
        for (int pile : piles) {
            cost += (pile / speed);
            if (pile % speed != 0) {
                cost++;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] piles = new int[]{3,6,7,11};
        int h = 8;
        int co = solution.minEatingSpeed(piles, h);
        System.out.println(co);
        Assert.assertEquals(4, co);


        piles = new int[]{312884470};
        h = 968709470;
        co = solution.minEatingSpeed(piles, h);
        System.out.println(co);
//        Assert.assertEquals(4, co);

    }

}
