package com.potato.study.leetcodecn.p01306.t001;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1306. 跳跃游戏 III
 *
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 *
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 *
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 2：
 *
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 3：
 *
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1306
    public boolean canReach(int[] arr, int start) {

        // 从 start 开始 bfs 找到 下一个没访问的点 更新是够访问过，是否是0 是否坐标在里边
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] visit = new boolean[arr.length];
        int[] dir = new int[] {
                -1, 1
        };
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            // 到达终点
            if (arr[poll] == 0) {
                return true;
            }
            visit[poll] = true;
            int step = arr[poll];
            for (int i = 0; i < dir.length; i++) {
                int next = poll + dir[i] * step;
                // 如果 next 超出了界线 那就返回
                if (next < 0 || next >= arr.length) {
                    continue;
                }
                // 如果 next 之前访问过
                if (visit[next]) {
                    continue;
                }
                queue.add(next);
            }
        }
        return false;
    }
}
