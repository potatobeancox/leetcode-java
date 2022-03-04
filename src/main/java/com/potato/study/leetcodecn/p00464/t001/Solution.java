package com.potato.study.leetcodecn.p00464.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 464. 我能赢吗
 *
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。
 *
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 *
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 *
 * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：maxChoosableInteger = 10, desiredTotal = 11
 * 输出：false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 * 示例 2:
 *
 * 输入：maxChoosableInteger = 10, desiredTotal = 0
 * 输出：true
 * 示例 3:
 *
 * 输入：maxChoosableInteger = 10, desiredTotal = 1
 * 输出：true
 *  
 *
 * 提示:
 *
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-i-win
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 464
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 终止条件
        int sum = 0;
        // 那一次就能到了 最终的 直接返回
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        // 状态
        int status = 0;
        Map<Integer, Boolean> winCache = new HashMap<>();
        return dfs(maxChoosableInteger, desiredTotal, status, 0, winCache);
    }

    /**
     * dfs 计算当前用户是否可以 完事
     * @param maxChoosableInteger
     * @param desiredTotal
     * @param status
     * @return
     */
    private boolean dfs(int maxChoosableInteger, int desiredTotal, int status,
            int sum, Map<Integer, Boolean> winCache) {

        if (winCache.containsKey(status)) {
            return winCache.get(status);
        }

        // 终止状态
        int finalStatus = (1 << maxChoosableInteger) - 1;
        if (finalStatus == status) {
            return sum >= desiredTotal;
        }
        // 找到每个数字
        for (int i = 1; i <= maxChoosableInteger; i++) {
            int bit = (1 << (i-1));
            // 已经使用过了
            if ((status & bit) > 0) {
                continue;
            }
            // 未使用 判断用这个行不行
            if (i + sum >= desiredTotal) {
                winCache.put(status, true);
                return true;
            }
            boolean result = !dfs(maxChoosableInteger, desiredTotal,
                    status|bit, sum + i, winCache);
            if (result) {
                winCache.put(status, true);
                return true;
            }
        }
        winCache.put(status, false);
        return false;
    }
}
