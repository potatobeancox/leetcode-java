package com.potato.study.leetcodecn.p01757.t001;

--  2668. 查询员工当前薪水
--
-- SQL架构
-- 表：Salary
--
-- +---------------+---------+
-- | 列名          | 类型    |
-- +---------------+---------+
-- | emp_id        | int     |
-- | firstname     | varchar |
-- | lastname      | varchar |
-- | salary        | varchar |
-- | department_id | varchar |
-- +---------------+---------+
-- (emp_id, salary) 是此表的主键。
-- 每行包含员工的详细信息和他们每年的薪水，但有些记录是旧的，包含过时的薪资信息。
-- 编写一个 SQL 查询，找到每个员工的当前薪水，假设薪水每年增加。输出他们的 emp_id 、firstname 、lastname 、salary 和 department_id 。
--
-- 按升序顺序按 emp_id 排序返回结果表。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1：
--
-- 输入：
-- Salary 表:
-- +--------+-----------+----------+--------+---------------+
-- | emp_id | firstname | lastname | salary | department_id |
-- +--------+-----------+----------+--------+---------------+
-- | 1      | Todd      | Wilson   | 110000 | D1006         |
-- | 1      | Todd      | Wilson   | 106119 | D1006         |
-- | 2      | Justin    | Simon    | 128922 | D1005         |
-- | 2      | Justin    | Simon    | 128922 | D1005         |
-- | 3      | Kelly     | Rosario  | 42689  | D1002         |
-- | 4      | Patricia  | Powell   | 162825 | D1004         |
-- | 4      | Patricia  | Powell   | 170000 | D1004         |
-- | 5      | Sherry    | Golden   | 44101  | D1002         |
-- | 6      | Natasha   | Swanson  | 79632  | D1005         |
-- | 6      | Natasha   | Swanson  | 90000  | D1005         |
-- +--------+-----------+----------+--------+---------------+
-- 输出：
-- +--------+-----------+----------+--------+---------------+
-- | emp_id | firstname | lastname | salary | department_id |
-- +--------+-----------+----------+--------+---------------+
-- | 1      | Todd      | Wilson   | 110000 | D1006         |
-- | 2      | Justin    | Simon    | 130000 | D1005         |
-- | 3      | Kelly     | Rosario  | 42689  | D1002         |
-- | 4      | Patricia  | Powell   | 170000 | D1004         |
-- | 5      | Sherry    | Golden   | 44101  | D1002         |
-- | 6      | Natasha   | Swanson  | 90000  | D1005         |
-- +--------+-----------+----------+--------+---------------+
--
-- 解释：
-- - emp_id 1 有两条记录，工资分别为 110000 和 106119，其中 110000 是更新后的工资（假设工资每年都会增加）
-- - emp_id 2 有两条记录，工资分别为 128922 和 128922，其中 130000 是更新后的工资。
-- - emp_id 3 只有一条工资记录，因此这已经是更新后的工资。
-- - emp_id 4 有两条记录，工资分别为 162825 和 170000，其中 170000 是更新后的工资。
-- - emp_id 5 只有一条工资记录，因此这已经是更新后的工资。
-- - emp_id 6 有两条记录，工资分别为 79632 和 90000，其中 90000 是更新后的工资。

SELECT
  emp_id,
  max(firstname) as firstname,
  max(lastname) as lastname,
  max(salary) as salary,
  max(department_id) as department_id
FROM Salary
GROUP BY emp_id
ORDER  BY  emp_id