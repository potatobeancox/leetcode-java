package com.potato.study.leetcodecn.p01074.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 1074. 元素和为目标值的子矩阵数量
 *
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 *
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 *
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * 示例 2：
 *
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * 示例 3：
 *
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 枚举开始行 0-len
        int totalCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            // 从开始行往下枚举结束行 i -len 对于当前范围求每列的值 （枚举列，求前缀和）对于每个范围 求 范围内有多少个 子矩阵
            int[] preLineSum = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) {
                // 求当前 值
                for (int k = 0; k < matrix[0].length; k++) {
                    preLineSum[k] = matrix[j][k] + preLineSum[k];
                }
                totalCount += getSubArrayCount(preLineSum, target);
            }
        }
        return totalCount;
    }


    /**
     *
     * @param nums 前缀和
     * @param k 目标子数组的和
     * @return
     */
    private int getSubArrayCount(int[] nums, int k) {
        // key是num 元素 的值，value是这个值出现的次数
        Map<Integer, Integer> valueCountMap = new HashMap<>();
        valueCountMap.put(0, 1);
        int count = 0;
        int preSum = 0;
        for (int num : nums) {
            // 计算当期sum
            int currentSum = preSum + num;
            // 计算之前需要找的key
            int targetKey = currentSum - k;
            Integer targetCount = valueCountMap.getOrDefault(targetKey, 0);
            count += targetCount;
            // 当前放入map
            valueCountMap.put(currentSum, valueCountMap.getOrDefault(currentSum, 0) + 1);

            preSum = currentSum;
        }

        return count;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,1,0],[1,1,1],[0,1,0]]";
        int[][] matrix = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int target = 0;
        int i = solution.numSubmatrixSumTarget(matrix, target);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
