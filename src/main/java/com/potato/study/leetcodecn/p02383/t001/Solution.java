package com.potato.study.leetcodecn.p02383.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 2383. 赢得比赛需要的最少训练时长
 *
 * 你正在参加一场比赛，给你两个 正 整数 initialEnergy 和 initialExperience 分别表示你的初始精力和初始经验。

 另给你两个下标从 0 开始的整数数组 energy 和 experience，长度均为 n 。

 你将会 依次 对上 n 个对手。第 i 个对手的精力和经验分别用 energy[i] 和 experience[i] 表示。当你对上对手时，需要在经验和精力上都 严格 超过对手才能击败他们，然后在可能的情况下继续对上下一个对手。

 击败第 i 个对手会使你的经验 增加 experience[i]，但会将你的精力 减少  energy[i] 。

 在开始比赛前，你可以训练几个小时。每训练一个小时，你可以选择将增加经验增加 1 或者 将精力增加 1 。

 返回击败全部 n 个对手需要训练的 最少 小时数目。

  

 示例 1：

 输入：initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
 输出：8
 解释：在 6 小时训练后，你可以将精力提高到 11 ，并且再训练 2 个小时将经验提高到 5 。
 按以下顺序与对手比赛：
 - 你的精力与经验都超过第 0 个对手，所以获胜。
 精力变为：11 - 1 = 10 ，经验变为：5 + 2 = 7 。
 - 你的精力与经验都超过第 1 个对手，所以获胜。
 精力变为：10 - 4 = 6 ，经验变为：7 + 6 = 13 。
 - 你的精力与经验都超过第 2 个对手，所以获胜。
 精力变为：6 - 3 = 3 ，经验变为：13 + 3 = 16 。
 - 你的精力与经验都超过第 3 个对手，所以获胜。
 精力变为：3 - 2 = 1 ，经验变为：16 + 1 = 17 。
 在比赛前进行了 8 小时训练，所以返回 8 。
 可以证明不存在更小的答案。
 示例 2：

 输入：initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
 输出：0
 解释：你不需要额外的精力和经验就可以赢得比赛，所以返回 0 。
  

 提示：

 n == energy.length == experience.length
 1 <= n <= 100
 1 <= initialEnergy, initialExperience, energy[i], experience[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-hours-of-training-to-win-a-competition
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int sumCostEnergy = 0;
        int sumCostExperience = 0;
        int currentExperience = initialExperience;

        int n = energy.length;
        for (int i = 0; i < n; i++) {
            int targetEnergy = energy[i];
            int targetExperience = experience[i];

            // 对于 能力 求和就行了 做差
            sumCostEnergy += targetEnergy;
            // 经验需要计算每次差值和当前的差 每次必须补上
            if (currentExperience <= targetExperience) {
                sumCostExperience += (targetExperience + 1 - currentExperience);
                // 设置成结束状态
                currentExperience = targetExperience + 1;
                // 加上这次的获取
                currentExperience += targetExperience;
            } else {
                currentExperience += targetExperience;
            }
        }

        if (initialEnergy > sumCostEnergy) {
            return sumCostExperience;
        }

        return sumCostExperience + sumCostEnergy + 1 - initialEnergy;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int initialEnergy = 1;
        int initialExperience = 1;
        int[] energy = new int[] {1,1,1,1};
        int[] experience = new int[] {1,1,1,50};
        int i = solution.minNumberOfHours(initialEnergy, initialExperience, energy, experience);
        System.out.println(i);
        Assert.assertEquals(51, i);



        initialEnergy = 100;
        initialExperience = 100;
        energy = new int[] {1,2,3,4,5,6,7,8,9};
        experience = new int[] {1,2,3,1,2,3,1,2,10};
        i = solution.minNumberOfHours(initialEnergy, initialExperience, energy, experience);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }

}
