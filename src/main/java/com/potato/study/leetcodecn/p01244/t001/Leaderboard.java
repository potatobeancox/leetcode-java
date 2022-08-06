package com.potato.study.leetcodecn.p01244.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1244. 力扣排行榜
 *
 * 新一轮的「力扣杯」编程大赛即将启动，为了动态显示参赛者的得分数据，需要设计一个排行榜 Leaderboard。

 请你帮忙来设计这个 Leaderboard 类，使得它有如下 3 个函数：

 addScore(playerId, score)：
 假如参赛者已经在排行榜上，就给他的当前得分增加 score 点分值并更新排行。
 假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 score。
 top(K)：返回前 K 名参赛者的 得分总和。
 reset(playerId)：将指定参赛者的成绩清零（换句话说，将其从排行榜中删除）。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上。
 请注意，在初始状态下，排行榜是空的。

  

 示例 1：

 输入：
 ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 输出：
 [null,null,null,null,null,null,73,null,null,null,141]

 解释：
 Leaderboard leaderboard = new Leaderboard ();
 leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 leaderboard.top(1);           // returns 73;
 leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
  

 提示：

 1 <= playerId, K <= 10000
 题目保证 K 小于或等于当前参赛者的数量
 1 <= score <= 100
 最多进行 1000 次函数调用

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-a-leaderboard
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Leaderboard {

    // treemap key 分数 val set id
    private TreeMap<Integer, Set<Integer>> scoreToUidMap;
    // map key id val 分数
    private Map<Integer, Integer> idToScoreMap;

    public Leaderboard() {
        // 按照分数 降序
        this.scoreToUidMap = new TreeMap<>((o1, o2) -> Integer.compare(o2, o1));
        this.idToScoreMap = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        // id 2 score
        if (!idToScoreMap.containsKey(playerId)) {
            // 不在榜单上
            idToScoreMap.put(playerId, score);
            // treemap
            Set<Integer> set = scoreToUidMap.getOrDefault(score, new HashSet<>());
            set.add(playerId);
            scoreToUidMap.put(score, set);
            return;
        }
        // 在上面修改得分
        int oldScore = idToScoreMap.get(playerId);
        int newScore = oldScore + score;
        idToScoreMap.put(playerId, newScore);
        // 老 oldScore 对应 treempa 改造 和新的改造
        Set<Integer> set = scoreToUidMap.get(oldScore);
        set.remove(playerId);

        Set<Integer> newSet = scoreToUidMap.getOrDefault(newScore, new HashSet<>());
        newSet.add(playerId);
        scoreToUidMap.put(newScore, newSet);
    }

    public int top(int k) {
        int limit = k;
        int scoreSum = 0;
        // 从 treemap 中获取
        for (int score : scoreToUidMap.keySet()) {
            if (limit <= 0) {
                break;
            }
            Set<Integer> uidSet = scoreToUidMap.get(score);
            scoreSum += (score * Math.min(limit, uidSet.size()));
            limit -= uidSet.size();
        }
        return scoreSum;
    }

    public void reset(int playerId) {
        // 获取原来得分
        Integer score = idToScoreMap.get(playerId);
        idToScoreMap.remove(playerId);
        if (score == null) {
            return;
        }
        // 修改 treemap
        Set<Integer> set = scoreToUidMap.get(score);
        set.remove(playerId);
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1,73);
        leaderboard.addScore(2,56);
        leaderboard.addScore(3,39);
        leaderboard.addScore(4,51);
        leaderboard.addScore(5,4);

        System.out.println(leaderboard.top(1));
    }
}