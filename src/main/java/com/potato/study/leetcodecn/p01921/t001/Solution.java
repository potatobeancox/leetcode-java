package com.potato.study.leetcodecn.p01921.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 1921. 消灭怪物的最大数量
 *
 * 你正在玩一款电子游戏，在游戏中你需要保护城市免受怪物侵袭。给你一个 下标从 0 开始 且长度为 n 的整数数组 dist ，其中 dist[i] 是第 i 个怪物与城市的 初始距离（单位：米）。
 *
 * 怪物以 恒定 的速度走向城市。给你一个长度为 n 的整数数组 speed 表示每个怪物的速度，其中 speed[i] 是第 i 个怪物的速度（单位：米/分）。
 *
 * 怪物从 第 0 分钟 时开始移动。你有一把武器，并可以 选择 在每一分钟的开始时使用，包括第 0 分钟。但是你无法在一分钟的中间使用武器。这种武器威力惊人，一次可以消灭任一还活着的怪物。
 *
 * 一旦任一怪物到达城市，你就输掉了这场游戏。如果某个怪物 恰 在某一分钟开始时到达城市，这会被视为 输掉 游戏，在你可以使用武器之前，游戏就会结束。
 *
 * 返回在你输掉游戏前可以消灭的怪物的 最大 数量。如果你可以在所有怪物到达城市前将它们全部消灭，返回  n 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：dist = [1,3,4], speed = [1,1,1]
 * 输出：3
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [1,3,4]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,2,3]，你没有消灭任何怪物。
 * 第 2 分钟开始时，怪物的距离是 [X,1,2]，你消灭了第二个怪物。
 * 第 3 分钟开始时，怪物的距离是 [X,X,1]，你消灭了第三个怪物。
 * 所有 3 个怪物都可以被消灭。
 * 示例 2：
 *
 * 输入：dist = [1,1,2,3], speed = [1,1,1,1]
 * 输出：1
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [1,1,2,3]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,0,1,2]，你输掉了游戏。
 * 你只能消灭 1 个怪物。
 * 示例 3：
 *
 * 输入：dist = [3,2,4], speed = [5,3,2]
 * 输出：1
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [3,2,4]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,0,2]，你输掉了游戏。
 * 你只能消灭 1 个怪物。
 *  
 *
 * 提示：
 *
 * n == dist.length == speed.length
 * 1 <= n <= 105
 * 1 <= dist[i], speed[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/eliminate-maximum-number-of-monsters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1921
    public int eliminateMaximum(int[] dist, int[] speed) {
        // 直接计算 到达时间 t 排序第一个相等的点
        int n = dist.length;
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = dist[i] / speed[i];
            if (dist[i] % speed[i] != 0) {
                time[i]++;
            }
        }
        Arrays.sort(time);
        // 遍历 找到第一个 不相同的数字
        int count = 1;
        for (int i = 1; i < n; i++) {
            // 一样的 直接 返回
            if (time[i] < i + 1) {
                break;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] dist = LeetcodeInputUtils.inputString2IntArray("[1,1,2,3]");
        int[] speed = LeetcodeInputUtils.inputString2IntArray("[1,1,1,1]");
        int i = solution.eliminateMaximum(dist, speed);
        System.out.println(i);
        Assert.assertEquals(1, i);


        dist = LeetcodeInputUtils.inputString2IntArray("[3,5,7,4,5]");
        speed = LeetcodeInputUtils.inputString2IntArray("[2,3,6,3,2]");
        i = solution.eliminateMaximum(dist, speed);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}
