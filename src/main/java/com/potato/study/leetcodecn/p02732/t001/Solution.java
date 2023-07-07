package com.potato.study.leetcodecn.p02732.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 *
 * 2732. 找到矩阵中的好子集
 *
 * 给你一个下标从 0 开始大小为 m x n 的二进制矩阵 grid 。
 *
 * 从原矩阵中选出若干行构成一个行的 非空 子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 好子集。
 *
 * 更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为 floor(k / 2) 。
 *
 * 请你返回一个整数数组，它包含好子集的行下标，请你将子集中的元素 升序 返回。
 *
 * 如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。
 *
 * 一个矩阵 grid 的行 子集 ，是删除 grid 中某些（也可能不删除）行后，剩余行构成的元素集合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
 * 输出：[0,1]
 * 解释：我们可以选择第 0 和第 1 行构成一个好子集。
 * 选出来的子集大小为 2 。
 * - 第 0 列的和为 0 + 0 = 0 ，小于等于子集大小的一半。
 * - 第 1 列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
 * - 第 2 列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
 * - 第 3 列的和为 0 + 1 = 1 ，小于等于子集大小的一半。
 * 示例 2：
 *
 * 输入：grid = [[0]]
 * 输出：[0]
 * 解释：我们可以选择第 0 行构成一个好子集。
 * 选出来的子集大小为 1 。
 * - 第 0 列的和为 0 ，小于等于子集大小的一半。
 * 示例 3：
 *
 * 输入：grid = [[1,1,1],[1,1,1]]
 * 输出：[]
 * 解释：没有办法得到一个好子集。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 104
 * 1 <= n <= 5
 * grid[i][j] 要么是 0 ，要么是 1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-a-good-subset-of-the-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        // 数学题 结果只有两种可能 第一种就是 所有的列都是 0
        Map<Integer, Integer> maskIndexMap = new HashMap<>();
        // 将 grid 按照行 处理成  1 <= n <= 5  二进制数字
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            int mask = getMask(grid[i]);
            // 如果找到一个 0
            if (mask == 0) {
                res.add(i);
                return res;
            }
            maskIndexMap.put(mask, i);
        }
        // 遍历一边 如果 按照 列 and 结果是 0 就是所求 返回
        for (int targetMask1: maskIndexMap.keySet()) {
            for (int targetMask2: maskIndexMap.keySet()) {
                if ((targetMask1 & targetMask2) == 0) {
                    int index1 = maskIndexMap.get(targetMask1);
                    int index2 = maskIndexMap.get(targetMask2);

                    res.add(Math.min(index1, index2));
                    res.add(Math.max(index1, index2));

                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 将line 按照 index 打平成 二进制数据
     * @param line
     * @return
     */
    private int getMask(int[] line) {
        int mask = 0;
        for (int i = 0; i < line.length; i++) {
            mask |= (line[i] << i);
        }
        return mask;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1,1,0],[0,0,0,1],[1,1,1,1]]");
        List<Integer> list = solution.goodSubsetofBinaryMatrix(grid);
        System.out.println(list);

    }

}
