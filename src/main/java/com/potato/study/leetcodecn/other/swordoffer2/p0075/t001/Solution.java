package com.potato.study.leetcodecn.other.swordoffer2.p0075.t001;

import java.util.*;

/**
 * 剑指 Offer II 075. 数组相对排序
 *
 * 给定两个数组，arr1 和 arr2，

 arr2 中的元素各不相同
 arr2 中的每个元素都出现在 arr1 中
 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

  

 示例：

 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 输出：[2,2,2,1,4,3,3,9,6,7,19]
  

 提示：

 1 <= arr1.length, arr2.length <= 1000
 0 <= arr1[i], arr2[i] <= 1000
 arr2 中的元素 arr2[i] 各不相同
 arr2 中的每个元素 arr2[i] 都出现在 arr1 中


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/0H97ZC
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 将 arr2 放入一个 map
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr2) {
            map.put(num, 0);
        }
        // 遍历 arr1 如果在map 中 计数，否则 按照顺序放在list 中
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                list.add(num);
            }
        }
        // 生成数组 遍历 arr2 按照map 个数 生成数组，最后 按照顺序添加list
        int insertIndex = 0;
        for (int i = 0; i < arr2.length; i++) {
            int count = map.get(arr2[i]);
            for (int j = 0; j < count; j++) {
                arr1[insertIndex] = arr2[i];
                insertIndex++;
            }
        }

        Collections.sort(list);

        for (int num : list) {
            arr1[insertIndex++] = num;
        }

        return arr1;
    }
}
