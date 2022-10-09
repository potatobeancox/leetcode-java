--
--
-- 550. 游戏玩法分析 IV
--
-- SQL架构
-- Table: Activity
--
-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | player_id    | int     |
-- | device_id    | int     |
-- | event_date   | date    |
-- | games_played | int     |
-- +--------------+---------+
-- （player_id，event_date）是此表的主键。
-- 这张表显示了某些游戏的玩家的活动情况。
-- 每一行是一个玩家的记录，他在某一天使用某个设备注销之前登录并玩了很多游戏（可能是 0）。
--
--
-- 编写一个 SQL 查询，报告在首次登录的第二天再次登录的玩家的比率，四舍五入到小数点后两位。换句话说，您需要计算从首次登录日期开始至少连续两天登录的玩家的数量，然后除以玩家总数。
--
-- 查询结果格式如下所示：
--
-- Activity table:
-- +-----------+-----------+------------+--------------+
-- | player_id | device_id | event_date | games_played |
-- +-----------+-----------+------------+--------------+
-- | 1         | 2         | 2016-03-01 | 5            |
-- | 1         | 2         | 2016-03-02 | 6            |
-- | 2         | 3         | 2017-06-25 | 1            |
-- | 3         | 1         | 2016-03-02 | 0            |
-- | 3         | 4         | 2018-07-03 | 5            |
-- +-----------+-----------+------------+--------------+
--
-- Result table:
-- +-----------+
-- | fraction  |
-- +-----------+
-- | 0.33      |
-- +-----------+
-- 只有 ID 为 1 的玩家在第一天登录后才重新登录，所以答案是 1/3 = 0.33

-- 题解
-- https://leetcode.cn/problems/game-play-analysis-iv/solution/by-zg104-uqvw/


SELECT
  ifnull(
    round(
      count(DISTINCT player_id) / (
        SELECT count(DISTINCT player_id) FROM Activity
      ), 2
    ), 0)as fraction
FROM Activity
WHERE (player_id, event_date) in (
  -- player_id 第二次登录的时间集合
  SELECT
    player_id,
    date(MIN(event_date) + 1)
  FROM Activity
  GROUP BY player_id
)


