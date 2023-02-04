--
-- 2394. 开除员工
--
--
-- SQL架构
-- 表: Employees
--
-- +--------------+------+
-- | Column Name  | Type |
-- +--------------+------+
-- | employee_id  | int  |
-- | needed_hours | int  |
-- +--------------+------+
-- employee_id 是该表的主键。
-- 每一行都包含员工的 id 和他们获得工资所需的最低工作时数。
--
--
-- 表: Logs
--
-- +-------------+----------+
-- | Column Name | Type     |
-- +-------------+----------+
-- | employee_id | int      |
-- | in_time     | datetime |
-- | out_time    | datetime |
-- +-------------+----------+
-- (employee_id, in_time, out_time) 是该表的主键。
-- 该表的每一行都显示了员工的时间戳。in_time 是员工开始工作的时间，out_time 是员工结束工作的时间。
-- 所有时间都在 2022 年 10 月。out_time 可以是 in_time 之后的一天，这意味着该员工在午夜之后工作。
--
--
-- 在公司里，每个员工每个月必须工作一定的小时数。员工在工作段中工作。员工工作的小时数可以通过员工在所有工作段中工作的分钟数的总和来计算。每个工作段的分钟数是四舍五入的。
--
-- 例如，如果员工在一个时间段中工作了 51 分 2 秒，我们就认为它是 52 分钟。
-- 编写一个 SQL 查询来报告将被开除的员工的 id。换句话说，报告没有工作所需时间的员工的 id。
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
-- Employees 表:
-- +-------------+--------------+
-- | employee_id | needed_hours |
-- +-------------+--------------+
-- | 1           | 20           |
-- | 2           | 12           |
-- | 3           | 2            |
-- +-------------+--------------+
-- Logs 表:
-- +-------------+---------------------+---------------------+
-- | employee_id | in_time             | out_time            |
-- +-------------+---------------------+---------------------+
-- | 1           | 2022-10-01 09:00:00 | 2022-10-01 17:00:00 |
-- | 1           | 2022-10-06 09:05:04 | 2022-10-06 17:09:03 |
-- | 1           | 2022-10-12 23:00:00 | 2022-10-13 03:00:01 |
-- | 2           | 2022-10-29 12:00:00 | 2022-10-29 23:58:58 |
-- +-------------+---------------------+---------------------+
-- 输出:
-- +-------------+
-- | employee_id |
-- +-------------+
-- | 2           |
-- | 3           |
-- +-------------+
-- 解释:
-- 员工 1:
--  - 参加了三个工作段:
--     - 在 2022-10-01, 他工作了 8 个小时。
--     - 在 2022-10-06, 他工作了 8 小时 4 分钟。
--     - 在 2022-10-12, 他工作了 4 小时 1 分钟。请注意，他一直工作到午夜。
--  - 员工 1 在各个时段总共工作了 20 小时5分钟，不被开除。
-- 员工 2:
--  - 参加了一个工作段:
--     - 在 2022-10-29, 他工作了 11 小时 59 分钟。
--  - 员工 2 没有工作足够的时长，将被开除。
-- 员工 3:
--  - 没有任何工作段。
--  - 员工 3 没有工作足够的时长，将被开除。


-- 每个员工时间差 MySQL CEIL() 函数返回大于或等于指定数字的最小整数值。CEIL() 函数等同于 CEILING() 函数。
--  ceil() https://www.cnblogs.com/andy0816/p/17013356.html
-- 时间差函数---timestampdiff
-- https://blog.csdn.net/qq_16470351/article/details/103686956


select
    employee_id
from (
    select
        Employees.employee_id,
        Employees.needed_hours,
        sum(ceil(timestampdiff(second, Logs.in_time,Logs.out_time)/60)) as work_min
    from Employees left join Logs
    on Employees.employee_id = Logs.employee_id
    group by Employees.employee_id
) tt where needed_hours * 60 > work_min or work_min is null

-- https://leetcode.cn/problems/employees-with-deductions/solution/xiao-bai-zuo-lian-jie-yi-ci-chu-jie-guo-8p3qv/