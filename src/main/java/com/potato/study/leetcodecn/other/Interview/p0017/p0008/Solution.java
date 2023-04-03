package com.potato.study.leetcodecn.other.Interview.p0017.p0008;


import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 面试题 17.08. 马戏团人塔
 *
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 *
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 * 提示：
 *
 * height.length == weight.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/circus-tower-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 17.08
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int n = height.length;
        // 以i作为最上面节点 最多叠多少层
        int[] dp = new int[n];
        // 从前往后 定位在最上面的 有多少层
        int[][] people = new int[n][3];
        for (int i = 0; i < n; i++) {
            // 0：height； 1：weight  2：index
            people[i] = new int[] {
                    height[i], weight[i], i
            };
        }
        Arrays.sort(people, (p1, p2) -> {
            int compare = Integer.compare(p1[0], p2[0]);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(p1[1], p2[1]);
        });
        // 中间维护全局最大值
        int max = 0;
        // 内部从 0 找到i-1 找最大 用这个垫背
        for (int i = 0; i < n; i++) {
            int maxHeight = 0;
            for (int j = 0; j < i; j++) {
                maxHeight = Math.max(maxHeight, dp[j]);
            }
            // 当前点站上去
            dp[i] = maxHeight + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
