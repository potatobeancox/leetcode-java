package com.potato.study.leetcodecn.p02069.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2069. 模拟行走机器人 II
 *
 * 给你一个在 XY 平面上的 width x height 的网格图，左下角 的格子为 (0, 0) ，右上角 的格子为 (width - 1, height - 1) 。网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。一个机器人 初始 在格子 (0, 0) ，方向为 "East" 。

 机器人可以根据指令移动指定的 步数 。每一步，它可以执行以下操作。

 沿着当前方向尝试 往前一步 。
 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。
 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。

 请你实现 Robot 类：

 Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 "East" 。
 void move(int num) 给机器人下达前进 num 步的指令。
 int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。
 String getDir() 返回当前机器人的朝向，为 "North" ，"East" ，"South" 或者 "West" 。
  

 示例 1：



 输入：
 ["Robot", "move", "move", "getPos", "getDir", "move", "move", "move", "getPos", "getDir"]
 [[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
 输出：
 [null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]

 解释：
 Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
 robot.move(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
 robot.move(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
 robot.getPos(); // 返回 [4, 0]
 robot.getDir(); // 返回 "East"
 robot.move(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
 // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
 // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
 robot.move(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 北 （不是朝西）。
 robot.move(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
 // 然后，移动 4 步到 (1, 2) ，并朝西。
 robot.getPos(); // 返回 [1, 2]
 robot.getDir(); // 返回 "West"

  

 提示：

 2 <= width, height <= 100
 1 <= num <= 105
 move ，getPos 和 getDir 总共 调用次数不超过 104 次。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/walking-robot-simulation-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Robot {


    private int[][] direction;
    private int i;
    private int j;
    private int width;
    private int height;
    private int directionIndex;

    public Robot(int width, int height) {
        this.direction = new int[][] {
                {0, 1}, // 东
                {1, 0}, // 北
                {0, -1},
                {-1, 0}
        };
        this.i = 0;
        this.j = 0;
        this.width = width;
        this.height = height;
        this.directionIndex = directionIndex;
    }

    // 往当前方向走 num 步骤
    public void step(int num) {
        for (int k = 0; k < num; k++) {
            int di = i + direction[directionIndex][0];
            int dj = j + direction[directionIndex][1];
            // 判断是否需要跟换方向
            if (di < 0 || di >= height
                    || dj < 0 || dj >= width) {
                directionIndex++;
                directionIndex %= 4;
            }
            i += direction[directionIndex][0];
            j += direction[directionIndex][1];
        }
    }

    public int[] getPos() {
        return new int[] {j, i};
    }

    public String getDir() {
        if (directionIndex == 0) {
            return "East";
        } else if (directionIndex == 1) {
            return "North";
        } else if (directionIndex == 2) {
            return "West";
        } else {
            return "South";
        }
    }

    public static void main(String[] args) {
        Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
        robot.step(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
        robot.step(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
        robot.getPos(); // 返回 [4, 0]
        robot.getDir(); // 返回 "East"
        robot.step(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
        // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
        // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
        robot.step(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 北 （不是朝西）。
        robot.step(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
        // 然后，移动 4 步到 (1, 2) ，并朝西。
        robot.getPos(); // 返回 [1, 2]
        robot.getDir(); // 返回 "West"


    }
}
