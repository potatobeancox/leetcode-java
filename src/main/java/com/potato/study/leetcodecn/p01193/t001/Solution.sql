package com.potato.study.leetcodecn.p01757.t001;

--  1193. 每月交易 I
--
-- SQL架构
-- Table: Transactions
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | id            | int     |
-- | country       | varchar |
-- | state         | enum    |
-- | amount        | int     |
-- | trans_date    | date    |
-- +---------------+---------+
-- id 是这个表的主键。
-- 该表包含有关传入事务的信息。
-- state 列类型为 “[”批准“，”拒绝“] 之一。
--
--
-- 编写一个 sql 查询来查找每个月和每个国家/地区的事务数及其总金额、已批准的事务数及其总金额。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Transactions table:
-- +------+---------+----------+--------+------------+
-- | id   | country | state    | amount | trans_date |
-- +------+---------+----------+--------+------------+
-- | 121  | US      | approved | 1000   | 2018-12-18 |
-- | 122  | US      | declined | 2000   | 2018-12-19 |
-- | 123  | US      | approved | 2000   | 2019-01-01 |
-- | 124  | DE      | approved | 2000   | 2019-01-07 |
-- +------+---------+----------+--------+------------+
-- 输出：
-- +----------+---------+-------------+----------------+--------------------+-----------------------+
-- | month    | country | trans_count | approved_count | trans_total_amount | approved_total_amount |
-- +----------+---------+-------------+----------------+--------------------+-----------------------+
-- | 2018-12  | US      | 2           | 1              | 3000               | 1000                  |
-- | 2019-01  | US      | 1           | 1              | 2000               | 2000                  |
-- | 2019-01  | DE      | 1           | 1              | 2000               | 2000                  |
-- +----------+---------+-------------+----------------+--------------------+-----------------------+

-- mysql if
-- https://blog.csdn.net/qq_36061501/article/details/121489121

-- 日志处理 date——format
-- https://www.w3school.com.cn/sql/func_date_format.asp


SELECT
  DATE_FORMAT(trans_date,'%Y-%m') as month,
  country,
  COUNT(id) as trans_count,
  COUNT(
    IF(state = 'approved', 1, NULL)
  ) as approved_count,
  SUM(amount) as trans_total_amount,
  SUM(
    IF(state = 'approved', amount, 0)
  ) as approved_total_amount
FROM Transactions
GROUP BY country, month