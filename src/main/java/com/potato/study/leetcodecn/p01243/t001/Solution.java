package com.potato.study.leetcodecn.p01243.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 1243. 数组变换
 *
 * 首先，给你一个初始数组 arr。然后，每天你都要根据前一天的数组生成一个新的数组。
 *
 * 第 i 天所生成的数组，是由你对第 i-1 天的数组进行如下操作所得的：
 *
 * 假如一个元素小于它的左右邻居，那么该元素自增 1。
 * 假如一个元素大于它的左右邻居，那么该元素自减 1。
 * 首、尾元素 永不 改变。
 * 过些时日，你会发现数组将会不再发生变化，请返回最终所得到的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[6,2,3,4]
 * 输出：[6,3,3,4]
 * 解释：
 * 第一天，数组从 [6,2,3,4] 变为 [6,3,3,4]。
 * 无法再对该数组进行更多操作。
 * 示例 2：
 *
 * 输入：[1,6,3,4,3,5]
 * 输出：[1,4,4,4,4,5]
 * 解释：
 * 第一天，数组从 [1,6,3,4,3,5] 变为 [1,5,4,3,4,5]。
 * 第二天，数组从 [1,5,4,3,4,5] 变为 [1,4,4,4,4,5]。
 * 无法再对该数组进行更多操作。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/array-transformation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> transformArray(int[] arr) {
        int[] newArr;
        boolean arraySame = false;
        do {
            newArr = new int[arr.length];
            newArr[0] = arr[0];
            newArr[arr.length - 1] = arr[arr.length - 1];
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i-1] < arr[i] && arr[i] > arr[i+1]) {
                    newArr[i] = arr[i] - 1;
                } else if (arr[i-1] > arr[i] && arr[i] < arr[i+1]) {
                    newArr[i] = arr[i] + 1;
                } else {
                    newArr[i] = arr[i];
                }
            }
            arraySame = isArraySame(arr, newArr);
            arr = newArr;
        } while (!arraySame);
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        return list;
    }

    private boolean isArraySame(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                6,2,3,4
        };
        List<Integer> list = solution.transformArray(arr);
        // [6, 3, 3, 4]
        System.out.println(list);


        arr = new int[] {
                1,6,3,4,3,5
        };
        list = solution.transformArray(arr);
        // 1,4,4,4,4,5
        System.out.println(list);

        arr = new int[] {
                6,5,8,6,7,7,3,9,8,8,3,1,2,9,8,3
        };
        list = solution.transformArray(arr);
        // [6,6,7,7,7,7,7,8,8,8,3,2,2,8,8,3]
        System.out.println(list);
    }

}
