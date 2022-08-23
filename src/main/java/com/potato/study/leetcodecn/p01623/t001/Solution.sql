package com.potato.study.leetcodecn.p01757.t001;

--  1623. 三人国家代表队
--
-- SchoolA
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | student_id    | int     |
-- | student_name  | varchar |
-- +---------------+---------+
-- student_id 是表的主键
-- 表中的每一行包含了学校A中每一个学生的名字和ID
-- 所有student_name在表中都是独一无二的
--  
--
-- 表: SchoolB
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | student_id    | int     |
-- | student_name  | varchar |
-- +---------------+---------+
-- student_id 是表的主键
-- 表中的每一行包含了学校B中每一个学生的名字和ID
-- 所有student_name在表中都是独一无二的
--  
--
-- 表: SchoolC
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | student_id    | int     |
-- | student_name  | varchar |
-- +---------------+---------+
-- student_id 是表的主键
-- 表中的每一行包含了学校C中每一个学生的名字和ID
-- 所有student_name在表中都是独一无二的
--  
--
-- 有一个国家只有三所学校，这个国家的每一个学生只会注册一所学校。
--
-- 这个国家正在参加一个竞赛，他们希望从这三所学校中各选出一个学生来组建一支三人的代表队。
--
-- 例如：
--
-- member_A是从 SchoolA中选出的
-- member_B是从 SchoolB中选出的
-- member_C是从 SchoolC中选出的
-- 被选中的学生具有不同的名字和ID（没有任何两个学生拥有相同的名字、没有任何两个学生拥有相同的ID）
-- 使用上述条件，编写SQL查询语句来找到所有可能的三人国家代表队组合。
--
-- 查询结果接受任何顺序。
--
-- 查询结果格式如下例。
--
--  
--
-- 示例 1:
--
-- 输入：
-- SchoolA table:
-- +------------+--------------+
-- | student_id | student_name |
-- +------------+--------------+
-- | 1          | Alice        |
-- | 2          | Bob          |
-- +------------+--------------+
-- SchoolB table:
-- +------------+--------------+
-- | student_id | student_name |
-- +------------+--------------+
-- | 3          | Tom          |
-- +------------+--------------+
-- SchoolC table:
-- +------------+--------------+
-- | student_id | student_name |
-- +------------+--------------+
-- | 3          | Tom          |
-- | 2          | Jerry        |
-- | 10         | Alice        |
-- +------------+--------------+
-- 输出：
-- +----------+----------+----------+
-- | member_A | member_B | member_C |
-- +----------+----------+----------+
-- | Alice    | Tom      | Jerry    |
-- | Bob      | Tom      | Alice    |
-- +----------+----------+----------+
-- 解释：
-- 让我们看看有哪些可能的组合：
-- - (Alice, Tom, Tom) --> 不适用，因为member_B（Tom）和member_C（Tom）有相同的名字和ID
-- - (Alice, Tom, Jerry) --> 可能的组合
-- - (Alice, Tom, Alice) --> 不适用，因为member_A和member_C有相同的名字
-- - (Bob, Tom, Tom) --> 不适用，因为member_B和member_C有相同的名字和ID
-- - (Bob, Tom, Jerry) --> 不适用，因为member_A和member_C有相同的ID
-- - (Bob, Tom, Alice) --> 可能的组合.
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/all-valid-triplets-that-can-represent-a-country
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


select
     SchoolA.student_name as member_A,
     SchoolB.student_name as member_B,
     SchoolC.student_name as member_C
from SchoolA join SchoolB join SchoolC
where
    SchoolA.student_name != SchoolB.student_name and
    SchoolA.student_name != SchoolC.student_name and
    SchoolC.student_name != SchoolB.student_name and
    SchoolA.student_id != SchoolB.student_id and
    SchoolA.student_id != SchoolC.student_id and
    SchoolC.student_id != SchoolB.student_id