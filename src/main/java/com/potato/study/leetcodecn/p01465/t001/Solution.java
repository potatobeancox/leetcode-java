package com.potato.study.leetcodecn.p01465.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1465. 切割后面积最大的蛋糕
 *
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中：
 *
 *  horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离
 * verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。由于答案可能是一个很大的数字，因此需要将结果 对 109 + 7 取余 后返回。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * 输出：4
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
 * 示例 2：
 *
 *
 *
 * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * 输出：6
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
 * 示例 3：
 *
 * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * 输出：9
 *  
 *
 * 提示：
 *
 * 2 <= h, w <= 109
 * 1 <= horizontalCuts.length <= min(h - 1, 105)
 * 1 <= verticalCuts.length <= min(w - 1, 105)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * 题目数据保证 horizontalCuts 中的所有元素各不相同
 * 题目数据保证 verticalCuts 中的所有元素各不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        // 遍历 horizontalCuts 两个之前的最大值， verticalCuts 同理
        int max1 = Math.max(0,  horizontalCuts[0] - 0);
        int max2 = Math.max(0,  verticalCuts[0] - 0);

        for (int i = 1; i < horizontalCuts.length; i++) {
            max1 = Math.max(max1, horizontalCuts[i] - horizontalCuts[i-1]);
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            max2 = Math.max(max2, verticalCuts[i] - verticalCuts[i-1]);
        }

        // 最后一个
        max1 = Math.max(max1, h - horizontalCuts[horizontalCuts.length - 1]);
        max2 = Math.max(max2, w - verticalCuts[verticalCuts.length - 1]);
        int mod = 1_000_000_000 + 7;
        return (int)(((long)max1 * max2) % mod);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] horizontalCuts = LeetcodeInputUtils.inputString2IntArray("[3,1]");
        int[] verticalCuts = LeetcodeInputUtils.inputString2IntArray("[1]");
        int i = solution.maxArea(5,4, horizontalCuts, verticalCuts);
        System.out.println(i);
        Assert.assertEquals(6, i);

    }
}
