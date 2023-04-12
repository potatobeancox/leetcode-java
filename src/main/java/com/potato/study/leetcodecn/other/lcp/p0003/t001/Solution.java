package com.potato.study.leetcodecn.other.lcp.p0003.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LCP 03. 机器人大冒险
 *
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：

 U: 向y轴正方向移动一格
 R: 向x轴正方向移动一格。
 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。

 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。

  

 示例 1：

 输入：command = "URR", obstacles = [], x = 3, y = 2
 输出：true
 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 示例 2：

 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 输出：false
 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 示例 3：

 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 输出：true
 解释：到达终点后，再碰到障碍物也不影响返回结果。
  

 限制：

 2 <= command的长度 <= 1000
 command由U，R构成，且至少有一个U，至少有一个R
 0 <= x <= 1e9, 0 <= y <= 1e9
 0 <= obstacles的长度 <= 1000
 obstacles[i]不为原点或者终点

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/programmable-robot
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // lcp 03 sb机器人
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        // 遍历 obstacles 任意超过 xy的丢弃 使用set x_y 进行存储
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            if (obstacle[0] > x || obstacle[1] > y) {
                continue;
            }
            obstacleSet.add(getKey(obstacle[0], obstacle[1]));
        }
        // 开始位置就是 结束为止
        if (x == 0 && y == 0) {
            return true;
        }
        // while 循环遍历 commend 知道 当前 ij 任意超过 xy
        int i = 0;
        int j = 0;
        int index = 0;
        // 走的过程中 判断走到的点是不是已经哦等到 了 障碍 或者已经走到了终点
        while (i <= x && j <= y) {
            // 往下走一走
            char c = command.charAt(index);
            // U: 向y轴正方向移动一格
            // R: 向x轴正方向移动一格。
            if (c == 'U') {
                // y
                j++;
            } else {
                // c == 'R' x
                i++;
            }
            // 判断当前位置 ij 是不是到了末尾
            if (obstacleSet.contains(getKey(i, j))) {
                return false;
            }
            if (i == x && j == y) {
                return true;
            }
            index++;
            // index 改一下
            if (index == command.length()) {
                index = 0;
            }
        }
        return false;
    }


    private String getKey(int i, int j) {
        return i + "_" + j;
    }



}
