package com.potato.study.leetcodecn.p01757.t001;

-- 2688. 查找活跃用户
--
-- SQL架构
-- Users 表：
--
-- +-------------+----------+
-- | 字段名       | 类型      |
-- +-------------+----------+
-- | user_id     | int      |
-- | item        | varchar  |
-- | created_at  | datetime |
-- | amount      | int      |
-- +-------------+----------+
-- 在这个表中没有主键。该表可能包含重复的记录。
-- 每一行包括 user_id、购买的商品、购买日期和购买金额。
-- 编写一个SQL查询，找出活跃用户。活跃用户是指在其任何一次购买之后的 七天内 进行了第二次购买的用户。
--
--
--
-- 例如，如果结束日期是2023年5月31日，那么在2023年5月31日和2023年6月7日之间（包括这两天）的任何日期都被视为"在7天内"。
--
--
--
-- 返回 任意顺序 的 user_id 列表，表示活跃用户列表。
--
--
--
-- 查询结果的格式如下示例：
--
--
--
-- 示例 1：
--
-- 输入：
-- Users 表:
-- +---------+-------------------+------------+--------+
-- | user_id | item              | created_at | amount |
-- +---------+-------------------+------------+--------+
-- | 5       | Smart Crock Pot   | 2021-09-18 | 698882 |
-- | 6       | Smart Lock        | 2021-09-14 | 11487  |
-- | 6       | Smart Thermostat  | 2021-09-10 | 674762 |
-- | 8       | Smart Light Strip | 2021-09-29 | 630773 |
-- | 4       | Smart Cat Feeder  | 2021-09-02 | 693545 |
-- | 4       | Smart Bed         | 2021-09-13 | 170249 |
-- +---------+-------------------+------------+--------+
-- 输出：
-- +---------+
-- | user_id |
-- +---------+
-- | 6       |
-- +---------+
-- 解释：
-- – user_id 为 5 的用户只有一笔交易，因此他不是活跃用户。
-- – user_id 为 6 的用户有两笔交易，第一笔交易是在2021年9月10日，第二笔交易是在2021年9月14日。第一笔和第二笔交易之间的时间间隔小于等于7天。因此，他是一个活跃用户。
-- – user_id 为 8 的用户只有一笔交易，因此他不是活跃用户。
-- – user_id 为 4 的用户有两笔交易，第一笔交易是在2021年9月2日，第二笔交易是在2021年9月13日。第一笔和第二笔交易之间的时间间隔大于7天。因此，他不是活跃用户。

-- https://leetcode.cn/problems/find-active-users/solution/join-datediff-by-letaon-nqfx/

-- left join 因为可能只有一条记录 所以用where 检测u2不能为空

SELECT
  DISTINCT u1.user_id as user_id
FROM Users u1 LEFT JOIN Users u2
on u1.user_id = u2.user_id AND u1.item != u2.item
AND datediff(u1.created_at, u2.created_at) BETWEEN 0 AND 7
where u2.created_at is not null