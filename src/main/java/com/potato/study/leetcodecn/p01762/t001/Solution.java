package com.potato.study.leetcodecn.p01762.t001;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * 1762. 能看到海景的建筑物
 *
 * 有 n 座建筑物。给你一个大小为 n 的整数数组 heights 表示每一个建筑物的高度。
 *
 * 建筑物的右边是海洋。如果建筑物可以无障碍地看到海洋，则建筑物能看到海景。确切地说，如果一座建筑物右边的所有建筑都比它 矮 时，就认为它能看到海景。
 *
 * 返回能看到海景建筑物的下标列表（下标 从 0 开始 ），并按升序排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：heights = [4,2,3,1]
 * 输出：[0,2,3]
 * 解释：1 号建筑物看不到海景，因为 2 号建筑物比它高
 * 示例 2：
 *
 * 输入：heights = [4,3,2,1]
 * 输出：[0,1,2,3]
 * 解释：所有的建筑物都能看到海景。
 * 示例 3：
 *
 * 输入：heights = [1,3,2,4]
 * 输出：[3]
 * 解释：只有 3 号建筑物能看到海景。
 * 示例 4：
 *
 * 输入：heights = [2,2,2,2]
 * 输出：[3]
 * 解释：如果建筑物右边有相同高度的建筑物则无法看到海景。
 *  
 *
 * 提示：
 *
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/buildings-with-an-ocean-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] findBuildings(int[] heights) {
        // 从后往前记录 最大的 heights 每次 超过 heights 都是可以看到 的
        int maxRightHeight = Integer.MIN_VALUE;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (maxRightHeight < heights[i]) {
                list.addFirst(i);
                maxRightHeight = heights[i];
            }
        }
        int [] result = new int[list.size()];
        int index = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            result[index++] = iterator.next();
        }
        return result;
    }
}
