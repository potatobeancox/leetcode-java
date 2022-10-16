package com.potato.study.leetcodecn.p01654.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1654. 到家的最少跳跃次数
 *
 * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。

 跳蚤跳跃的规则如下：

 它可以 往前 跳恰好 a 个位置（即往右跳）。
 它可以 往后 跳恰好 b 个位置（即往左跳）。
 它不能 连续 往后跳 2 次。
 它不能跳到任何 forbidden 数组中的位置。
 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。

 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 x 的可行方案，请你返回 -1 。

  

 示例 1：

 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 输出：3
 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
 示例 2：

 输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 输出：-1
 示例 3：

 输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 输出：2
 解释：往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
  

 提示：

 1 <= forbidden.length <= 1000
 1 <= a, b, forbidden[i] <= 2000
 0 <= x <= 2000
 forbidden 中所有位置互不相同。
 位置 x 不在 forbidden 中。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-jumps-to-reach-home
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        // forbidden 变成 set
        Set<Integer> set = new HashSet<>();
        for (int forbid : forbidden) {
            set.add(forbid);
        }
        // 使用visit 记录 已经用过的 位置和步数
        Queue<int[]> queue = new LinkedList<>();
        // 开始位置
        queue.add(new int[] {0, 0});
        int step = 0;
        // 使用bfs 记录 当前【位置，之前跳转次数】 每层就是 跳的步数 按照 8000 作为最大值
        int limit = 8000;
        boolean[][] visit = new boolean[limit+1][2];
        visit[0][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int pos = poll[0];
                if (pos == x) {
                    return step;
                }
                int hasJumpTimes = poll[1];
                // 往前跳转或者往后跳转
                int next = pos + a;
                // 往前走的位置
                if (next <= limit && !visit[next][0] && !set.contains(next)) {
                    visit[next][0] = true;
                    queue.add(new int[]{next, 0});
                }
                // 往后走的位置
                int nextReturn = pos - b;
                if (nextReturn > 0
                        && hasJumpTimes + 1 < 2
                        && !visit[nextReturn][hasJumpTimes+1]
                        && !set.contains(nextReturn)) {
                    visit[nextReturn][hasJumpTimes+1] = true;
                    queue.add(new int[]{nextReturn, hasJumpTimes+1});
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] forbidden = LeetcodeInputUtils.inputString2IntArray("[14,4,18,1,15]");
        int a = 3;
        int b = 15;
        int x = 9;
        int i = solution.minimumJumps(forbidden, a, b, x);
        System.out.println(i);
        Assert.assertEquals(3, i);


        forbidden = LeetcodeInputUtils.inputString2IntArray("[8,3,16,6,12,20]");
        a = 15;
        b = 13;
        x = 11;
        i = solution.minimumJumps(forbidden, a, b, x);
        System.out.println(i);
        Assert.assertEquals(-1, i);


        forbidden = LeetcodeInputUtils.inputString2IntArray("[162,118,178,152,167,100,40,74,199,186,26,73,200,127,30,124,193,84,184,36,103,149,153,9,54,154,133,95,45,198,79,157,64,122,59,71,48,177,82,35,14,176,16,108,111,6,168,31,134,164,136,72,98]");
        a = 29;
        b = 98;
        x = 80;
        i = solution.minimumJumps(forbidden, a, b, x);
        System.out.println(i);
        Assert.assertEquals(121, i);
    }

}
