package com.potato.study.leetcodecn.p02053.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 2053. 数组中第 K 个独一无二的字符串
 *
 * 独一无二的字符串 指的是在一个数组中只出现过 一次 的字符串。
 *
 * 给你一个字符串数组 arr 和一个整数 k ，请你返回 arr 中第 k 个 独一无二的字符串 。如果 少于 k 个独一无二的字符串，那么返回 空字符串 "" 。
 *
 * 注意，按照字符串在原数组中的 顺序 找到第 k 个独一无二字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：arr = ["d","b","c","b","c","a"], k = 2
 * 输出："a"
 * 解释：
 * arr 中独一无二字符串包括 "d" 和 "a" 。
 * "d" 首先出现，所以它是第 1 个独一无二字符串。
 * "a" 第二个出现，所以它是 2 个独一无二字符串。
 * 由于 k == 2 ，返回 "a" 。
 * 示例 2:
 *
 * 输入：arr = ["aaa","aa","a"], k = 1
 * 输出："aaa"
 * 解释：
 * arr 中所有字符串都是独一无二的，所以返回第 1 个字符串 "aaa" 。
 * 示例 3：
 *
 * 输入：arr = ["a","b","a"], k = 3
 * 输出：""
 * 解释：
 * 唯一一个独一无二字符串是 "b" 。由于少于 3 个独一无二字符串，我们返回空字符串 "" 。
 *  
 *
 * 提示：
 *
 * 1 <= k <= arr.length <= 1000
 * 1 <= arr[i].length <= 5
 * arr[i] 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-distinct-string-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2053
    public String kthDistinct(String[] arr, int k) {
        // 使用 map 记录 只出现一次 记录出现的index
        Map<String, Integer> valueIndexMap = new HashMap<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (valueIndexMap.containsKey(arr[i])) {
                valueIndexMap.remove(arr[i]);
            } else {
                valueIndexMap.put(arr[i], i);
            }
        }
        List<java.util.Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(valueIndexMap.entrySet());
        // 遍历 map 进行排序 然后找到 第k个
        Collections.sort(list, new Comparator<java.util.Map.Entry<String, Integer>>() {
            @Override
            public int compare(java.util.Map.Entry<String, Integer> o1, java.util.Map.Entry<String, Integer> o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        if (list.size() < k) {
            return "";
        }
        return list.get(k).getKey();
    }


}
