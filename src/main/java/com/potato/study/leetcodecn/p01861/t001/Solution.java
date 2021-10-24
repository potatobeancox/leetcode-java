package com.potato.study.leetcodecn.p01861.t001;

import com.potato.study.leetcode.util.ArrayUtil;

/**
 * 1861. 旋转盒子
 *
 * 给你一个 m x n 的字符矩阵 box ，它表示一个箱子的侧视图。箱子的每一个格子可能为：

 '#' 表示石头
 '*' 表示固定的障碍物
 '.' 表示空位置
 这个箱子被 顺时针旋转 90 度 ，由于重力原因，部分石头的位置会发生改变。每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。重力 不会 影响障碍物的位置，同时箱子旋转不会产生惯性 ，也就是说石头的水平位置不会发生改变。

 题目保证初始时 box 中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。

 请你返回一个 n x m的矩阵，表示按照上述旋转后，箱子内的结果。

  

 示例 1：



 输入：box = [["#",".","#"]]
 输出：[["."],
       ["#"],
       ["#"]]
 示例 2：



 输入：box = [["#",".","*","."],
             ["#","#","*","."]]
 输出：[["#","."],
       ["#","#"],
       ["*","*"],
       [".","."]]
 示例 3：



 输入：box = [["#","#","*",".","*","."],
             ["#","#","#","*",".","."],
             ["#","#","#",".","#","."]]
 输出：[[".","#","#"],
       [".","#","#"],
       ["#","#","*"],
       ["#","*","."],
       ["#",".","*"],
       ["#",".","."]]
  

 提示：

 m == box.length
 n == box[i].length
 1 <= m, n <= 500
 box[i][j] 只可能是 '#' ，'*' 或者 '.' 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/rotating-the-box
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * '#' 表示石头
     *  '*' 表示固定的障碍物
     *  '.' 表示空位置
     * @param box
     * @return
     */
    public char[][] rotateTheBox(char[][] box) {
        // 按照每行开始遍历，每行内从后到前进行遍历 找到第一个石头和 改放置的位置
        for (int i = 0; i < box.length; i++) {
            // 从后往前遍历
            // 是否确定移动位置
            boolean isConfirmMove = false;
            // 要移动到的index 如果当前石头
            int moveIndex = -1;
            for (int j = box[i].length - 1; j >= 0; j--) {
                char ch = box[i][j];
                if (ch == '.') {
                    // 空位置 判断是否需要重置 开始放置的位置 需要的话重置 不需要continue
                    if (!isConfirmMove) {
                        moveIndex = j;
                        isConfirmMove = true;
                    }
//                    rotateBox[j][i] = '.';
                } else if (ch == '*') {
                    // 障碍物需要 重置 是否已经确定了移动位置
                    isConfirmMove = false;
//                    rotateBox[j][i] = '*';
                } else {
                    // 石头 如果需要移动就移动 否则 重置 移动位置
                    if (isConfirmMove) {
                        // 移动
                        box[i][moveIndex] = '#';
                        moveIndex--;
                        box[i][j] = '.';
                    }
                }
            }
        }
        // 生成结果
        char[][] rotateBox = new char[box[0].length][box.length];
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                rotateBox[j][i] = box[i][j];
            }
        }
        // 数值中心线交换
        for (int i = 0; i < rotateBox.length; i++) {
            int left = 0;
            int right = rotateBox[i].length - 1;
            while (left < right) {
                char ch = rotateBox[i][left];
                rotateBox[i][left] = rotateBox[i][right];
                rotateBox[i][right] = ch;

                left++;
                right--;
            }
        }
        return rotateBox;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] input = new char[][] {
                {'#','.','*','.'},
                {'#','#','*','.'}
        };
        // [["#",".","*","."],["#","#","*","."]]

        char[][] chars = solution.rotateTheBox(input);
        ArrayUtil.printMatrix(chars);
    }


}
