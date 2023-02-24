package com.potato.study.leetcodecn.p02572.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 2572. 无平方子集计数
 给你一个正整数数组 nums 。

 如果数组 nums 的子集中的元素乘积是一个 无平方因子数 ，则认为该子集是一个 无平方 子集。

 无平方因子数 是无法被除 1 之外任何平方数整除的数字。

 返回数组 nums 中 无平方 且 非空 的子集数目。因为答案可能很大，返回对 109 + 7 取余的结果。

 nums 的 非空子集 是可以由删除 nums 中一些元素（可以不删除，但不能全部删除）得到的一个数组。如果构成两个子集时选择删除的下标不同，则认为这两个子集不同。

  

 示例 1：

 输入：nums = [3,4,4,5]
 输出：3
 解释：示例中有 3 个无平方子集：
 - 由第 0 个元素 [3] 组成的子集。其元素的乘积是 3 ，这是一个无平方因子数。
 - 由第 3 个元素 [5] 组成的子集。其元素的乘积是 5 ，这是一个无平方因子数。
 - 由第 0 个和第 3 个元素 [3,5] 组成的子集。其元素的乘积是 15 ，这是一个无平方因子数。
 可以证明给定数组中不存在超过 3 个无平方子集。
 示例 2：

 输入：nums = [1]
 输出：1
 解释：示例中有 1 个无平方子集：
 - 由第 0 个元素 [1] 组成的子集。其元素的乘积是 1 ，这是一个无平方因子数。
 可以证明给定数组中不存在超过 1 个无平方子集。
  

 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 30

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-the-number-of-square-free-subsets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    // 2572
    public int squareFreeSubsets(int[] nums) {
        // 求给定 nums 子集有多少个 没有 平方数因子
        int[] primes = new int[] {
                2,3,5,7,11,13,17,19,23,29
        };
        // 看看每个数字 是否能被 上述整除 并使用 一个 int 记录 每种出现的次数
        int limit = 1 << primes.length;
        // dp i 记录 达到状态位1 能有多少种子集  注意处理 dp 0 每个位置 都可以判断 要不要 最终 减去全不要
        long[] dp = new long[limit];
        // 只选 1 有多少种组合
        int mod = 1_000_000_000 + 7;
        dp[0] = 1;
        for (int num : nums) {
            if (num == 1) {
                dp[0] *= 2;
                dp[0] %= mod;
            }
        }
        // 枚举每个num
        for (int num : nums) {
            // 如果这个 num 等于 本身就是 平方数 就continue掉
            if (num == 1 || num % 4 == 0 || num % 9 == 0 || num % 25 == 0) {
                continue;
            }
            // 从 1开始 枚举 dp i 的位置 每个i都要看看 内部
            for (int i = 0; i < limit; i++) {
                // num 和当前状态是不是能组合
                int state = getState(num);
                // 如果当前 i 和 state 没有公共的素数因子 那么 i 可以加入
                if ((i & state) == 0) {
                    int target = (i | state);
                    dp[target] += dp[i];
                    dp[target] %= mod;
                }
            }
        }
        // 返回 dp0 - dp 1<<8 个数字 也是从 0判断到最大 从大到小处理
        long sum = 0;
        for (int i = 0; i < dp.length; i++) {
            if (i == 0) {
                sum += (dp[i] - 1);
            } else {
                sum += dp[i];
            }
            sum %= mod;
        }
        return (int) sum;
    }


    private int getState(int num) {
        // 因为 nums 每个数字 都在 30以内 {2,3,5,7,11,13,17,19}
        int[] primes = new int[] {
                2,3,5,7,11,13,17,19,23,29
        };
        int state = 0;
        for (int i = 0; i < primes.length; i++) {
            if (num % primes[i] == 0) {
                state |= (1 << i);
            }
        }
        return state;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,4,4,5
        };
        int i = solution.squareFreeSubsets(nums);
        System.out.println(i);
        Assert.assertEquals(3, i);


        nums = new int[] {
                1
        };
        i = solution.squareFreeSubsets(nums);
        System.out.println(i);
        Assert.assertEquals(1, i);

        nums = LeetcodeInputUtils.inputString2IntArray("[11,2,19,7,9,27]");
        i = solution.squareFreeSubsets(nums);
        System.out.println(i);
        Assert.assertEquals(15, i);
    }

}
