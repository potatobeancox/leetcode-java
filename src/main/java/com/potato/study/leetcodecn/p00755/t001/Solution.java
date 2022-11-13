package com.potato.study.leetcodecn.p00755.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 755. 倒水
 *
 * 给出一个地形高度图， heights[i] 表示该索引处的高度。每个索引的宽度为 1。在 V 个单位的水落在索引 K 处以后，每个索引位置有多少水？
 *
 * 水最先会在索引 K 处下降并且落在该索引位置的最高地形或水面之上。然后按如下方式流动：
 *
 * 如果液滴最终可以通过向左流动而下降，则向左流动。
 * 否则，如果液滴最终可以通过向右流动而下降，则向右流动。
 * 否则，在当前的位置上升。
 * 这里，“最终下降” 的意思是液滴如果按此方向移动的话，最终可以下降到一个较低的水平。而且，“水平”的意思是当前列的地形的高度加上水的高度。
 * 我们可以假定在数组两侧的边界外有无限高的地形。而且，不能有部分水在多于 1 个的网格块上均匀分布 - 每个单位的水必须要位于一个块中。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：heights = [2,1,1,2,1,2,2], V = 4, K = 3
 * 输出：[2,2,2,3,2,2,2]
 * 解释：
 * #       #
 * #       #
 * ##  # ###
 * #########
 *  0123456    <- 索引
 *
 * 第一个水滴降落在索引 K = 3 上：
 *
 * #       #
 * #   w   #
 * ##  # ###
 * #########
 *  0123456
 *
 * 当向左或向右移动时，水可以移动到相同或更低的高度。When moving left or right, the water can only move to the same level or a lower level.
 * （从水平上看，意思是该列的地形高度加上水的高度）
 * 由于向左移动可以最终下落，因此向左移动。
 * （一个水滴 “下落” 的意思是可以相比之前可以进入更低的高度）
 *
 * #       #
 * #       #
 * ## w# ###
 * #########
 *  0123456
 *
 * 由于向左移动不会使其降落，所以停在该位置上。下一个水滴下落：
 *
 * #       #
 * #   w   #
 * ## w# ###
 * #########
 *  0123456
 *
 *
 * 由于新水滴向左移动可以最终下落，因此向左移动。
 * 注意水滴仍然是优先选择向左移动，
 * 尽管可以向右移动（而且向右移动可以下落更快）
 *
 *
 * #       #
 * #  w    #
 * ## w# ###
 * #########
 *  0123456
 *
 * #       #
 * #       #
 * ##ww# ###
 * #########
 *  0123456
 *
 * 经过刚才的阶段后，第三个水滴下落。
 * 由于向左移动不会最终下落，因此尝试向右移动。
 * 由于向右移动可以最终下落，因此向右移动。
 *
 *
 * #       #
 * #   w   #
 * ##ww# ###
 * #########
 *  0123456
 *
 * #       #
 * #       #
 * ##ww#w###
 * #########
 *  0123456
 *
 * 最终，第四个水滴下落。
 * 由于向左移动不会最终下落，因此尝试向右移动。
 * 由于向右移动不会最终下落，因此停在当前位置：
 *
 * #       #
 * #   w   #
 * ##ww#w###
 * #########
 *  0123456
 *
 * 最终的答案为 [2,2,2,3,2,2,2]:
 *
 *     #
 *  #######
 *  #######
 *  0123456
 * 示例 2：
 *
 * 输入：heights = [1,2,3,4], V = 2, K = 2
 * 输出：[2,3,3,4]
 * 解释：
 * 最后的水滴落在索引 1 位置，因为继续向左移动不会使其下降到更低的高度。
 * 示例 3：
 *
 * 输入：heights = [3,1,3], V = 5, K = 1
 * 输出：[4,4,4]
 *  
 *
 * 注：
 *
 * heights 的长度为 [1, 100] ，并且每个数的范围为[0, 99]。
 * V 的范围 [0, 2000]。
 * K 的范围 [0, heights.length - 1]。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pour-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] pourWater(int[] heights, int volume, int k) {
        // 遍历 volume 次 依次从 k往左找和 k往右找
        for (int i = 0; i < volume; i++) {
            int leftIndex = k;
            // 先往左 找到 局部最小值
            while (leftIndex-1 >= 0 && heights[leftIndex-1] <= heights[leftIndex]) {
                leftIndex--;
            }
            // 找下右边的index
            int rightIndex = k;
            while (rightIndex+1 < heights.length && heights[rightIndex+1] <= heights[rightIndex]) {
                rightIndex++;
            }
            // 判断一下哪个小
            if (leftIndex == rightIndex) {
                // 都在中间
                heights[k]++;
                continue;
            }
            // 如果都一样
            if (heights[leftIndex] == heights[rightIndex] && heights[rightIndex] == heights[k]) {
                // 都在中间
                heights[k]++;
                continue;
            }

            // 两边哪个小
            if (heights[leftIndex] <= heights[rightIndex]) {
                heights[leftIndex]++;
            } else {
                heights[rightIndex]++;
            }
        }
        return heights;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = LeetcodeInputUtils.inputString2IntArray("[2,1,1,2,1,2,2]");
        int volume = 4;
        int k = 3;
        int[] ints = solution.pourWater(heights, volume, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
                2,2,2,3,2,2,2
        }, ints);

        heights = LeetcodeInputUtils.inputString2IntArray("[1,2,3,4]");
        volume = 2;
        k = 2;
        ints = solution.pourWater(heights, volume, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
                2,3,3,4
        }, ints);
    }
}