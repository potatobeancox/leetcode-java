package com.potato.study.leetcodecn.p01989.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 1989. 捉迷藏中可捕获的最大人数
 *
 * 你正在和你的朋友玩捉迷藏游戏。在捉迷藏比赛中，人们被分成两组：是 “鬼” 的人，和不是 “鬼” 的人。是 “鬼” 的人想要抓住尽可能多的不是 “鬼” 的人。
 *
 * 给定一个 从 0 开始建立索引 的整数数组 team，其中只包含 0 (表示 不是 “鬼” 的人) 和 1 (表示是 “鬼” 的人)，以及一个整数 dist。索引 i 为 “鬼” 的人可以捕获索引在 [i - dist, i + dist](包括) 范围内且 不是 “鬼” 的任何一个人。
 *
 * 返回 “鬼” 所能捕获的最大人数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: team = [0,1,0,1,0], dist = 3
 * 输出: 2
 * 解释:
 * 在索引 1 的 “鬼” 可以捕获范围 [i-dist, i+dist] = [1-3, 1+3] = [-2, 4] 内的人。
 * 他们可以抓住索引 2 中不是 “鬼” 的人。
 * 在索引 3 的 “鬼” 可以捕获范围 [i-dist, i+dist] = [3-3, 3+3] = [0, 6] 内的人。
 * 他们可以抓住索引 0 中不是 “鬼” 的人。
 * 在索引 4 上不是 “鬼” 的人不会被抓住，因为在索引 1 和 3 上的人已经抓住了一个人。
 * 示例 2:
 *
 * 输入: team = [1], dist = 1
 * 输出: 0
 * 解释:
 * 没有 “鬼" 要抓的人。
 * 示例 3:
 *
 * 输入: team = [0], dist = 1
 * 输出: 0
 * 解释:
 * 没有 “鬼” 来zh
 *  
 *
 * 提示:
 *
 * 1 <= team.length <= 105
 * 0 <= team[i] <= 1
 * 1 <= dist <= team.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-people-that-can-be-caught-in-tag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/maximum-number-of-people-that-can-be-caught-in-tag/solution/java-tan-xin-shuang-duan-dui-lie-by-qing-kex1/
     * @param team
     * @param dist
     * @return
     */
    public int catchMaximumAmountofPeople(int[] team, int dist) {
        // 遍历 往队列中 后面添加 人的index
        Deque<Integer> indexDeque = new LinkedList<>();
        for (int i = 0; i < team.length; i++) {
            if (team[i] == 0) {
                indexDeque.addLast(i);
            }
        }
        int catchCount = 0;
        for (int i = 0; i < team.length; i++) {
            // 遍历每个位置 如果当前 双端队列中 前面的位置 已经距离 当前位置 i 大于 dist 那么循环pop
            while (!indexDeque.isEmpty() && indexDeque.peekFirst() < i - dist) {
                indexDeque.pollFirst();
            }
            // i 找到 当前第一个位置 肯定是厚点 左边的 然后右边的 有的话 po 计数
            if (team[i] == 0) {
                continue;
            }
            if (!indexDeque.isEmpty() && indexDeque.peekFirst() <= i + dist) {
                indexDeque.pollFirst();
                catchCount++;
            }
        }
        return catchCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] team = new int[] {
                0
        };
        int dist = 1;
        int i = solution.catchMaximumAmountofPeople(team, dist);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }



}
