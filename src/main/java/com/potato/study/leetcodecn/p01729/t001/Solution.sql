package com.potato.study.leetcodecn.p01757.t001;

--  1729. 求关注者的数量
--
-- 表： Followers
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | user_id     | int  |
-- | follower_id | int  |
-- +-------------+------+
-- (user_id, follower_id) 是这个表的主键。
-- 该表包含一个关注关系中关注者和用户的编号，其中关注者关注用户。
--  
--
-- 写出 SQL 语句，对于每一个用户，返回该用户的关注者数量。
--
-- 按 user_id 的顺序返回结果表。
--
-- 查询结果的格式如下示例所示。
--
--  
--
-- 示例 1：
--
-- 输入：
-- Followers 表：
-- +---------+-------------+
-- | user_id | follower_id |
-- +---------+-------------+
-- | 0       | 1           |
-- | 1       | 0           |
-- | 2       | 0           |
-- | 2       | 1           |
-- +---------+-------------+
-- 输出：
-- +---------+----------------+
-- | user_id | followers_count|
-- +---------+----------------+
-- | 0       | 1              |
-- | 1       | 1              |
-- | 2       | 2              |
-- +---------+----------------+
-- 解释：
-- 0 的关注者有 {1}
-- 1 的关注者有 {0}
-- 2 的关注者有 {0,1}
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/find-followers-count
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

SELECT
  user_id,
  COUNT(DISTINCT follower_id) as followers_count
FROM Followers
GROUP BY user_id