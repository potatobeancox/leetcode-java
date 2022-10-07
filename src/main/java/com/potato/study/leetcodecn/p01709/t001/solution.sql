--
-- 1709. 访问日期之间最大的空档期
-- SQL架构
-- 表： UserVisits
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | user_id     | int  |
-- | visit_date  | date |
-- +-------------+------+
-- 该表没有主键。
-- 该表包含用户访问某特定零售商的日期日志。
--
--
-- 假设今天的日期是 '2021-1-1' 。
--
-- 编写 SQL 语句，对于每个 user_id ，求出每次访问及其下一个访问（若该次访问是最后一次，则为今天）之间最大的空档期天数 window 。
--
-- 返回结果表，按用户编号 user_id 排序。
--
-- 查询格式如下示例所示：
--
--
--
-- UserVisits 表：
-- +---------+------------+
-- | user_id | visit_date |
-- +---------+------------+
-- | 1       | 2020-11-28 |
-- | 1       | 2020-10-20 |
-- | 1       | 2020-12-3  |
-- | 2       | 2020-10-5  |
-- | 2       | 2020-12-9  |
-- | 3       | 2020-11-11 |
-- +---------+------------+
-- 结果表：
-- +---------+---------------+
-- | user_id | biggest_window|
-- +---------+---------------+
-- | 1       | 39            |
-- | 2       | 65            |
-- | 3       | 51            |
-- +---------+---------------+
-- 对于第一个用户，问题中的空档期在以下日期之间：
--     - 2020-10-20 至 2020-11-28 ，共计 39 天。
--     - 2020-11-28 至 2020-12-3 ，共计 5 天。
--     - 2020-12-3 至 2021-1-1 ，共计 29 天。
-- 由此得出，最大的空档期为 39 天。
-- 对于第二个用户，问题中的空档期在以下日期之间：
--     - 2020-10-5 至 2020-12-9 ，共计 65 天。
--     - 2020-12-9 至 2021-1-1 ，共计 23 天。
-- 由此得出，最大的空档期为 65 天。
-- 对于第三个用户，问题中的唯一空档期在 2020-11-11 至 2021-1-1 之间，共计 51 天。

-- 下一行窗口函数 LEAD
-- https://leetcode.cn/problems/biggest-window-between-visits/solution/mysql-leadchuang-kou-han-shu-group-bymia-esnc/

-- DATEDIFF 函数 DATEDIFF() 函数返回两个日期之间的天数。
-- https://www.runoob.com/sql/func-datediff-mysql.html


SELECT
  user_id,
  max(DATEDIFF(next_visit_date, visit_date)) as biggest_window
FROM (
  -- 下一次请求 当前请求 放在一起 (当前用户的下一次，所以PARTITION 且需要指明顺序)
  SELECT
    user_id,
    visit_date,
    lead(visit_date, 1, '2021-1-1') OVER(PARTITION BY user_id ORDER BY visit_date) as next_visit_date
  FROM UserVisits
) t
GROUP BY user_id
ORDER BY user_id

