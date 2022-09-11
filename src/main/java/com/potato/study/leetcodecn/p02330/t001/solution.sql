--
-- 2230. 查找可享受优惠的用户
-- SQL架构
-- Table: Purchases
--
-- +-------------+----------+
-- | Column Name | Type     |
-- +-------------+----------+
-- | user_id     | int      |
-- | time_stamp  | datetime |
-- | amount      | int      |
-- +-------------+----------+
-- (user_id，time _ stamp)是此表的主键。
-- 每一行都包含有关购买时间和用户user _ ID 以及购买的数量的信息。
-- 如果用户在包含时间间隔[ startDate，endDate ]内购买了至少最少数量的商品，则有资格享受折扣。
--
-- 编写一个 SQL 查询来报告符合折扣条件的用户的 id。
--
-- 返回符合条件的用户 ，按照id 排序的结果表。
--
-- 查询结果格式如下例所示。
--
-- 示例 1:
--
-- 输入:
-- Purchases table:
-- +---------+---------------------+--------+
-- | user_id | time_stamp          | amount |
-- +---------+---------------------+--------+
-- | 1       | 2022-04-20 09:03:00 | 4416   |
-- | 2       | 2022-03-19 19:24:02 | 678    |
-- | 3       | 2022-03-18 12:03:09 | 4523   |
-- | 3       | 2022-03-30 09:43:42 | 626    |
-- +---------+---------------------+--------+
-- startDate = 2022-03-08, endDate = 2022-03-20, minAmount = 1000
-- 输出:
-- +---------+
-- | user_id |
-- +---------+
-- | 3       |
-- +---------+
-- 说明:
-- 在三个用户中，只有用户3有资格享受折扣。
-- - 用户1有一次至少购买了 minAmount的数量 ，但不是在时间间隔内。
-- - 用户2在时间间隔内有一次购买，但少于 minAmount 数量。
-- - 用户3是唯一满足这两个条件的用户。
--
-- 重要提示: 这个问题基本上与有资格享受折扣的用户数量相同。

-- https://www.php.cn/mysql-tutorials-463993.html

CREATE PROCEDURE getUserIDs(startDate DATE, endDate DATE, minAmount INT)
BEGIN
	# Write your MySQL query statement below.
    select distinct user_id
    from Purchases
    where amount >= minAmount
        and time_stamp between startDate and endDate
        order by user_id asc;
END