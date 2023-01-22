package com.potato.study.leetcodecn.p02214.t001;

/**
 * 2214. 通关游戏所需的最低生命值
 *
 * 你正在玩一个有 n 个关卡的游戏，从 0 到 n - 1。给你一个 下标从 0 开始 的整数数组 damage，其中 damage[i] 是你完成第 i 个关卡所损失的生命值。
 *
 * 你也会得到一个整数 armor。你最多只能在 任何 等级使用 一次 护甲技能，这将保护你免受 最多 armor 伤害。
 *
 * 你必须按顺序完成关卡，并且你的生命值必须一直 大于 0 才能通关。
 *
 * 返回你开始通关所需的最低生命值。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: damage = [2,7,4,3], armor = 4
 * 输出: 13
 * 解释: 从 13 生命值开始通关游戏的最佳方法是:
 * 第 1 回合，受到 2 点伤害。你还有 13 - 2 = 11 生命值。
 * 第 2 回合，受到 7 点伤害。你还有 11 - 7 = 4 生命值。
 * 第 3 回合，使用你的护甲保护你免受 4 点伤害。你有 4 - 0 = 4 生命值。
 * 第 4 回合，受到 3 点伤害。你还有 4 - 3 = 1 生命值。
 * 注意，13 是你开始时通关游戏所需的最低生命值。
 * 示例 2:
 *
 * 输入: damage = [2,5,3,4], armor = 7
 * 输出: 10
 * 解释: 从 10 生命值开始通关游戏的最佳方法是:
 * 第 1 回合，受到 2 点伤害。你还有 10 - 2 = 8 生命值。
 * 第 2 回合，使用护甲保护自己免受 5 点伤害。你还有 8 - 0 = 8 生命值。
 * 第 3 回合，受到 3 点伤害。你还有 8 - 3 = 5 生命值。
 * 第 4 回合，受到 4 点伤害。你还有 5 - 4 = 1 生命值。
 * 注意，10 是你开始通关所需的最低生命值。
 * 示例 3:
 *
 * 输入: damage = [3,3,3], armor = 0
 * 输出: 10
 * 解释: 从 10 生命值开始通关游戏的最佳方法是:
 * 第 1 回合，受到 2 点伤害。你还有 10 - 3 = 7 生命值。
 * 第 2 回合，受到 3 点伤害。你还有 7 - 3 = 4 生命值。
 * 第 3 回合， 受到 3 点伤害。你还有 4 - 3 = 1 生命值。
 * 注意你没有使用护甲技能。
 *  
 *
 * 提示:
 *
 * n == damage.length
 * 1 <= n <= 105
 * 0 <= damage[i] <= 105
 * 0 <= armor <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-health-to-beat-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long minimumHealth(int[] damage, int armor) {
        if (null == damage || damage.length == 0) {
            return 0;
        }
        long max = damage[0];
        long sum = damage[0] + 1;
        // 找到最大伤害 过程中 求伤害的和
        for (int i = 1; i < damage.length; i++) {
            max = Math.max(damage[i], max);
            sum += damage[i];
        }
        // 用 armor 顶一顶
        if (armor >= max) {
            return sum - max;
        } else {
            return sum - armor;
        }
    }
}
