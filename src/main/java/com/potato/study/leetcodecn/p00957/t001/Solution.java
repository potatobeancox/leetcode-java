package com.potato.study.leetcodecn.p00957.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 957. N 天后的牢房
 *
 * 8 间牢房排成一排，每间牢房不是有人住就是空着。
 *
 * 每天，无论牢房是被占用或空置，都会根据以下规则进行更改：
 *
 * 如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。
 * 否则，它就会被空置。
 * （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）
 *
 * 我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。
 *
 * 根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：cells = [0,1,0,1,1,0,0,1], N = 7
 * 输出：[0,0,1,1,0,0,0,0]
 * 解释：
 * 下表概述了监狱每天的状况：
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * 示例 2：
 *
 * 输入：cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * 输出：[0,0,1,1,1,1,1,0]
 *  
 *
 * 提示：
 *
 * cells.length == 8
 * cells[i] 的值为 0 或 1 
 * 1 <= N <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prison-cells-after-n-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/prison-cells-after-n-days/solution/n-tian-hou-de-lao-fang-by-leetcode/
     * @param cells
     * @param n
     * @return
     */
    public int[] prisonAfterNDays(int[] cells, int n) {
        int time = n;
        Map<String, Integer> map = new HashMap<>();
        String cellString = getCellString(cells);
        while (time > 0) {
            // 如果 当前 cellString 已经在 map中了 去 % 计算
            if (map.containsKey(cellString)) {
                time %= (map.get(cellString) - time);
            }
            // 当前点作为看过的点
            map.put(cellString, time);
            // 转移到下一个点
            if (time >= 1) {
                time--;
                cells = getNextCells(cells);
                cellString = getCellString(cells);
            }
        }
        return cells;
    }

    /**
     * 下一个 cell
     * @param cells
     * @return
     */
    private int[] getNextCells(int[] cells) {
        int[] nextCells = new int[8];
        nextCells[0] = 0;
        nextCells[cells.length-1] = 0;
        for (int i = 1; i < cells.length-1 ; i++) {
            if (cells[i-1] == cells[i+1]) {
                nextCells[i] = 1;
            } else {
                nextCells[i] = 0;
            }
        }
        return nextCells;
    }

    private String getCellString(int[] cells) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            builder.append(cells[i]);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] cells = new int[] {
                0,1,0,1,1,0,0,1
        };
        int n = 7;
        int[] ints = solution.prisonAfterNDays(cells, n);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                0,0,1,1,0,0,0,0
        }, ints);

        cells = new int[] {
                1,0,0,1,0,0,1,0
        };
        n = 1000000000;
        ints = solution.prisonAfterNDays(cells, n);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                0,0,1,1,1,1,1,0
        }, ints);

        cells = new int[] {
                0,1,1,1,0,0,0,1
        };
        n = 78;
        ints = solution.prisonAfterNDays(cells, n);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                0,0,1,1,1,1,1,0
        }, ints);
    }



}
