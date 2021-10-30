package com.potato.study.leetcodecn.p00312.t001;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

/**
 * 312. 戳气球
 *
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或
 * i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 *  
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 *
 * 输入：nums = [1,5]
 * 输出：10
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/burst-balloons/solution/chuo-qi-qiu-by-leetcode-solution/
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        // 处理 下 nums 在两边 补充 1 好处理
        int[] values = new int[nums.length + 2];
        values[0] = 1;
        values[values.length - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            values[i+1] = nums[i];
        }
        //  dp ij ij 区间里边 最多 可以 获得多少个硬币   di ij 等于 选择k 位置 求每个的最大值
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        // i 从 末尾遍历到 0 j 比i 多2  在ij中 枚举每个 k 并求解 每个k位置 值 找到ij中的最大值
        for (int i = values.length - 1; i >= 0; i--) {
            // 控制 左边断电
            for (int j = i + 2; j < values.length; j++) {
                // 控制右边端点
                for (int k = i + 1; k < j; k++) {
                    // 控制本次 打爆哪个气球 两边分别能获得多少
                    int val = dp[i][k] + dp[k][j] + values[k] * values[i] * values[j];
                    dp[i][j] = Math.max(dp[i][j], val);
                }
            }

        }
        return dp[0][values.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,1,5,8
        };
        int i = solution.maxCoins(nums);
        System.out.println(i);
        Assert.assertEquals( 167, i);



        nums = new int[] {
                1,5
        };
        i = solution.maxCoins(nums);
        System.out.println(i);
        Assert.assertEquals( 10, i);

    }
}
