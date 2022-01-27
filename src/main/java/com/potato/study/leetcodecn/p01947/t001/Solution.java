package com.potato.study.leetcodecn.p01947.t001;

import org.junit.Assert;

/**
 * 1947. 最大兼容性评分和
 *
 * 有一份由 n 个问题组成的调查问卷，每个问题的答案要么是 0（no，否），要么是 1（yes，是）。

 这份调查问卷被分发给 m 名学生和 m 名导师，学生和导师的编号都是从 0 到 m - 1 。学生的答案用一个二维整数数组 students 表示，其中 students[i] 是一个整数数组，包含第 i 名学生对调查问卷给出的答案（下标从 0 开始）。导师的答案用一个二维整数数组 mentors 表示，其中 mentors[j] 是一个整数数组，包含第 j 名导师对调查问卷给出的答案（下标从 0 开始）。

 每个学生都会被分配给 一名 导师，而每位导师也会分配到 一名 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。

 例如，学生答案为[1, 0, 1] 而导师答案为 [0, 0, 1] ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。
 请你找出最优的学生与导师的配对方案，以 最大程度上 提高 兼容性评分和 。

 给你 students 和 mentors ，返回可以得到的 最大兼容性评分和 。

  

 示例 1：

 输入：students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
 输出：8
 解释：按下述方式分配学生和导师：
 - 学生 0 分配给导师 2 ，兼容性评分为 3 。
 - 学生 1 分配给导师 0 ，兼容性评分为 2 。
 - 学生 2 分配给导师 1 ，兼容性评分为 3 。
 最大兼容性评分和为 3 + 2 + 3 = 8 。
 示例 2：

 输入：students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
 输出：0
 解释：任意学生与导师配对的兼容性评分都是 0 。
  

 提示：

 m == students.length == mentors.length
 n == students[i].length == mentors[j].length
 1 <= m, n <= 8
 students[i][k] 为 0 或 1
 mentors[j][k] 为 0 或 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-compatibility-score-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int maxScore;
    /**
     * https://leetcode-cn.com/problems/maximum-compatibility-score-sum/solution/jian-dan-yi-dong-dfsji-bai-100-by-merick-zxaz/
     * @param students
     * @param mentors
     * @return
     */
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        this.maxScore = 0;
        // 将同学和老师 分表换成数字 数组 二维
        int len = students.length;
        // 每个学生 分配到每个老师的得分
        int[][] score = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                score[i][j] = getScore(students[i], mentors[j]);
            }
        }
        // flag 记录当前 老师使用情况 记录 dfs 对于每个学生选择一个老师 求最大值
        boolean[] visitMentor = new boolean[len];
        int currentSum = 0;
        dfs(score, visitMentor, 0, currentSum);
        return maxScore;
    }

    /**
     *
     * @param score         得分矩阵
     * @param visitMentor   memter 是否被使用标识
     * @param studentIndex  当前分配到哪个学生
     * @param currentSum    当前的和
     */
    private void dfs(int[][] score, boolean[] visitMentor, int studentIndex, int currentSum) {
        // 终止条件 当前 学生分配晚
        int len = visitMentor.length;
        if (studentIndex == len) {
            this.maxScore = Math.max(maxScore, currentSum);
            return;
        }
        // 对 studentIndex 选择各种方式分配一个老师
        for (int i = 0; i < len; i++) {
            // 当前老师已经被分配了
            if (visitMentor[i]) {
                continue;
            }
            // 计算这种分配方式得分
            int nextScoreSum = currentSum + score[studentIndex][i];
            // 老师被分配
            visitMentor[i] = true;
            dfs(score, visitMentor, studentIndex + 1, nextScoreSum);
            visitMentor[i] = false;
        }
    }

    /**
     * 计算 student 和 mentor获得的匹配得分
     * @param student
     * @param mentor
     * @return
     */
    private int getScore(int[] student, int[] mentor) {
        int len = student.length;
        int score = 0;
        for (int i = 0; i < len; i++) {
            if (student[i] == mentor[i]) {
                score++;
            }
        }
        return score;
    }

}
