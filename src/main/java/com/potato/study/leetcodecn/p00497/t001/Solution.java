package com.potato.study.leetcodecn.p00497.t001;

import java.util.Random;

/**
 * 497. 非重叠矩形中的随机点
 *
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角角点。设计一个算法来挑选一个随机整数点内的空间所覆盖的一个给定的矩形。矩形周长上的一个点包含在矩形覆盖的空间中。

 在一个给定的矩形覆盖的空间内任何整数点都有可能被返回。

 请注意 ，整数点是具有整数坐标的点。

 实现 Solution 类:

 Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
  

 示例 1：



 输入:
 ["Solution","pick","pick","pick","pick","pick"]
 [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 输出:
 [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]

 解释：
 Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 solution.pick(); // 返回 [1, -2]
 solution.pick(); // 返回 [1, -1]
 solution.pick(); // 返回 [-1, -2]
 solution.pick(); // 返回 [-2, -2]
 solution.pick(); // 返回 [0, 0]
  

 提示：

 1 <= rects.length <= 100
 rects[i].length == 4
 -109 <= ai < xi <= 109
 -109 <= bi < yi <= 109
 xi - ai <= 2000
 yi - bi <= 2000
 所有的矩形不重叠。
 pick 最多被调用 104 次。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/random-point-in-non-overlapping-rectangles
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int[][] rects;
    private Random random;
    private int[] pointCountSum;
    private int[] pointCount;

    public Solution(int[][] rects) {
        this.rects = rects;
        this.random = new Random();
        // 预处理 pointCount 每个区域中 有多少个点
        this.pointCountSum = new int[rects.length];
        this.pointCount = new int[rects.length];

        for (int i = 0; i < rects.length; i++) {
            this.pointCount[i] = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            if (i == 0) {
                this.pointCountSum[i] = this.pointCount[i];
            } else {
                this.pointCountSum[i] = this.pointCountSum[i-1] + this.pointCount[i];
            }
        }

    }

    public int[] pick() {
        // 找到一共有多少个点 每次在0-n 进行随机 如果在里边 确定是 这个index
        int target = 0;
        for (int i = 0; i < pointCountSum.length; i++) {
            int limit = pointCountSum[i];
            int randomNum = random.nextInt(limit);
            if (randomNum < pointCount[i]) {
                target = i;
            }
        }
        // 在点之中进行 random
        int dx = random.nextInt(rects[target][2] - rects[target][0] + 1);
        int dy = random.nextInt(rects[target][3] - rects[target][1] + 1);
        return new int[] {rects[target][0] + dx, rects[target][1] + dy};
    }

}
