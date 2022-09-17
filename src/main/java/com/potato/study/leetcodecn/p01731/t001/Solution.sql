package com.potato.study.leetcodecn.p01757.t001;

--  1731. 每位经理的下属员工数量
--
--  SQL架构
-- Table: Employees
--
-- +-------------+----------+
-- | Column Name | Type     |
-- +-------------+----------+
-- | employee_id | int      |
-- | name        | varchar  |
-- | reports_to  | int      |
-- | age         | int      |
-- +-------------+----------+
-- employee_id 是这个表的主键.
-- 该表包含员工以及需要听取他们汇报的上级经理的ID的信息。 有些员工不需要向任何人汇报（reports_to 为空）。
--
--
-- 对于此问题，我们将至少有一个其他员工需要向他汇报的员工，视为一个经理。
--
-- 编写SQL查询需要听取汇报的所有经理的ID、名称、直接向该经理汇报的员工人数，以及这些员工的平均年龄，其中该平均年龄需要四舍五入到最接近的整数。
--
-- 返回的结果集需要按照 employee_id 进行排序。
--
-- 查询结果的格式如下：
--
--
--
-- Employees table:
-- +-------------+---------+------------+-----+
-- | employee_id | name    | reports_to | age |
-- +-------------+---------+------------+-----+
-- | 9           | Hercy   | null       | 43  |
-- | 6           | Alice   | 9          | 41  |
-- | 4           | Bob     | 9          | 36  |
-- | 2           | Winston | null       | 37  |
-- +-------------+---------+------------+-----+
--
-- Result table:
-- +-------------+-------+---------------+-------------+
-- | employee_id | name  | reports_count | average_age |
-- +-------------+-------+---------------+-------------+
-- | 9           | Hercy | 2             | 39          |
-- +-------------+-------+---------------+-------------+
-- Hercy 有两个需要向他汇报的员工, 他们是 Alice and Bob. 他们的平均年龄是 (41+36)/2 = 38.5, 四舍五入的结果是 39.


SELECT
  t1.manager_id as employee_id,
  t2.name,
  t1.reports_count,
  t1.average_age
FROM (
  -- 计算每个非空的汇报次数
  SELECT
    reports_to as manager_id,
    COUNT(employee_id) as reports_count,
    round(avg(age), 0) as average_age
  FROM Employees
  WHERE reports_to is not NULL
  GROUP BY reports_to

) t1 LEFT JOIN Employees t2
ON t1.manager_id = t2.employee_id
ORDER BY employee_id


