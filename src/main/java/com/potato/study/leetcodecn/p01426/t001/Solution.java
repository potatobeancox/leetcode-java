package com.potato.study.leetcodecn.p01426.t001;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1426. 数元素
 *
 * 给你一个整数数组 arr， 对于元素 x ，只有当 x + 1 也在数组 arr 里时，才能记为 1 个数。

 如果数组 arr 里有重复的数，每个重复的数单独计算。

  

 示例 1：

 输入：arr = [1,2,3]
 输出：2
 解释：1 和 2 被计算次数因为 2 和 3 在数组 arr 里。
 示例 2：

 输入：arr = [1,1,3,3,5,5,7,7]
 输出：0
 解释：所有的数都不算, 因为数组里没有 2、4、6、8。
  

 提示：

 1 <= arr.length <= 1000
 0 <= arr[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/counting-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countElements(int[] arr) {
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        int count = 0;
        set.add(arr[arr.length - 1]);
        for (int i = arr.length - 2; i >= 0; i--) {
            if (set.contains(arr[i] + 1)) {
                count++;
            }
            set.add(arr[i]);
        }
        return count;
    }
}
