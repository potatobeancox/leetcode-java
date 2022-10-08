--
-- 1355. 活动参与者
--
--
-- SQL架构
-- 表: Friends
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | id            | int     |
-- | name          | varchar |
-- | activity      | varchar |
-- +---------------+---------+
-- id 是朋友的 id 和该表的主键
-- name 是朋友的名字
-- activity 是朋友参加的活动的名字
--
--
-- 表: Activities
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | id            | int     |
-- | name          | varchar |
-- +---------------+---------+
-- id 是该表的主键
-- name 是活动的名字
--
--
-- 写一条 SQL 查询那些既没有最多，也没有最少参与者的活动的名字
--
-- Activities 表中的任意活动都有在 Friends 中参与过
--
-- 可以以 任何顺序 返回结果。
--
-- 下面是查询结果格式的例子。
--
--
--
-- 示例 1:
--
-- 输入：
-- Friends 表:
-- +------+--------------+---------------+
-- | id   | name         | activity      |
-- +------+--------------+---------------+
-- | 1    | Jonathan D.  | Eating        |
-- | 2    | Jade W.      | Singing       |
-- | 3    | Victor J.    | Singing       |
-- | 4    | Elvis Q.     | Eating        |
-- | 5    | Daniel A.    | Eating        |
-- | 6    | Bob B.       | Horse Riding  |
-- +------+--------------+---------------+
-- Activities 表:
-- +------+--------------+
-- | id   | name         |
-- +------+--------------+
-- | 1    | Eating       |
-- | 2    | Singing      |
-- | 3    | Horse Riding |
-- +------+--------------+
-- 输出：
-- +--------------+
-- | activity     |
-- +--------------+
-- | Singing      |
-- +--------------+
-- 解释：
-- Eating 活动有三个人参加, 是最多人参加的活动 (Jonathan D. , Elvis Q. and Daniel A.)
-- Horse Riding 活动有一个人参加, 是最少人参加的活动 (Bob B.)
-- Singing 活动有两个人参加 (Victor J. and Jade W.)

-- 题解：
-- https://leetcode.cn/problems/activity-participants/solution/1355-by-yininghang-d7jf/

SELECT activity
FROM (
  SELECT
    Activities.name as activity,
    COUNT(Friends.id) as count,
    MAX(COUNT(Friends.id)) over() as max_count,
    MIN(COUNT(Friends.id)) over() as min_count
  FROM Activities
  LEFT JOIN Friends
  ON Activities.name = Friends.activity
  GROUP BY Activities.name
) t
WHERE
  t.count < t.max_count
  and t.count > t.min_count

