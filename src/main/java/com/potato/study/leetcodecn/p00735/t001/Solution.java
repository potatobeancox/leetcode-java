package com.potato.study.leetcodecn.p00735.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 735. 行星碰撞
 *
 * 给定一个整数数组 asteroids，表示在同一行的行星。

 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。

 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

  

 示例 1：

 输入：asteroids = [5,10,-5]
 输出：[5,10]
 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 示例 2：

 输入：asteroids = [8,-8]
 输出：[]
 解释：8 和 -8 碰撞后，两者都发生爆炸。
 示例 3：

 输入：asteroids = [10,2,-5]
 输出：[10]
 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 示例 4：

 输入：asteroids = [-2,-1,1,2]
 输出：[-2,-1,1,2]
 解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
  

 提示：

 2 <= asteroids.length <= 104
 -1000 <= asteroids[i] <= 1000
 asteroids[i] != 0


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/asteroid-collision
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        // 正数往右边走 负数往左 用一个 stack 存 往左边走的 行星 然后 如果有往右走的行星 入栈 ，
        Deque<Integer> deque = new LinkedList<>();
        // 如果 栈顶是往右走的 当前是往左的发生膨胀 计算膨胀后的结果
        for (int i = 0; i < asteroids.length ; i++) {
            if (deque.isEmpty() || deque.peekLast() < 0 || asteroids[i] > 0) {
                // 同方向
                deque.add(asteroids[i]);
                continue;
            }
            // 相撞 还能往右走 说明 asteroids 小
            Integer poll = deque.pollLast();
            if (poll + asteroids[i] > 0) {
                // 相撞但是 poll 大
                deque.addLast(poll);
                continue;
            } else if (poll + asteroids[i] == 0) {
                continue;
            }

            while (poll > 0 && poll + asteroids[i] < 0
                    && !deque.isEmpty()) {
                poll = deque.pollLast();
            }
            if (poll < 0) {
                deque.addLast(poll);
                deque.addLast(asteroids[i]);
                continue;
            }

            if (poll + asteroids[i] > 0) {
                deque.addLast(poll);
            } else if (poll + asteroids[i] == 0) {
                continue;
            } else {
                deque.addLast(asteroids[i]);
            }
        }
        // 最后把剩余的按照顺序 出队
        int[] result = new int[deque.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = deque.pollFirst();
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
        Assert.assertArrayEquals(new int[]{
        }, ints);

        asteroids = new int[] {
                -2,-2,1,-2
        };
        ints = solution.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
                -2,-2,-2
        }, ints);

        asteroids = new int[] {
                10,2,-5
        };
        ints = solution.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
            10
        }, ints);
    }
}
