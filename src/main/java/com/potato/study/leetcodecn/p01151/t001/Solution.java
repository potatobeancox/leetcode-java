package com.potato.study.leetcodecn.p01151.t001;


/**
 * 1151. 最少交换次数来组合所有的 1
 *
 * 给出一个二进制数组 data，你需要通过交换位置，将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: data = [1,0,1,0,1]
 * 输出: 1
 * 解释:
 * 有三种可能的方法可以把所有的 1 组合在一起：
 * [1,1,1,0,0]，交换 1 次；
 * [0,1,1,1,0]，交换 2 次；
 * [0,0,1,1,1]，交换 1 次。
 * 所以最少的交换次数为 1。
 * 示例  2:
 *
 * 输入：data = [0,0,0,1,0]
 * 输出：0
 * 解释：
 * 由于数组中只有一个 1，所以不需要交换。
 * 示例 3:
 *
 * 输入：data = [1,0,1,0,1,0,0,1,1,0,1]
 * 输出：3
 * 解释：
 * 交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
 * 示例 4:
 *
 * 输入: data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
 * 输出: 8
 *  
 *
 * 提示:
 *
 * 1 <= data.length <= 105
 * data[i] == 0 or 1.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minSwaps(int[] data) {
        // 统计一遍有多少 个1 一共
        int oneCount = 0;
        for (int num : data) {
            if (num == 1) {
                oneCount++;
            }
        }
        // 用上面的值 作为窗口 顺序往后扫 看窗口中有多少个 元素
        int windowZeroCount = 0;
        int minMove = oneCount;
        for (int i = 0; i < data.length; i++) {
            if (i < oneCount) {
                if (data[i] == 0) {
                    windowZeroCount++;
                }
            } else {
                // 超过了 需要进行计数判断
                minMove = Math.min(minMove, windowZeroCount);
                if (data[i] == 0) {
                    windowZeroCount++;
                }
                // 左边点
                if (data[i - oneCount] == 0) {
                    windowZeroCount--;
                }
            }
        }
        minMove = Math.min(minMove, windowZeroCount);
        return minMove;
    }
}
