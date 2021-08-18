package com.potato.study.leetcodecn.p01287.t001;


import org.junit.Assert;

import java.util.*;

/**
 * 1287. 有序数组中出现次数超过25%的元素
 *
 * 你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。

 请你找到并返回这个整数

  

 示例：

 输入：arr = [1,2,2,6,6,6,6,7,10]
 输出：6
  

 提示：

 1 <= arr.length <= 10^4
 0 <= arr[i] <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findSpecialInteger(int[] arr) {
        int target = arr.length / 4;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
            if (count > target) {
                return num;
            }
        }
        return -1;
    }
}
