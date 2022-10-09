package com.potato.study.leetcodecn.p02174.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 2174. Remove All Ones With Row and Column Flips II
 *
 * You are given a 0-indexed m x n binary matrix grid.

 In one operation, you can choose any i and j that meet the following conditions:

 0 <= i < m
 0 <= j < n
 grid[i][j] == 1
 and change the values of all cells in row i and column j to zero.

 Return the minimum number of operations needed to remove all 1's from grid.

  

 Example 1:


 Input: grid = [[1,1,1],[1,1,1],[0,1,0]]
 Output: 2
 Explanation:
 In the first operation, change all cell values of row 1 and column 1 to zero.
 In the second operation, change all cell values of row 0 and column 0 to zero.
 Example 2:


 Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
 Output: 2
 Explanation:
 In the first operation, change all cell values of row 1 and column 0 to zero.
 In the second operation, change all cell values of row 2 and column 1 to zero.
 Note that we cannot perform an operation using row 1 and column 1 because grid[1][1] != 1.
 Example 3:


 Input: grid = [[0,0],[0,0]]
 Output: 0
 Explanation:
 There are no 1's to remove so return 0.
  

 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 15
 1 <= m * n <= 15
 grid[i][j] is either 0 or 1.

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/remove-all-ones-with-row-and-column-flips-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int minOperation;


    public int removeOnes(int[][] grid) {
        // 将坐标一维化存储在 list 中
        List<Integer> oneIndexList = new ArrayList<>();
        //  1 <= m, n <= 15  i * 16 + j
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    oneIndexList.add(i * 16 + j);
                }
            }
        }
        // dfs 每次选择一个 进行变更，当没有的时候结算最终操作次数
        this.minOperation = Integer.MAX_VALUE;
        int currentCount = 0;
        dfs(oneIndexList, currentCount);
        return minOperation;
    }

    private void dfs(List<Integer> oneIndexList, int currentCount) {
        // 终止条件 如果当前已经没有 index 为1了
        if (oneIndexList.size() == 0) {
            this.minOperation = Math.min(minOperation, currentCount);
            return;
        }
        // 遍历当前为 1的每个index 将对应不是这个行列的放入新的list dfs
        for (int flipIndex : oneIndexList) {
            int i = flipIndex / 16;
            int j = flipIndex % 16;
            // 去掉同行 同列的1
            List<Integer> nextOneIndexList = new ArrayList<>();
            for (int otherIndex : oneIndexList) {
                int di = otherIndex / 16;
                int dj = otherIndex % 16;

                if (di != i && dj != j) {
                    nextOneIndexList.add(otherIndex);
                }
            }
            dfs(nextOneIndexList, currentCount + 1);
        }
    }
}
