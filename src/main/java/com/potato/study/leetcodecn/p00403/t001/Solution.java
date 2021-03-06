package com.potato.study.leetcodecn.p00403.t001;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 403. 青蛙过河
 *
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。

 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。

 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。

 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。

  

 示例 1：

 输入：stones = [0,1,3,5,6,8,12,17]
 输出：true
 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 示例 2：

 输入：stones = [0,1,2,3,4,8,9,11]
 输出：false
 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
  

 提示：

 2 <= stones.length <= 2000
 0 <= stones[i] <= 231 - 1
 stones[0] == 0

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/frog-jump
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    private Boolean[][] canReach;
    /**
     * https://leetcode-cn.com/problems/frog-jump/solution/qing-wa-guo-he-by-leetcode-solution-mbuo/
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        // 用一个 全局的 Bool 形 dp ij 代表 走到i (index) 上一步走了 j 是否可以走到
        int n = stones.length;
        this.canReach = new Boolean[n][n];
        // dfs 求是否可以走到 i
        int i = 0;
        return dfs(stones, i, 0);
    }

    /**
     *
     * @param stones
     * @param i 当前所在位置
     * @param j 上一步走了 j
     * @return 从i开始走 能不能走到终点
     */
    private boolean dfs(int[] stones, int i, int j) {
        // 记录一下 剪枝
        if (null != canReach[i][j]) {
            return canReach[i][j];
        }
        // 当前i就是终点
        if (i == stones.length - 1) {
            canReach[i][j] = true;
            return true;
        }
        // 没到终点 枚举下一步能走多少
        for (int step = j-1; step <= j+1; step++) {
            if (step <= 0) {
                continue;
            }
            // 正经走 能不能有石头 从i+1 到末尾 找一下 下一个石头坐标位置
            int nextIndex = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step);
            if (nextIndex <= 0) {
                continue;
            }
            // 有下一个石头
            boolean res = dfs(stones, nextIndex, step);
            if (res) {
                canReach[i][j] = true;
                return true;
            }
        }
        // 记录
        canReach[i][j] = false;
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                0,1,3,5,6,8,12,17
        };
        boolean b = solution.canCross(arr);
        System.out.println(b);
        Assert.assertEquals(true, b);

        arr = new int[] {
                0,1,2,3,4,8,9,11
        };
        b = solution.canCross(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);


        arr = new int[] {
                0,1,3,6,7
        };
        b = solution.canCross(arr);
        System.out.println(b);
        Assert.assertEquals(false, b);
    }
}
