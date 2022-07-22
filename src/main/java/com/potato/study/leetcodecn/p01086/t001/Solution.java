package com.potato.study.leetcodecn.p01086.t001;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 1086. 前五科的均分
 *
 * 给你一个不同学生的分数列表 items，其中 items[i] = [IDi, scorei] 表示 IDi 的学生的一科分数，你需要计算每个学生 最高的五科 成绩的 平均分。
 *
 * 返回答案 result 以数对数组形式给出，其中 result[j] = [IDj, topFiveAveragej] 表示 IDj 的学生和他 最高的五科 成绩的 平均分。result 需要按 IDj  递增的 顺序排列 。
 *
 * 学生 最高的五科 成绩的 平均分 的计算方法是将最高的五科分数相加，然后用 整数除法 除以 5 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * 输出：[[1,87],[2,88]]
 * 解释：
 * ID = 1 的学生分数为 91、92、60、65、87 和 100 。前五科的平均分 (100 + 92 + 91 + 87 + 65) / 5 = 87
 * ID = 2 的学生分数为 93、97、77、100 和 76 。前五科的平均分 (100 + 97 + 93 + 77 + 76) / 5 = 88.6，但是由于使用整数除法，结果转换为 88
 * 示例 2：
 *
 * 输入：items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
 * 输出：[[1,100],[7,100]]
 *  
 *
 * 提示：
 *
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * 1 <= IDi <= 1000
 * 0 <= scorei <= 100
 * 对于每个 IDi，至少 存在五个分数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/high-five
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> id2ScoreMap = new TreeMap<>();
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            // 小根堆
            PriorityQueue<Integer> priorityQueue =
                    id2ScoreMap.getOrDefault(id, new PriorityQueue<>());
            if (priorityQueue.isEmpty() || priorityQueue.size() < 5) {
                priorityQueue.add(score);
            } else {
                if (score > priorityQueue.peek()) {
                    priorityQueue.poll();
                    priorityQueue.add(score);
                }
            }
            id2ScoreMap.put(id, priorityQueue);
        }
        int n = id2ScoreMap.size();
        int[][] ave = new int[n][2];
        int index = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : id2ScoreMap.entrySet()) {
            int id = entry.getKey();
            ave[index][0] = id;
            int sum = 0;
            PriorityQueue<Integer> scorePriorityQueue = entry.getValue();
            int size = scorePriorityQueue.size();
            while (!scorePriorityQueue.isEmpty()) {
                sum += scorePriorityQueue.poll();
            }
            ave[index][1] = sum / size;
            index++;
        }
        return ave;
    }
}
