package com.potato.study.leetcodecn.p01757.t001;

--  1179. 重新格式化部门表
--
-- SQL架构
-- 部门表 Department：
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | id            | int     |
-- | revenue       | int     |
-- | month         | varchar |
-- +---------------+---------+
-- (id, month) 是表的联合主键。
-- 这个表格有关于每个部门每月收入的信息。
-- 月份（month）可以取下列值 ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]。
--
--
-- 编写一个 SQL 查询来重新格式化表，使得新的表中有一个部门 id 列和一些对应 每个月 的收入（revenue）列。
--
-- 查询结果格式如下面的示例所示：
--
-- Department 表：
-- +------+---------+-------+
-- | id   | revenue | month |
-- +------+---------+-------+
-- | 1    | 8000    | Jan   |
-- | 2    | 9000    | Jan   |
-- | 3    | 10000   | Feb   |
-- | 1    | 7000    | Feb   |
-- | 1    | 6000    | Mar   |
-- +------+---------+-------+
--
-- 查询得到的结果表：
-- +------+-------------+-------------+-------------+-----+-------------+
-- | id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
-- +------+-------------+-------------+-------------+-----+-------------+
-- | 1    | 8000        | 7000        | 6000        | ... | null        |
-- | 2    | 9000        | null        | null        | ... | null        |
-- | 3    | null        | 10000       | null        | ... | null        |
-- +------+-------------+-------------+-------------+-----+-------------+
--
-- 注意，结果表有 13 列 (1个部门 id 列 + 12个月份的收入列)。


-- ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]

-- https://www.php.cn/sql/442968.html
-- case when



SELECT
  id,
  sum(CASE WHEN month = 'Jan' THEN revenue END) as Jan_Revenue,
  sum(CASE WHEN month = 'Feb' THEN revenue END) as Feb_Revenue,
  sum(CASE WHEN month = 'Mar' THEN revenue END) as Mar_Revenue,
  sum(CASE WHEN month = 'Apr' THEN revenue END) as Apr_Revenue,
  sum(CASE WHEN month = 'May' THEN revenue END) as May_Revenue,
  sum(CASE WHEN month = 'Jun' THEN revenue END) as Jun_Revenue,
  sum(CASE WHEN month = 'Jul' THEN revenue END) as Jul_Revenue,
  sum(CASE WHEN month = 'Aug' THEN revenue END) as Aug_Revenue,
  sum(CASE WHEN month = 'Sep' THEN revenue END) as Sep_Revenue,
  sum(CASE WHEN month = 'Oct' THEN revenue END) as Oct_Revenue,
  sum(CASE WHEN month = 'Nov' THEN revenue END) as Nov_Revenue,
  sum(CASE WHEN month = 'Dec' THEN revenue END) as Dec_Revenue

  FROM Department
  GROUP BY id
  ORDER BY id