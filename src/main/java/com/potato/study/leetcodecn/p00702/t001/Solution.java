package com.potato.study.leetcodecn.p00702.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 702. 搜索长度未知的有序数组
 *
 * 这是一个交互问题。
 *
 * 您有一个升序整数数组，其长度未知。您没有访问数组的权限，但是可以使用 ArrayReader 接口访问它。你可以调用 ArrayReader.get(i):
 *
 * 返回数组第ith个索引(0-indexed)处的值(即secret[i])，或者
 *
 * 如果 i  超出了数组的边界，则返回 231 - 1
 *
 * 你也会得到一个整数 target。
 *
 * 如果存在secret[k] == target，请返回索引 k 的值；否则返回 -1
 *
 * 你必须写一个时间复杂度为 O(log n) 的算法。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: secret = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 存在在 nums 中，下标为 4
 * 示例 2：
 *
 * 输入: secret = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不在数组中所以返回 -1
 *  
 *
 * 提示：
 *
 * 1 <= secret.length <= 104
 * -104 <= secret[i], target <= 104
 * secret 严格递增
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-in-a-sorted-array-of-unknown-size
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 1_0000 - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int value = reader.get(mid);
            if (Integer.MAX_VALUE == value) {
                // 太大了 小一点
                right = mid - 1;
                continue;
            }
            if (value == target) {
                return mid;
            } else if (value > target) {
                // value 需要小一些
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}

interface ArrayReader {
    public int get(int index);
}
