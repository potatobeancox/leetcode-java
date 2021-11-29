package com.potato.study.leetcodecn.p02078.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 2078. 两栋颜色不同且距离最远的房子
 *
 * 街上有 n 栋房子整齐地排成一列，每栋房子都粉刷上了漂亮的颜色。给你一个下标从 0 开始且长度为 n 的整数数组 colors ，其中 colors[i] 表示第  i 栋房子的颜色。

 返回 两栋 颜色 不同 房子之间的 最大 距离。

 第 i 栋房子和第 j 栋房子之间的距离是 abs(i - j) ，其中 abs(x) 是 x 的绝对值。

  

 示例 1：



 输入：colors = [1,1,1,6,1,1,1]
 输出：3
 解释：上图中，颜色 1 标识成蓝色，颜色 6 标识成红色。
 两栋颜色不同且距离最远的房子是房子 0 和房子 3 。
 房子 0 的颜色是颜色 1 ，房子 3 的颜色是颜色 6 。两栋房子之间的距离是 abs(0 - 3) = 3 。
 注意，房子 3 和房子 6 也可以产生最佳答案。
 示例 2：



 输入：colors = [1,8,3,8,3]
 输出：4
 解释：上图中，颜色 1 标识成蓝色，颜色 8 标识成黄色，颜色 3 标识成绿色。
 两栋颜色不同且距离最远的房子是房子 0 和房子 4 。
 房子 0 的颜色是颜色 1 ，房子 4 的颜色是颜色 3 。两栋房子之间的距离是 abs(0 - 4) = 4 。
 示例 3：

 输入：colors = [0,1]
 输出：1
 解释：两栋颜色不同且距离最远的房子是房子 0 和房子 1 。
 房子 0 的颜色是颜色 0 ，房子 1 的颜色是颜色 1 。两栋房子之间的距离是 abs(0 - 1) = 1 。
  

 提示：

 n == colors.length
 2 <= n <= 100
 0 <= colors[i] <= 100
 生成的测试数据满足 至少 存在 2 栋颜色不同的房子

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-furthest-houses-with-different-colors
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxDistance(int[] colors) {
        // 记录每种非这个颜色的第一个位置的index， 初始为 -1
        int[] firstDiffColorIndex = new int[101];
        Arrays.fill(firstDiffColorIndex, -1);
        // 遍历 color 对于 -1的 进行更新
        int max = 0;
        for (int i = 0; i < colors.length; i++) {
            int currentColor = colors[i];
            if (firstDiffColorIndex[currentColor] != -1) {
                max = Math.max(max, i - firstDiffColorIndex[currentColor]);
            }
            // 更新 所有为 -1的 且不是当前显色的开始值
            for (int j = 0; j < 101; j++) {
                if (j == currentColor || firstDiffColorIndex[j] != -1) {
                    continue;
                }
                firstDiffColorIndex[j] = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,1,1,6,1,1,1
        };
        int distance = solution.maxDistance(arr);
        System.out.println(distance);
        Assert.assertEquals(3, distance);
    }


}
