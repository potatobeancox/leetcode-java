package com.potato.study.leetcodecn.p00846.t001;


import org.junit.Assert;

import java.util.Set;
import java.util.TreeMap;

/**
 * 846. 一手顺子
 *
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。

 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。

  

 示例 1：

 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 输出：true
 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 示例 2：

 输入：hand = [1,2,3,4,5], groupSize = 4
 输出：false
 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
  

 提示：

 1 <= hand.length <= 104
 0 <= hand[i] <= 109
 1 <= groupSize <= hand.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/hand-of-straights
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        // treemap 按照 计算次数，按照 key小到大排序
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : hand) {
            int count = treeMap.getOrDefault(num, 0);
            count++;
            treeMap.put(num, count);
        }
        // 遍历 key 找个数并修改
        Set<Integer> keySet = treeMap.keySet();
        for (int key : keySet) {
            int currentCount = treeMap.getOrDefault(key, 0);
            if (currentCount == 0) {
                continue;
            }

            for (int i = 1; i < groupSize; i++) {
                Integer count = treeMap.getOrDefault(key + i, 0);
                if (count < currentCount) {
                    return false;
                }
                count -= currentCount;
                treeMap.put(key + i, count);
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] hand = new int[]{1,2,3,6,2,3,4,7,8};
        int groupSize = 3;
        boolean nStraightHand = solution.isNStraightHand(hand, groupSize);
        System.out.println(nStraightHand);
        Assert.assertEquals(true, nStraightHand);
    }
}
