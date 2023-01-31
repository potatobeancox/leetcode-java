--
-- 2175. 世界排名的变化
-- SQL架构
-- 表：TeamPoints
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | team_id     | int     |
-- | name        | varchar |
-- | points      | int     |
-- +-------------+---------+
-- team_id 是这张表的主键。
-- 这张表的每一行均包含了一支国家队的 ID，它所代表的国家，以及它在全球排名中的得分。没有两支队伍代表同一个国家。
--
--
-- 表：PointsChange
--
-- +---------------+------+
-- | Column Name   | Type |
-- +---------------+------+
-- | team_id       | int  |
-- | points_change | int  |
-- +---------------+------+
-- team_id 是这张表的主键。
-- 这张表的每一行均包含了一支国家队的 ID 以及它在世界排名中的得分的变化。
-- 分数的变化分以下情况：
-- - 0:代表分数没有改变
-- - 正数:代表分数增加
-- - 负数:代表分数降低
-- TeamPoints 表中出现的每一个 team_id 均会在这张表中出现。
--
--
-- 国家队的全球排名是按 降序排列 所有队伍的得分后所得出的排名。如果两支队伍得分相同，我们将按其名称的 字典顺序 排列以打破平衡。
--
-- 每支国家队的分数应根据其相应的 points_change 进行更新。
--
-- 编写一条 SQL 查询来计算在分数更新后，每个队伍的全球排名的变化。
--
-- 以 任意顺序 返回结果。
--
-- 查询结果的格式如下例所示：
--
--
--
-- 示例 1：
--
-- 输入：
-- TeamPoints 表：
-- +---------+-------------+--------+
-- | team_id | name        | points |
-- +---------+-------------+--------+
-- | 3       | Algeria     | 1431   |
-- | 1       | Senegal     | 2132   |
-- | 2       | New Zealand | 1402   |
-- | 4       | Croatia     | 1817   |
-- +---------+-------------+--------+
-- PointsChange 表：
-- +---------+---------------+
-- | team_id | points_change |
-- +---------+---------------+
-- | 3       | 399           |
-- | 2       | 0             |
-- | 4       | 13            |
-- | 1       | -22           |
-- +---------+---------------+
-- 输出：
-- +---------+-------------+-----------+
-- | team_id | name        | rank_diff |
-- +---------+-------------+-----------+
-- | 1       | Senegal     | 0         |
-- | 4       | Croatia     | -1        |
-- | 3       | Algeria     | 1         |
-- | 2       | New Zealand | 0         |
-- +---------+-------------+-----------+
-- 解释：
-- 世界排名如下所示：
-- +---------+-------------+--------+------+
-- | team_id | name        | points | rank |
-- +---------+-------------+--------+------+
-- | 1       | Senegal     | 2132   | 1    |
-- | 4       | Croatia     | 1817   | 2    |
-- | 3       | Algeria     | 1431   | 3    |
-- | 2       | New Zealand | 1402   | 4    |
-- +---------+-------------+--------+------+
-- 在更新分数后，世界排名变为下表：
-- +---------+-------------+--------+------+
-- | team_id | name        | points | rank |
-- +---------+-------------+--------+------+
-- | 1       | Senegal     | 2110   | 1    |
-- | 3       | Algeria     | 1830   | 2    |
-- | 4       | Croatia     | 1830   | 3    |
-- | 2       | New Zealand | 1402   | 4    |
-- +---------+-------------+--------+------+
-- 由于在更新分数后，Algeria 和 Croatia 的得分相同，因此根据字典顺序对它们进行排序。
-- Senegal 丢失了22分但他们的排名没有改变。
-- Croatia 获得了13分但是他们的排名下降了1名。
-- Algeria 获得399分，排名上升了1名。
-- New Zealand 没有获得或丢失分数，他们的排名也没有发生变化。

-- 计算每个队伍的总分数变化


SELECT
  tt.team_id,
  tt.name,
  cast(tt.rank_before as SIGNED) - cast(tt.rank_after as SIGNED) as rank_diff
FROM (
  SELECT
    TeamPoints.team_id,
    TeamPoints.name,
    row_number() over(order by TeamPoints.points DESC , TeamPoints.name asc) as rank_before,
    row_number() over(order by TeamPoints.points + PointsChange.points_change DESC , TeamPoints.name asc) as rank_after
  FROM TeamPoints LEFT JOIN PointsChange using(team_id)
) as tt

