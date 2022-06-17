package com.potato.study.leetcodecn.p02300.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 2300. 咒语和药水的成功对数
 *
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 *
 * 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 *
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 输出：[4,0,3]
 * 解释：
 * - 第 0 个咒语：5 * [1,2,3,4,5] = [5,10,15,20,25] 。总共 4 个成功组合。
 * - 第 1 个咒语：1 * [1,2,3,4,5] = [1,2,3,4,5] 。总共 0 个成功组合。
 * - 第 2 个咒语：3 * [1,2,3,4,5] = [3,6,9,12,15] 。总共 3 个成功组合。
 * 所以返回 [4,0,3] 。
 * 示例 2：
 *
 * 输入：spells = [3,1,2], potions = [8,5,8], success = 16
 * 输出：[2,0,2]
 * 解释：
 * - 第 0 个咒语：3 * [8,5,8] = [24,15,24] 。总共 2 个成功组合。
 * - 第 1 个咒语：1 * [8,5,8] = [8,5,8] 。总共 0 个成功组合。
 * - 第 2 个咒语：2 * [8,5,8] = [16,10,16] 。总共 2 个成功组合。
 * 所以返回 [2,0,2] 。
 *  
 *
 * 提示：
 *
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 105
 * 1 <= spells[i], potions[i] <= 105
 * 1 <= success <= 1010
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/successful-pairs-of-spells-and-potions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    /**
     * 2300
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // 将 spells index  potions 排序
        Integer[] indexes = new Integer[spells.length];
        for (int i = 0; i < spells.length; i++) {
            indexes[i] = i;
        }
        // index 对应数字降序
        Arrays.sort(indexes, (o1, o2) -> {
            return Integer.compare(spells[o2], spells[o1]);
        });
        Arrays.sort(potions);
        // 便利 spells 从 0 开始 找 potions 是的 乘法大小 大于等于 success 记录个数
        int[] result = new int[spells.length];
        int index = 0;
        for (int i = 0; i < indexes.length; i++) {
            long spell = spells[indexes[i]];
            while (index < potions.length && spell * potions[index] < success) {
                index++;
            }
            // 计算个数
            if (index >= potions.length) {
                break;
            }
            result[indexes[i]] = potions.length - index;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] spells = new int[] {
                5,1,3
        };
        int[] potions = new int[] {
                1,2,3,4,5
        };
        long success = 7;
        int[] ints = solution.successfulPairs(spells, potions, success);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
                4,0,3
        }, ints);


        spells = new int[] {
                3,1,2
        };
        potions = new int[] {
                8,5,8
        };
        success = 16;
        ints = solution.successfulPairs(spells, potions, success);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
                2,0,2
        }, ints);
    }

}
