package com.potato.study.leetcodecn.p01757.t001;

--  1445. 苹果和桔子
--
-- SQL架构
-- 表: Sales
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | sale_date     | date    |
-- | fruit         | enum    |
-- | sold_num      | int     |
-- +---------------+---------+
-- (sale_date,fruit) 是该表主键.
-- 该表包含了每一天中"苹果" 和 "桔子"的销售情况.
--
--
-- 写一个 SQL 查询, 报告每一天 苹果 和 桔子 销售的数目的差异.
--
-- 返回的结果表, 按照格式为 ('YYYY-MM-DD') 的 sale_date 排序.
--
-- 查询结果表如下例所示:
--
--
--
-- Sales 表:
-- +------------+------------+-------------+
-- | sale_date  | fruit      | sold_num    |
-- +------------+------------+-------------+
-- | 2020-05-01 | apples     | 10          |
-- | 2020-05-01 | oranges    | 8           |
-- | 2020-05-02 | apples     | 15          |
-- | 2020-05-02 | oranges    | 15          |
-- | 2020-05-03 | apples     | 20          |
-- | 2020-05-03 | oranges    | 0           |
-- | 2020-05-04 | apples     | 15          |
-- | 2020-05-04 | oranges    | 16          |
-- +------------+------------+-------------+
--
-- Result 表:
-- +------------+--------------+
-- | sale_date  | diff         |
-- +------------+--------------+
-- | 2020-05-01 | 2            |
-- | 2020-05-02 | 0            |
-- | 2020-05-03 | 20           |
-- | 2020-05-04 | -1           |
-- +------------+--------------+
--
-- 在 2020-05-01, 卖了 10 个苹果 和 8 个桔子 (差异为 10 - 8 = 2).
-- 在 2020-05-02, 卖了 15 个苹果 和 15 个桔子 (差异为 15 - 15 = 0).
-- 在 2020-05-03, 卖了 20 个苹果 和 0 个桔子 (差异为 20 - 0 = 20).
-- 在 2020-05-04, 卖了 15 个苹果 和 16 个桔子 (差异为 15 - 16 = -1).

-- https://leetcode.cn/problems/apples-oranges/solution/by-jam007-lkl1/
-- https://www.php.cn/sql/442968.html
-- case when 类似 if else

SELECT
  sale_date,
  sum(
    CASE fruit
      WHEN 'apples'
      THEN sold_num
      ELSE -sold_num
    END
  ) as diff
  FROM Sales
  GROUP BY sale_date
  ORDER BY sale_date ASC