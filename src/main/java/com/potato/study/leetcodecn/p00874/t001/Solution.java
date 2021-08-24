package com.potato.study.leetcodecn.p00874.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.*;

/**
 * 874. 模拟行走机器人
 *
 * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：

 -2 ：向左转 90 度
 -1 ：向右转 90 度
 1 <= x <= 9 ：向前移动 x 个单位长度
 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。

 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。

 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）

  
 注意：

 北表示 +Y 方向。
 东表示 +X 方向。
 南表示 -Y 方向。
 西表示 -X 方向。
  

 示例 1：

 输入：commands = [4,-1,3], obstacles = []
 输出：25
 解释：
 机器人开始位于 (0, 0)：
 1. 向北移动 4 个单位，到达 (0, 4)
 2. 右转
 3. 向东移动 3 个单位，到达 (3, 4)
 距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
 示例 2：

 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 输出：65
 解释：机器人开始位于 (0, 0)：
 1. 向北移动 4 个单位，到达 (0, 4)
 2. 右转
 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
 4. 左转
 5. 向北走 4 个单位，到达 (1, 8)
 距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65
  

 提示：

 1 <= commands.length <= 104
 commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
 0 <= obstacles.length <= 104
 -3 * 104 <= xi, yi <= 3 * 104
 答案保证小于 231

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/walking-robot-simulation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int robotSim(int[] commands, int[][] obstacles) {
        // 用一个set 标记目前的障碍位置
        Set<String> obstacleSet = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            obstacleSet.add(obstacles[i][0] + "_" + obstacles[i][1]);
        }
        // 方向数字，遍历 commands 并且对每个command执行走的过程 过程中记录最大位置
        int x = 0;
        int y = 0;
        int[][] direction = new int[][] {

                {0, 1}, // north
                {1, 0}, // east
                {0, -1}, // south
                {-1, 0}  // west
//                {-1, 0}, // north
//                {0, 1}, // east
//                {1, 0}, // south
//                {0, -1}  // west
        };
        int maxLen = 0;
        int directionIndex = 0;
        for (int i = 0; i < commands.length; i++) {
            int command = commands[i];
//            、、-2 ：向左转 90 度
            if (command == -2) {
                directionIndex--;
                directionIndex+=4;
                directionIndex %= 4;
//                    -1 ：向右转 90 度
            } else if (command == -1) {
                directionIndex++;
                directionIndex %= 4;
            } else {
                for (int j = 0; j < command; j++) {
                    int dx = x + direction[directionIndex][0];
                    int dy = y + direction[directionIndex][1];
                    if (!obstacleSet.contains(dx + "_" + dy)) {
                        x = dx;
                        y = dy;
                        maxLen = Math.max(x*x + y*y, maxLen);
                    } else {
                        break;
                    }
                }
            }
        }
        return maxLen;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] commands = new int[] {4,-1,4,-2,4};
        int[][] obstacles = new int[][] {
                {2, 4}
        };
        int i = solution.robotSim(commands, obstacles);
        System.out.println(i);
        Assert.assertEquals(65, i);
    }

}
