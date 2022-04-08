package com.potato.study.leetcodecn.p01865.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.p0004.Solution;

/**
 * 1865. 找出和为指定值的下标对
 *
 * 给你两个整数数组 nums1 和 nums2 ，请你实现一个支持下述两类查询的数据结构：
 *
 * 累加 ，将一个正整数加到 nums2 中指定下标对应元素上。
 * 计数 ，统计满足 nums1[i] + nums2[j] 等于指定值的下标对 (i, j) 数目（0 <= i < nums1.length 且 0 <= j < nums2.length）。
 * 实现 FindSumPairs 类：
 *
 * FindSumPairs(int[] nums1, int[] nums2) 使用整数数组 nums1 和 nums2 初始化 FindSumPairs 对象。
 * void add(int index, int val) 将 val 加到 nums2[index] 上，即，执行 nums2[index] += val 。
 * int count(int tot) 返回满足 nums1[i] + nums2[j] == tot 的下标对 (i, j) 数目。
 *  
 *
 * 示例：
 *
 * 输入：
 * ["FindSumPairs", "count", "add", "count", "count", "add", "add", "count"]
 * [[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
 * 输出：
 * [null, 8, null, 2, 1, null, null, 11]
 *
 * 解释：
 * FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
 * findSumPairs.count(7);  // 返回 8 ; 下标对 (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) 满足 2 + 5 = 7 ，下标对 (5,1), (5,5) 满足 3
 * + 4 = 7
 * findSumPairs.add(3, 2); // 此时 nums2 = [1,4,5,4,5,4]
 * findSumPairs.count(8);  // 返回 2 ；下标对 (5,2), (5,4) 满足 3 + 5 = 8
 * findSumPairs.count(4);  // 返回 1 ；下标对 (5,0) 满足 3 + 1 = 4
 * findSumPairs.add(0, 1); // 此时 nums2 = [2,4,5,4,5,4]
 * findSumPairs.add(1, 1); // 此时 nums2 = [2,5,5,4,5,4]
 * findSumPairs.count(7);  // 返回 11 ；下标对 (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) 满足 2 + 5 = 7
 * ，下标对 (5,3), (5,5) 满足 3 + 4 = 7
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length <= 1000
 * 1 <= nums2.length <= 105
 * 1 <= nums1[i] <= 109
 * 1 <= nums2[i] <= 105
 * 0 <= index < nums2.length
 * 1 <= val <= 105
 * 1 <= tot <= 109
 * 最多调用 add 和 count 函数各 1000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/finding-pairs-with-a-certain-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FindSumPairs {

    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> num2CountMap;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.num2CountMap = new HashMap<>();
        // 缓存 nums2 value 对应 个数
        for (int n2 : nums2) {
            Integer count = num2CountMap.getOrDefault(n2, 0);
            count++;
            num2CountMap.put(n2, count);
        }
    }

    public void add(int index, int val) {
        int target = nums2[index];
        // 修改之前的 计数
        Integer count = num2CountMap.get(target);
        count--;
        num2CountMap.put(target, count);
        // 变更
        nums2[index] += val;
        num2CountMap.put(nums2[index], num2CountMap.getOrDefault(nums2[index], 0) + 1);
        return;
    }

    public int count(int tot) {
        // 遍历 num1 计算总的数量
        int count = 0;
        for (int n1 : nums1) {
            int target = tot - n1;
            if (num2CountMap.containsKey(target)) {
                count += num2CountMap.get(target);
            }
        }
        return count;
    }

    // ["FindSumPairs","count","add","count","count","add","add","count"]
    //[[[1,1,2,2,2,3],[1,4,5,2,5,4]],[7],[3,2],[8],[4],[0,1],[1,1],[7]]

    // [null,8,null,2,4,null,null,11] 输出

    // [null,8,null,2,1,null,null,11] 预期

    public static void main(String[] args) {
        int[] nums1 = new int[] {
                1,1,2,2,2,3
        };
        int[] nums2 = new int[] {
                1,4,5,2,5,4
        };
        FindSumPairs findSumPairs = new FindSumPairs(nums1, nums2);

        findSumPairs.count(7); // 8
        findSumPairs.add(3, 2);
        findSumPairs.count(8); // 2
        findSumPairs.count(4); // 1
        findSumPairs.add(0, 1);
        findSumPairs.add(1, 1);
        findSumPairs.count(7); // 11
    }
}
