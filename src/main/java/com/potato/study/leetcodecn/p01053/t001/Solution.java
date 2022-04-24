package com.potato.study.leetcodecn.p01053.t001;

import org.junit.Assert;

/**
 * 1053. 交换一次的先前排列
 *
 * 给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。

 如果无法这么操作，就请返回原数组。

  

 示例 1：

 输入：arr = [3,2,1]
 输出：[3,1,2]
 解释：交换 2 和 1
 示例 2：

 输入：arr = [1,1,5]
 输出：[1,1,5]
 解释：已经是最小排列
 示例 3：

 输入：arr = [1,9,4,6,7]
 输出：[1,7,4,6,9]
 解释：交换 9 和 7
 示例 4：

 输入：arr = [3,1,1,3]
 输出：[1,3,1,3]
 解释：交换 1 和 3
  

 提示：

 1 <= arr.length <= 104
 1 <= arr[i] <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/previous-permutation-with-one-swap
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] prevPermOpt1(int[] arr) {
        // 从左往右找到逆序位置 i > i+1
        int i = arr.length - 2;
        for (; i >= 0; i--) {
            if (arr[i] > arr[i+1]) {
                break;
            }
        }
        if (i == -1) {
            return arr;
        }
        // 从i 往后找到 小于 i的最大值，
        int maxIndex = i + 1;
        for (int j = i + 2; j < arr.length; j++) {
            if (arr[maxIndex] < arr[j] && arr[i] > arr[j]) {
                maxIndex = j;
            }
        }
        // 交换
        int tmp = arr[maxIndex];
        arr[maxIndex] = arr[i];
        arr[i] = tmp;
        return arr;
    }
}
