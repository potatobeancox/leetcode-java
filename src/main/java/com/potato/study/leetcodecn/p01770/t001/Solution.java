package com.potato.study.leetcodecn.p01770.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * 1770. 执行乘法运算的最大分数
 *
 * 给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。
 *
 * 初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：
 *
 * 选择数组 nums 开头处或者末尾处 的整数 x 。
 * 你获得 multipliers[i] * x 分，并累加到你的分数中。
 * 将 x 从数组 nums 中移除。
 * 在执行 m 步操作后，返回 最大 分数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], multipliers = [3,2,1]
 * 输出：14
 * 解释：一种最优解决方案如下：
 * - 选择末尾处的整数 3 ，[1,2,3] ，得 3 * 3 = 9 分，累加到分数中。
 * - 选择末尾处的整数 2 ，[1,2] ，得 2 * 2 = 4 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[1] ，得 1 * 1 = 1 分，累加到分数中。
 * 总分数为 9 + 4 + 1 = 14 。
 * 示例 2：
 *
 * 输入：nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * 输出：102
 * 解释：一种最优解决方案如下：
 * - 选择开头处的整数 -5 ，[-5,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-3,-2,7,1] ，得 -3 * -5 = 15 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[-2,7,1] ，得 1 * 4 = 4 分，累加到分数中。
 * - 选择末尾处的整数 7 ，[-2,7] ，得 7 * 6 = 42 分，累加到分数中。
 * 总分数为 50 + 15 - 9 + 4 + 42 = 102 。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 1770
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        // dp ij 表示 前面 取了i个元素的总得分 后面取了j个元素得分的 最大得分
        long[][] dp = new long[m+1][m+1];
        // dp 0i   dp i0 前面没有取 后面也没有取 肯定是0
        dp[0][0] = 0;
        // 最开始 0 的转移
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + multipliers[i-1] * nums[i-1];
            dp[0][i] = dp[0][i - 1] +  multipliers[i-1] * nums[nums.length-i];
        }


        // 控制当前取了多少 总计
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j + i <= m; j++) {
                dp[i][j] = Math.max(dp[i-1][j] + multipliers[i+j-1] * nums[i-1],
                        dp[i][j-1] + multipliers[i+j-1] * nums[nums.length-j]);
            }
        }
        // 找到最大值
        long max = Integer.MIN_VALUE;
        for (int i = 0; i <= m; i++) {
            max = Math.max(max, dp[i][m-i]);
        }
        return (int)max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,2,3};
        int[] multipliers = new int[]{3,2,1};
        int i = solution.maximumScore(nums, multipliers);
        System.out.println(i);
        Assert.assertEquals(14, i);


        nums = new int[]{-5,-3,-3,-2,7,1};
        multipliers = new int[]{-10,-5,3,4,6};
        i = solution.maximumScore(nums, multipliers);
        System.out.println(i);
        Assert.assertEquals(102, i);


        nums = new int[]{
                -947,897,328,-467,14,-918,-858,-701,-518,-997,22,259,-4,968,947,582,-449,895,-121,-403,633,490,
                64,543,-396,-997,841,-398,247,297,-147,-708,804,-199,-765,-547,-599,406,-223,-11,663,746,-365,-859,256,
                -25,919,-334,490,-511,865,-139,-968,961,-793,451,317,645,-294,240,-312,-822,961,-572,309,579,161,780,525,
                -622,-511,423,946,-28,-199,822,-123,-316,-913,113,-458,-428,-414,49,922,722,-854,323,-219,581,302,124,164,
                31,727,186,308,-936,-937,-862,939,213,966,-74,-76,-1,521,777,-966,454,-199,526,-895,447,-749,-518,-639,849,
                -771,979,-760,-763,-601,-201,40,-911,207,890,-942,-352,700,267,830,-396,536,877,-896,-687,262,-60,-373,-373,
                526};
        multipliers = new int[]{
                864,849,586,769,309,-413,318,652,883,-690,796,251,-648,433,1000,-969,422,194,-785,-242,-118,
                69,187,-925,-418,-556,88,-399,-619,-383,-188,206,-793,-9,738,-587,878,360,640,318,-399,-366,365,-291,-75,-451,
                -674,-199,177,862,1,11,390,-52,-101,127,-354,-717,-717,180,655,817,-898,28,-641,-35,-563,-737,283,-223,-322,-59,
                955,172,230,512,-205,-180,899,169,-663,-253,270,651,168,417,613,-443,980,-189,417,372,-891,-272,993,-877,906,680,
                -630,-328,-873,-811,78,-667,-2,190,-773,878,529,620,-951,-687,314,-989,-48,-601,-950,31,-789,-663,-480,750,-872,
                -358,529,-426,-111,517,750,-536,-673,370
        };
        i = solution.maximumScore(nums, multipliers);
        System.out.println(i);
        Assert.assertEquals(32383191, i);


    }
}
