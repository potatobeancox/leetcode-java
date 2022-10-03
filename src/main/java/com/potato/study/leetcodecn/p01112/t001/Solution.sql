package com.potato.study.leetcodecn.p01757.t001;

--  1112. 每位学生的最高成绩
--
-- SQL架构
-- 表：Enrollments
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | student_id    | int     |
-- | course_id     | int     |
-- | grade         | int     |
-- +---------------+---------+
-- (student_id, course_id) 是该表的主键。
--
--
-- 编写一个 SQL 查询，查询每位学生获得的最高成绩和它所对应的科目，若科目成绩并列，取 course_id 最小的一门。查询结果需按 student_id 增序进行排序。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1：
--
-- 输入：
-- Enrollments 表：
-- +------------+-------------------+
-- | student_id | course_id | grade |
-- +------------+-----------+-------+
-- | 2          | 2         | 95    |
-- | 2          | 3         | 95    |
-- | 1          | 1         | 90    |
-- | 1          | 2         | 99    |
-- | 3          | 1         | 80    |
-- | 3          | 2         | 75    |
-- | 3          | 3         | 82    |
-- +------------+-----------+-------+
-- 输出：
-- +------------+-------------------+
-- | student_id | course_id | grade |
-- +------------+-----------+-------+
-- | 1          | 2         | 99    |
-- | 2          | 2         | 95    |
-- | 3          | 3         | 82    |
-- +------------+-----------+-------+

-- https://leetcode.cn/problems/highest-grade-for-each-student/solution/sql-by-jamartin-7clx/

-- 查询 在里边的 学生和分数
SELECT
  student_id,
  min(course_id) as course_id,
  grade
FROM Enrollments
where (student_id, grade) in (
  -- 先求出每个学生的最高分数
  SELECT
    student_id,
    MAX(grade) as max_grade
  FROM Enrollments
  GROUP BY student_id
)
GROUP BY student_id
ORDER BY student_id



