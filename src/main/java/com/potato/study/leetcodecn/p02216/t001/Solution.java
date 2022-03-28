package com.potato.study.leetcodecn.p02216.t001;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2216. 美化数组的最少删除数
 *
 * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：

 nums.length 为偶数
 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 注意，空数组同样认为是美丽数组。

 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。

 返回使 nums 变为美丽数组所需删除的 最少 元素数目。

  

 示例 1：

 输入：nums = [1,1,2,3,5]
 输出：1
 解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
 示例 2：

 输入：nums = [1,1,2,2,3,3]
 输出：2
 解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
  

 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-deletions-to-make-array-beautiful
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        // 美丽的两个条件 1.数组个数为偶数 2.满足上述题目条件
        int deleteNum = 0;
        // 两个指针i 指向偶数 index j指向 奇数index
        int i = 0;
        int j;
        while (i < nums.length) {
            j = i + 1;
            // 删除 j位置字符
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
                deleteNum++;
            }
            // i 的下一个位置就是 j+1
            i = j + 1;
        }
        // 数组个数为偶数
        if ((nums.length - deleteNum) % 2 == 1) {
            deleteNum++;
        }
        return deleteNum;
    }
}
