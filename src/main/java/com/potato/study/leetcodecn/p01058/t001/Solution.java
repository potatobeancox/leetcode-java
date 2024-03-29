package com.potato.study.leetcodecn.p01058.t001;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1058. 最小化舍入误差以满足目标
 *
 * 给定一系列价格 [p1,p2...,pn] 和一个目标 target，将每个价格 pi 舍入为 Roundi(pi) 以使得舍入数组 [Round1(p1),Round2(p2)...,Roundn(pn)] 之和达到给定的目标值 target。每次舍入操作 Roundi(pi) 可以是向下舍 Floor(pi) 也可以是向上入 Ceil(pi)。

 如果舍入数组之和无论如何都无法达到目标值 target，就返回字符串 "-1"。否则，以保留到小数点后三位的字符串格式返回最小的舍入误差，其定义为 Σ |Roundi(pi) - (pi)|（ i 从 1 到 n ）。

  

 示例 1：

 输入：prices = ["0.700","2.800","4.900"], target = 8
 输出："1.000"
 解释：
 使用 Floor，Ceil 和 Ceil 操作得到 (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 。
 示例 2：

 输入：prices = ["1.500","2.500","3.500"], target = 10
 输出："-1"
 解释：
 达到目标是不可能的。
 示例 3：

 输入：prices = ["1.500","2.500","3.500"], target = 9
 输出："1.500"
  

 提示：

 1 <= prices.length <= 500
 表示价格的每个字符串 prices[i] 都代表一个介于 [0.0, 1000.0] 之间的实数，并且正好有 3 个小数位。
 target 介于 0 和 1000000 之间。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimize-rounding-error-to-meet-target
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String minimizeError(String[] prices, int target) {
        // 先计算base 值 再使用一个 大根堆计算
        int base = 0;
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (String price : prices) {
            String[] split = price.split("\\.");
            // 元 和 分
            int yuan = Integer.parseInt(split[0]);
            base += yuan;
            if (!"000".equals(split[1])) {
                priorityQueue.add(Double.parseDouble("0." + split[1]));
            }
        }
        // 有的可以网上走
        double diff = 0;
        while (!priorityQueue.isEmpty() && base < target) {
            Double poll = priorityQueue.poll();
            diff += (1 - poll);
            base++;
        }

        // 转换成 返回 字符串
        if (base != target) {
            return "-1";
        }
        // 三位输出
        while (!priorityQueue.isEmpty()) {
            Double poll = priorityQueue.poll();
            diff += poll;
        }
        // https://zhuanlan.zhihu.com/p/365669071
        return String.format("%.3f",diff);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] prices = new String[] {
                "0.700","2.800","4.900"
        };
        int target = 8;
        String s = solution.minimizeError(prices, target);
        System.out.println(s);
        Assert.assertEquals("1.000", s);
    }


}
