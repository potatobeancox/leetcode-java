package com.potato.study.leetcodecn.p01757.t001;

--  1783. 大满贯数量
--
-- 表：Players
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | player_id      | int     |
-- | player_name    | varchar |
-- +----------------+---------+
-- player_id 是这个表的主键
-- 这个表的每一行给出一个网球运动员的 ID 和 姓名
--  
--
-- 表：Championships
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | year          | int     |
-- | Wimbledon     | int     |
-- | Fr_open       | int     |
-- | US_open       | int     |
-- | Au_open       | int     |
-- +---------------+---------+
-- year 是这个表的主键
-- 该表的每一行都包含在每场大满贯网球比赛中赢得比赛的球员的 ID
--  
--
-- 请写出查询语句，查询出每一个球员赢得大满贯比赛的次数。结果不包含没有赢得比赛的球员的ID 。
--
-- 结果集 无顺序要求 。
--
-- 查询结果的格式，如下所示。
--
--  
--
-- 示例 1:
--
-- 输入：
-- Players 表：
-- +-----------+-------------+
-- | player_id | player_name |
-- +-----------+-------------+
-- | 1         | Nadal       |
-- | 2         | Federer     |
-- | 3         | Novak       |
-- +-----------+-------------+
-- Championships 表：
-- +------+-----------+---------+---------+---------+
-- | year | Wimbledon | Fr_open | US_open | Au_open |
-- +------+-----------+---------+---------+---------+
-- | 2018 | 1         | 1       | 1       | 1       |
-- | 2019 | 1         | 1       | 2       | 2       |
-- | 2020 | 2         | 1       | 2       | 2       |
-- +------+-----------+---------+---------+---------+
-- 输出：
-- +-----------+-------------+-------------------+
-- | player_id | player_name | grand_slams_count |
-- +-----------+-------------+-------------------+
-- | 2         | Federer     | 5                 |
-- | 1         | Nadal       | 7                 |
-- +-----------+-------------+-------------------+
-- 解释：
-- Player 1 (Nadal) 获得了 7 次大满贯：其中温网 2 次(2018, 2019), 法国公开赛 3 次 (2018, 2019, 2020), 美国公开赛 1 次 (2018)以及澳网公开赛 1 次 (2018) 。
-- Player 2 (Federer) 获得了 5 次大满贯：其中温网 1 次 (2020), 美国公开赛 2 次 (2019, 2020) 以及澳网公开赛 2 次 (2019, 2020) 。
-- Player 3 (Novak)  没有赢得，因此不包含在结果集中。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/grand-slam-titles
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

SELECT
  player_id,
  player_name,
  SUM(
    (CASE WHEN Wimbledon=player_id THEN 1 ELSE 0 END) +
    (CASE WHEN Fr_open=player_id THEN 1 ELSE 0 END) +
    (CASE WHEN US_open=player_id THEN 1 ELSE 0 END) +
    (CASE WHEN Au_open=player_id THEN 1 ELSE 0 END)
  ) AS grand_slams_count
FROM Championships
INNER JOIN Players
GROUP BY player_id
HAVING grand_slams_count > 0
