package com.potato.study.leetcodecn.p01757.t001;

--  1667. 修复表中的名字
--
-- SQL架构
-- 表： Users
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | user_id        | int     |
-- | name           | varchar |
-- +----------------+---------+
-- user_id 是该表的主键。
-- 该表包含用户的 ID 和名字。名字仅由小写和大写字符组成。
--
--
-- 编写一个 SQL 查询来修复名字，使得只有第一个字符是大写的，其余都是小写的。
--
-- 返回按 user_id 排序的结果表。
--
-- 查询结果格式示例如下。
--
--
--
-- 示例 1：
--
-- 输入：
-- Users table:
-- +---------+-------+
-- | user_id | name  |
-- +---------+-------+
-- | 1       | aLice |
-- | 2       | bOB   |
-- +---------+-------+
-- 输出：
-- +---------+-------+
-- | user_id | name  |
-- +---------+-------+
-- | 1       | Alice |
-- | 2       | Bob   |
-- +---------+-------+

-- 截断函数
-- https://blog.csdn.net/qq_49480008/article/details/123880876

SELECT user_id,
concat(UPPER(LEFT(name, 1)), LOWER(substr(name, 2))) as name
FROM Users
ORDER by user_id

