package com.potato.study.leetcodecn.p02391.t001;

import org.junit.Assert;

/**
 * 2391. 收集垃圾的最少总时间
 *
 * 给你一个下标从 0 开始的字符串数组 garbage ，其中 garbage[i] 表示第 i 个房子的垃圾集合。garbage[i] 只包含字符 'M' ，'P' 和 'G' ，但可能包含多个相同字符，每个字符分别表示一单位的金属、纸和玻璃。垃圾车收拾 一 单位的任何一种垃圾都需要花费 1 分钟。
 *
 * 同时给你一个下标从 0 开始的整数数组 travel ，其中 travel[i] 是垃圾车从房子 i 行驶到房子 i + 1 需要的分钟数。
 *
 * 城市里总共有三辆垃圾车，分别收拾三种垃圾。每辆垃圾车都从房子 0 出发，按顺序 到达每一栋房子。但它们 不是必须 到达所有的房子。
 *
 * 任何时刻只有 一辆 垃圾车处在使用状态。当一辆垃圾车在行驶或者收拾垃圾的时候，另外两辆车 不能 做任何事情。
 *
 * 请你返回收拾完所有垃圾需要花费的 最少 总分钟数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：garbage = ["G","P","GP","GG"], travel = [2,4,3]
 * 输出：21
 * 解释：
 * 收拾纸的垃圾车：
 * 1. 从房子 0 行驶到房子 1
 * 2. 收拾房子 1 的纸垃圾
 * 3. 从房子 1 行驶到房子 2
 * 4. 收拾房子 2 的纸垃圾
 * 收拾纸的垃圾车总共花费 8 分钟收拾完所有的纸垃圾。
 * 收拾玻璃的垃圾车：
 * 1. 收拾房子 0 的玻璃垃圾
 * 2. 从房子 0 行驶到房子 1
 * 3. 从房子 1 行驶到房子 2
 * 4. 收拾房子 2 的玻璃垃圾
 * 5. 从房子 2 行驶到房子 3
 * 6. 收拾房子 3 的玻璃垃圾
 * 收拾玻璃的垃圾车总共花费 13 分钟收拾完所有的玻璃垃圾。
 * 由于没有金属垃圾，收拾金属的垃圾车不需要花费任何时间。
 * 所以总共花费 8 + 13 = 21 分钟收拾完所有垃圾。
 * 示例 2：
 *
 * 输入：garbage = ["MMM","PGM","GP"], travel = [3,10]
 * 输出：37
 * 解释：
 * 收拾金属的垃圾车花费 7 分钟收拾完所有的金属垃圾。
 * 收拾纸的垃圾车花费 15 分钟收拾完所有的纸垃圾。
 * 收拾玻璃的垃圾车花费 15 分钟收拾完所有的玻璃垃圾。
 * 总共花费 7 + 15 + 15 = 37 分钟收拾完所有的垃圾。
 *  
 *
 * 提示：
 *
 * 2 <= garbage.length <= 105
 * garbage[i] 只包含字母 'M' ，'P' 和 'G' 。
 * 1 <= garbage[i].length <= 10
 * travel.length == garbage.length - 1
 * 1 <= travel[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-amount-of-time-to-collect-garbage
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int garbageCollection(String[] garbage, int[] travel) {
        // 计算前缀和 到每个房子距离
        int n = garbage.length;
        int[] prefixSum = new int[n];
        // 统计最后出现的 mpg的房子index 计算路程
        int lastIndexM = 0;
        int lastIndexP = 0;
        int lastIndexG = 0;
        // 统计过程中的 mpg 数量 统计 需要多少单位 进行收集 直接记录总和就行
        int totalGarbage = garbage[0].length();
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i-1] + travel[i-1];
            totalGarbage += garbage[i].length();

            String currentGarbage = garbage[i];
            if (currentGarbage.contains("M")) {
                lastIndexM = i;
            }
            if (currentGarbage.contains("P")) {
                lastIndexP = i;
            }
            if (currentGarbage.contains("G")) {
                lastIndexG = i;
            }
        }
        // 计算总话费
        int costTime = totalGarbage + prefixSum[lastIndexM] + prefixSum[lastIndexP] + prefixSum[lastIndexG];
        return costTime;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] garbage = new String[] {
                "MMM","PGM","GP"
        };
        int[] travel = new int[] {
                3,10
        };
        // 8 的收集划划分 gp 要走到头 13 * 2=26 m 3
        int i = solution.garbageCollection(garbage, travel);
        System.out.println(i);
        Assert.assertEquals(37, i);
    }

}
