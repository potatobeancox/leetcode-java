package com.potato.study.leetcodecn.p01757.t001;

--  1204. 最后一个能进入电梯的人
--
-- SQL架构
-- 表: Queue
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | person_id   | int     |
-- | person_name | varchar |
-- | weight      | int     |
-- | turn        | int     |
-- +-------------+---------+
-- person_id 是这个表的主键。
-- 该表展示了所有等待电梯的人的信息。
-- 表中 person_id 和 turn 列将包含从 1 到 n 的所有数字，其中 n 是表中的行数。
--
--
-- 有一群人在等着上公共汽车。然而，巴士有1000 公斤的重量限制，所以可能会有一些人不能上。
--
-- 写一条 SQL 查询语句查找 最后一个 能进入电梯且不超过重量限制的 person_name 。题目确保队列中第一位的人可以进入电梯，不会超重。
--
-- 查询结果如下所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Queue 表
-- +-----------+-------------------+--------+------+
-- | person_id | person_name       | weight | turn |
-- +-----------+-------------------+--------+------+
-- | 5         | George Washington | 250    | 1    |
-- | 3         | John Adams        | 350    | 2    |
-- | 6         | Thomas Jefferson  | 400    | 3    |
-- | 2         | Will Johnliams    | 200    | 4    |
-- | 4         | Thomas Jefferson  | 175    | 5    |
-- | 1         | James Elephant    | 500    | 6    |
-- +-----------+-------------------+--------+------+
-- 输出：
-- +-------------------+
-- | person_name       |
-- +-------------------+
-- | Thomas Jefferson  |
-- +-------------------+
-- 解释：
-- 为了简化，Queue 表按 turn 列由小到大排序。
-- 上例中 George Washington(id 5), John Adams(id 3) 和 Thomas Jefferson(id 6) 将可以进入电梯,因为他们的体重和为 250 + 350 + 400 = 1000。
-- Thomas Jefferson(id 6) 是最后一个体重合适并进入电梯的人。

-- 自然链接 https://blog.csdn.net/mliangc33/article/details/114448774?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-114448774-blog-2249209.t5_layer_eslanding_SACD_04&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-114448774-blog-2249209.t5_layer_eslanding_SACD_04&utm_relevant_index=1

-- 题解 https://leetcode.cn/problems/last-person-to-fit-in-the-bus/solution/zui-hou-yi-ge-neng-jin-ru-dian-ti-de-ren-by-leetco/

-- n* n 按照 turn group 这样每个 turn 都对应着之前的 结果 计算 sum  判断 sum 小于等于 1000 倒序排列 turn
SELECT q1.person_name as person_name FROM Queue q1, Queue q2
WHERE q1.turn >= q2.turn
GROUP BY q1.turn HAVING sum(q2.weight) <= 1000 ORDER BY q1.turn desc limit 1