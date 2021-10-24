package com.potato.study.leetcodecn.p01701.t001;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1701. 平均等待时间
 *
 * 有一个餐厅，只有一位厨师。你有一个顾客数组 customers ，其中 customers[i] = [arrivali, timei] ：

 arrivali 是第 i 位顾客到达的时间，到达时间按 非递减 顺序排列。
 timei 是给第 i 位顾客做菜需要的时间。
 当一位顾客到达时，他将他的订单给厨师，厨师一旦空闲的时候就开始做这位顾客的菜。每位顾客会一直等待到厨师完成他的订单。厨师同时只能做一个人的订单。厨师会严格按照 订单给他的顺序 做菜。

 请你返回所有顾客需要等待的 平均 时间。与标准答案误差在 10-5 范围以内，都视为正确结果。

  

 示例 1：

 输入：customers = [[1,2],[2,5],[4,3]]
 输出：5.00000
 解释：
 1) 第一位顾客在时刻 1 到达，厨师拿到他的订单并在时刻 1 立马开始做菜，并在时刻 3 完成，第一位顾客等待时间为 3 - 1 = 2 。
 2) 第二位顾客在时刻 2 到达，厨师在时刻 3 开始为他做菜，并在时刻 8 完成，第二位顾客等待时间为 8 - 2 = 6 。
 3) 第三位顾客在时刻 4 到达，厨师在时刻 8 开始为他做菜，并在时刻 11 完成，第三位顾客等待时间为 11 - 4 = 7 。
 平均等待时间为 (2 + 6 + 7) / 3 = 5 。
 示例 2：

 输入：customers = [[5,2],[5,4],[10,3],[20,1]]
 输出：3.25000
 解释：
 1) 第一位顾客在时刻 5 到达，厨师拿到他的订单并在时刻 5 立马开始做菜，并在时刻 7 完成，第一位顾客等待时间为 7 - 5 = 2 。
 2) 第二位顾客在时刻 5 到达，厨师在时刻 7 开始为他做菜，并在时刻 11 完成，第二位顾客等待时间为 11 - 5 = 6 。
 3) 第三位顾客在时刻 10 到达，厨师在时刻 11 开始为他做菜，并在时刻 14 完成，第三位顾客等待时间为 14 - 10 = 4 。
 4) 第四位顾客在时刻 20 到达，厨师拿到他的订单并在时刻 20 立马开始做菜，并在时刻 21 完成，第四位顾客等待时间为 21 - 20 = 1 。
 平均等待时间为 (2 + 6 + 4 + 1) / 4 = 3.25 。
  

 提示：

 1 <= customers.length <= 105
 1 <= arrivali, timei <= 104
 arrivali <= arrivali+1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/average-waiting-time
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param customers
     * @return
     */
    public double averageWaitingTime(int[][] customers) {
        int cheifTime = 0;
        long totalTime = 0;
        for (int i = 0; i < customers.length; i++) {
            int arrivalTime = customers[i][0];
            int time = customers[i][1];

            // 厨师啥时候能开始做这个
            cheifTime = Math.max(cheifTime, arrivalTime);
            cheifTime += time;
            int waitTime = cheifTime - arrivalTime;
            totalTime += waitTime;
        }
        Double aDouble = new Double(totalTime);
        return aDouble / customers.length;
    }

}
