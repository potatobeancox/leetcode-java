package com.potato.study.leetcodecn.p02079.t001;

import org.junit.Assert;

import java.util.concurrent.RecursiveTask;

/**
 * 2079. 给植物浇水
 *
 * 你打算用一个水罐给花园里的 n 株植物浇水。植物排成一行，从左到右进行标记，编号从 0 到 n - 1 。其中，第 i 株植物的位置是 x = i 。x = -1 处有一条河，你可以在那里重新灌满你的水罐。

 每一株植物都需要浇特定量的水。你将会按下面描述的方式完成浇水：

 按从左到右的顺序给植物浇水。
 在给当前植物浇完水之后，如果你没有足够的水 完全 浇灌下一株植物，那么你就需要返回河边重新装满水罐。
 你 不能 提前重新灌满水罐。
 最初，你在河边（也就是，x = -1），在 x 轴上每移动 一个单位 都需要 一步 。

 给你一个下标从 0 开始的整数数组 plants ，数组由 n 个整数组成。其中，plants[i] 为第 i 株植物需要的水量。另有一个整数 capacity 表示水罐的容量，返回浇灌所有植物需要的 步数 。

  

 示例 1：

 输入：plants = [2,2,3,3], capacity = 5
 输出：14
 解释：从河边开始，此时水罐是装满的：
 - 走到植物 0 (1 步) ，浇水。水罐中还有 3 单位的水。
 - 走到植物 1 (1 步) ，浇水。水罐中还有 1 单位的水。
 - 由于不能完全浇灌植物 2 ，回到河边取水 (2 步)。
 - 走到植物 2 (3 步) ，浇水。水罐中还有 2 单位的水。
 - 由于不能完全浇灌植物 3 ，回到河边取水 (3 步)。
 - 走到植物 3 (4 步) ，浇水。
 需要的步数是 = 1 + 1 + 2 + 3 + 3 + 4 = 14 。
 示例 2：

 输入：plants = [1,1,1,4,2,3], capacity = 4
 输出：30
 解释：从河边开始，此时水罐是装满的：
 - 走到植物 0，1，2 (3 步) ，浇水。回到河边取水 (3 步)。
 - 走到植物 3 (4 步) ，浇水。回到河边取水 (4 步)。
 - 走到植物 4 (5 步) ，浇水。回到河边取水 (5 步)。
 - 走到植物 5 (6 步) ，浇水。
 需要的步数是 = 3 + 3 + 4 + 4 + 5 + 5 + 6 = 30 。
 示例 3：

 输入：plants = [7,7,7,7,7,7,7], capacity = 8
 输出：49
 解释：每次浇水都需要重新灌满水罐。
 需要的步数是 = 1 + 1 + 2 + 2 + 3 + 3 + 4 + 4 + 5 + 5 + 6 + 6 + 7 = 49 。
  

 提示：

 n == plants.length
 1 <= n <= 1000
 1 <= plants[i] <= 106
 max(plants[i]) <= capacity <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/watering-plants
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int wateringPlants(int[] plants, int capacity) {
        int pos = -1;
        int current = capacity;
        // 遍历 每个 plants 记录当前位置 current 初始为 -1
        int totalStep = 0;
        for (int i = 0; i < plants.length; i++) {
            // 判断当前水量是否满足 大于等于 x可以浇水数量，大于浇水，下雨等于 回到河边蓄水
            if (current >= plants[i]) {
                // 当前水是否满足
                totalStep++;
            } else {
                // 不满足，先回到河边，在回来
                totalStep += (pos + 1);
                current = capacity;
                totalStep += (i + 1);
                // 浇水
            }
            current -= plants[i];
            pos = i;
        }
        return totalStep;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] plants = new int[] {2,2,3,3};
        int capacity = 5;
        int i = solution.wateringPlants(plants, capacity);
        System.out.println(i);
        Assert.assertEquals(14, i);
    }


}
