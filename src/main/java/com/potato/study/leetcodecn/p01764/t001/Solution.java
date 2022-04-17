package com.potato.study.leetcodecn.p01764.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1764. 通过连接另一个数组的子数组得到一个数组
 *
 * 给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。

 你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。（也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）

 如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。

 如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。

  

 示例 1：

 输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
 输出：true
 解释：你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
 这两个子数组是不相交的，因为它们没有任何共同的元素。
 示例 2：

 输入：groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
 输出：false
 解释：选择子数组 [1,2,3,4,10,-2] 和 [1,2,3,4,10,-2] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。
 [10,-2] 必须出现在 [1,2,3,4] 之前。
 示例 3：

 输入：groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
 输出：false
 解释：选择子数组 [7,7,1,2,3,4,7,7] 和 [7,7,1,2,3,4,7,7] 是不正确的，因为它们不是不相交子数组。
 它们有一个共同的元素 nums[4] （下标从 0 开始）。
  

 提示：

 groups.length == n
 1 <= n <= 103
 1 <= groups[i].length, sum(groups[i].length) <= 103
 1 <= nums.length <= 103
 -107 <= groups[i][j], nums[k] <= 107


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/form-array-by-concatenating-subarrays-of-another-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean canChoose(int[][] groups, int[] nums) {
        // 对于 每个 groups 在nums 找 如果都找到了 返回 true 否则nums 消耗完了 返回false
        int numsIndex = 0;
        int groupIndex = 0;
        while (numsIndex < nums.length) {
            // 找到 group Index 对应的值 all found
            if (groupIndex == groups.length) {
                break;
            }
            while (numsIndex < nums.length && nums[numsIndex] != groups[groupIndex][0]) {
                numsIndex++;
            }
            int tmpStartIndex = numsIndex;
            // 找到了 开始位置 继续
            int eachGroupIndex = 0;
            while (numsIndex < nums.length && eachGroupIndex < groups[groupIndex].length
                    && nums[numsIndex] == groups[groupIndex][eachGroupIndex]) {
                numsIndex++;
                eachGroupIndex++;
            }
            // 判断是否找到结束
            if (eachGroupIndex == groups[groupIndex].length) {
                groupIndex++;
            } else {
                // 重置下一个开始位置
                numsIndex = tmpStartIndex + 1;
            }
        }
        return groupIndex == groups.length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] groups = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2,3],[3,4]]");
        int[] nums = new int[] {
                1,2,3,3,4
        };
        boolean b = solution.canChoose(groups, nums);
        System.out.println(b);
        Assert.assertEquals(true, b);



        groups = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2]]");
        nums = new int[] {
                1,3,2
        };
        b = solution.canChoose(groups, nums);
        System.out.println(b);
        Assert.assertEquals(false, b);


        groups = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[21,22,21,22,21,30]]");
        nums = new int[] {
                21,22,21,22,21,22,21,30
        };
        b = solution.canChoose(groups, nums);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
