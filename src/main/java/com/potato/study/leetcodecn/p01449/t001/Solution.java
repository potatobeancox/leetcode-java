package com.potato.study.leetcodecn.p01449.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 1449. 数位成本和为目标值的最大数字
 *
 * 给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：

 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
 总成本必须恰好等于 target 。
 添加的数位中没有数字 0 。
 由于答案可能会很大，请你以字符串形式返回。

 如果按照上述要求无法得到任何整数，请你返回 "0" 。

  

 示例 1：

 输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 输出："7772"
 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要求的数字，但 "7772" 是较大的数字。
 数字     成本
 1  ->   4
 2  ->   3
 3  ->   2
 4  ->   5
 5  ->   6
 6  ->   7
 7  ->   2
 8  ->   5
 9  ->   5
 示例 2：

 输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
 输出："85"
 解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
 示例 3：

 输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
 输出："0"
 解释：总成本是 target 的条件下，无法生成任何整数。
 示例 4：

 输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
 输出："32211"
  

 提示：

 cost.length == 9
 1 <= cost[i] <= 5000
 1 <= target <= 5000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/form-largest-integer-with-digits-that-add-up-to-target
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String largestNumber(int[] cost, int target) {
        // dp i 0 - target 最多有多少个字母
        int[] dp = new int[target+1];
        // 最小值有之后判断是够转换用
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        // 从 1-9 开始 找到每个数字对应的cost 从 cost -target j 计算 dp j的max
        for (int i = 1; i <= 9; i++) {
            // 当前数字的cost
            int price = cost[i-1];
            for (int j = price; j <= target; j++) {
                if (dp[j-price] < 0) {
                    continue;
                }
                dp[j] = Math.max(dp[j], dp[j-price] + 1);
            }
        }
        // 反向从 9-1 找当前是否可以满足从某一个位置转过来 生成StringBuilder 就按 dp 两个点之间的差是不是
        StringBuilder builder = new StringBuilder();
        int len = dp[target];
        if (len <= 0) {
            return "0";
        }
        // 从9-1开始找
        int remind = target;
        for (int i = 0; i < len; i++) {
            for (int j = 9; j >= 1; j--) {
                int price = cost[j-1];
                if (remind < price) {
                    continue;
                }
                // 不是从这里转移过来的
                if (dp[remind] - dp[remind-price] != 1) {
                    continue;
                }
                remind -= price;
                // 判断当前是不是可以要
                builder.append(j);
                break;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] cost = LeetcodeInputUtils.inputString2IntArray("[4,3,2,5,6,7,2,5,5]");
        int target = 9;
        String s = solution.largestNumber(cost, target);
        System.out.println(s);
        Assert.assertEquals("7772", s);

        cost = LeetcodeInputUtils.inputString2IntArray("[2,4,6,2,4,6,4,4,4]");
        target = 5;
        s = solution.largestNumber(cost, target);
        System.out.println(s);
        Assert.assertEquals("0", s);
    }
}
