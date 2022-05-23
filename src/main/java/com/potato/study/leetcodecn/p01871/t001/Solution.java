package com.potato.study.leetcodecn.p01871.t001;

import org.junit.Assert;

/**
 * 1871. 跳跃游戏 VII
 *
 * 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：

 i + minJump <= j <= min(i + maxJump, s.length - 1) 且
 s[j] == '0'.
 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。

  

 示例 1：

 输入：s = "011010", minJump = 2, maxJump = 3
 输出：true
 解释：
 第一步，从下标 0 移动到下标 3 。
 第二步，从下标 3 移动到下标 5 。
 示例 2：

 输入：s = "01101110", minJump = 2, maxJump = 3
 输出：false
  

 提示：

 2 <= s.length <= 105
 s[i] 要么是 '0' ，要么是 '1'
 s[0] == '0'
 1 <= minJump <= maxJump < s.length

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/jump-game-vii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/jump-game-vii/solution/jian-dan-de-dong-tai-gui-hua-zhuang-tai-260xm/
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        // 状态数组记录 当前点是否能够达到
        boolean[] visit = new boolean[s.length()];
        visit[0] = true;
        // 枚举开始 位置 如果是走不到的点 continue 下个位置
        int hasNotVisitIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            // 这个点之前走不到
            if (!visit[i]) {
                continue;
            }
            // 最晚的位置
            int startIndex = Math.max(minJump + i, hasNotVisitIndex);
            int endIndex = Math.min(maxJump + i, s.length() - 1);
            // 如果是 能够到达的点 找到 minJump 和已经访问过的最大位置 maxJump 和最后一个位置的最大位置
            for (int j = startIndex; j <= endIndex; j++) {
                visit[j] = (s.charAt(j) == '0');
            }
            hasNotVisitIndex = endIndex+1;
        }
        // 如果 si 是 0 说明能够到达，否则不能到达
        return visit[s.length() - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "011010";
        int minJump = 2;
        int maxJump = 3;
        boolean b = solution.canReach(s, minJump, maxJump);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
