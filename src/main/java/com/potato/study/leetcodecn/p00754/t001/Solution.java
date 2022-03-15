package com.potato.study.leetcodecn.p00754.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

/**
 * 754. 到达终点数字
 *
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 *
 * 你可以做一些数量的移动 numMoves :
 *
 * 每次你可以选择向左或向右移动。
 * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 * 示例 2:
 *
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 *  
 *
 * 提示:
 *
 * -109 <= target <= 109
 * target != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reach-a-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 754
    public int reachNumber(int target) {
        target = Math.abs(target);
        // 从 0 开始遍历 知道 sum 大于等于 target
        int sum = 0;
        int num = 0;
        while (sum < target || (sum - target) % 2 != 0) {
            sum += num;
            num++;
        }
        return num-1;
    }
}
