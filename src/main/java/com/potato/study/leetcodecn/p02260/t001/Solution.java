package com.potato.study.leetcodecn.p02260.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 2260. 必须拿起的最小连续卡牌数
 *
 * 给你一个整数数组 cards ，其中 cards[i] 表示第 i 张卡牌的 值 。如果两张卡牌的值相同，则认为这一对卡牌 匹配 。

 返回你必须拿起的最小连续卡牌数，以使在拿起的卡牌中有一对匹配的卡牌。如果无法得到一对匹配的卡牌，返回 -1 。

  

 示例 1：

 输入：cards = [3,4,2,3,4,7]
 输出：4
 解释：拿起卡牌 [3,4,2,3] 将会包含一对值为 3 的匹配卡牌。注意，拿起 [4,2,3,4] 也是最优方案。
 示例 2：

 输入：cards = [1,0,5,3]
 输出：-1
 解释：无法找出含一对匹配卡牌的一组连续卡牌。
  

 提示：

 1 <= cards.length <= 105
 0 <= cards[i] <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-consecutive-cards-to-pick-up
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumCardPickup(int[] cards) {
        // 遍历 cards 记录每个卡片 最后一次出现的index
        Map<Integer, Integer> lastAppearMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            if (lastAppearMap.containsKey(cards[i])) {
                // 如果出现过 计算最小差值 i - pre + 1
                int count = i - lastAppearMap.get(cards[i]) + 1;
                min = Math.min(min, count);
            }
            lastAppearMap.put(cards[i], i);
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }
}
