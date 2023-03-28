package com.potato.study.leetcodecn.other.lcp.p0008.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LCP 08. 剧情触发时间
 *
 * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。在游戏开始时（第 0 天），三种属性的值均为 0。
 *
 * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
 *
 * 所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
 *
 * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
 *
 * 示例 1：
 *
 * 输入： increase = [[2,8,4],[2,5,0],[10,9,8]] requirements = [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]
 *
 * 输出: [2,-1,3,-1]
 *
 * 解释：
 *
 * 初始时，C = 0，R = 0，H = 0
 *
 * 第 1 天，C = 2，R = 8，H = 4
 *
 * 第 2 天，C = 4，R = 13，H = 4，此时触发剧情 0
 *
 * 第 3 天，C = 14，R = 22，H = 12，此时触发剧情 2
 *
 * 剧情 1 和 3 无法触发。
 *
 * 示例 2：
 *
 * 输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]] requirements = [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]
 *
 * 输出: [-1,4,3,3,3]
 *
 * 示例 3：
 *
 * 输入： increase = [[1,1,1]] requirements = [[0,0,0]]
 *
 * 输出: [0]
 *
 * 限制：
 *
 * 1 <= increase.length <= 10000
 * 1 <= requirements.length <= 100000
 * 0 <= increase[i] <= 10
 * 0 <= requirements[i] <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ju-qing-hong-fa-shi-jian
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // lcp 08
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        // 对 increase 形成一个 前缀和 数列
        int n = increase.length;
        long[][] prefix = new long[n][3];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                prefix[i][0] = increase[i][0];
                prefix[i][1] = increase[i][1];
                prefix[i][2] = increase[i][2];
            } else {
                prefix[i][0] = prefix[i-1][0] + increase[i][0];
                prefix[i][1] = prefix[i-1][1] + increase[i][1];
                prefix[i][2] = prefix[i-1][2] + increase[i][2];
            }
        }
        // 遍历 requirements 对每一个维度进行 遍历找到 第一个大于等于 的坐标 然后求最大值
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int[] requirement = requirements[i];

            int dayIndex1 = binarySearch(0, requirement[0], prefix);
            int dayIndex2 = binarySearch(1, requirement[1], prefix);
            int dayIndex3 = binarySearch(2, requirement[2], prefix);

            if (dayIndex1 == -1 || dayIndex2 == -1 || dayIndex3 == -1) {
                result[i] = -1;
            } else {
                result[i] = Math.max(dayIndex1, Math.max(dayIndex2, dayIndex3));
            }

        }
        return result;
    }

    private int binarySearch(int searchIndex, int target, long[][] prefix) {
        int left = 0;
        int n = prefix.length;
        int right = n - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (prefix[mid][searchIndex] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
