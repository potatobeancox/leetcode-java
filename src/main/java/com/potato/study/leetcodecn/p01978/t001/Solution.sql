package com.potato.study.leetcodecn.p01757.t001;

--  1978. 上级经理已离职的公司员工
--
-- 表: Employees
--
-- +-------------+----------+
-- | Column Name | Type     |
-- +-------------+----------+
-- | employee_id | int      |
-- | name        | varchar  |
-- | manager_id  | int      |
-- | salary      | int      |
-- +-------------+----------+
-- employee_id 是这个表的主键。
-- 这个表包含了员工，他们的薪水和上级经理的id。
-- 有一些员工没有上级经理（其manager_id 是空值）。
--  
--
-- 写一个查询语句，查询出，这些员工的id，他们的薪水严格少于$30000 并且他们的上级经理已离职。当一个经理离开公司时，他们的信息需要从员工表中删除掉，但是表中的员工的manager_id  这一列还是设置的离职经理的id 。
--
-- 返回的结果按照employee_id 从小到大排序。
--
-- 查询结果如下所示：
--
--  
--
-- 示例：
--
-- 输入：
-- Employees table:
-- +-------------+-----------+------------+--------+
-- | employee_id | name      | manager_id | salary |
-- +-------------+-----------+------------+--------+
-- | 3           | Mila      | 9          | 60301  |
-- | 12          | Antonella | null       | 31000  |
-- | 13          | Emery     | null       | 67084  |
-- | 1           | Kalel     | 11         | 21241  |
-- | 9           | Mikaela   | null       | 50937  |
-- | 11          | Joziah    | 6          | 28485  |
-- +-------------+-----------+------------+--------+
-- 输出：
-- +-------------+
-- | employee_id |
-- +-------------+
-- | 11          |
-- +-------------+
--
-- 解释：
-- 薪水少于30000美元的员工有1号(Kalel) and 11号 (Joziah)。
-- Kalel的上级经理是11号员工，他还在公司上班(他是Joziah)。
-- Joziah的上级经理是6号员工，他已经离职，因为员工表里面已经没有6号员工的信息了，它被删除了。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/employees-whose-manager-left-the-company
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

select employee_id from Employees where salary < 30000 and manager_id not in (
    select employee_id from Employees
)
order by employee_id

