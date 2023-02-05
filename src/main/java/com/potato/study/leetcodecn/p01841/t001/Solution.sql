package com.potato.study.leetcodecn.p01757.t001;

--  1841. 联赛信息统计
--
--
-- SQL架构
-- 表: Teams
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | team_id        | int     |
-- | team_name      | varchar |
-- +----------------+---------+
-- team_id 是该表主键.
-- 每一行都包含了一个参加联赛的队伍信息.
--
--
-- 表: Matches
--
-- +-----------------+---------+
-- | Column Name     | Type    |
-- +-----------------+---------+
-- | home_team_id    | int     |
-- | away_team_id    | int     |
-- | home_team_goals | int     |
-- | away_team_goals | int     |
-- +-----------------+---------+
-- (home_team_id, away_team_id) 是该表主键.
-- 每一行包含了一次比赛信息.
-- home_team_goals 代表主场队得球数.
-- away_team_goals 代表客场队得球数.
-- 获得球数较多的队伍为胜者队伍.
--
--
-- 写一段SQL，用来报告联赛信息. 统计数据应使用已进行的比赛来构建，其中 获胜 球队获得 三分 ，而失败球队获得 零分 。如果 打平 ，两支球队都得 一分 。
--
-- result 表的每行应包含以下信息:
--
-- team_name - Teams 表中的队伍名字
-- matches_played - 主场与客场球队进行的比赛次数.
-- points - 球队获得的总分数.
-- goal_for - 球队在所有比赛中获取的总进球数
-- goal_against - 球队在所有比赛中，他的对手球队的所有进球数
-- goal_diff - goal_for - goal_against.
-- 按 points 降序 返回结果表。 如果两队或多队得分相同，则按 goal_diff 降序 排列。 如果仍然存在平局，则以 team_name 按字典顺序 排列它们。
--
-- 查询的结果格式如下例所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Teams 表:
-- +---------+-----------+
-- | team_id | team_name |
-- +---------+-----------+
-- | 1       | Ajax      |
-- | 4       | Dortmund  |
-- | 6       | Arsenal   |
-- +---------+-----------+
-- Matches 表:
-- +--------------+--------------+-----------------+-----------------+
-- | home_team_id | away_team_id | home_team_goals | away_team_goals |
-- +--------------+--------------+-----------------+-----------------+
-- | 1            | 4            | 0               | 1               |
-- | 1            | 6            | 3               | 3               |
-- | 4            | 1            | 5               | 2               |
-- | 6            | 1            | 0               | 0               |
-- +--------------+--------------+-----------------+-----------------+
-- 输出：
-- +-----------+----------------+--------+----------+--------------+-----------+
-- | team_name | matches_played | points | goal_for | goal_against | goal_diff |
-- +-----------+----------------+--------+----------+--------------+-----------+
-- | Dortmund  | 2              | 6      | 6        | 2            | 4         |
-- | Arsenal   | 2              | 2      | 3        | 3            | 0         |
-- | Ajax      | 4              | 2      | 5        | 9            | -4        |
-- +-----------+----------------+--------+----------+--------------+-----------+
-- 解释：
-- Ajax (team_id=1) 有4场比赛: 2败2平. 总分数 = 0 + 0 + 1 + 1 = 2.
-- Dortmund (team_id=4) 有2场比赛: 2胜. 总分数 = 3 + 3 = 6.
-- Arsenal (team_id=6) 有2场比赛: 2平. 总分数 = 1 + 1 = 2.
-- Dortmund 是积分榜上的第一支球队. Ajax和Arsenal 有同样的分数, 但Arsenal的goal_diff高于Ajax, 所以Arsenal在表中的顺序在Ajaxzhi'qian.

-- https://leetcode.cn/problems/league-statistics/solution/bu-yong-unionzhi-jie-dui-mei-ge-tong-ji-0okzt/


SELECT
  Teams.team_name as team_name,
  COUNT(*) as matches_played,
  sum(
    CASE
    WHEN (team_id=home_team_id AND home_team_goals > away_team_goals) OR
     (team_id=away_team_id AND home_team_goals < away_team_goals)
     THEN 3
    WHEN (team_id=home_team_id AND home_team_goals < away_team_goals) OR
     (team_id=away_team_id AND home_team_goals > away_team_goals) THEN 0
    ELSE 1 END
  ) as points,
  sum(if(team_id=home_team_id, home_team_goals, away_team_goals)) as goal_for,
  sum(if(team_id=home_team_id, away_team_goals, home_team_goals)) as goal_against,
  sum(if(team_id=home_team_id, home_team_goals, away_team_goals))
  - sum(if(team_id=home_team_id, away_team_goals, home_team_goals)) as goal_diff
FROM Teams INNER JOIN Matches
ON Teams.team_id = Matches.home_team_id OR Teams.team_id = Matches.away_team_id
GROUP BY Teams.team_id
ORDER BY points DESC , goal_diff DESC , team_name ASC