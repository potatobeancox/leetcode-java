package com.potato.study.leetcodecn.p00764.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 764. 最大加号标志
 *
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 *
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 *
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为
 * 0 也可能为 1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: n = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 * 示例 2：
 *
 *
 *
 * 输入: n = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释: 没有加号标志，返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * 每一对 (xi, yi) 都 不重复
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-plus-sign
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // 使用 一个 set 记录 0的位置
        Set<String> set = new HashSet<>();
        for (int i = 0; i < mines.length; i++) {
            set.add(mines[i][0] + "_" + mines[i][1]);
        }
        // 双循环遍历 每个位置 计算最大值
        int max = 0;
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (set.contains(i + "_" + j)) {
                    continue;
                }
                // 计算最大值
                int currentMax = 1;
                for (int k = 1; k < n; k++) {
                    // 4个方向 哪个方向不行了 直接 break 调 返回值
                    boolean isValid = true;
                    for (int l = 0; l < 4; l++) {
                        int di = i + k * direction[l][0];
                        int dj = j + k * direction[l][1];
                        // 坐标非法
                        if (di < 0 || di >= n
                                || dj < 0 || dj >= n) {
                            isValid = false;
                            break;
                        }
                        // 不是 1
                        if (set.contains(di + "_" + dj)) {
                            isValid = false;
                            break;
                        }
                    }
                    if (isValid) {
                        currentMax = k + 1;
                    } else {
                        break;
                    }
                }
                max = Math.max(max, currentMax);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        String str = "[[4, 2]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.orderOfLargestPlusSign(n, arr);
        System.out.println(i);
        Assert.assertEquals(2, i);

        n = 1;
        str = "[[0, 0]]";
        arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        i = solution.orderOfLargestPlusSign(n, arr);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
