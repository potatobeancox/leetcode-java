package com.potato.study.leetcodecn.p01033.t001;

import org.junit.Assert;


/**
 * 1033. 移动石子直到连续
 *
 * 三枚石子放置在数轴上，位置分别为 a，b，c。

 每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。形式上，假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。那么就可以从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。

 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。

 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]

  

 示例 1：

 输入：a = 1, b = 2, c = 5
 输出：[1, 2]
 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
 示例 2：

 输入：a = 4, b = 3, c = 2
 输出：[0, 0]
 解释：我们无法进行任何移动。
  

 提示：

 1 <= a <= 100
 1 <= b <= 100
 1 <= c <= 100
 a != b, b != c, c != a

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/moving-stones-until-consecutive
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/moving-stones-until-consecutive/solution/java-jian-dan-si-lu-by-wuzhenyu-0bbf/
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] numMovesStones(int a, int b, int c) {
        // 保证 c 》 b 》 a
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b > c) {
            int tmp = b;
            b = c;
            c = tmp;
        }

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        // 最多移动 c-a-2
        int[] res = new int[2];
        res[1] = c - a - 2;
        // 还有种最少情况 ab 后者 ba 中间有一个空位
        if (a + 2 == b || b + 2 == c) {
            res[0] = 1;
            return res;
        }
        // 最少移动 判断 是否挨着 就少移动一个 不挨着就移动
        if (a+1 != b) {
            res[0]++;
        }
        if (b+1 != c) {
            res[0]++;
        }
        return res;
    }
}
