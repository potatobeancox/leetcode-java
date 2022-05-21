package com.potato.study.leetcodecn.p02274.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2274. 不含特殊楼层的最大连续楼层数
 *
 * Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。Alice 决定将一些楼层作为 特殊楼层 ，仅用于放松。

 给你两个整数 bottom 和 top ，表示 Alice 租用了从 bottom 到 top（含 bottom 和 top 在内）的所有楼层。另给你一个整数数组 special ，其中 special[i] 表示  Alice 指定用于放松的特殊楼层。

 返回不含特殊楼层的 最大 连续楼层数。

  

 示例 1：

 输入：bottom = 2, top = 9, special = [4,6]
 输出：3
 解释：下面列出的是不含特殊楼层的连续楼层范围：
 - (2, 3) ，楼层数为 2 。
 - (5, 5) ，楼层数为 1 。
 - (7, 9) ，楼层数为 3 。
 因此，返回最大连续楼层数 3 。
 示例 2：

 输入：bottom = 6, top = 8, special = [7,6,8]
 输出：0
 解释：每层楼都被规划为特殊楼层，所以返回 0 。
  

 提示

 1 <= special.length <= 105
 1 <= bottom <= special[i] <= top <= 109
 special 中的所有值 互不相同


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-consecutive-floors-without-special-floors
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxConsecutive(int bottom, int top, int[] special) {
        // 将 special 排序 找到任意两个 之间的个数 最后算开始和末尾
        Arrays.sort(special);
        int maxLength = 0;
        for (int i = 0; i < special.length - 1; i++) {
            maxLength = Math.max(maxLength, special[i+1] - special[i] - 1);
        }
        maxLength = Math.max(maxLength, special[0] - bottom);
        maxLength = Math.max(maxLength, top - special[special.length - 1]);
        return maxLength;
    }
}
