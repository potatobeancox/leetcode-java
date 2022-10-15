package com.potato.study.leetcodecn.p01757.t001;

--  2159. 分别排序两列
--
--  SQL架构
-- 表：Data
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | first_col   | int  |
-- | second_col  | int  |
-- +-------------+------+
-- 该表没有主键且可能包含重复数据。
--
--
-- 请你编写 SQL 使：
--
-- first_col 按照 升序 排列。
-- second_col 按照 降序 排列。
-- 查询返回的结果格式如下。
--
--
--
-- 示例：
--
-- 输入：
-- Data 表：
-- +-----------+------------+
-- | first_col | second_col |
-- +-----------+------------+
-- | 4         | 2          |
-- | 2         | 3          |
-- | 3         | 1          |
-- | 1         | 4          |
-- +-----------+------------+
-- 输出：
-- +-----------+------------+
-- | first_col | second_col |
-- +-----------+------------+
-- | 1         | 4          |
-- | 2         | 3          |
-- | 3         | 2          |
-- | 4         | 1          |
-- +-----------+------------+



SELECT
  t1.first_col,
  t2.second_col
FROM (
  SELECT
    first_col,
    row_number() over(ORDER BY first_col) as rk
  FROM Data
) as t1 INNER JOIN (
  SELECT
    second_col,
    row_number() over(ORDER BY second_col DESC ) as rk
  FROM Data
) as t2 USING(rk)




