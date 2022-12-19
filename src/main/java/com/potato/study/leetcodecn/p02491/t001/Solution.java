package com.potato.study.leetcodecn.p02491.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2491. 划分技能点相等的团队
 *
 * 给你一个正整数数组 skill ，数组长度为 偶数 n ，其中 skill[i] 表示第 i 个玩家的技能点。将所有玩家分成 n / 2 个 2 人团队，使每一个团队的技能点之和 相等 。

 团队的 化学反应 等于团队中玩家的技能点 乘积 。

 返回所有团队的 化学反应 之和，如果无法使每个团队的技能点之和相等，则返回 -1 。

  

 示例 1：

 输入：skill = [3,2,5,1,3,4]
 输出：22
 解释：
 将玩家分成 3 个团队 (1, 5), (2, 4), (3, 3) ，每个团队的技能点之和都是 6 。
 所有团队的化学反应之和是 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22 。
 示例 2：

 输入：skill = [3,4]
 输出：12
 解释：
 两个玩家形成一个团队，技能点之和是 7 。
 团队的化学反应是 3 * 4 = 12 。
 示例 3：

 输入：skill = [1,1,2,3]
 输出：-1
 解释：
 无法将玩家分成每个团队技能点都相等的若干个 2 人团队。
  

 提示：

 2 <= skill.length <= 105
 skill.length 是偶数
 1 <= skill[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/divide-players-into-teams-of-equal-skill
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long dividePlayers(int[] skill) {
        // 求和 看看能不能 整除 并且map 计数
        int[] countArr = new int[1001];
        long sumSkill = 0;
        for (int s : skill) {
            sumSkill += s;
            countArr[s]++;
        }
        int times = skill.length / 2;
        if (sumSkill % times != 0) {
            return -1;
        }
        long target = sumSkill / times;
        long totalSum = 0;
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] == 0) {
                continue;
            }
            // 不为0
            int otherKey = (int)(target - i);
            if (i == otherKey) {
                // 奇数个 没法分 直接返回
                if (countArr[i] % 2 == 1) {
                    return -1;
                }
                totalSum += ((long)countArr[i] / 2) * ((long)i * i);
                countArr[i] = 0;
            } else {
                if (countArr[i] != countArr[otherKey]) {
                    return -1;
                }
                totalSum += countArr[i] * ((long)i * otherKey);
                countArr[i] = 0;
                countArr[otherKey] = 0;
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] skill = new int[] {
                3,2,5,1,3,4
        };
        long l = solution.dividePlayers(skill);
        System.out.println(l);
        Assert.assertEquals(22, l);
    }

}
