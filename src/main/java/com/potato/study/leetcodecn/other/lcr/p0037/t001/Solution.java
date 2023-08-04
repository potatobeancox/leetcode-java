package com.potato.study.leetcodecn.other.lcr.p0037.t001;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 037. 小行星碰撞
 *
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 *
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * 示例 4：
 *
 * 输入：asteroids = [-2,-1,1,2]
 * 输出：[-2,-1,1,2]
 * 解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 *  
 *
 * 提示：
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *  
 *
 * 注意：本题与主站 735 题相同： https://leetcode-cn.com/problems/asteroid-collision/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/XagZNi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // ii 037
    public int[] asteroidCollision(int[] asteroids) {
        // 正负表示小行星的移动方向（正表示向右移动，负表示向左移动）
        Deque<Integer> rightTowards = new LinkedList<>();
        Deque<Integer> leftTowards = new LinkedList<>();
        // 从左往右遍历 数组 当前值是往右的 往右边 放入右边的 stack
        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];
            if (asteroid > 0) {
                // 往右移动 之前往右移动的也装不上 往左的也撞不上
                rightTowards.addLast(asteroid);
            } else {
                // 往左移动 能撞上之前往右边的
                boolean isCollision = false;
                while (!rightTowards.isEmpty()) {
                    int rightTowardsOne = rightTowards.pollLast();
                    int coll = rightTowardsOne + asteroid;
                    if (coll == 0) {
                        // 左右都爆炸了
                        isCollision = true;
                        break;
                    } else if (coll > 0) {
                        // asteroid 小 爆炸
                        rightTowards.addLast(rightTowardsOne);
                        isCollision = true;
                        break;
                    }
                }
                if (!isCollision) {
                    leftTowards.addLast(asteroid);
                }
            }
        }
        // 先处理往左的 再处理 往右边的
        int[] result = new int[leftTowards.size() + rightTowards.size()];
        for (int i = 0; i < result.length; i++) {
            if (!leftTowards.isEmpty()) {
                result[i] = leftTowards.pollFirst();
            } else {
                result[i] = rightTowards.pollFirst();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] asteroids = new int[] {
                8,-8
        };
        int[] ints = solution.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(ints));
    }
}
