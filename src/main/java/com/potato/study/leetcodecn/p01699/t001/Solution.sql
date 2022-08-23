package com.potato.study.leetcodecn.p01757.t001;

--  1699. 两人之间的通话次数
--
-- 表： Calls
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | from_id     | int     |
-- | to_id       | int     |
-- | duration    | int     |
-- +-------------+---------+
-- 该表没有主键，可能存在重复项。
-- 该表包含 from_id 与 to_id 间的一次电话的时长。
-- from_id != to_id
--  
--
-- 编写 SQL 语句，查询每一对用户 (person1, person2) 之间的通话次数和通话总时长，其中 person1 < person2 。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式如下示例所示。
--
--  
--
-- 示例 1：
--
-- 输入：
-- Calls 表：
-- +---------+-------+----------+
-- | from_id | to_id | duration |
-- +---------+-------+----------+
-- | 1       | 2     | 59       |
-- | 2       | 1     | 11       |
-- | 1       | 3     | 20       |
-- | 3       | 4     | 100      |
-- | 3       | 4     | 200      |
-- | 3       | 4     | 200      |
-- | 4       | 3     | 499      |
-- +---------+-------+----------+
-- 输出：
-- +---------+---------+------------+----------------+
-- | person1 | person2 | call_count | total_duration |
-- +---------+---------+------------+----------------+
-- | 1       | 2       | 2          | 70             |
-- | 1       | 3       | 1          | 20             |
-- | 3       | 4       | 4          | 999            |
-- +---------+---------+------------+----------------+
-- 解释：
-- 用户 1 和 2 打过 2 次电话，总时长为 70 (59 + 11)。
-- 用户 1 和 3 打过 1 次电话，总时长为 20。
-- 用户 3 和 4 打过 4 次电话，总时长为 999 (100 + 200 + 200 + 499)。
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/number-of-calls-between-two-persons
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

-- if 表达式
-- https://blog.csdn.net/weixin_45659364/article/details/115468039
-- 将 Calls 转化成  person1 person2 duration 其中  person1 《 person2

select person1,person2,
    count(duration) as call_count,
    sum(duration) as total_duration
from (
    select
        IF(from_id>to_id, to_id, from_id) as person1,
        IF(from_id>to_id, from_id, to_id) as person2,
        duration
    from Calls
) t
group by person1,person2