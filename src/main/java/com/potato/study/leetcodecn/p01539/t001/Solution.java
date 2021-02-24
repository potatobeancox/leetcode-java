package com.potato.study.leetcodecn.p01539.t001;

/**
 * 1539. 第 k 个缺失的正整数
 *
 * 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。

 请你找到这个数组里第 k 个缺失的正整数。

  

 示例 1：

 输入：arr = [2,3,4,7,11], k = 5
 输出：9
 解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
 示例 2：

 输入：arr = [1,2,3,4], k = 2
 输出：6
 解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
  

 提示：

 1 <= arr.length <= 1000
 1 <= arr[i] <= 1000
 1 <= k <= 1000
 对于所有 1 <= i < j <= arr.length 的 i 和 j 满足 arr[i] < arr[j] 

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/kth-missing-positive-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 直接比较计数
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int count = 0;
        int index = 0;
        int num = 1;
        while (index < arr.length) {
            if (num < arr[index]) {
                count++;
                if (count == k) {
                    return num;
                }
                num++;
            } else if (num == arr[index]) {
                num++;
                index++;
            } else {
                break;
            }
        }

        while (count < k) {
            count++;
            if (count == k) {
                return num;
            }
            num++;
        }
        return num;
    }
}
