package com.potato.study.leetcodecn.p02611.t001;

import java.util.PriorityQueue;

/**
 *
 * 2611. 老鼠和奶酪
 *
 * 有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
 *
 * 下标为 i 处的奶酪被吃掉的得分为：
 *
 * 如果第一只老鼠吃掉，则得分为 reward1[i] 。
 * 如果第二只老鼠吃掉，则得分为 reward2[i] 。
 * 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。
 *
 * 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
 * 输出：15
 * 解释：这个例子中，第一只老鼠吃掉第 2 和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
 * 总得分为 4 + 4 + 3 + 4 = 15 。
 * 15 是最高得分。
 * 示例 2：
 *
 * 输入：reward1 = [1,1], reward2 = [1,1], k = 2
 * 输出：2
 * 解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
 * 总得分为 1 + 1 = 2 。
 * 2 是最高得分。
 *  
 *
 * 提示：
 *
 * 1 <= n == reward1.length == reward2.length <= 105
 * 1 <= reward1[i], reward2[i] <= 1000
 * 0 <= k <= n
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/mice-and-cheese
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2611 贪心
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        // 计算 reward1 减去 reward2 按照降序排序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o2[0], o1[0])
        );
        int n = reward1.length;
        for (int i = 0; i < n; i++) {
            priorityQueue.add(new int[] {
                    reward1[i] - reward2[i], i
            });
        }
        // 将大的都给 reward1 k个
        int score = 0;
        for (int i = 0; i < k; i++) {
            int[] poll = priorityQueue.poll();
            score += reward1[poll[1]];
        }
        // 剩余的按照 reward2 计算
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            score += reward2[poll[1]];
        }
        return score;
    }



}
