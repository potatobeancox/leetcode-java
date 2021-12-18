package com.potato.study.leetcodecn.p00220.t001;

import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Assert;

/**
 * 220. 存在重复元素 III
 *
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 滑动窗口
     *
     * https://leetcode-cn.com/problems/contains-duplicate-iii/solution/cun-zai-zhong-fu-yuan-su-iii-by-leetcode-bbkt/
     *
     * 直接说几个结论
     *
     * 直接一遍过 找k个大小的窗口 k个
     *
     * 窗口中 使用treeset 排序
     * ceil 大于等于当前值的tree
     *
     * 如果窗口内有相等的元素 即说明 满足条件
     *
     * fori 0-len
     *     每次从treeset ceil 取出大于等于它的最小值
     * 将abs 不等式 打开 取出之后 判断另一侧是否满足
     *     满足的话 返回true
     *     否则 将当前值插入set 删除 k个之前的值 如果有的话
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> window = new TreeMap<>();
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            long current = nums[i];
            // 大于等于 current测最小值
            Long ceilingKey = window.ceilingKey((long)current - t);
            if (null != ceilingKey && window.get(ceilingKey) > 0
                    && Math.abs((long)ceilingKey - (long)current) <= (long)t) {
                return true;
            }
            Integer count = window.getOrDefault(current, 0);
            count++;
            size++;
            window.put(current, count);
            if (size > k) {
                // 此时 i 一定大于 k
                Integer cc = window.get((long)nums[i - k]);
                cc--;
                if (cc <= 0) {
                    window.remove((long)nums[i - k]);
                } else {
                    window.put((long)nums[i - k], cc);
                }
                size--;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        // int[] nums, int k, int t
        int[] nums = new int[]{1,5,9,1,5,9};
        int k = 2;
        int t = 3;
        boolean b = solution.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(b);
        Assert.assertEquals(false, b);


        nums = new int[]{1,0,1,1};
        k = 1;
        t = 2;
        b = solution.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(b);
        Assert.assertEquals(true, b);

        nums = new int[]{1,2,3,1};
        k = 3;
        t = 0;
        b = solution.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(b);
        Assert.assertEquals(true, b);

        nums = new int[]{-2147483640,-2147483641};
        k = 1;
        t = 100;
        b = solution.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(b);
        Assert.assertEquals(true, b);

    }
}
