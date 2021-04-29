package com.potato.study.leetcodecn.p01710.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1710. 卡车上的最大单元数
 *
 * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：

 numberOfBoxesi 是类型 i 的箱子的数量。
 numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。

 返回卡车可以装载 单元 的 最大 总数。

  

 示例 1：

 输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 输出：8
 解释：箱子的情况如下：
 - 1 个第一类的箱子，里面含 3 个单元。
 - 2 个第二类的箱子，每个里面含 2 个单元。
 - 3 个第三类的箱子，每个里面含 1 个单元。
 可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
 单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
 示例 2：

 输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 输出：91
  

 提示：

 1 <= boxTypes.length <= 1000
 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 1 <= truckSize <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-units-on-a-truck
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 按照 boxTypes【】【1】 排序 降序
     * 然后依次 对 truckSize -- 并计算最大的值
     * @param boxTypes
     * @param truckSize
     * @return
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // boxTypes【】【1】 排序 降序
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        // 然后依次 对 truckSize -- 并计算最大的值
        int totalUnits = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            if (truckSize >= boxTypes[i][0]) {
                totalUnits += boxTypes[i][1] * boxTypes[i][0];
                truckSize -= boxTypes[i][0];
            } else {
                totalUnits += boxTypes[i][1] * truckSize;
                truckSize = 0;
            }
            if (truckSize == 0) {
                break;
            }
        }
        return totalUnits;
    }
}
