package com.potato.study.leetcodecn.p01996.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1996. 游戏中弱角色的数量
 *
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。

 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。

 返回 弱角色 的数量。

  

 示例 1：

 输入：properties = [[5,5],[6,3],[3,6]]
 输出：0
 解释：不存在攻击和防御都严格高于其他角色的角色。
 示例 2：

 输入：properties = [[2,2],[3,3]]
 输出：1
 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 示例 3：

 输入：properties = [[1,5],[10,4],[4,3]]
 输出：1
 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
  

 提示：

 2 <= properties.length <= 105
 properties[i].length == 2
 1 <= attacki, defensei <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/comments/
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        // 按照攻击力降序排序 防御从小到大
        Arrays.sort(properties, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o2[0], o1[0]);
            }
        });
        // 遍历 排序后数组，如果当前
        int maxDefense = 0;
        int weakCount = 0;
        for (int i = 0; i < properties.length; i++) {
            // 防御力小于之前，按照排序 如果攻击力相同已经排在前边，所以之前有攻击力大于的情况
            if (maxDefense > properties[i][1]) {
                weakCount++;
            } else {
                // maxDefense <= properties[i][1]
                maxDefense = Math.max(maxDefense, properties[i][1]);
            }
        }
        return weakCount;
    }




    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] properties =
                LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,7],[1,7],[7,5],[9,6],[10,10],[10,6],[4,5],[9,5]]");

        int i = solution.numberOfWeakCharacters(properties);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }

}
