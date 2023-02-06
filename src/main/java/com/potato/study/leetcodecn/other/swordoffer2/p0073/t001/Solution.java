package com.potato.study.leetcodecn.other.swordoffer2.p0073.t001;

/**
 * 剑指 Offer II 073. 狒狒吃香蕉
 *
 * 狒狒喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。

 狒狒可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。  

 狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。

  

 示例 1：

 输入：piles = [3,6,7,11], h = 8
 输出：4
 示例 2：

 输入：piles = [30,11,23,4,20], h = 5
 输出：30
 示例 3：

 输入：piles = [30,11,23,4,20], h = 6
 输出：23
  

 提示：

 1 <= piles.length <= 104
 piles.length <= h <= 109
 1 <= piles[i] <= 109
  

 注意：本题与主站 875 题相同： https://leetcode-cn.com/problems/koko-eating-bananas/



 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/nZZqjQ
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // ii 073
    public int minEatingSpeed(int[] piles, int h) {
        // 二分法 记录 以x速度吃香蕉是否能在 h小时内吃完
        int left = 1;
        int max = piles[0];
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        int right = max;
        int speed = -1;
        while (left <= right) {
            int mid = (left + right) /2;
            if (eatCost(mid, piles) > h) {
                // 吃的太慢
                left = mid + 1;
            } else {
                // 吃完了
                speed = mid;
                right = mid - 1;
            }
        }
        return speed;
    }

    /**
     * 狒狒每小时吃 s 个香蕉
     * 吃完 piles需要多久
     * @param s
     * @param piles
     * @return
     */
    private int eatCost(int s, int[] piles) {
        int time = 0;
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] % s == 0) {
                time += piles[i] / s;
            } else {
                time += piles[i] / s + 1;
            }
        }
        return time;
    }

}
