package com.potato.study.leetcodecn.p00932.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 932. 漂亮数组
 *
 * 对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：
 *
 * 对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。
 *
 * 那么数组 A 是漂亮数组。
 *
 *  
 *
 * 给定 N，返回任意漂亮数组 A（保证存在一个）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：4
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：5
 * 输出：[3,1,2,5,4]
 *  
 *
 * 提示：
 *
 * 1 <= N <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/beautiful-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private Map<Integer, int[]> value2BeautifulArrayMap;
    // 932
    public int[] beautifulArray(int n) {
        this.value2BeautifulArrayMap = new HashMap<>();
        // 递归求
        return getBeautifulArray(n);
    }

    /**
     * 获取 排列
     * @param n
     * @return
     */
    private int[] getBeautifulArray(int n) {
        if (value2BeautifulArrayMap.containsKey(n)) {
            return value2BeautifulArrayMap.get(n);
        }
        // 终止条件
        if (n == 1) {
            return new int[] {1};
        }
        // 递归获取奇数 放在前边
        int[] result = new int[n];
        int index = 0;
        int[] beautifulArrayOdd = getBeautifulArray((n + 1) / 2);
        // 递归获取偶数 放在后边
        for (int num : beautifulArrayOdd) {
            result[index++] = 2 * num - 1;
        }
        int[] beautifulArrayEven = getBeautifulArray(n / 2);
        for (int num : beautifulArrayEven) {
            result[index++] = 2 * num;
        }
        return result;
    }


}
