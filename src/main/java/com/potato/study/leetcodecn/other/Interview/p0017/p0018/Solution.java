package com.potato.study.leetcodecn.other.Interview.p0017.p0018;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.18. 最短超串
 *
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 *
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 *
 * 示例 1:
 *
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 * 示例 2:
 *
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 * 提示：
 *
 * big.length <= 100000
 * 1 <= small.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-supersequence-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 17.18
    public int[] shortestSeq(int[] big, int[] small) {
        Map<Integer, Integer> smallCountMap = new HashMap<>();
        for (int s : small) {
            int cnt = smallCountMap.getOrDefault(s, 0);
            cnt++;

            smallCountMap.put(s, cnt);
        }
        int left = 0;
        Map<Integer, Integer> windowCountMap = new HashMap<>();
        int windowLen = Integer.MAX_VALUE;
        int resLeft = -1;
        int resRight = -1;
        for (int right = 0; right < big.length; right++) {
            int cnt = windowCountMap.getOrDefault(big[right], 0);
            cnt++;

            windowCountMap.put(big[right], cnt);

            while (left <= right && canCover(windowCountMap, smallCountMap)) {

                if (windowLen > right - left + 1) {
                    // 找到最小的了
                    resLeft = left;
                    resRight = right;
                }
                int count = windowCountMap.getOrDefault(big[left], 0);
                count--;
                windowCountMap.put(big[left], count);

                left++;
            }

        }
        return new int[] {resLeft, resRight};
    }

    /**
     * 遍历 small key 看 window能不能 cover
     * @param windowCountMap
     * @param smallCountMap
     * @return
     */
    private boolean canCover(Map<Integer, Integer> windowCountMap, Map<Integer, Integer> smallCountMap) {
        for (int key : smallCountMap.keySet()) {
            if (!windowCountMap.containsKey(key)) {
                return false;
            }
            if (windowCountMap.get(key) < smallCountMap.get(key)) {
                return false;
            }
        }
        return true;
    }
}
