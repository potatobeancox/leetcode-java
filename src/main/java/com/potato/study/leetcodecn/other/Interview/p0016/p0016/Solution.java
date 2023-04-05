package com.potato.study.leetcodecn.other.Interview.p0016.p0016;


/**
 * 面试题 16.16. 部分排序
 *
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。

 示例：

 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 输出： [3,9]
 提示：

 0 <= len(array) <= 1000000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sub-sort-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] subSort(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // 分别维护左边和右边的 最大值
        int n = array.length;
        int left = -1;
        int right = -1;
        for (int i = 0; i < n; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = array[i];
            }

            if (array[n-1-i] > min) {
                left = n-1-i;
            } else {
                min = array[n-1-i];
            }
        }
        return new int[] {left, right};
    }
}
