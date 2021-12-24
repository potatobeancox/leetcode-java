package com.potato.study.leetcodecn.p00475.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 475. 供暖器
 *
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。

 在加热器的加热半径范围内的每个房屋都可以获得供暖。

 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。

 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。

  

 示例 1:

 输入: houses = [1,2,3], heaters = [2]
 输出: 1
 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 示例 2:

 输入: houses = [1,2,3,4], heaters = [1,4]
 输出: 1
 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 示例 3：

 输入：houses = [1,5], heaters = [2]
 输出：3
  

 提示：

 1 <= houses.length, heaters.length <= 3 * 104
 1 <= houses[i], heaters[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/heaters
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findRadius(int[] houses, int[] heaters) {
        // 排序 升序
        Arrays.sort(houses);
        Arrays.sort(heaters);
        // 遍历 houses 找到离他最近 的 heaters 的index 记录 最近距离 和index 对于下个 house 从最近的那个 加热器开始查找
        int maxDistance = 0;
        int heaterIndex = 0;
        for (int i = 0; i < houses.length; i++) {
            // 找到最近的heater
            int minHeaterDis = Math.abs(houses[i] - heaters[heaterIndex]);
            while (heaterIndex + 1 < heaters.length
                    && minHeaterDis >= Math.abs(houses[i] - heaters[heaterIndex + 1])) {
                minHeaterDis = Math.abs(houses[i] - heaters[heaterIndex + 1]);
                heaterIndex++;
            }
            maxDistance = Math.max(minHeaterDis, maxDistance);
        }
        // 比较并 记录 houses 中最大的距离
        return maxDistance;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] houses = new int[] {
                1,2,3
        };
        int[] heaters = new int[] {
                2
        };
        int radius = solution.findRadius(houses, heaters);
        System.out.println(radius);
        Assert.assertEquals(1, radius);
    }

}
