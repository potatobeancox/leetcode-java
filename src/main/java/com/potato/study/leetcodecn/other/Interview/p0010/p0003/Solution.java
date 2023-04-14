package com.potato.study.leetcodecn.other.Interview.p0010.p0003;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 10.03. 搜索旋转数组
 *
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 * 示例2:
 *
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 * 提示:
 *
 * arr 长度范围在[1, 1000000]之间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-rotate-array-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 10.03
    public int search(int[] arr, int target) {
        // 用笔记本试了一下 无论怎么旋转都相当于 只找到枢轴 旋转一次
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 如果当前找到了 一直往前找 看看是不是最开始的
            if (target == arr[mid]) {
                while (mid > 0 && arr[mid - 1] == arr[mid]) {
                    mid--;
                }
                result = mid;
            } else if (target > arr[mid]) {
                // 如果右边边是升序的 那么往左走 否则
                if (arr[mid] <= target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    // 往左边找
                    right = mid - 1;
                }
            } else {
                // target < arr[mid]
                if (arr[left] <= target && target <= arr[mid]) {
                    // 如果左边是升序且 target在左边位置
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return result;
    }
}
