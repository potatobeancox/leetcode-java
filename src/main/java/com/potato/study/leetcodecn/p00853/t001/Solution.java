package com.potato.study.leetcodecn.p00853.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;

/**
 * 853. 车队
 *
 * 在一条单行道上，有 n 辆车开往同一目的地。目的地是几英里以外的 target 。
 *
 * 给定两个整数数组 position 和 speed ，长度都是 n ，其中 position[i] 是第 i 辆车的位置， speed[i] 是第 i 辆车的速度(单位是英里/小时)。
 *
 * 一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车 以相同的速度 紧接着行驶。此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
 *
 * 车队 是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
 *
 * 即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
 *
 * 返回到达目的地的 车队数量 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * 输出：3
 * 解释：
 * 从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
 * 从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
 * 从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
 * 请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
 * 示例 2:
 *
 * 输入: target = 10, position = [3], speed = [3]
 * 输出: 1
 * 解释: 只有一辆车，因此只有一个车队。
 * 示例 3:
 *
 * 输入: target = 100, position = [0,2,4], speed = [4,2,1]
 * 输出: 1
 * 解释:
 * 以0(速度4)和2(速度2)出发的车辆组成车队，在4点相遇。舰队以2的速度前进。
 * 然后，车队(速度2)和以4(速度1)出发的汽车组成一个车队，在6点相遇。舰队以1的速度前进，直到到达目标。
 *  
 *
 * 提示：
 *
 * n == position.length == speed.length
 * 1 <= n <= 105
 * 0 < target <= 106
 * 0 <= position[i] < target
 * position 中每个值都 不同
 * 0 < speed[i] <= 106
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/car-fleet
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/car-fleet/solution/che-dui-by-leetcode/
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet(int target, int[] position, int[] speed) {
        // 记录每个车起始位置和当前位置往重点 数据结构 记录起始位置和 花费时间
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            Car car = new Car();
            car.pos = position[i];
            car.costTime = 1.0 * (target - position[i]) / speed[i];
            carList.add(car);
        }
        // 最开始将车 按照起始位置降序排列
        Collections.sort(carList, new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return Integer.compare(car2.pos, car1.pos);
            }
        });
        // 第一个肯定一个车
        int fleet = 0;
        for (int i = 0; i < carList.size() - 1; i++) {
            Car afterCar = carList.get(i+1);
            Car car = carList.get(i);
            if (afterCar.costTime > car.costTime) {
                // 从前往后遍历 如果当前位置比之前的满花费时间比较 说明出来一个车队
                fleet++;
            } else {
                // 否则就是同一个车队 到达时间为上一个
                afterCar.costTime = car.costTime;
            }
        }
        //最后一个 是否能作为一个车
        return fleet + 1;
    }

    class Car {
        public int pos;
        public double costTime;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 12;
        int[] position = new int[]{
                10,8,0,5,3
        };
        int[] speed = new int[]{
                2,4,1,1,3
        };
        int i = solution.carFleet(target, position, speed);
        System.out.println(i);
        Assert.assertEquals(3, i);


        target = 10;
        position = new int[]{
                3
        };
        speed = new int[]{
                3
        };
        i = solution.carFleet(target, position, speed);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
