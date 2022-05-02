package com.potato.study.leetcodecn.p00315.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 *
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

  

 示例：

 输入：nums = [5,2,6,1]
 输出：[2,1,1,0]
 解释：
 5 的右侧有 2 个更小的元素 (2 和 1)
 2 的右侧仅有 1 个更小的元素 (1)
 6 的右侧有 1 个更小的元素 (1)
 1 的右侧有 0 个更小的元素
  

 提示：

 0 <= nums.length <= 10^5
 -10^4 <= nums[i] <= 10^4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/cong-you-wang-zuo-cha-ru-pai-xu-by-utopiahiker/
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> result = new LinkedList<>();
        // 使用类似插入排序方法 先确定插入排序的开始位置 从 len - 2 到0
        for (int i = nums.length - 2; i >= 0; i--) {
            int j = i + 1;
            // 记录i的数字
            int tmp = nums[i];
            // 将大于 nums[i] 都交换到前边
            // 因为插入排序会将 大的数字都插入到前边，所以值需要从 i+1 -》 往后遍历 将大的往前移动就行 最终 i停留位置 用len 减去就可以得到 小于的个数了
            while (j < nums.length && nums[j] >= tmp) {
                nums[j-1] = nums[j];
                j++;
            }
            // j-1 最终的停留位置 就是 放tmp的
            nums[j-1] = tmp;
            // 计算还有多少个小于 tmp的个数
            int count = nums.length - j;
            result.addFirst(count);

        }
        // 往最后一个位置 添加 0
        result.addLast(0);
        return result;
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String input = "bcabc";
//        String s = solution.removeDuplicateLetters(input);
//        System.out.println(s);
//        Assert.assertEquals("abc", s);
//
//
//        input = "cbacdcbc";
//        s = solution.removeDuplicateLetters(input);
//        System.out.println(s);
//        Assert.assertEquals("acdb", s);
    }
}
