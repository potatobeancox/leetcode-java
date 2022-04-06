package com.potato.study.leetcodecn.p02225.t001;

import java.util.*;

/**
 * 2225. 找出输掉零场或一场比赛的玩家
 *
 * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。

 返回一个长度为 2 的列表 answer ：

 answer[0] 是所有 没有 输掉任何比赛的玩家列表。
 answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
 两个列表中的值都应该按 递增 顺序返回。

 注意：

 只考虑那些参与 至少一场 比赛的玩家。
 生成的测试用例保证 不存在 两场比赛结果 相同 。
  

 示例 1：

 输入：matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 输出：[[1,2,10],[4,5,7,8]]
 解释：
 玩家 1、2 和 10 都没有输掉任何比赛。
 玩家 4、5、7 和 8 每个都输掉一场比赛。
 玩家 3、6 和 9 每个都输掉两场比赛。
 因此，answer[0] = [1,2,10] 和 answer[1] = [4,5,7,8] 。
 示例 2：

 输入：matches = [[2,3],[1,3],[5,4],[6,4]]
 输出：[[1,2,5,6],[]]
 解释：
 玩家 1、2、5 和 6 都没有输掉任何比赛。
 玩家 3 和 4 每个都输掉两场比赛。
 因此，answer[0] = [1,2,5,6] 和 answer[1] = [] 。
  

 提示：

 1 <= matches.length <= 105
 matches[i].length == 2
 1 <= winneri, loseri <= 105
 winneri != loseri
 所有 matches[i] 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-players-with-zero-or-one-losses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<List<Integer>> findWinners(int[][] matches) {
        // set 统计没有输掉比赛的 人
        Set<Integer> neverLose = new HashSet<>();
        // set 统计 输掉一次比赛的人
        Set<Integer> loseOnce = new HashSet<>();
        Set<Integer> other = new HashSet<>();
        for (int i = 0; i < matches.length; i++) {
            int winner = matches[i][0];
            int loser = matches[i][1];

            if (!other.contains(winner) && !loseOnce.contains(winner)) {
                neverLose.add(winner);
            }

            if (!other.contains(loser)) {
                // 只输了一次
                if (loseOnce.contains(loser)) {
                    loseOnce.remove(loser);
                    other.add(loser);
                } else {
                    // 一次都没输
                    loseOnce.add(loser);
                    neverLose.remove(loser);
                }
            }
        }
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> list0 = new ArrayList<>(neverLose);
        Collections.sort(list0);

        List<Integer> list1 = new ArrayList<>(loseOnce);
        Collections.sort(list1);

        resultList.add(list0);
        resultList.add(list1);
        return resultList;
    }
}
