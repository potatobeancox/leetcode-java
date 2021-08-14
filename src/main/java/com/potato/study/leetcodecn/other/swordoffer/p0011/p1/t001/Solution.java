package com.potato.study.leetcodecn.other.swordoffer.p0011.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

 示例 1：

 输入：[3,4,5,1,2]
 输出：1
 示例 2：

 输入：[2,2,2,0,1]
 输出：0
 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /** 耳返查找 left mid right
     * 如果 mid = left + right / 2
     * if left > mid 说明一定在 （left， mid】中间
     * else left <= mid,
     *      如果 mid > right 说明一定在 【mid+1， right】这里
     *      else mid <= right (left <= mid <= rigth) 那不就是left
     *
     *
     * 如果 left
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            if (left == right) {
                return numbers[left];
            }
            if (right - left == 1){
                return Math.min(numbers[left], numbers[right]);
            }
            int mid = (left + right) / 2;
            if (numbers[left] > numbers[mid]) {
                left = left + 1;
                right = mid;
            } else {
                if (numbers[mid] > numbers[right]) {
                    left = mid + 1;
                } else {
                    right--;
                }
            }
        }
        // 相等
        return numbers[left];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = new int[] {
                3,4,5,1,2
        };
        int res = solution.minArray(numbers);
        System.out.println(res);
        Assert.assertEquals(1, res);

        numbers = new int[] {
                2,2,2,0,1
        };
        res = solution.minArray(numbers);
        System.out.println(res);
        Assert.assertEquals(0, res);

        numbers = new int[] {
                3,3,1,3
        };
        res = solution.minArray(numbers);
        System.out.println(res);
        Assert.assertEquals(1, res);
    }

}
