package com.potato.study.leetcodecn.p02512.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2512. 奖励最顶尖的 K 名学生
 *
 * 给你两个字符串数组 positive_feedback 和 negative_feedback ，分别包含表示正面的和负面的词汇。不会 有单词同时是正面的和负面的。
 *
 * 一开始，每位学生分数为 0 。每个正面的单词会给学生的分数 加 3 分，每个负面的词会给学生的分数 减  1 分。
 *
 * 给你 n 个学生的评语，用一个下标从 0 开始的字符串数组 report 和一个下标从 0 开始的整数数组 student_id 表示，其中 student_id[i] 表示这名学生的 ID ，这名学生的评语是 report[i] 。每名学生的 ID 互不相同。
 *
 * 给你一个整数 k ，请你返回按照得分 从高到低 最顶尖的 k 名学生。如果有多名学生分数相同，ID 越小排名越前。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
 * 输出：[1,2]
 * 解释：
 * 两名学生都有 1 个正面词汇，都得到 3 分，学生 1 的 ID 更小所以排名更前。
 * 示例 2：
 *
 * 输入：positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
 * 输出：[2,1]
 * 解释：
 * - ID 为 1 的学生有 1 个正面词汇和 1 个负面词汇，所以得分为 3-1=2 分。
 * - ID 为 2 的学生有 1 个正面词汇，得分为 3 分。
 * 学生 2 分数更高，所以返回 [2,1] 。
 *  
 *
 * 提示：
 *
 * 1 <= positive_feedback.length, negative_feedback.length <= 104
 * 1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
 * positive_feedback[i] 和 negative_feedback[j] 都只包含小写英文字母。
 * positive_feedback 和 negative_feedback 中不会有相同单词。
 * n == report.length == student_id.length
 * 1 <= n <= 104
 * report[i] 只包含小写英文字母和空格 ' ' 。
 * report[i] 中连续单词之间有单个空格隔开。
 * 1 <= report[i].length <= 100
 * 1 <= student_id[i] <= 109
 * student_id[i] 的值 互不相同 。
 * 1 <= k <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reward-top-k-students
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<Integer> topStudents(String[] positiveFeedback, String[] negativeFeedback,
                                     String[] report, int[] studentId, int k) {
        // 用map 分别计算 positiveFeedback 和 negativeFeedback分数
        Map<String, Integer> positivePointMap = new HashMap<>();
        for (String positiveWord : positiveFeedback) {
            positivePointMap.put(positiveWord, 3);
        }
        Map<String, Integer> negativePointMap = new HashMap<>();
        for (String negativeWord : negativeFeedback) {
            negativePointMap.put(negativeWord, -1);
        }
        // 遍历 report student_id 记录每个id的分数
        int n = report.length;
        Map<Integer, Integer> scoreMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = report[i];
            int id = studentId[i];
            // 计算这个id的分数
            String[] split = s.split(" ");
            int currentScore = 0;
            for (String key : split) {
                currentScore += positivePointMap.getOrDefault(key, 0);
                currentScore += negativePointMap.getOrDefault(key, 0);
            }
            scoreMap.put(id, currentScore);
        }
        // array sort 进行 index 排序
        List<Integer> list = new ArrayList<>(scoreMap.keySet());
        Collections.sort(list, (o1, o2) -> {
            int compare = Integer.compare(scoreMap.get(o2), scoreMap.get(o1));
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(o1, o2);
        });
        return list.subList(0, k);
    }

}
