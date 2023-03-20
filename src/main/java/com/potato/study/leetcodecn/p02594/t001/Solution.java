package com.potato.study.leetcodecn.p02594.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2594. 修车的最少时间

 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。

 同时给你一个整数 cars ，表示总共需要修理的汽车数目。

 请你返回修理所有汽车 最少 需要多少时间。

 注意：所有机械工可以同时修理汽车。

  

 示例 1：

 输入：ranks = [4,2,3,1], cars = 10
 输出：16
 解释：
 - 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
 - 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
 - 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
 - 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 16 分钟是修理完所有车需要的最少时间。
 示例 2：

 输入：ranks = [5,1,8], cars = 6
 输出：16
 解释：
 - 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
 - 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 - 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
 16 分钟时修理完所有车需要的最少时间。
  

 提示：

 1 <= ranks.length <= 105
 1 <= ranks[i] <= 100
 1 <= cars <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-time-to-repair-cars
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2594
    public long repairCars(int[] ranks, int cars) {
        // 二分法 给出 time 分钟看看 按照 ranks 能修的车的数量是否大于等于 cars
        long left = 0;
        // 车都给 能力值最小的人修
        long right = Arrays.stream(ranks).max().getAsInt();
        right *= cars;
        right *= cars;
        long target = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            long repairCarCount = getRepairCarCount(ranks, mid);
            if (repairCarCount >= cars) {
                target = mid;
                right = mid - 1;
            } else {
                // 时间不够需要yanc
                left = mid + 1;
            }
        }
        return target;
    }

    /**
     * 每个人花费 times 能修多少车
     * @param ranks
     * @param times
     * @return
     */
    private long getRepairCarCount(int[] ranks, long times) {
        long count = 0;
        for (int r : ranks) {
            double sqrt = Math.sqrt(1.0 * times / r);
            count += (long)(sqrt);
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ranks = new int[] {5,1,8};
        int cars = 6;
        long l = solution.repairCars(ranks, cars);
        System.out.println(l);
        Assert.assertEquals(16, l);
    }

}
