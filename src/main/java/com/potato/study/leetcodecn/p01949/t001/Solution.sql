package com.potato.study.leetcodecn.p01757.t001;

--  1949. 坚定的友谊
--
-- SQL架构
-- 表名: Friendship
--
-- +-------------+------+
-- | 列名         | 类型 |
-- +-------------+------+
-- | user1_id    | int  |
-- | user2_id    | int  |
-- +-------------+------+
-- (user1_id, user2_id) 是这个表的主键。
-- 这张表的每一行都表示用户 user1_id 和 user2_id 是朋友。
-- 请注意，user1_id < user2_id。
--
--
-- 如果 x  和 y 为朋友且他们至少有三个共同的朋友 ，那么 x 和 y 之间的友谊就是坚定的。
--
-- 写一个 SQL 查询来找到所有的坚定的友谊。
--
-- 注意，结果表不应该包含重复，并且 user1_id < user2_id。
--
-- 以任何顺序返回结果表。
--
-- 查询结果的格式在下面的例子中。
--
--
--
-- 示例 1:
--
-- 输入:
-- 表 Friendship:
-- +----------+----------+
-- | user1_id | user2_id |
-- +----------+----------+
-- | 1        | 2        |
-- | 1        | 3        |
-- | 2        | 3        |
-- | 1        | 4        |
-- | 2        | 4        |
-- | 1        | 5        |
-- | 2        | 5        |
-- | 1        | 7        |
-- | 3        | 7        |
-- | 1        | 6        |
-- | 3        | 6        |
-- | 2        | 6        |
-- +----------+----------+
-- 输出:
-- +----------+----------+---------------+
-- | user1_id | user2_id | common_friend |
-- +----------+----------+---------------+
-- | 1        | 2        | 4             |
-- | 1        | 3        | 3             |
-- +----------+----------+---------------+
-- 解释:
-- 用户 1 和 2 有 4 个共同的朋友（3，4，5，和 6）。
-- 用户 1 和 3 有 3 个共同的朋友（2，6，和 7）。
-- 我们没有包括用户 2 和 3 的友谊，因为他们只有两个共同的朋友（1 和 6）。

with tt as (
  SELECT
    user1_id as user_id,
    user2_id as friend_id
  FROM Friendship
  UNION
  SELECT
    user2_id,
    user1_id
  FROM Friendship
)

SELECT
  t1.user_id as user1_id,
  t2.user_id as user2_id,
  COUNT(t1.user_id) as common_friend
FROM tt as t1
CROSS JOIN tt as t2
ON t1.user_id < t2.user_id and t1.friend_id = t2.friend_id
WHERE (t1.user_id, t2.user_id) in (SELECT * FROM tt)
GROUP BY t1.user_id, t2.user_id
HAVING common_friend > 2
