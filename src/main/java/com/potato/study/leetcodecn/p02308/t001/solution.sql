--
-- 2308. Arrange Table by Gender
--
-- SQL架构
-- Table: Genders
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | user_id     | int     |
-- | gender      | varchar |
-- +-------------+---------+
-- user_id is the primary key for this table.
-- gender is ENUM of type 'female', 'male', or 'other'.
-- Each row in this table contains the ID of a user and their gender.
-- The table has an equal number of 'female', 'male', and 'other'.
-- Write an SQL query to rearrange the Genders table such that the rows alternate between 'female', 'other', and 'male' in order. The table should be rearranged such that the IDs of each gender are sorted in ascending order.
--
-- Return the result table in the mentioned order.
--
-- The query result format is shown in the following example.
--
--
--
-- Example 1:
--
-- Input:
-- Genders table:
-- +---------+--------+
-- | user_id | gender |
-- +---------+--------+
-- | 4       | male   |
-- | 7       | female |
-- | 2       | other  |
-- | 5       | male   |
-- | 3       | female |
-- | 8       | male   |
-- | 6       | other  |
-- | 1       | other  |
-- | 9       | female |
-- +---------+--------+
-- Output:
-- +---------+--------+
-- | user_id | gender |
-- +---------+--------+
-- | 3       | female |
-- | 1       | other  |
-- | 4       | male   |
-- | 7       | female |
-- | 2       | other  |
-- | 5       | male   |
-- | 9       | female |
-- | 6       | other  |
-- | 8       | male   |
-- +---------+--------+
-- Explanation:
-- Female gender: IDs 3, 7, and 9.
-- Other gender: IDs 1, 2, and 6.
-- Male gender: IDs 4, 5, and 8.
-- We arrange the table alternating between 'female', 'other', and 'male'.
-- Note that the IDs of each gender are sorted in ascending order.
--
--
-- 题解
-- https://leetcode.cn/problems/arrange-table-by-gender/solution/by-ql2142-blql/
-- 开窗函数
-- https://www.cnblogs.com/lcngu/p/5335170.html
-- 开窗函数生成 行号 按照 gender 分类生成



SELECT user_id, gender
FROM (
  SELECT
    *,
    ROW_NUMBER() OVER(PARTITION BY gender ORDER BY user_id) as ran
  FROM Genders
) as t
ORDER BY t.ran, gender