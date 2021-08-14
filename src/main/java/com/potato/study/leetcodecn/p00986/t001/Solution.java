package com.potato.study.leetcodecn.p00986.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. 区间列表的交集
 *
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。
 * 每个区间列表都是成对 不相交 的，并且 已经排序 。

 返回这 两个区间列表的交集 。

 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。

 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。

  

 示例 1：


 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 示例 2：

 输入：firstList = [[1,3],[5,9]], secondList = []
 输出：[]
 示例 3：

 输入：firstList = [], secondList = [[4,8],[10,12]]
 输出：[]
 示例 4：

 输入：firstList = [[1,7]], secondList = [[3,10]]
 输出：[[3,7]]
  

 提示：

 0 <= firstList.length, secondList.length <= 1000
 firstList.length + secondList.length >= 1
 0 <= starti < endi <= 109
 endi < starti+1
 0 <= startj < endj <= 109
 endj < startj+1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/interval-list-intersections
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int index1 = 0;
        int index2 = 0;
        List<int[]> result = new ArrayList<>();
        while (index1 < firstList.length && index2 < secondList.length) {
            int[] interval1 = firstList[index1];
            int[] interval2 = secondList[index1];
            int[] target = new int[] {
                    Math.max(interval1[0], interval2[0]),
                    Math.min(interval1[1], interval2[1])
            };
            if (target[0] <= target[1]) {
                result.add(target);
            }
            // 移动
            if (interval1[1] <= interval2[1]) {
                index1++;
            } else {
                index2++;
            }
        }
        int[][] resultArray = new int[result.size()][2];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }


}
