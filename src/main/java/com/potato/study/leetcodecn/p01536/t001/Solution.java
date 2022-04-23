package com.potato.study.leetcodecn.p01536.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1536. 排布二进制网格的最少交换次数
 *
 * 给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。

 一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。

 请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。

 主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。

  

 示例 1：



 输入：grid = [[0,0,1],[1,1,0],[1,0,0]]
 输出：3
 示例 2：



 输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 输出：-1
 解释：所有行都是一样的，交换相邻行无法使网格符合要求。
 示例 3：



 输入：grid = [[1,0,0],[1,1,0],[1,1,1]]
 输出：0
  

 提示：

 n == grid.length
 n == grid[i].length
 1 <= n <= 200
 grid[i][j] 要么是 0 要么是 1 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-swaps-to-arrange-a-binary-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minSwaps(int[][] grid) {
        // 计算每行 最后一个连续1的位置
        int[] lastZeroIndex = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            int index = -1;
            for (int j = grid.length - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    break;
                }
                index = j;
            }
            lastZeroIndex[i] = index;
        }
        // 从前往后找到 符合当前多少的i的位置 计算总移动次数
        int totalCount = 0;
        int lineIndex = 0;
        for (int i = 1; i <= grid.length - 1; i++) {
            // 从 当前第 x列 找 lastZeroIndexero
            boolean find = false;
            for (int j = lineIndex; j < lastZeroIndex.length; j++) {
                if (lastZeroIndex[j] <= i && lastZeroIndex[j] != -1) {
                    // 交换计数
                    totalCount += (j-lineIndex);
                    // 从下往上 依次交换上来
                    if (j != lineIndex) {
                        for (int k = j; k > lineIndex; k--) {
                            int tmp = lastZeroIndex[k];
                            lastZeroIndex[k] = lastZeroIndex[k-1];
                            lastZeroIndex[k-1] = tmp;
                        }
                    }
                    find = true;
                    lineIndex++;
                    break;
                }
            }
            if (!find) {
                return -1;
            }
        }
        return totalCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[0,0,1],[1,1,0],[1,0,0]]";
        int[][] arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.minSwaps(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
