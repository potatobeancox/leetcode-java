package com.potato.study.leetcodecn.other.Interview.p0005.p0008;


import org.junit.Assert;


/**
 * 面试题 05.08. 绘制直线
 *
 * 已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
 *
 * 现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
 *
 * 我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
 *
 *  
 *
 * 注意：
 *
 * 用例保证屏幕宽度 w 可被 32 整除（即一个 int 不会分布在两行上）
 *  
 *
 * 示例1:
 *
 *  输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 *  输出：[3]
 *  解释：在第 0 行的第 30 位到第 31 位画一条直线，屏幕二进制形式表示为 [00000000000000000000000000000011]，因此返回 [3]
 * 示例2:
 *
 *  输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 *  输出：[-1, -1, -1]
 *  解释：由于二进制 11111111111111111111111111111111 的 int 类型代表 -1，因此返回 [-1,-1,-1]
 *  
 *
 * 提示：
 *
 * 1 <= length <= 10^5
 * 1 <= w <= 3 * 10^5
 * 0 <= x1 <= x2 < w
 * 0 <= y <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/draw-line-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 05.08
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        // 每个 32 bit 作为一组 然后将内部 的值替换一下
        int n = w / 32;
        int[] result = new int[n];
        // 便利每个n 计算当前的开始位置和计数位置
        for (int i = 0; i < n; i++) {
            // 0 32, 64
            int left = 32 * i;
            // 31, 63
            int right = 32 * (i+1) - 1;
            // 计算从 多少位置到多少位置 为1 然后计数生成
            // 看看有没有交集
            int realLeft = Math.max(left, x1);
            int realRight = Math.min(right, x2);

            int target = 0;
            // 没有直接设置成 0
            if (realLeft > realRight) {
                result[i] = 0;
            } else {
                // 计算有多少个 1
                for (int j = realLeft; j <= realRight; j++) {
                    int bit = (j - left);
                    target |= (1 << bit);
                }
                result[i] = target;
            }
        }
        return result;
    }
}