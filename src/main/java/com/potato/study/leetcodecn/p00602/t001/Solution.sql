package com.potato.study.leetcodecn.p01757.t001;

--  602. 好友申请 II ：谁有最多的好友
--
--  在 Facebook 或者 Twitter 这样的社交应用中，人们经常会发好友申请也会收到其他人的好友申请。
--
-- RequestAccepted 表：
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | requester_id   | int     |
-- | accepter_id    | int     |
-- | accept_date    | date    |
-- +----------------+---------+
-- (requester_id, accepter_id) 是这张表的主键。
-- 这张表包含发送好友请求的人的 ID ，接收好友请求的人的 ID ，以及好友请求通过的日期。
--  
--
-- 写一个查询语句，找出拥有最多的好友的人和他拥有的好友数目。
--
-- 生成的测试用例保证拥有最多好友数目的只有 1 个人。
--
-- 查询结果格式如下例所示。
--
--  
--
-- 示例：
--
-- 输入：
-- RequestAccepted 表：
-- +--------------+-------------+-------------+
-- | requester_id | accepter_id | accept_date |
-- +--------------+-------------+-------------+
-- | 1            | 2           | 2016/06/03  |
-- | 1            | 3           | 2016/06/08  |
-- | 2            | 3           | 2016/06/08  |
-- | 3            | 4           | 2016/06/09  |
-- +--------------+-------------+-------------+
-- 输出：
-- +----+-----+
-- | id | num |
-- +----+-----+
-- | 3  | 3   |
-- +----+-----+
-- 解释：
-- 编号为 3 的人是编号为 1 ，2 和 4 的人的好友，所以他总共有 3 个好友，比其他人都多。
--  
--
-- 进阶：在真实世界里，可能会有多个人拥有好友数相同且最多，你能找到所有这些人吗？
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/friend-requests-ii-who-has-the-most-friends
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
--


SELECT * FROM (
  SELECT
    t.id,
    SUM(id_count) as num
  FROM (
    SELECT
      requester_id as id,
      COUNT(accepter_id) as id_count
    FROM RequestAccepted
    GROUP BY requester_id
    union ALL
    SELECT
      accepter_id as id,
      COUNT(requester_id) as id_count
    FROM RequestAccepted
    GROUP BY accepter_id
  ) as t
  GROUP BY t.id
) as tt
ORDER BY num DESC, id ASC
limit 1

