package com.potato.study.leetcodecn.p01552.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1552. 两球之间的磁力
 *
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。

 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。

 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。

  

 示例 1：



 输入：position = [1,2,3,4,7], m = 3
 输出：3
 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
 示例 2：

 输入：position = [5,4,3,2,1,1000000000], m = 2
 输出：999999999
 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
  

 提示：

 n == position.length
 2 <= n <= 10^5
 1 <= position[i] <= 10^9
 所有 position 中的整数 互不相同 。
 2 <= m <= position.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/magnetic-force-between-two-balls
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxDistance(int[] position, int m) {
        // 二分查找 1 - 10e9 每次找到 mid 如果mid 可以满足 每 index + mid 都放一个球 记录 并 往大找 否则往小找
        Arrays.sort(position);
        int left = 1;
        int right = 1_000_000_000;
        int ans = 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean res = canMake(position, m, mid);
            if (res) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    /**
     * position 中 按照 最小距离 每 dis 防止 一个求 是否能放下 m个球
     * @param position
     * @param m
     * @param dis
     * @return
     */
    private boolean canMake(int[] position, int m, int dis) {
        int lastIndex = 0;
        int count = 1;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - position[lastIndex] >= dis) {
                count++;
                lastIndex = i;
            }
        }
        return count >= m;
    }
}


