package com.potato.study.leetcodecn.p01089.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1089. 复写零
 *
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。

 注意：请不要在超过该数组长度的位置写入元素。

 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。

  

 示例 1：

 输入：[1,0,2,3,0,4,5,0]
 输出：null
 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 示例 2：

 输入：[1,2,3]
 输出：null
 解释：调用函数后，输入的数组将被修改为：[1,2,3]
  

 提示：

 1 <= arr.length <= 10000
 0 <= arr[i] <= 9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/duplicate-zeros
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历 找到最后一个留下的元素
     * 然后从后往前 一次搞元素
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int actIndexLen = 0;
        int index = 0;
        while (actIndexLen < arr.length) {
            if (arr[index] == 0) {
                actIndexLen += 2;
            } else {
                actIndexLen++;
            }
            index++;
        }

        int startIndex = index - 1;
        int tmp = arr.length - 1;
        // 如果多了 说明多了一个0
        if (actIndexLen > arr.length) {
            arr[tmp] = 0;
            tmp--;
            startIndex--;
        }
        // 不多就正式开始
        while (startIndex >= 0) {
            int num = arr[startIndex];
            if (num == 0) {
                arr[tmp--] = 0;
                arr[tmp--] = 0;
            } else {
                arr[tmp] = num;
                tmp--;
            }
            startIndex--;
        }
        return;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,0,2,3,0,4,5,0
        };
        solution.duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));
        Assert.assertArrayEquals(new int[] {1,0,0,2,3,0,0,4}, arr);
    }
}
