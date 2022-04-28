package com.potato.study.leetcodecn.p00954.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * 954. 二倍数对数组
 *
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。

  

 示例 1：

 输入：arr = [3,1,3,6]
 输出：false
 示例 2：

 输入：arr = [2,1,2,6]
 输出：false
 示例 3：

 输入：arr = [4,-2,2,-4]
 输出：true
 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
  

 提示：

 0 <= arr.length <= 3 * 104
 arr.length 是偶数
 -105 <= arr[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 954
    public boolean canReorderDoubled(int[] arr) {
        // 对应 arr 从小到大计数 往大找每个小的数的 2倍是否存在 在 map 中进行维护
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            Integer count = countMap.getOrDefault(num, 0);
            count++;
            countMap.put(num, count);
        }
        int zeroCount = countMap.getOrDefault(0, 0);
        if (zeroCount % 2 != 0) {
            return false;
        }
        // 将key 排序
        List<Integer> list = new ArrayList<>(countMap.keySet());
        Collections.sort(list);
        for (int num : list) {
            if (num <= 0) {
                continue;
            }
            if (countMap.get(num) == 0) {
                continue;
            }
            Integer thisCount = countMap.get(num);
            if (countMap.getOrDefault(num * 2, 0) < thisCount) {
                return false;
            }
            countMap.put(num * 2, countMap.get(num * 2) - thisCount);
        }


        Collections.sort(list, Collections.reverseOrder());
        for (int num : list) {
            if (num >= 0) {
                continue;
            }
            if (countMap.get(num) == 0) {
                continue;
            }
            Integer thisCount = countMap.get(num);
            if (countMap.getOrDefault(num * 2, 0) < thisCount) {
                return false;
            }
            countMap.put(num * 2, countMap.get(num * 2) - thisCount);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                4,-2,2,-4
        };
        boolean b = solution.canReorderDoubled(arr);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
