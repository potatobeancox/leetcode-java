package com.potato.study.leetcodecn.p01757.t001;

--  1613. 找到遗失的ID
--
-- SQL架构
-- 表: Customers
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | customer_id   | int     |
-- | customer_name | varchar |
-- +---------------+---------+
-- customer_id 是该表主键.
-- 该表第一行包含了顾客的名字和id.
--
--
-- 写一个 SQL 语句, 找到所有遗失的顾客id. 遗失的顾客id是指那些不在 Customers 表中, 值却处于 1 和表中最大 customer_id 之间的id.
--
-- 注意: 最大的 customer_id 值不会超过 100.
--
-- 返回结果按 ids 升序排列
--
-- 查询结果格式如下例所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Customers 表:
-- +-------------+---------------+
-- | customer_id | customer_name |
-- +-------------+---------------+
-- | 1           | Alice         |
-- | 4           | Bob           |
-- | 5           | Charlie       |
-- +-------------+---------------+
-- 输出：
-- +-----+
-- | ids |
-- +-----+
-- | 2   |
-- | 3   |
-- +-----+
-- 解释：
-- 表中最大的customer_id是5, 所以在范围[1,5]内, ID2和3从表中遗失.

with recursive t as (

  SELECT 1 customer_id
  UNION ALL
  SELECT customer_id+1 FROM t WHERE customer_id<(SELECT MAX(customer_id) FROM Customers)
)

SELECT
 t.customer_id as ids
FROM t LEFT JOIN Customers
USING(customer_id)
WHERE Customers.customer_id is NULL
