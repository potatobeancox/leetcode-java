package com.potato.study.leetcodecn.p02184.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2184. 建造坚实的砖墙的方法数
 *
 * 给你两个整数 height 与width ，表示你要建造的砖墙的高和宽。再给你一个下标从 0 开始的数组 bricks ，其中第 i 块砖的高度是 1 ，宽度为 bricks[i] 。每种砖的数量都是 无限 的，并且砖 不可以 进行旋转。

 墙的每一行必须正好 width 单位长。为了让墙体 坚实 ，除了在首尾的位置，相邻的行砖缝 不能 在同一个位置。

 请你返回建造坚实的砖墙的方法数，由于答案可能很大，需要对 109 + 7 取余 。

  

 示例 1：


 输入：height = 2, width = 3, bricks = [1,2]
 输出：2
 解释：前两图中的两种方法是建造一座坚实砖墙的唯二的方法。注意，第三幅图所展示的不是坚实的砖墙，因为相邻的行在中间的连接点位置相同。
 示例 2：

 输入：height = 1, width = 1, bricks = [5]
 输出：0
 解释：无法建造符合题目要求的砖墙，因为仅有的砖的长度比墙还要长。
  

 提示：

 1 <= height <= 100
 1 <= width <= 10
 1 <= bricks.length <= 10
 1 <= bricks[i] <= 10
 bricks 中所有数字 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-ways-to-build-sturdy-brick-wall
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    /**
     * https://leetcode.cn/problems/number-of-ways-to-build-sturdy-brick-wall/solution/-by-yu-niang-niang-i9ao/
     * @param height
     * @param width
     * @param bricks
     * @return
     */
    public int buildWall(int height, int width, int[] bricks) {
        // 用一个 bool 行 hasBricks i 记录是否有 i长度的砖 因为 width 小于等于 10
        boolean[] hasBrick = new boolean[11];
        // 遍历 bricks 生成 hasBricks
        for (int brick : bricks) {
            hasBrick[brick] = true;
        }
        // 用一个 list 存一下可能分割形成的二进制 就是所有 可以形成的 砖的 缝隙的状态
        List<Integer> validStateList = new ArrayList<>();
        int limit = (1 << (width-1));
        // 对于 0 到 2^width 个状态 判断这个状态是否可以用 hasBricks 形成 是的话 加入上面的list
        for (int i = 0; i < limit; i++) {
            if (canSplit(i, hasBrick, width)) {
                validStateList.add(i);
            }
        }
        // 维护一个 list size size 长度的 是否可以相邻 判断缝隙状态是不是对的上
        int size = validStateList.size();
        if (height == 1) {
            return size;
        }
        // 两个状态是不是能连在一起
        boolean[][] canConnect = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((validStateList.get(i) & validStateList.get(j)) == 0) {
                    canConnect[i][j] = true;
                    canConnect[j][i] = true;
                }
            }
        }
        // 第一层 弄成 validStateList 中每个样子 有1种方式
        int[][] dp = new int[height][size];
        // dp ij 达到 i层 状态为 validStateList j的 可能数量
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // dp 0 处理
                if (canConnect[i][j]) {
                    dp[0][j] = 1;
                }
            }
        }
        // 从第二层开始 统计 能连接的个数
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < height; i++) {
            for (int from = 0; from < size; from++) {
                for (int to = 0; to < size; to++) {
                    // dp 0 处理
                    if (canConnect[from][to]) {
                        dp[i][to] += dp[i-1][from];
                        dp[i][to] %= mod;
                    }
                }
            }
        }
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += dp[height - 1][i];
            total %= mod;
        }
        return total;
    }

    /**
     *
     * @param state 二进制 每个位置代表一个缝隙
     * @param hasBrick
     * @return
     */
    private boolean canSplit(int state, boolean[] hasBrick, int width) {
        // 上一个缝隙位置
        int lastIndex = -1;
        // 找下这个缝隙位置
        for (int i = 0; i < width; i++) {
            // 看看这个位置有没有缝隙
            if ((state & (1 << i)) != 0) {
                // 有缝隙
                int brick = i - lastIndex;
                // 没有这种砖 就直接返回false
                if (!hasBrick[brick]) {
                    return false;
                }
                lastIndex = i;
            }
        }
        return hasBrick[width - lastIndex - 1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int height = 2;
        int width = 3;
        int[] bricks = new int[] {
            1,2
        };
        int i = solution.buildWall(height, width, bricks);
        System.out.println(i);
        Assert.assertEquals(2, i);


        height = 1;
        width = 1;
        bricks = new int[] {
                1
        };
        i = solution.buildWall(height, width, bricks);
        System.out.println(i);
        Assert.assertEquals(1, i);



        height = 1;
        width = 3;
        bricks = new int[] {
                1
        };
        i = solution.buildWall(height, width, bricks);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
