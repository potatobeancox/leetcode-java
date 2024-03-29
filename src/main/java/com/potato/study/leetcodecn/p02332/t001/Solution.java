package com.potato.study.leetcodecn.p02332.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2332. 坐上公交的最晚时间
 *
 * 给你一个下标从 0 开始长度为 n 的整数数组 buses ，其中 buses[i] 表示第 i 辆公交车的出发时间。同时给你一个下标从 0 开始长度为
 * m 的整数数组 passengers ，其中 passengers[j] 表示第 j 位乘客的到达时间。所有公交车出发的时间互不相同，所有乘客到达的时间也互不相同。
 *
 * 给你一个整数 capacity ，表示每辆公交车 最多 能容纳的乘客数目。
 *
 * 每位乘客都会搭乘下一辆有座位的公交车。如果你在 y 时刻到达，公交在 x 时刻出发，满足 y <= x  且公交没有满，那么你可以搭乘这一辆公交。最早 到达的乘客优先上车。
 *
 * 返回你可以搭乘公交车的最晚到达公交站时间。你 不能 跟别的乘客同时刻到达。
 *
 * 注意：数组 buses 和 passengers 不一定是有序的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：buses = [10,20], passengers = [2,17,18,19], capacity = 2
 * 输出：16
 * 解释：
 * 第 1 辆公交车载着第 1 位乘客。
 * 第 2 辆公交车载着你和第 2 位乘客。
 * 注意你不能跟其他乘客同一时间到达，所以你必须在第二位乘客之前到达。
 * 示例 2：
 *
 * 输入：buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
 * 输出：20
 * 解释：
 * 第 1 辆公交车载着第 4 位乘客。
 * 第 2 辆公交车载着第 6 位和第 2 位乘客。
 * 第 3 辆公交车载着第 1 位乘客和你。
 *  
 *
 * 提示：
 *
 * n == buses.length
 * m == passengers.length
 * 1 <= n, m, capacity <= 105
 * 2 <= buses[i], passengers[i] <= 109
 * buses 中的元素 互不相同 。
 * passengers 中的元素 互不相同 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-latest-time-to-catch-a-bus
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        // 2332 找到最后一辆车的最后一个位置 往前一直找不在 passengers 里边的时间点
        Arrays.sort(buses);
        Arrays.sort(passengers);
        // 存储已经有乘客的点
        Set<Integer> passengerTimeSet = new HashSet<>();
        for (int pass : passengers) {
            passengerTimeSet.add(pass);
        }
        int passengerIndex = 0;
        // 遍历 bus 时间点 找到 之前 的 pass
        boolean isLastFull = false;
        int lastPassIndex = -1;
        for (int i = 0; i < buses.length; i++) {
            int departureTime = buses[i];
            int passNum = 0;
            while (passengerIndex < passengers.length
                    && passNum < capacity
                    && passengers[passengerIndex] <= departureTime) {
                passNum++;
                // 最后一个乘客
                lastPassIndex = passengerIndex;
                passengerIndex++;
            }
            if (i == buses.length - 1 && passNum == capacity) {
                isLastFull = true;
            }
        }
        // 最后一趟车还有座位 且最后到的那个人在发车之前到了
        if (!isLastFull && (lastPassIndex == -1 || passengers[lastPassIndex] < buses[buses.length-1])) {
            return buses[buses.length-1];
        }
        // 最后一趟没有座位了 只能顶一个人 最后的人的时间 往前找一个
        int time = passengers[lastPassIndex];
        // 从time开始枚举 找到嗲一个没有的时间
        for (int i = time; i >= 0; i--) {
            boolean isSame = false;
            while (lastPassIndex >= 0 && i == passengers[lastPassIndex]) {
                lastPassIndex--;
                isSame = true;
            }
            if (!isSame) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] buses = new int[] {
                20,30,10
        };
        int[] passengers = new int[] {
                19,13,26,4,25,11,21
        };
        int capacity = 2;
        int i = solution.latestTimeCatchTheBus(buses, passengers, capacity);
        System.out.println(i);
        Assert.assertEquals(20, i);


        buses = new int[] {
                3
        };
        passengers = new int[] {
                4
        };
        capacity = 1;
        i = solution.latestTimeCatchTheBus(buses, passengers, capacity);
        System.out.println(i);
//        Assert.assertEquals(20, i);
    }

}
