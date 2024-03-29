--
-- 569. 员工薪水中位数
--
--
-- SQL架构
-- 表: Employee
--
-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | id           | int     |
-- | company      | varchar |
-- | salary       | int     |
-- +--------------+---------+
-- Id是该表的主键列。
-- 该表的每一行表示公司和一名员工的工资。
--
--
-- 写一个SQL查询，找出每个公司的工资中位数。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式如下所示。
--
--
--
-- 示例 1:
--
-- 输入:
-- Employee 表:
-- +----+---------+--------+
-- | id | company | salary |
-- +----+---------+--------+
-- | 1  | A       | 2341   |
-- | 2  | A       | 341    |
-- | 3  | A       | 15     |
-- | 4  | A       | 15314  |
-- | 5  | A       | 451    |
-- | 6  | A       | 513    |
-- | 7  | B       | 15     |
-- | 8  | B       | 13     |
-- | 9  | B       | 1154   |
-- | 10 | B       | 1345   |
-- | 11 | B       | 1221   |
-- | 12 | B       | 234    |
-- | 13 | C       | 2345   |
-- | 14 | C       | 2645   |
-- | 15 | C       | 2645   |
-- | 16 | C       | 2652   |
-- | 17 | C       | 65     |
-- +----+---------+--------+
-- 输出:
-- +----+---------+--------+
-- | id | company | salary |
-- +----+---------+--------+
-- | 5  | A       | 451    |
-- | 6  | A       | 513    |
-- | 12 | B       | 234    |
-- | 9  | B       | 1154   |
-- | 14 | C       | 2645   |
-- +----+---------+--------+
--
--
-- 进阶: 你能在不使用任何内置函数或窗口函数的情况下解决它吗?

-- 题解
-- https://leetcode.cn/problems/median-employee-salary/solution/si-chong-fang-fa-jie-yuan-gong-xin-shui-gykpa/

-- 求 每个公司排行 每个公司总数
SELECT id, company, salary FROM (

  SELECT
    id,
    company,
    salary,
    row_number() over(partition BY company ORDER BY salary) as rk,
    count(id) over(partition BY company) as total
  FROM Employee

) as t WHERE t.rk in( floor((total+1)/2), floor((total+2)/2))


