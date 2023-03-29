package com.potato.study.leetcodecn.other.Interview.p0017.p0005;


import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.05.  字母与数字
 *
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 *
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 *
 * 示例 1:
 *
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 *
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 * 示例 2:
 *
 * 输入: ["A","A"]
 *
 * 输出: []
 * 提示：
 *
 * array.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-longest-subarray-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 17.05
    public String[] findLongestSubarray(String[] array) {
        // 遇到 字母 +1 遇到数字 -1 每次计算当前右边的状态 记录 每种状态结束的节点位置
        int status = 0;
        Map<Integer, Integer> firstAppearIndexMap = new HashMap<>();
        firstAppearIndexMap.put(status, -1);
        // 用一个 map 记录 最左边状态出现的位置  left + 1 - right
        int maxLen = 0;
        int from = -1;
        for (int i = 0; i < array.length; i++) {
            char c = array[i].charAt(0);
            if (Character.isDigit(c)) {
                status--;
            } else {
                status++;
            }
            if (firstAppearIndexMap.containsKey(status)) {
                // 有的话 记录下最大间距的开始的结束位置
                int fromIndex = firstAppearIndexMap.get(status) + 1;
                int currentLen = i - fromIndex + 1;
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    from = fromIndex;
                }
            } else {
                firstAppearIndexMap.put(status, i);
            }
        }
        if (maxLen == 0) {
            // 不存在
            return new String[0];
        }
        String[] result = new String[maxLen];
        System.arraycopy(array, from, result, 0, maxLen);
        return result;
    }


}
