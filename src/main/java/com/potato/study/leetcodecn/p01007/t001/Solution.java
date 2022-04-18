package com.potato.study.leetcodecn.p01007.t001;

import org.junit.Assert;

import java.util.Stack;

/**
 * 1007. 行相等的最少多米诺旋转
 *
 * 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）

 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。

 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。

 如果无法做到，返回 -1.

  

 示例 1：



 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 输出：2
 解释：
 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
 示例 2：

 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
 输出：-1
 解释：
 在这种情况下，不可能旋转多米诺牌使一行的值相等。
  

 提示：

 1 <= A[i], B[i] <= 6
 2 <= A.length == B.length <= 20000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-domino-rotations-for-equal-row
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        // top 0 和 botom 0 一定有一个 是 先找 top0
        int flipTime = filp(tops, bottoms, tops[0]);
        if (flipTime >= 0) {
            return flipTime;
        }
        return filp(tops, bottoms, bottoms[0]);
    }

    private int filp(int[] tops, int[] bottoms, int side) {
        int time = -1;
        // 判定是不是每个位置都有 side
        int up = 0;
        int down = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != side && bottoms[i] != side) {
                return -1;
            }
            if (tops[i] == bottoms[i]) {
                continue;
            } else if (tops[i] == side) {
                up++;
            } else {
                down++;
            }
        }
        return Math.min(up, down);
    }
}
