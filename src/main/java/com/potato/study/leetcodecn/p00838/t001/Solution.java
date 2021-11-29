package com.potato.study.leetcodecn.p00838.t001;

import org.junit.Assert;

/**
 * 838. 推多米诺
 *
 * 一行中有 N 张多米诺骨牌，我们将每张多米诺骨牌垂直竖立。

 在开始时，我们同时把一些多米诺骨牌向左或向右推。



 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。

 同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。

 如果同时有多米诺骨牌落在一张垂直竖立的多米诺骨牌的两边，由于受力平衡， 该骨牌仍然保持不变。

 就这个问题而言，我们会认为正在下降的多米诺骨牌不会对其它正在下降或已经下降的多米诺骨牌施加额外的力。

 给定表示初始状态的字符串 "S" 。如果第 i 张多米诺骨牌被推向左边，则 S[i] = 'L'；如果第 i 张多米诺骨牌被推向右边，则 S[i] = 'R'；如果第 i 张多米诺骨牌没有被推动，则 S[i] = '.'。

 返回表示最终状态的字符串。

 示例 1：

 输入：".L.R...LR..L.."
 输出："LL.RR.LLRRLL.."
 示例 2：

 输入："RR.L"
 输出："RR.L"
 说明：第一张多米诺骨牌没有给第二张施加额外的力。
 提示：

 0 <= N <= 10^5
 表示多米诺骨牌状态的字符串只含有 'L'，'R'; 以及 '.';

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/push-dominoes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/push-dominoes/solution/tui-duo-mi-nuo-by-leetcode/
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        // 分别维护2个数组，记录 距离当前位置最近的左边的骨牌和，最近的右边的
        int n = dominoes.length();
        // 受力情况，大于0 往右 倒，否则往左倒
        int[] force = new int[n];
        int currentForce = 0;
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == 'L') {
                currentForce = 0;
            } else if (dominoes.charAt(i) == 'R') {
                currentForce = n;
            } else {
                currentForce = Math.max(0, currentForce - 1);
            }
            force[i] += currentForce;
        }

        // 往左边倒的位置
        currentForce = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                currentForce = n;
            } else if (dominoes.charAt(i) == 'R') {
                currentForce = 0;
            } else {
                currentForce = Math.max(0, currentForce - 1);
            }
            force[i] -= currentForce;
        }
        // 遍历 dominoes 判断往需要往那边倒
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (force[i] > 0) {
                builder.append("R");
            } else if (force[i] < 0) {
                builder.append("L");
            } else {
                builder.append(".");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String dominoes = ".L.R...LR..L..";
        String s = solution.pushDominoes(dominoes);
        System.out.println(s);
        Assert.assertEquals("LL.RR.LLRRLL..", s);
    }
}
