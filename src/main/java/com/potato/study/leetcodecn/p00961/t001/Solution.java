package com.potato.study.leetcodecn.p00961.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 961. 重复 N 次的元素
 *
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。

 返回重复了 N 次的那个元素。

  

 示例 1：

 输入：[1,2,3,3]
 输出：3
 示例 2：

 输入：[2,1,2,5,3,2]
 输出：2
 示例 3：

 输入：[5,1,5,2,5,3,5,4]
 输出：5
  

 提示：

 4 <= A.length <= 10000
 0 <= A[i] < 10000
 A.length 为偶数

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 排序 不管怎样 index = N的位置 一定是那个值
     * @param arr
     * @return
     */
    public int repeatedNTimes(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length / 2;
        // 如果在左端点是 0 和 n-1位置相等
        if (arr[0] == arr[n-1]) {
            return arr[0];
        }
        // 如果是在右端点
        if (arr[n] == arr[arr.length - 1]) {
            return arr[n];
        }
        // 否则返回 n-1 个元素 （中间哪个）
        return arr[n-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,2,3,3
        };
        int i = solution.repeatedNTimes(nums);
        System.out.println(i);
        Assert.assertEquals(3, i);


        nums = new int[] {
                5,1,5,2,5,3,5,4
        };
        i = solution.repeatedNTimes(nums);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }


}
