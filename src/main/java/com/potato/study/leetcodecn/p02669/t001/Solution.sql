package com.potato.study.leetcodecn.p01757.t001;

--  2669. 统计 Spotify 排行榜上艺术家出现次数
--
-- SQL架构
-- 表： Spotify
--
-- +-------------+---------+
-- | 列名        | 类型    |
-- +-------------+---------+
-- | id          | int     |
-- | track_name  | varchar |
-- | artist      | varchar |
-- +-------------+---------+
-- id是该表的主键。
-- 每行包含 id、track_name 和 artist。
-- 编写一个SQL查询来查找每个艺术家在Spotify排行榜上出现的次数。
--
-- 返回结果表，其中包含艺术家的名称以及相应的出现次数，按出现次数降序排列。如果出现次数相等，则按艺术家名称升序排列。
--
-- 查询结果格式如下所示：
--
--
--
-- 示例 1：
--
-- 输入：
-- Spotify 表:
-- +---------+--------------------+------------+
-- | id      | track_name         | artist     |
-- +---------+--------------------+------------+
-- | 303651  | Heart Won't Forget | Sia        |
-- | 1046089 | Shape of you       | Ed Sheeran |
-- | 33445   | I'm the one        | DJ Khalid  |
-- | 811266  | Young Dumb & Broke | DJ Khalid  |
-- | 505727  | Happier            | Ed Sheeran |
-- +---------+--------------------+------------+
-- 输出：
-- +------------+-------------+
-- | artist     | occurrences |
-- +------------+-------------+
-- | DJ Khalid  | 2           |
-- | Ed Sheeran | 2           |
-- | Sia        | 1           |
-- +------------+-------------+
--
-- 解释："occurrences" 列下按降序列出了出现次数的计数。如果出现次数相同，则艺术家名称按升序排序。


SELECT
  artist,
  COUNT(*) occurrences
FROM Spotify
GROUP BY artist
ORDER BY occurrences DESC , artist ASC