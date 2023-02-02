package com.potato.study.leetcodecn.p01953.t001;

import org.junit.Assert;

/**
 * 1953. 你可以工作的最大周数
 *
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 *
 * 你可以按下面两个规则参与项目中的工作：
 *
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 *
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：milestones = [1,2,3]
 * 输出：6
 * 解释：一种可能的情形是：
 * ​​​​- 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * 总周数是 6 。
 * 示例 2：
 *
 * 输入：milestones = [5,2,1]
 * 输出：7
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 7 周，你参与并完成项目 0 中的一个阶段任务。
 * 总周数是 7 。
 * 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
 * 因此，项目 0 中会有一个阶段任务维持未完成状态。
 *  
 *
 * 提示：
 *
 * n == milestones.length
 * 1 <= n <= 105
 * 1 <= milestones[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1953
    public long numberOfWeeks(int[] milestones) {
        // 统计 总的 sum 和最大的 max
        long max = milestones[0];
        long sum = milestones[0];
        for (int i = 1; i < milestones.length; i++) {
            max = Math.max(max, milestones[i]);
            sum += milestones[i];
        }
        // 找到除了 最大的max之前 剩余的 是否大于max 大于的话 就是 都可以完成 否则就是 2 * 剩余 + 1
        long remind = sum - max;
        if (remind >= max) {
            return sum;
        }
        // remind 小于 max
        return 2 * remind + 1;
    }

}
