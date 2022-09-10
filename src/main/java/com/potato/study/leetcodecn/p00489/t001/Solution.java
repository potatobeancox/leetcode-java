package com.potato.study.leetcodecn.p00489.t001;

import java.util.HashSet;
import java.util.Set;

/**
 * 489. 扫地机器人
 *
 * 房间（用格栅表示）中有一个扫地机器人。格栅中的每一个格子有空和障碍物两种可能。
 *
 * 扫地机器人提供4个API，可以向前进，向左转或者向右转。每次转弯90度。
 *
 * 当扫地机器人试图进入障碍物格子时，它的碰撞传感器会探测出障碍物，使它停留在原地。
 *
 * 请利用提供的4个API编写让机器人清理整个房间的算法。
 *
 * interface Robot {
 *   // 若下一个方格为空，则返回true，并移动至该方格
 *   // 若下一个方格为障碍物，则返回false，并停留在原地
 *   boolean move();
 *
 *   // 在调用turnLeft/turnRight后机器人会停留在原位置
 *   // 每次转弯90度
 *   void turnLeft();
 *   void turnRight();
 *
 *   // 清理所在方格
 *   void clean();
 * }
 * 示例:
 *
 * 输入:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * 解析:
 * 房间格栅用0或1填充。0表示障碍物，1表示可以通过。
 * 机器人从row=1，col=3的初始位置出发。在左上角的一行以下，三列以右。
 * 注意:
 *
 * 输入只用于初始化房间和机器人的位置。你需要“盲解”这个问题。换而言之，你必须在对房间和机器人位置一无所知的情况下，只使用4个给出的API解决问题。 
 * 扫地机器人的初始位置一定是空地。
 * 扫地机器人的初始方向向上。
 * 所有可抵达的格子都是相连的，亦即所有标记为1的格子机器人都可以抵达。
 * 可以假定格栅的四周都被墙包围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/robot-room-cleaner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private Set<Integer> set;

    private int[][] dir;

    private int mod;

    private Robot robot;

    /**
     * https://leetcode.cn/problems/robot-room-cleaner/solution/489-jin-huan-tui-3-ge-zi-gao-ding-ji-qi-irrsj/
     * @param robot
     */
    public void cleanRoom(Robot robot) {
        this.set = new HashSet<>();
        this.mod = 100000;
        // 枚举4个方向 往一个方向一直走 如果可以的话 递归走
        this.dir = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        this.robot = robot;
        dfs(0, 0, 0);
    }

    /**
     *
     * @param i
     * @param j
     */
    private void dfs(int i, int j, int dirIndex) {
        set.add(i * mod + j);
        robot.clean();
        // 走到头换方向
        for (int k = 0; k < 4; k++) {
            // 计算方向
            int di = i + dir[dirIndex][0];
            int dj = j + dir[dirIndex][1];

            // 按照当前di dj 看看能不能走
            if (!set.contains(di * mod + dj) && robot.move()) {
                // 往前走 并走回来
                dfs(di, dj, dirIndex);

                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            // 看看 还能不能走了
            robot.turnLeft();
            dirIndex = (dirIndex + 1) % 4;
        }
    }

}

interface Robot {
      // Returns true if the cell in front is open and robot moves into the cell.
      // Returns false if the cell in front is blocked and robot stays in the current cell.
      public boolean move();
      // Robot will stay in the same cell after calling turnLeft/turnRight.
      // Each turn will be 90 degrees.
      public void turnLeft();
      public void turnRight();

      // Clean the current cell.
      public void clean();
}