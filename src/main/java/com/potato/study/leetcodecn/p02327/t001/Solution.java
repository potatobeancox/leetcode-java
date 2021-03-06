package com.potato.study.leetcodecn.p02327.t001;

import org.junit.Assert;

/**
 * 2327. 知道秘密的人数
 *
 * 在第 1 天，有一个人发现了一个秘密。
 *
 * 给你一个整数 delay ，表示每个人会在发现秘密后的 delay 天之后，每天 给一个新的人 分享 秘密。同时给你一个整数 forget ，表示每个人在发现秘密 forget
 *  天之后会 忘记 这个秘密。一个人 不能 在忘记秘密那一天及之后的日子里分享秘密。
 *
 * 给你一个整数 n ，请你返回在第 n 天结束时，知道秘密的人数。由于答案可能会很大，请你将结果对 109 + 7 取余 后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 6, delay = 2, forget = 4
 * 输出：5
 * 解释：
 * 第 1 天：假设第一个人叫 A 。（一个人知道秘密）
 * 第 2 天：A 是唯一一个知道秘密的人。（一个人知道秘密）
 * 第 3 天：A 把秘密分享给 B 。（两个人知道秘密）
 * 第 4 天：A 把秘密分享给一个新的人 C 。（三个人知道秘密）
 * 第 5 天：A 忘记了秘密，B 把秘密分享给一个新的人 D 。（三个人知道秘密）
 * 第 6 天：B 把秘密分享给 E，C 把秘密分享给 F 。（五个人知道秘密）
 * 示例 2：
 *
 * 输入：n = 4, delay = 1, forget = 3
 * 输出：6
 * 解释：
 * 第 1 天：第一个知道秘密的人为 A 。（一个人知道秘密）
 * 第 2 天：A 把秘密分享给 B 。（两个人知道秘密）
 * 第 3 天：A 和 B 把秘密分享给 2 个新的人 C 和 D 。（四个人知道秘密）
 * 第 4 天：A 忘记了秘密，B、C、D 分别分享给 3 个新的人。（六个人知道秘密）
 *  
 *
 * 提示：
 *
 * 2 <= n <= 1000
 * 1 <= delay < forget <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-people-aware-of-a-secret
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 1. dp[i] 表示 第i天新增的知道秘密的人数
        int[] dp = new int[n+1];
        // 2. 初始化条件 第1天 有1个人知道秘密 即 dp[1] = 1;
        dp[1] = 1;
        int mod = 1_000_000_000 + 7;
        // 3. 转移方程 对于i天来说 dp[i] = sum （n-forget, n-delay] 也就是 具备传播能力的 且还没有忘记的 sum,转移方向从1天开始往后转移,
        for (int i = 2; i <= n; i++) {
            for (int j = Math.max(i - forget + 1, 1); j <= Math.max(i - delay, 0); j++) {
                dp[i] += dp[j];
                dp[i] %= mod;
            }
        }
        // 4. 得到 dp 后 计算 sum dp （n-forget, n-1] （左开右闭区间），也就是还没有忘记的人数之和
        long result = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            result += dp[i];
            result %= mod;
        }
        return (int) (result % mod);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 6;
        int delay = 2;
        int forget = 4;
        int i = solution.peopleAwareOfSecret(n, delay, forget);
        System.out.println(i);
        Assert.assertEquals(5, i);


        n = 4;
        delay = 1;
        forget = 3;
        i = solution.peopleAwareOfSecret(n, delay, forget);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }

}
