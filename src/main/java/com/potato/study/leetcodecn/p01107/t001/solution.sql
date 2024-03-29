--
--
--
-- 1107. 每日新用户统计
--
-- SQL架构
-- Traffic 表：
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | user_id       | int     |
-- | activity      | enum    |
-- | activity_date | date    |
-- +---------------+---------+
-- 该表没有主键，它可能有重复的行。
-- activity 列是 ENUM 类型，可能取 ('login', 'logout', 'jobs', 'groups', 'homepage') 几个值之一。
--
--
-- 编写一个 SQL 查询，以查询从今天起最多 90 天内，每个日期该日期首次登录的用户数。假设今天是 2019-06-30.
--
-- 查询结果格式如下例所示：
--
-- Traffic 表：
-- +---------+----------+---------------+
-- | user_id | activity | activity_date |
-- +---------+----------+---------------+
-- | 1       | login    | 2019-05-01    |
-- | 1       | homepage | 2019-05-01    |
-- | 1       | logout   | 2019-05-01    |
-- | 2       | login    | 2019-06-21    |
-- | 2       | logout   | 2019-06-21    |
-- | 3       | login    | 2019-01-01    |
-- | 3       | jobs     | 2019-01-01    |
-- | 3       | logout   | 2019-01-01    |
-- | 4       | login    | 2019-06-21    |
-- | 4       | groups   | 2019-06-21    |
-- | 4       | logout   | 2019-06-21    |
-- | 5       | login    | 2019-03-01    |
-- | 5       | logout   | 2019-03-01    |
-- | 5       | login    | 2019-06-21    |
-- | 5       | logout   | 2019-06-21    |
-- +---------+----------+---------------+
--
-- Result 表：
-- +------------+-------------+
-- | login_date | user_count  |
-- +------------+-------------+
-- | 2019-05-01 | 1           |
-- | 2019-06-21 | 2           |
-- +------------+-------------+
-- 请注意，我们只关心用户数非零的日期.
-- ID 为 5 的用户第一次登陆于 2019-03-01，因此他不算在 2019-06-21 的的统计内。
SELECT
  t.activity_date as login_date,
  COUNT(distinct user_id) as user_count
from (
  -- 每个用户第一次登录的时间
  SELECT
    *,
    rank() over(partition BY user_id order BY activity_date) as ran
    FROM Traffic
  WHERE activity = 'login'
) t
WHERE t.ran = 1 and DATEDIFF('2019-6-30', activity_date) <= 90
GROUP BY activity_date
ORDER BY activity_date


