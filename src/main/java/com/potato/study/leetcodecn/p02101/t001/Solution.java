package com.potato.study.leetcodecn.p02101.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 2101. 引爆最多的炸弹
 *
 * 给你一个炸弹列表。一个炸弹的 爆炸范围 定义为以炸弹为圆心的一个圆。

 炸弹用一个下标从 0 开始的二维整数数组 bombs 表示，其中 bombs[i] = [xi, yi, ri] 。xi 和 yi 表示第 i 个炸弹的 X 和 Y 坐标，ri 表示爆炸范围的 半径 。

 你需要选择引爆 一个 炸弹。当这个炸弹被引爆时，所有 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。

 给你数组 bombs ，请你返回在引爆 一个 炸弹的前提下，最多 能引爆的炸弹数目。

  

 示例 1：



 输入：bombs = [[2,1,3],[6,1,4]]
 输出：2
 解释：
 上图展示了 2 个炸弹的位置和爆炸范围。
 如果我们引爆左边的炸弹，右边的炸弹不会被影响。
 但如果我们引爆右边的炸弹，两个炸弹都会爆炸。
 所以最多能引爆的炸弹数目是 max(1, 2) = 2 。
 示例 2：



 输入：bombs = [[1,1,5],[10,10,5]]
 输出：1
 解释：
 引爆任意一个炸弹都不会引爆另一个炸弹。所以最多能引爆的炸弹数目为 1 。
 示例 3：



 输入：bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
 输出：5
 解释：
 最佳引爆炸弹为炸弹 0 ，因为：
 - 炸弹 0 引爆炸弹 1 和 2 。红色圆表示炸弹 0 的爆炸范围。
 - 炸弹 2 引爆炸弹 3 。蓝色圆表示炸弹 2 的爆炸范围。
 - 炸弹 3 引爆炸弹 4 。绿色圆表示炸弹 3 的爆炸范围。
 所以总共有 5 个炸弹被引爆。
  

 提示：

 1 <= bombs.length <= 100
 bombs[i].length == 3
 1 <= xi, yi, ri <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/detonate-the-maximum-bombs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 注意计算叠加 并查集
     * @param bombs
     * @return
     */
    public int maximumDetonation(int[][] bombs) {
        // 直接统计以每个炸弹为中心 能引爆的次数 找到最大
        int max = 0;
        UnionFind unionFind = new UnionFind(bombs.length);
        for (int i = 0; i < bombs.length; i++) {
            // 枚举中心
            for (int j = i + 1; j < bombs.length; j++) {
                long dis = (0L + bombs[i][0] - bombs[j][0]) * (bombs[i][0] - bombs[j][0])
                        + (bombs[i][1] - bombs[j][1]) * (bombs[i][1] - bombs[j][1]);
                if (dis <= 1L * bombs[i][2] * bombs[i][2]) {
                    unionFind.union(i, j);
                }
            }
        }
        // 遍历 parent 找到 相同的 数量
        int[] count = new int[bombs.length];






        return max;
    }

    class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find (int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

        public void union (int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 != p2) {
                parent[p1] = p2;
            }
        }


        public int[] getParent() {
            return parent;
        }
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]";
        int[][] bombs = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.maximumDetonation(bombs);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
