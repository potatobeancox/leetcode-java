package com.potato.study.leetcodecn.p00904.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 904. 水果成篮
 *
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 *
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 *
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 *
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 *  
 *
 * 提示：
 *
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fruit-into-baskets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int totalFruit(int[] fruits) {
        // 滑动窗口 使用一个 map 记录 窗口内部的 元素种类数 和最后一次出现的位置
        Map<Integer, Integer> lastIndexMap = new HashMap<>();
        // 遍历 fruits 如果 map 个数 还没到3种 更新map 最后出现的元素个数
        int windowSize = 0;
        int maxSize = 0;
        for (int i = 0; i < fruits.length; i++) {
            lastIndexMap.put(fruits[i], i);
            windowSize++;
            // 一旦超过了 拿到开始位置 往后的一个位置 修改窗口大小删除
            if (lastIndexMap.size() == 3) {
                // 找到最先结束的index
                int lastAppearIndex = -1;
                for (int lastIndex : lastIndexMap.values()) {
                    if (lastAppearIndex == -1) {
                        lastAppearIndex = lastIndex;
                        continue;
                    }
                    if (lastIndex < lastAppearIndex) {
                        lastAppearIndex = lastIndex;
                    }
                }

                // pre 最后一次出现的index  删除最早出现完了的
                windowSize = i - lastAppearIndex;
                lastIndexMap.remove(fruits[lastAppearIndex]);
            }
            // 计算最大的窗口长度
            maxSize = Math.max(maxSize, windowSize);
        }
        return maxSize;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] fruits = new int[] {
                1,0,1,4,1,4,1,2,3
        };
        int i = solution.totalFruit(fruits);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }

}
