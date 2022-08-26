package com.potato.study.leetcodecn.p00351.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 351. 安卓系统手势解锁
 *
 * 我们都知道安卓有个手势解锁的界面，是一个 3 x 3 的点所绘制出来的网格。用户可以设置一个 “解锁模式” ，通过连接特定序列中的点，形成一系列彼此连接的线段，每个线段的端点都是序列中两个连续的点。如果满足以下两个条件，则 k
 * 点序列是有效的解锁模式：
 *
 * 解锁模式中的所有点 互不相同 。
 * 假如模式中两个连续点的线段需要经过其他点的 中心 ，那么要经过的点 必须提前出现 在序列中（已经经过），不能跨过任何还未被经过的点。
 * 例如，点 5 或 6 没有提前出现的情况下连接点 2 和 9 是有效的，因为从点 2 到点 9 的线没有穿过点 5 或 6 的中心。
 * 然而，点 2 没有提前出现的情况下连接点 1 和 3 是无效的，因为从圆点 1 到圆点 3 的直线穿过圆点 2 的中心。
 * 以下是一些有效和无效解锁模式的示例：
 *
 *
 *
 * 无效手势：[4,1,3,6] ，连接点 1 和点 3 时经过了未被连接过的 2 号点。
 * 无效手势：[4,1,9,2] ，连接点 1 和点 9 时经过了未被连接过的 5 号点。
 * 有效手势：[2,4,1,3,6] ，连接点 1 和点 3 是有效的，因为虽然它经过了点 2 ，但是点 2 在该手势中之前已经被连过了。
 * 有效手势：[6,5,4,1,9,2] ，连接点 1 和点 9 是有效的，因为虽然它经过了按键 5 ，但是点 5 在该手势中之前已经被连过了。
 * 给你两个整数，分别为 ​​m 和 n ，那么请返回有多少种 不同且有效的解锁模式 ，是 至少 需要经过 m 个点，但是 不超过 n 个点的。
 *
 * 两个解锁模式 不同 需满足：经过的点不同或者经过点的顺序不同。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：m = 1, n = 1
 * 输出：9
 * 示例 2：
 *
 * 输入：m = 1, n = 2
 * 输出：65
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/android-unlock-patterns
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numberOfPatterns(int m, int n) {
        int totalCount = 0;
        // 每个长度的可能性
        for (int i = m; i <= n; i++) {
            boolean[] used = new boolean[9];
            // 回溯 使用current 记录当前字符没用之前的可能性
            int lastIndex = -1;
            totalCount += backtracking(i, 0, used, lastIndex);
        }
        return totalCount;
    }

    /**
     *
     * @param len   密码长度
     * @param currentCount  当前这个字符使用之前的种类数
     * @param used  使用的状态
     * @param lastIndex 上一个使用的 index
     * @return
     */
    private int backtracking(int len, int currentCount, boolean[] used, int lastIndex) {
        // 啥也没有 那只有一种可能
        if (len == 0) {
            return 1;
        }
        // 从每个字符开始找 确定当前位置
        int count = 0;
        // 遍历 1-9 每一个字符 如果 没有 visit 的时候 使用
        for (int i = 0; i < 9; i++) {
            // 使用过么
            if (used[i]) {
                continue;
            }
            // 能不能用
            if (!isValid(lastIndex, i, used)) {
                continue;
            }
            used[i] = true;
            // 并累加记录 可能性 返回
            count += backtracking(len - 1, currentCount, used, i);
            used[i] = false;
        }
        return count;
    }

    private boolean isValid(int lastIndex, int i, boolean[] used) {
        // 加和是 计数可以
        if (used[i]) {
            return false;
        }
        if (lastIndex == -1) {
            return true;
        }
        // 相邻 象棋马跳跃的形式 可以
        if ((i+1+lastIndex+1) % 2 == 1) {
            return true;
        }
        // 同行 同列 对角线判断
        if (i/3 == lastIndex/3 || i%3 == lastIndex%3) {
            int mid = (lastIndex + i) / 2;
            return used[mid];
        }
        // 对角线
        int mid = (lastIndex + i) / 2;
        if (mid == 4) {
            return used[mid];
        }
        // 应该不会到这个分支
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.numberOfPatterns(1, 1);
        System.out.println(i);
        Assert.assertEquals(9, i);



        i = solution.numberOfPatterns(1, 2);
        System.out.println(i);
        Assert.assertEquals(65, i);
    }
}
