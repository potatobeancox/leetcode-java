package com.potato.study.leetcodecn.other.Interview.p0017.p0017;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 17.17. 多次搜索
 *
 * 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 *
 * 示例：
 *
 * 输入：
 * big = "mississippi"
 * smalls = ["is","ppi","hi","sis","i","ssippi"]
 * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
 * 提示：
 *
 * 0 <= len(big) <= 1000
 * 0 <= len(smalls[i]) <= 1000
 * smalls的总字符数不会超过 100000。
 * 你可以认为smalls中没有重复字符串。
 * 所有出现的字符均为英文小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/multi-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 17.17
    public int[][] multiSearch(String big, String[] smalls) {
        int n = smalls.length;
        int[][] result = new int[n][];
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> list = new ArrayList<>();
            if (smalls[i].length() == 0) {
                result[i] = new int[] {};
                continue;
            }
            int index = big.indexOf(smalls[i]);
            while (index >= 0) {
                list.add(index);
                index = big.indexOf(smalls[i], index + 1);
            }
            result[i] = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                result[i][j] = list.get(j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String big = "";
        String[] smalls = new String[] {
                "a","b","c"
        };
        int[][] ints = solution.multiSearch(big, smalls);
        System.out.println(Arrays.deepToString(ints));
    }
}
