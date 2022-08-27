package com.potato.study.leetcodecn.p01757.t001;

--  1142. 过去30天的用户活动 II
--
-- Activity 表：
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | user_id       | int     |
-- | session_id    | int     |
-- | activity_date | date    |
-- | activity_type | enum    |
-- +---------------+---------+
-- 该表没有主键，它可能有重复的行。
-- activity_type 列是 ENUM 类型，可以取（“ open_session”，“ end_session”，“ scroll_down”，“ send_message”）四种活动类型之一。
-- 该表显示了社交媒体网站的用户活动。
-- 请注意，每个会话只属于一个用户。
--  
--
-- 编写 SQL 查询以查找截至 2019-07-27（含）的 30 天内每个用户的平均会话数，四舍五入到小数点后两位。只统计那些会话期间用户至少进行一项活动的有效会话。
--
-- 查询结果格式如下例所示。
--
--  
--
-- 示例：
--
-- 输入：
-- Activity 表：
-- +---------+------------+---------------+---------------+
-- | user_id | session_id | activity_date | activity_type |
-- +---------+------------+---------------+---------------+
-- | 1       | 1          | 2019-07-20    | open_session  |
-- | 1       | 1          | 2019-07-20    | scroll_down   |
-- | 1       | 1          | 2019-07-20    | end_session   |
-- | 2       | 4          | 2019-07-20    | open_session  |
-- | 2       | 4          | 2019-07-21    | send_message  |
-- | 2       | 4          | 2019-07-21    | end_session   |
-- | 3       | 2          | 2019-07-21    | open_session  |
-- | 3       | 2          | 2019-07-21    | send_message  |
-- | 3       | 2          | 2019-07-21    | end_session   |
-- | 3       | 5          | 2019-07-21    | open_session  |
-- | 3       | 5          | 2019-07-21    | scroll_down   |
-- | 3       | 5          | 2019-07-21    | end_session   |
-- | 4       | 3          | 2019-06-25    | open_session  |
-- | 4       | 3          | 2019-06-25    | end_session   |
-- +---------+------------+---------------+---------------+
-- 输出：
-- +---------------------------+
-- | average_sessions_per_user |
-- +---------------------------+
-- | 1.33                      |
-- +---------------------------+
-- 解释：用户 1 和 2 每人在过去 30 天有 1 个会话，而用户 3 有 2 个会话。所以平均是 (1 + 1 + 2) / 3 = 1.33 。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/user-activity-for-the-past-30-days-ii
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

SELECT ifnull(round(COUNT(DISTINCT session_id) / COUNT(DISTINCT user_id), 2), 0.0) as average_sessions_per_user
 FROM Activity
 WHERE activity_date
 BETWEEN '2019-06-28' AND '2019-07-27'
