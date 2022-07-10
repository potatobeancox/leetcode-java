package com.potato.study.leetcodecn.other.swordoffer.p0060.p1.t001;


import java.util.*;

/**
 * 剑指 Offer 60. n个骰子的点数
 *
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

  

 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

  

 示例 1:

 输入: 1
 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 示例 2:

 输入: 2
 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
  

 限制：

 1 <= n <= 11

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double[] dicesProbability(int n) {
        // dp ij 用了 i个筛子 弄出来 j的概率
        Map<Integer, Double>[] probability = new Map[n+1];
        // 1.初始化 probability【i】 （1-6） 都是 1/ 6
        for (int i = 0; i <= n; i++) {
            probability[i] = new HashMap<>();
        }
        // i == 1
        for (int i = 1; i <= 6; i++) {
            probability[1].put(i, 1.0 / 6);
        }
        // 2.从2开始 生成可能 的值  probability[i].get j = 1/6 * sum (j-1, j-6)
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                double pro = 0;
                for (int k = 1; k <= 6; k++) {
                    pro += probability[i-1].getOrDefault(j-k, 0.0);
                }
                pro /= 6;
                probability[i].put(j, pro);
            }
        }
        // 返回结果
        double[] result = new double[probability[n].size()];
        for (int i = n; i <= 6 * n; i++) {
            result[i - n] = probability[n].get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        double[] doubles = solution.dicesProbability(1);
        System.out.println(Arrays.toString(doubles));


        doubles = solution.dicesProbability(2);
        System.out.println(Arrays.toString(doubles));
    }


}
