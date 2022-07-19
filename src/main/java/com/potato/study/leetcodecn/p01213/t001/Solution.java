package com.potato.study.leetcodecn.p01213.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 1213. 三个有序数组的交集
 *
 * 给出三个均为 严格递增排列 的整数数组 arr1，arr2 和 arr3。返回一个由 仅 在这三个数组中 同时出现 的整数所构成的有序数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * 输出: [1,5]
 * 解释: 只有 1 和 5 同时在这三个数组中出现.
 * 示例 2:
 *
 * 输入: arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
 * 输出: []
 *  
 *
 * 提示：
 *
 * 1 <= arr1.length, arr2.length, arr3.length <= 1000
 * 1 <= arr1[i], arr2[i], arr3[i] <= 2000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/intersection-of-three-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1213
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int index2 = 0;
        int index3 = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            int index1 = i;
            while (index2 < arr2.length && arr2[index2] < arr1[index1]) {
                index2++;
            }
            if (index2 >= arr2.length) {
                break;
            }
            while (index3 < arr3.length && arr3[index3] < arr1[index1]) {
                index3++;
            }
            if (index3 >= arr3.length) {
                break;
            }
            if (arr1[index1] == arr2[index2]
                    && arr2[index2] == arr3[index3]) {
                list.add(arr1[index1]);
            }
        }
        return list;
    }
}
