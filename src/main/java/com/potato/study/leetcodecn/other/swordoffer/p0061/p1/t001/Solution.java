package com.potato.study.leetcodecn.other.swordoffer.p0061.p1.t001;


import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 *
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

  

 示例 1:

 输入: [1,2,3,4,5]
 输出: True
  

 示例 2:

 输入: [0,0,1,2,5]
 输出: True
  

 限制：

 数组长度为 5 

 数组的数取值为 [0, 13] .

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历 记录 最大最小值
     * 跳过王 记录重复
     * 无重复 最大最小值 小于 5 就是顺子
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> appear = new HashSet<>();
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (appear.contains(num)) {
                return false;
            }
            appear.add(num);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return max - min < 5;
    }


}
