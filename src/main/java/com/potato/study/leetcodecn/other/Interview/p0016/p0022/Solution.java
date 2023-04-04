package com.potato.study.leetcodecn.other.Interview.p0016.p0022;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 面试题 16.22. 兰顿蚂蚁
 *
 * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
 *
 * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
 * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
 *
 * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
 *
 * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
 *
 * 示例 1:
 *
 * 输入: 0
 * 输出: ["R"]
 * 示例 2:
 *
 * 输入: 2
 * 输出:
 * [
 *   "_X",
 *   "LX"
 * ]
 * 示例 3:
 *
 * 输入: 5
 * 输出:
 * [
 *   "_U",
 *   "X_",
 *   "XX"
 * ]
 * 说明：
 *
 * K <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/langtons-ant-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 16.22
    public List<String> printKMoves(int k) {
        int[][] dir = new int[][] {
                {0, 1}, // right
                {1, 0},
                {0, -1},
                {-1, 0} // up
        };
        char[] chs = new char[] {
                'R',
                'D',
                'L',
                'U'
        };
        int i = 0;
        int j = 0;
        // 初始向右边
        int dirIndex = 0;
        // key  是 i_j 坐标， value 是最终展示的颜色
        Map<String, Character> map = new HashMap<>();
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        map.put(getKey(i, j), chs[dirIndex]);

        for (int l = 0; l < k; l++) {
            // 获取当前格子颜色
            String currentKey = getKey(i, j);
            Character ch = map.getOrDefault(currentKey, '_');

            // 计算下坐标
            i += dir[dirIndex][0];
            j += dir[dirIndex][1];

            if (ch == 'X') {
                // 黑的
                map.put(currentKey, '_');
                dirIndex = (dirIndex - 1 + 4) % 4;
            } else {
                // 白的
                map.put(currentKey, 'X');
                dirIndex = (dirIndex + 1 + 4) % 4;
            }
            // 下一个位置的蚂蚁状态
            map.put(getKey(i, j), chs[dirIndex]);

            // 修改上下界限
            up = Math.min(up, i);
            down = Math.max(down, i);

            left = Math.min(left, j);
            right = Math.max(right, j);

        }
        // 遍历上下左右 获取结果
        List<String> result = new ArrayList<>();
        for (int l = up; l <= down; l++) {
            StringBuilder builder = new StringBuilder();
            for (int m = left; m < right; m++) {
                Character ch = map.getOrDefault(getKey(l, m), '_');
                builder.append(ch);
            }
            result.add(builder.toString());
        }
        return result;
    }

    private String getKey(int i, int j) {
        return i + "_" + j;
    }


}
