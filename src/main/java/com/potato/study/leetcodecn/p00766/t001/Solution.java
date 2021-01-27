package com.potato.study.leetcodecn.p00766.t001;

/**
 * 766. 托普利茨矩阵
 *
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。

 给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。

 示例 1:

 输入:
 matrix = [
   [1,2,3,4],
   [5,1,2,3],
   [9,5,1,2]
 ]
 输出: True
 解释:
 在上述矩阵中, 其对角线为:
 "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 各条对角线上的所有元素均相同, 因此答案是True。
 示例 2:

 输入:
 matrix = [
   [1,2],
   [2,2]
 ]
 输出: False
 解释:
 对角线"[1, 2]"上的元素不同。
 说明:

  matrix 是一个包含整数的二维数组。
 matrix 的行数和列数均在 [1, 20]范围内。
 matrix[i][j] 包含的整数在 [0, 99]范围内。
 进阶:

 如果矩阵存储在磁盘上，并且磁盘内存是有限的，因此一次最多只能将一行矩阵加载到内存中，该怎么办？
 如果矩阵太大以至于只能一次将部分行加载到内存中，该怎么办？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/toeplitz-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 按照对角线维度进行判断 是不是相等
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        // 按照第一行进行遍历
        if (null == matrix) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = m-1; i >= 0 ; i--) {
            // 第一个元素
            int firstVal = matrix[0][i];
            int x = 1;
            int y = i+1;
            while (x < n && y < m) {
                if (matrix[x][y] != firstVal) {
                    return false;
                }
                x++;
                y++;
            }
        }
        // 按照第一列的顺序进行遍历
        for (int i = 1; i < n; i++) {
            // 第一个元素
            int firstVal = matrix[i][0];
            int x = i+1;
            int y = 1;
            while (x < n && y < m) {
                if (matrix[x][y] != firstVal) {
                    return false;
                }
                x++;
                y++;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] cost = new int[]{10, 15, 20};
//        int minCost = solution.minCostClimbingStairs(cost);
//        System.out.println(minCost);
//        Assert.assertEquals(15, minCost);
//
//
//        cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        minCost = solution.minCostClimbingStairs(cost);
//        System.out.println(minCost);
//        Assert.assertEquals(6, minCost);
//
//    }
}
