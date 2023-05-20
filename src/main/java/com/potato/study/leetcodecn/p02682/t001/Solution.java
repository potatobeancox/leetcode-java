package com.potato.study.leetcodecn.p02682.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * 2682. 找出转圈游戏输家
 *
 * n 个朋友在玩游戏。这些朋友坐成一个圈，按 顺时针方向 从 1 到 n 编号。从第 i 个朋友的位置开始顺时针移动 1 步会到达第 (i + 1) 个朋友的位置（1 <= i < n），而从第 n 个朋友的位置开始顺时针移动 1 步会回到第 1 个朋友的位置。
 *
 * 游戏规则如下：
 *
 * 第 1 个朋友接球。
 *
 * 接着，第 1 个朋友将球传给距离他顺时针方向 k 步的朋友。
 * 然后，接球的朋友应该把球传给距离他顺时针方向 2 * k 步的朋友。
 * 接着，接球的朋友应该把球传给距离他顺时针方向 3 * k 步的朋友，以此类推。
 * 换句话说，在第 i 轮中持有球的那位朋友需要将球传递给距离他顺时针方向 i * k 步的朋友。
 *
 * 当某个朋友第 2 次接到球时，游戏结束。
 *
 * 在整场游戏中没有接到过球的朋友是 输家 。
 *
 * 给你参与游戏的朋友数量 n 和一个整数 k ，请按升序排列返回包含所有输家编号的数组 answer 作为答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 5, k = 2
 * 输出：[4,5]
 * 解释：以下为游戏进行情况：
 * 1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 2 步的玩家 —— 第 3 个朋友。
 * 2）第 3 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 2 个朋友。
 * 3）第 2 个朋友将球传给距离他顺时针方向 6 步的玩家 —— 第 3 个朋友。
 * 4）第 3 个朋友接到两次球，游戏结束。
 * 示例 2：
 *
 * 输入：n = 4, k = 4
 * 输出：[2,3,4]
 * 解释：以下为游戏进行情况：
 * 1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 1 个朋友。
 * 2）第 1 个朋友接到两次球，游戏结束。
 *  
 *
 * 提示：
 *
 * 1 <= k <= n <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-losers-of-the-circular-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2682
    public int[] circularGameLosers(int n, int k) {
        // 用一个 set 尝试记录 访问过的数字 如果第二次遇到 那么就从 1开始没有找到的字符串
        Set<Integer> visit = new HashSet<>();
        int val = 1;
        int i = 0;
        while (!visit.contains(val)) {
            visit.add(val);
            val += i * k;
            i++;
        }
        int[] res = new int[n - visit.size()];
        int index = 0;
        for (int j = 1; j <= n; j++) {
            if (!visit.contains(j)) {
                res[index] = j;
                index++;
            }
        }
        return res;
    }
}
