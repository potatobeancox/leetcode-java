package com.potato.study.leetcodecn.p02555.t001;

import org.junit.Assert;

/**
 * 2555. 两个线段获得的最多奖品
 *
 * 在 X轴 上有一些奖品。给你一个整数数组 prizePositions ，它按照 非递减 顺序排列，其中 prizePositions[i] 是第 i 件奖品的位置。数轴上一个位置可能会有多件奖品。再给你一个整数 k 。
 *
 * 你可以选择两个端点为整数的线段。每个线段的长度都必须是 k 。你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。
 *
 * 比方说 k = 2 ，你可以选择线段 [1, 3] 和 [2, 4] ，你可以获得满足 1 <= prizePositions[i] <= 3 或者 2 <= prizePositions[i] <= 4 的所有奖品 i 。
 * 请你返回在选择两个最优线段的前提下，可以获得的 最多 奖品数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：prizePositions = [1,1,2,2,3,3,5], k = 2
 * 输出：7
 * 解释：这个例子中，你可以选择线段 [1, 3] 和 [3, 5] ，获得 7 个奖品。
 * 示例 2：
 *
 * 输入：prizePositions = [1,2,3,4], k = 0
 * 输出：2
 * 解释：这个例子中，一个选择是选择线段 [3, 3] 和 [4, 4] ，获得 2 个奖品。
 *  
 *
 * 提示：
 *
 * 1 <= prizePositions.length <= 105
 * 1 <= prizePositions[i] <= 109
 * 0 <= k <= 109
 * prizePositions 有序非递减。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximize-win-from-two-segments
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2555
    public int maximizeWin(int[] prizePositions, int k) {
        // 滑动窗口 记录当前位置作为 right 同时 记录 right+1 左边的最大值 用于计算全局2个最大
        int n = prizePositions.length;
        // 不包含当前index
        int[] max = new int[n];
        // 遍历 prizePositions 对于每个位置 right 看一下是否最后一个节点 或者是否与后面的不一样，如果不一样
        int leftIndex = 0;
        int totalMax = 0;
        for (int rightIndex = 0; rightIndex < n; rightIndex++) {
            if (rightIndex > 0) {
                max[rightIndex] = Math.max(max[rightIndex-1], max[rightIndex]);
            }
            if (rightIndex != n-1 && prizePositions[rightIndex] == prizePositions[rightIndex+1]) {
                continue;
            }
            // 当前需要进行结算 左边
            while (leftIndex <= rightIndex && prizePositions[leftIndex] < prizePositions[rightIndex] - k) {
                leftIndex++;
            }
            // 当前的win 长度
            // 不满足上面条件 window 继续滑动即 right ++ 左边还是left  线段长度是 right - left
            int winLen = rightIndex - leftIndex + 1;
            totalMax = Math.max(totalMax, winLen + max[leftIndex]);
            // 之前的算一下
            if (rightIndex != n - 1) {
                max[rightIndex+1] = Math.max(winLen, max[rightIndex]);
            }
        }
        return totalMax;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prizePositions = new int[] {
                1,1,2,2,3,3,5
        };
        int k = 2;
        int i = solution.maximizeWin(prizePositions, k);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }


}
