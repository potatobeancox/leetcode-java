package com.potato.study.leetcodecn.p02061.t001;


import java.util.HashSet;
import java.util.Set;

/**
 * 2061. 扫地机器人清扫过的空间个数
 *
 * 一个房间用一个从 0 开始索引的二维二进制矩阵 room 表示，其中 0 表示空闲空间， 1 表示放有物体的空间。在每个测试用例中，房间左上角永远是空闲的。

 一个扫地机器人面向右侧，从左上角开始清扫。机器人将一直前进，直到抵达房间边界或触碰到物体时，机器人将会顺时针旋转 90 度并重复以上步骤。初始位置和所有机器人走过的空间都会被它清扫干净。

 若机器人持续运转下去，返回被清扫干净的空间数量。

  

 示例 1：


 输入: room = [[0,0,0],[1,1,0],[0,0,0]]
 输出: 7
 解释:
 机器人清理了位于 (0, 0)、 (0, 1) 和 (0, 2) 的空间。
 机器人位于房间边界，所以它顺时针旋转 90 度，现在面向下。
 机器人清理了位于 (1, 2) 和 (2, 2) 的空间。
 机器人位于房间边界，所以它顺时针旋转 90 度，现在面向左。
 机器人清理了位于 (2, 1) 和 (2, 0) 的空间。
 机器人已清理了所有 7 处空闲空间，所以返回 7。
 示例 2：


 输入: room = [[0,1,0],[1,0,0],[0,0,0]]
 输出t: 1
 解释:
 机器人清理了位于 (0, 0) 的空间。
 机器人触碰到了物体，所以它顺时针旋转 90 度，现在面向下。
 机器人触碰到了物体，所以它顺时针旋转 90 度，现在面向左。
 机器人位于房间边界，所以它顺时针旋转 90 度，现在面向上。
 机器人位于房间边界，所以它顺时针旋转 90 度，现在面向右。
 机器人回到了起始位置。
 机器人清理了 1 处空间，所以返回 1。
  

 提示：

 m == room.length
 n == room[r].length
 1 <= m, n <= 300
 room[r][c] 只会是 0 或 1 。
 room[0][0] == 0


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-spaces-cleaning-robot-cleaned
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    /**
     * https://leetcode.cn/problems/number-of-spaces-cleaning-robot-cleaned/solution/java-ji-yi-hua-vlya-by-qing-bi-ning-shua-i5nd/
     * @param room
     * @return
     */
    public int numberOfCleanRooms(int[][] room) {
        // 用一个字符串记录 4个状态 分别代码四个方向有没有走过这个点 开始 从0点走
        int[][] dir = new int[][] {
                {0, 1},// 右
                {1, 0},// 下
                {0, -1},// 左
                {-1, 0}// 上
        };
        //  1 <= m, n <= 300  直接用string 拼接 感觉比较好 每次都先往一个方向走 如果next 的下个方向也被遍历了，那就不能走了
        int i = 0;
        int j = 0;
        // 向右
        int dirIndex = 0;
        Set<String> visit = new HashSet<>();
        visit.add(getKey(i, j, dirIndex));
        int cleanCount = 0;
        while (true) {
            // 下一个点
            int nextI = i + dir[dirIndex][0];
            int nextJ = j + dir[dirIndex][1];
            // 如果 没有遇到需要转弯的 构造下一个点
            if (0 <= nextI && nextI < room.length
                    && 0 <= nextJ && nextJ < room[0].length
                    && room[nextI][nextJ] == 0) {
                i = nextI;
                j = nextJ;
            } else {
                // 否则 转向 构造下一个
                dirIndex = (dirIndex + 1) % 4;
            }
            // 如果下一个和方向之前已经记录过 说明再往下走 就重复了 直接 break
            String key = getKey(i, j, dirIndex);
            if (visit.contains(key)) {
                break;
            }
            // 这种方式 记录这个点
            visit.add(key);
        }

        Set<String> resultSet = new HashSet<>();
        for (String key :visit) {
            String[] split = key.split("_");
            resultSet.add(split[1]);
        }

        return resultSet.size();
    }

    private String getKey(int i, int j, int dirIndex) {
        return dirIndex + "_" + i + "#" + j;
    }




}
