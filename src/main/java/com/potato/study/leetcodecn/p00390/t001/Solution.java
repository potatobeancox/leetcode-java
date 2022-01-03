package com.potato.study.leetcodecn.p00390.t001;

import org.junit.Assert;

/**
 * 390. 消除游戏
 *
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：

 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 给你整数 n ，返回 arr 最后剩下的数字。

  

 示例 1：

 输入：n = 9
 输出：6
 解释：
 arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 arr = [2, 4, 6, 8]
 arr = [2, 6]
 arr = [6]
 示例 2：

 输入：n = 1
 输出：1
  

 提示：

 1 <= n <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/elimination-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 模拟法
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        // 个数 当前剩余
        int length = n;
        // 当前开始删除位置
        int start = 1;
        // 当前进行遍历删除 一次走多少步数
        int step = 1;
        // 当前遍历 轮次 按说 偶数位从左边开始 奇数为从右边开始
        int loopIndex = 0;

        while (length > 1) {
            // 计算下次开始的 start
            if (loopIndex % 2 == 1) {
                if (length % 2 == 1) {
                    start += step;
                }
            } else {
                start += step;
            }
            step *= 2;
            loopIndex++;
            length /= 2;
        }
        // 当前 length == 1 时，开始删除位置就是剩余的位置
        return start;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.lastRemaining(9);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }
}
