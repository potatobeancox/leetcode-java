package com.potato.study.leetcodecn.p01757.t001;

--  603. 连续空余座位
--
-- SQL架构
-- 表: Cinema
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | seat_id     | int  |
-- | free        | bool |
-- +-------------+------+
-- Seat_id是该表的自动递增主键列。
-- 该表的每一行表示第i个座位是否空闲。1表示空闲，0表示被占用。
--
--
-- 编写一个SQL查询来报告电影院所有连续可用的座位。
--
-- 返回按 seat_id 升序排序 的结果表。
--
-- 测试用例的生成使得两个以上的座位连续可用。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1:
--
-- 输入:
-- Cinema 表:
-- +---------+------+
-- | seat_id | free |
-- +---------+------+
-- | 1       | 1    |
-- | 2       | 0    |
-- | 3       | 1    |
-- | 4       | 1    |
-- | 5       | 1    |
-- +---------+------+
-- 输出:
-- +---------+
-- | seat_id |
-- +---------+
-- | 3       |
-- | 4       |
-- | 5       |
-- +---------+

SELECT DISTINCT c1.seat_id as seat_id FROM Cinema as c1
inner join Cinema as c2
ON abs(c1.seat_id - c2.seat_id) = 1 and c1.free = 1 and c2.free = 1
ORDER BY seat_id ASC


