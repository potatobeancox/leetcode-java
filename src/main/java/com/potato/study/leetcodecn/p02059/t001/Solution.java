package com.potato.study.leetcodecn.p02059.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 2059. 转化数字的最小运算数
 *
 *
 * 给你一个下标从 0 开始的整数数组 nums ，该数组由 互不相同 的数字组成。另给你两个整数 start 和 goal 。
 *
 * 整数 x 的值最开始设为 start ，你打算执行一些运算使 x 转化为 goal 。你可以对数字 x 重复执行下述运算：
 *
 * 如果 0 <= x <= 1000 ，那么，对于数组中的任一下标 i（0 <= i < nums.length），可以将 x 设为下述任一值：
 *
 * x + nums[i]
 * x - nums[i]
 * x ^ nums[i]（按位异或 XOR）
 * 注意，你可以按任意顺序使用每个 nums[i] 任意次。使 x 越过 0 <= x <= 1000 范围的运算同样可以生效，但该该运算执行后将不能执行其他运算。
 *
 * 返回将 x = start 转化为 goal 的最小操作数；如果无法完成转化，则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,4,12], start = 2, goal = 12
 * 输出：2
 * 解释：
 * 可以按 2 → 14 → 12 的转化路径进行，只需执行下述 2 次运算：
 * - 2 + 12 = 14
 * - 14 - 2 = 12
 * 示例 2：
 *
 * 输入：nums = [3,5,7], start = 0, goal = -4
 * 输出：2
 * 解释：
 * 可以按 0 → 3 → -4 的转化路径进行，只需执行下述 2 次运算：
 * - 0 + 3 = 3
 * - 3 - 7 = -4
 * 注意，最后一步运算使 x 超过范围 0 <= x <= 1000 ，但该运算仍然可以生效。
 * 示例 3：
 *
 * 输入：nums = [2,8,16], start = 0, goal = 1
 * 输出：-1
 * 解释：
 * 无法将 0 转化为 1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -109 <= nums[i], goal <= 109
 * 0 <= start <= 1000
 * start != goal
 * nums 中的所有整数互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-operations-to-convert-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumOperations(int[] nums, int start, int goal) {
        // bfs 从 start 开始 进行每个元素 进行计算使用 visited 计算已经访问过得点 使用 staep 记录
        if (start == goal) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int step = 1;
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (poll < 0 | poll > 1000) {
                    continue;
                }
                // 三种运算 使用 任意数字任意多次
                for (int j = 0; j < nums.length; j++) {
                    int tmp = poll + nums[j];
                    if (!visited.contains(tmp)) {
                        if (tmp == goal) {
                            return step;
                        } else {
                            queue.add(tmp);
                            visited.add(tmp);
                        }
                    }
                    tmp = poll - nums[j];
                    if (!visited.contains(tmp)) {
                        if (tmp == goal) {
                            return step;
                        } else {
                            queue.add(tmp);
                            visited.add(tmp);
                        }
                    }
                    tmp = (poll ^ nums[j]);
                    if (!visited.contains(tmp)) {
                        if (tmp == goal) {
                            return step;
                        } else {
                            queue.add(tmp);
                            visited.add(tmp);
                        }
                    }
                }
            }
            step++;
        }
        // 无法完成转化
        return -1;
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = new int[] {
                2,4,12
        };
        int start = 2;
        int goal = 12;
        int i = solution.minimumOperations(nums, start, goal);
        System.out.println(i);
        Assert.assertEquals(2, i);


        nums = new int[] {
                2,8,16
        };
        start = 0;
        goal = 1;
        i = solution.minimumOperations(nums, start, goal);
        System.out.println(i);
        Assert.assertEquals(-1, i);


        nums = new int[] {
                3,5,7
        };
        start = 0;
        goal = -4;
        i = solution.minimumOperations(nums, start, goal);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }


}
