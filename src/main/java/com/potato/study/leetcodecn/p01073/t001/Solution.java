package com.potato.study.leetcodecn.p01073.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 1073. 负二进制数相加
 *
 * 给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
 *
 * 数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。
 *
 * 返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * 输出：[1,0,0,0,0]
 * 解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
 * 示例 2：
 *
 * 输入：arr1 = [0], arr2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：arr1 = [0], arr2 = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= arr1.length, arr2.length <= 1000
 * arr1[i] 和 arr2[i] 都是 0 或 1
 * arr1 和 arr2 都没有前导0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/adding-two-negabinary-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1073
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        // 多申请 4个位置  从后往前计算每个位置
        int len1 = arr1.length;
        int len2 = arr2.length;

        int len = 4 + Math.max(len1, len2);
        int[] arr = new int[len];
        int index1 = len1 - 1;
        int index2 = len2 - 1;
        int index = len - 1;

        while (index1 >= 0 || index2 >= 0) {
            int num1 = 0;
            if (index1 >= 0) {
                num1 = arr1[index1];
                index1--;
            }
            int num2 = 0;
            if (index2 >= 0) {
                num2 = arr2[index2];
                index2--;
            }
            arr[index] = num1 + num2 + arr[index];
            // 有了 num 看看 是否需要进位 第一种 num -1 可以减去1 且 当前num等于 2 使用第一种方式
            if (arr[index] > 1 && arr[index-1] > 0) {
                arr[index] = 0;
                arr[index-1]--;
            }
            // 第二种方式
            if (arr[index] > 1) {
                arr[index] = 0;
                arr[index-1]++;
                arr[index-2]++;
            }
            // 如果还是需要 进位 使用第二种方式
            index--;
        }
        // 判断下第一个不为0的位置
        int notZeroIndex = 0;
        while (notZeroIndex < arr.length && arr[notZeroIndex] == 0) {
            notZeroIndex++;
        }
        int[] res = new int[arr.length - notZeroIndex];
        System.arraycopy(arr, notZeroIndex, res, 0, res.length);
        if (res.length == 0) {
            return new int[]{0};
        }
        return res;
    }
}
