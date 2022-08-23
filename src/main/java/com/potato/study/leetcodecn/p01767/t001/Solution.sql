package com.potato.study.leetcodecn.p01757.t001;

--  1767. 寻找没有被执行的任务对
--
-- 表：Tasks
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | task_id        | int     |
-- | subtasks_count | int     |
-- +----------------+---------+
-- task_id 是这个表的主键。
-- task_id 表示的为主任务的id,每一个task_id被分为了多个子任务(subtasks)，subtasks_count表示为子任务的个数（n），它的值表示了子任务的索引从1到n。
-- 本表保证2 <=subtasks_count<= 20。
--  
--
-- 表： Executed
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | task_id       | int     |
-- | subtask_id    | int     |
-- +---------------+---------+
-- (task_id, subtask_id) 是这个表的主键。
-- 每一行表示标记为task_id的主任务与标记为subtask_id的子任务被成功执行。
-- 本表 保证 ，对于每一个task_id，subtask_id <= subtasks_count。
--  
--
-- 请试写一个SQL查询语句报告没有被执行的（主任务，子任务）对，即没有被执行的（task_id, subtask_id）。
--
-- 以 任何顺序 返回即可。
--
-- 查询结果格式如下。
--
--  
--
-- 示例 1：
--
-- 输入：
-- Tasks 表:
-- +---------+----------------+
-- | task_id | subtasks_count |
-- +---------+----------------+
-- | 1       | 3              |
-- | 2       | 2              |
-- | 3       | 4              |
-- +---------+----------------+
-- Executed 表:
-- +---------+------------+
-- | task_id | subtask_id |
-- +---------+------------+
-- | 1       | 2          |
-- | 3       | 1          |
-- | 3       | 2          |
-- | 3       | 3          |
-- | 3       | 4          |
-- +---------+------------+
-- 输出：
-- +---------+------------+
-- | task_id | subtask_id |
-- +---------+------------+
-- | 1       | 1          |
-- | 1       | 3          |
-- | 2       | 1          |
-- | 2       | 2          |
-- +---------+------------+
-- 解释：
-- Task 1 被分成了 3 subtasks (1, 2, 3)。只有 subtask 2 被成功执行, 所以我们返回 (1, 1) 和 (1, 3) 这两个主任务子任务对。
-- Task 2 被分成了 2 subtasks (1, 2)。没有一个subtask被成功执行, 因此我们返回(2, 1)和(2, 2)。
-- Task 3 被分成了 4 subtasks (1, 2, 3, 4)。所有的subtask都被成功执行，因此对于Task 3,我们不返回任何值。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/find-the-subtasks-that-did-not-execute
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


-- 利用 Tasks 表: 生成 task_id subtask_id 全
-- WITH RECURSIVE 用法
-- https://blog.csdn.net/mjfppxx/article/details/124879326
-- 题解
-- https://leetcode.cn/problems/find-the-subtasks-that-did-not-execute/solution/1767-jian-ji-xie-fa-recursive-xian-zhao-wl15z/
-- using 用法
-- https://blog.csdn.net/weiguang102/article/details/122957768

with recursive t(task_id, subtask_id) as (
    select task_id,subtasks_count from Tasks
    union all
    select task_id, subtask_id-1 from t where subtask_id-1>0

)
-- union all 将两个表组成一个 字段数量类型必须相同

select * from t left join Executed using(task_id, subtask_id)
where Executed.subtask_id is null