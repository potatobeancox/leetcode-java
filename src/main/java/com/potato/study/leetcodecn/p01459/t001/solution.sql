--
-- 1459. 矩形面积
--
-- --SQL架构
-- 表: Points
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | id            | int     |
-- | x_value       | int     |
-- | y_value       | int     |
-- +---------------+---------+
-- id 是该表主键
-- 每个点都用二维坐标 (x_value, y_value) 表示
--
--
-- 写一个 SQL 语句，报告由表中任意两点可以形成的所有 边与坐标轴平行 且 面积不为零 的矩形。
--
-- 结果表中的每一行包含三列 (p1, p2, area) 如下:
--
-- p1 和 p2 是矩形两个对角的 id
-- 矩形的面积由列 area 表示
-- 请按照面积 area 大小降序排列；如果面积相同的话, 则按照 p1 升序排序；若仍相同，则按 p2 升序排列。
--
-- 查询结果如下例所示：
--
-- Points 表:
-- +----------+-------------+-------------+
-- | id       | x_value     | y_value     |
-- +----------+-------------+-------------+
-- | 1        | 2           | 7           |
-- | 2        | 4           | 8           |
-- | 3        | 2           | 10          |
-- +----------+-------------+-------------+
--
-- Result 表:
-- +----------+-------------+-------------+
-- | p1       | p2          | area        |
-- +----------+-------------+-------------+
-- | 2        | 3           | 4           |
-- | 1        | 2           | 2           |
-- +----------+-------------+-------------+
--
--
--
-- p1 = 2 且 p2 = 3 时, 面积等于 |4-2| * |8-10| = 4
-- p1 = 1 且 p2 = 2 时, 面积等于 ||2-4| * |7-8| = 2
-- p1 = 1 且 p2 = 3 时, 是不可能为矩形的, 面积等于 0

SELECT
  Points1.id  p1,
  Points2.id  p2,
  abs(Points1.x_value - Points2.x_value) * abs(Points1.y_value - Points2.y_value) area
FROM Points Points1
INNER join Points Points2
ON Points1.id < Points2.id
WHERE (Points1.x_value != Points2.x_value or Points1.y_value != Points2.y_value)
and abs(Points1.x_value - Points2.x_value) * abs(Points1.y_value - Points2.y_value) > 0
ORDER BY area desc, p1 ASC , p2 ASC