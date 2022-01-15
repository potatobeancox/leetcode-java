package com.potato.study.leetcodecn.p00672.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 672. 灯泡开关 Ⅱ
 *
 * 现有一个房间，墙上挂有 n 只已经打开的灯泡和 4 个按钮。在进行了 m 次未知操作后，你需要返回这 n 只灯泡可能有多少种不同的状态。

 假设这 n 只灯泡被编号为 [1, 2, 3 ..., n]，这 4 个按钮的功能如下：

 将所有灯泡的状态反转（即开变为关，关变为开）
 将编号为偶数的灯泡的状态反转
 将编号为奇数的灯泡的状态反转
 将编号为 3k+1 的灯泡的状态反转（k = 0, 1, 2, ...)
 示例 1:

 输入: n = 1, m = 1.
 输出: 2
 说明: 状态为: [开], [关]
 示例 2:

 输入: n = 2, m = 1.
 输出: 3
 说明: 状态为: [开, 关], [关, 开], [关, 关]
 示例 3:

 输入: n = 3, m = 1.
 输出: 4
 说明: 状态为: [关, 开, 关], [开, 关, 开], [关, 关, 关], [关, 开, 开].
 注意： n 和 m 都属于 [0, 1000].

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/bulb-switcher-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/bulb-switcher-ii/solution/deng-pao-kai-guan-ii-by-leetcode/
     * @param n
     * @param presses
     * @return
     */
    public int flipLights(int n, int presses) {
        // 画图看出来前只考虑1-6 include 个位置即可，因为循环了 进一步发现 其实 前3个可以决定所有状态
        n = Math.min(3, n);
        // 如果 press = 0
        if (presses == 0) {
            return 1;
        }
        // 只按了一次 1盏灯 2 次 2盏灯 3次（11，10，01） 3盏灯4次
        if (presses == 1) {
            return n + 1;
        }
        // 按了2次 1-2 2-（00， 10， 01， 11）4次 3盏灯-7
        if (presses == 2) {
            if (n == 1) {
                return 2;
            }
            if (n == 2) {
                return 4;
            }
            if (n == 3) {
                return 7;
            }
        }
        // press == 3 以上 4次操作 只要选了 全部和奇数 相当于执行偶数
        return (1 << n);
    }


}
