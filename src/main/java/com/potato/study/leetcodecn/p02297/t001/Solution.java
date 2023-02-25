package com.potato.study.leetcodecn.p02297.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Stack;

/**
 * 2297. 跳跃游戏 VIII
 *
 * 给定一个长度为 n 的下标从 0 开始的整数数组 nums。初始位置为下标 0。当 i < j 时，你可以从下标 i 跳转到下标 j:

 对于在 i < k < j 范围内的所有下标 k 有 nums[i] <= nums[j] 和 nums[k] < nums[i] , 或者
 对于在 i < k < j 范围内的所有下标 k 有 nums[i] > nums[j] 和 nums[k] >= nums[i] 。
 你还得到了一个长度为 n 的整数数组 costs，其中 costs[i] 表示跳转到下标 i 的代价。

 返回跳转到下标 n - 1 的最小代价。

 示例 1:

 输入: nums = [3,2,4,4,1], costs = [3,7,6,4,2]
 输出: 8
 解释: 从下标 0 开始。
 - 以 costs[2]= 6 的代价跳转到下标 2。
 - 以 costs[4]= 2 的代价跳转到下标 4。
 总代价是 8。可以证明，8 是所需的最小代价。
 另外两个可能的路径是:下标 0 -> 1 -> 4 和下标 0 -> 2 -> 3 -> 4。
 它们的总代价分别为9和12。
 示例 2:

 输入: nums = [0,1,2], costs = [1,1,1]
 输出: 2
 解释: 从下标 0 开始。
 - 以 costs[1] = 1 的代价跳转到下标 1。
 - 以 costs[2] = 1 的代价跳转到下标 2。
 总代价是 2。注意您不能直接从下标 0 跳转到下标 2，因为 nums[0] <= nums[1]。
  

 解释:

 n == nums.length == costs.length
 1 <= n <= 105
 0 <= nums[i], costs[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/jump-game-viii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/jump-game-viii/solution/by-esther29-fgck/
     * @param nums
     * @param costs
     * @return
     */
    public long minCost(int[] nums, int[] costs) {
        // 两个站 记录 min max min 站内是 单调减 的 如果当前peek 要小于等于 说明
        // 中间的点都小于等于 peek 和 num 可以跳转
        Stack<Integer> minStack = new Stack<>();
        // 单调增
        Stack<Integer> maxStack = new Stack<>();
        // 使用 dp i 记录跳转到i 的消耗
        int n = nums.length;
        long[] dp = new long[n];
        // 默认都弄个比较大的值
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        // 最开始就在那
        dp[0] = 0;
        // 从0开始 遍历 找到 min 和max 中 满足条件的两个端点 看看跳到 当前i的最小值
        for (int i = 0; i < n; i++) {
            // 看看能不能跳 中间的点都小于 peek 被干掉了
            while (!minStack.isEmpty() && nums[minStack.peek()] <= nums[i]) {
                int popIndex  = minStack.pop();
                dp[i] = Math.min(dp[i], dp[popIndex] + costs[i]);
            }
            minStack.push(i);

            while (!maxStack.isEmpty() && nums[maxStack.peek()] > nums[i]) {
                int popIndex  = maxStack.pop();
                dp[i] = Math.min(dp[i], dp[popIndex] + costs[i]);
            }
            maxStack.push(i);

        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();


        int[] nums = LeetcodeInputUtils.inputString2IntArray("[3,2,4,4,1]");
        int[] costs = LeetcodeInputUtils.inputString2IntArray("[3,7,6,4,2]");

        long l = solution.minCost(nums, costs);
        System.out.println(l);
        Assert.assertEquals(8, l);
    }
}
