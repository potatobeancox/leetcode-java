--
--
--
-- 2346. Compute the Rank as a Percentage
--
-- SQL架构
-- Table: Students
--
-- +---------------+------+
-- | Column Name   | Type |
-- +---------------+------+
-- | student_id    | int  |
-- | department_id | int  |
-- | mark          | int  |
-- +---------------+------+
-- student_id is the primary key of this table.
-- Each row of this table indicates a student's ID, the ID of the department in which the student enrolled, and their mark in the exam.
--
--
-- Write an SQL query that reports the rank of each student in their department as a percentage, where the rank as a percentage is computed using the following formula: (student_rank_in_the_department - 1) * 100 / (the_number_of_students_in_the_department - 1). The percentage should be rounded to 2 decimal places. student_rank_in_the_department is determined by descending mark, such that the student with the highest mark is rank 1. If two students get the same mark, they also get the same rank.
--
-- Return the result table in any order.
--
-- The query result format is in the following example.
--
--
--
-- Example 1:
--
-- Input:
-- Students table:
-- +------------+---------------+------+
-- | student_id | department_id | mark |
-- +------------+---------------+------+
-- | 2          | 2             | 650  |
-- | 8          | 2             | 650  |
-- | 7          | 1             | 920  |
-- | 1          | 1             | 610  |
-- | 3          | 1             | 530  |
-- +------------+---------------+------+
-- Output:
-- +------------+---------------+------------+
-- | student_id | department_id | percentage |
-- +------------+---------------+------------+
-- | 7          | 1             | 0.0        |
-- | 1          | 1             | 50.0       |
-- | 3          | 1             | 100.0      |
-- | 2          | 2             | 0.0        |
-- | 8          | 2             | 0.0        |
-- +------------+---------------+------------+
-- Explanation:
-- For Department 1:
--  - Student 7: percentage = (1 - 1) * 100 / (3 - 1) = 0.0
--  - Student 1: percentage = (2 - 1) * 100 / (3 - 1) = 50.0
--  - Student 3: percentage = (3 - 1) * 100 / (3 - 1) = 100.0
-- For Department 2:
--  - Student 2: percentage = (1 - 1) * 100 / (2 - 1) = 0.0
--  - Student 8: percentage = (1 - 1) * 100 / (2 - 1) = 0.0

-- 题解
-- https://leetcode.cn/problems/compute-the-rank-as-a-percentage/solution/by-redemption-7-m5rn/

-- with mysql 8.0 之后支持
-- https://blog.csdn.net/qq_41361162/article/details/116695706


-- with 语法部分
-- https://blog.csdn.net/howeres/article/details/122111478

-- PERCENT_RANK 函数 返回 小数排名
-- https://blog.csdn.net/weixin_41100555/article/details/118998353

-- 注意不能有注释。。。。。。。。

with t as (
  select *,percent_rank() over(partition by department_id order by mark desc) as percentage
  from students
)
select
  student_id,
  department_id,
  round(100*percentage,2) as percentage
from t
