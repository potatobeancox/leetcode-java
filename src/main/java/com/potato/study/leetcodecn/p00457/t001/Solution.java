package com.potato.study.leetcodecn.p00457.t001;

import java.util.Arrays;
import java.util.TreeMap;

import org.junit.Assert;

/**
 * 457. 环形数组是否存在循环
 *
 * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 *
 * 如果 nums[i] 是正数，向前（下标递增方向）移动 |nums[i]| 步
 * 如果 nums[i] 是负数，向后（下标递减方向）移动 |nums[i]| 步
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 *
 * 数组中的 循环 由长度为 k 的下标序列 seq 标识：
 *
 * 遵循上述移动规则将导致一组重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * k > 1
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * 示例 2：
 *
 * 输入：nums = [-1,2]
 * 输出：false
 * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * 示例 3:
 *
 * 输入：nums = [-2,1,-1,-2,-2]
 * 输出：false
 * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
 * 所有 nums[seq[j]] 应当不是全正就是全负。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 *  
 *
 * 进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/circular-array-loop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 用快慢指针
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        // 从每个位置 开始查找
//        for (int i = 0; i < nums.length; i++) {
//            // 之前访问过
//            if (nums[i] == 0) {
//                continue;
//            }
//            int slowIndex = i;
//            int fastIndex = getNextIndex(slowIndex);
//            while (nums[slowIndex] != 0) {
//                // 如果 slow 与fast相遇
//                if (slowIndex == fastIndex) {
//
//
//                    continue;
//                }
//                // slow 和faset 没有相遇 slow 每次走一步
//                slowIndex = getNextIndex(nums, slowIndex);
//                fastIndex = getNextIndex(nums, fastIndex);
//            }
//
//
//        }
        // 快慢指针找环 快慢指针相遇 说明找到了环，快指针使用 nums = 0 表示已经访问过 没有环




        // 使用 一个 int visit 记录 当前是低级轮遍历的，如果当前轮已经被遍历过 continue 否则 往后遍历至无法继续
//        int[] visit = new int[nums.length];
//        Arrays.fill(visit, -1);
//        // 开始找的位置
//        for (int i = 0; i < nums.length; i++) {
//            // 这个节点被之前遍历过
//            if (visit[i] >= 0) {
//                continue;
//            }
//            int direction = nums[i];
//            // 本次访问了多少个节点
//            int visitedLen = 0;
//            int next = i;
//            int last = -1;
//            boolean isOpposite = false;
//            do {
//                // 遍历这个点
//                last = next;
//                visit[next] = i;
//                visitedLen++;
//                if (nums[next] * direction < 0) {
//                    isOpposite = true;
//                    break;
//                }
//                next += nums[next];
//                while (next < 0) {
//                    next += nums.length;
//                }
//                next %= nums.length;
//                // 没有访问过就继续访问
//            } while (visit[next] < 0);
//            // 出现了方向相反
//            if (isOpposite) {
//                continue;
//            }
//            // 只请求了一个
//            if (visitedLen == 1) {
//                continue;
//            }
//            // 如果无法继续时，方向是走到了 本轮访问的点 说明 有环，如果是其他轮访问的点说明没有换
//            if (visit[next] == i && last != next) {
//                return true;
//            }
//        }
//        return false;
        return false;
    }


    private int getNextIndex(int[] nums, int currentIndex) {
        int nextIndex = currentIndex + nums[currentIndex];
        while (nextIndex < 0) {
            nextIndex += nums.length;
        }
        return nextIndex % nums.length;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                2,-1,1,2,2
        };
        boolean b = solution.circularArrayLoop(arr);
        System.out.println(b);
        Assert.assertEquals(true, b);


        arr = new int[] {
                -1,2
        };
        b = solution.circularArrayLoop(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);

        arr = new int[] {
                -2,1,-1,-2,-2
        };
        b = solution.circularArrayLoop(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);


        arr = new int[] {
                -1,2
        };
        b = solution.circularArrayLoop(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);

        arr = new int[] {
                -1,-2,-3,-4,-5
        };
        b = solution.circularArrayLoop(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);


        arr = new int[] {
                2,2,2,2,2,4,7
        };
        b = solution.circularArrayLoop(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);
    }
}
