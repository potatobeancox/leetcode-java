package com.potato.study.leetcodecn.p01757.t001;

-- 1076. 项目员工II

--
-- Table: Project
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | project_id  | int     |
-- | employee_id | int     |
-- +-------------+---------+
-- 主键为 (project_id, employee_id)。
-- employee_id 是员工表 Employee 表的外键。
-- Table: Employee
--
-- +------------------+---------+
-- | Column Name      | Type    |
-- +------------------+---------+
-- | employee_id      | int     |
-- | name             | varchar |
-- | experience_years | int     |
-- +------------------+---------+
-- 主键是 employee_id。
--  
--
-- 编写一个SQL查询，报告所有雇员最多的项目。
--
-- 查询结果格式如下所示：
--
-- Project table:
-- +-------------+-------------+
-- | project_id  | employee_id |
-- +-------------+-------------+
-- | 1           | 1           |
-- | 1           | 2           |
-- | 1           | 3           |
-- | 2           | 1           |
-- | 2           | 4           |
-- +-------------+-------------+
--
-- Employee table:
-- +-------------+--------+------------------+
-- | employee_id | name   | experience_years |
-- +-------------+--------+------------------+
-- | 1           | Khaled | 3                |
-- | 2           | Ali    | 2                |
-- | 3           | John   | 1                |
-- | 4           | Doe    | 2                |
-- +-------------+--------+------------------+
--
-- Result table:
-- +-------------+
-- | project_id  |
-- +-------------+
-- | 1           |
-- +-------------+
-- 第一个项目有3名员工，第二个项目有2名员工。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/project-employees-ii
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



-- 找到最大

SELECT project_id FROM
(
  SELECT project_id, COUNT(employee_id) as emp_sum FROM Project GROUP BY project_id
) as tab WHERE tab.emp_sum = (
  SELECT emp_sum as emp_sum_max FROM (
    SELECT project_id, COUNT(employee_id) as emp_sum FROM Project GROUP BY project_id ORDER BY emp_sum DESC
  ) as t
  limit 1
)


