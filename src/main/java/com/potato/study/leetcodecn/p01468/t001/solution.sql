--
-- 1468. 计算税后工资
--
--
--
-- SQL架构
-- Salaries 表：
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | company_id    | int     |
-- | employee_id   | int     |
-- | employee_name | varchar |
-- | salary        | int     |
-- +---------------+---------+
-- (company_id, employee_id) 是这个表的主键
-- 这个表包括员工的company id, id, name 和 salary
--
--
-- 写一条查询 SQL 来查找每个员工的税后工资
--
-- 每个公司的税率计算依照以下规则
--
-- 如果这个公司员工最高工资不到 1000 ，税率为 0%
-- 如果这个公司员工最高工资在 1000 到 10000 之间，税率为 24%
-- 如果这个公司员工最高工资大于 10000 ，税率为 49%
-- 按任意顺序返回结果，税后工资结果取整
--
--
--
-- 结果表格式如下例所示：
--
-- Salaries 表：
-- +------------+-------------+---------------+--------+
-- | company_id | employee_id | employee_name | salary |
-- +------------+-------------+---------------+--------+
-- | 1          | 1           | Tony          | 2000   |
-- | 1          | 2           | Pronub        | 21300  |
-- | 1          | 3           | Tyrrox        | 10800  |
-- | 2          | 1           | Pam           | 300    |
-- | 2          | 7           | Bassem        | 450    |
-- | 2          | 9           | Hermione      | 700    |
-- | 3          | 7           | Bocaben       | 100    |
-- | 3          | 2           | Ognjen        | 2200   |
-- | 3          | 13          | Nyancat       | 3300   |
-- | 3          | 15          | Morninngcat   | 7777   |
-- +------------+-------------+---------------+--------+
--
-- Result 表：
-- +------------+-------------+---------------+--------+
-- | company_id | employee_id | employee_name | salary |
-- +------------+-------------+---------------+--------+
-- | 1          | 1           | Tony          | 1020   |
-- | 1          | 2           | Pronub        | 10863  |
-- | 1          | 3           | Tyrrox        | 5508   |
-- | 2          | 1           | Pam           | 300    |
-- | 2          | 7           | Bassem        | 450    |
-- | 2          | 9           | Hermione      | 700    |
-- | 3          | 7           | Bocaben       | 76     |
-- | 3          | 2           | Ognjen        | 1672   |
-- | 3          | 13          | Nyancat       | 2508   |
-- | 3          | 15          | Morninngcat   | 5911   |
-- +------------+-------------+---------------+--------+
-- 对于公司 1 ，最高工资是 21300 ，其每个员工的税率为 49%
-- 对于公司 2 ，最高工资是 700 ，其每个员工税率为 0%
-- 对于公司 3 ，最高工资是 7777 ，其每个员工税率是 24%
-- 税后工资计算 = 工资 - ( 税率 / 100）*工资
-- 对于上述案例，Morninngcat 的税后工资 = 7777 - 7777 * ( 24 / 100) = 7777 - 1866.48 = 5910.52 ，取整为 5911


-- 连表获取每个公司的最高工资
SELECT
  Salaries.company_id,
  Salaries.employee_id,
  Salaries.employee_name,
  CASE
      WHEN t.max_salary < 1000 THEN salary
      WHEN t.max_salary >= 1000 AND t.max_salary <= 10000 THEN round(salary * 0.76, 0)
      WHEN t.max_salary > 10000 THEN round(salary * 0.51, 0)
      ELSE 0
  END
  as salary
FROM Salaries
INNER JOIN (
  -- 查公司id 对应的最高工资
  SELECT
    company_id,
    max(salary) as max_salary
  FROM Salaries
  GROUP BY company_id

) t USING(company_id)
