--
-- 1501. 可以放心投资的国家
--
-- 表 Person:
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | id             | int     |
-- | name           | varchar |
-- | phone_number   | varchar |
-- +----------------+---------+
-- id 是该表主键.
-- 该表每一行包含一个人的名字和电话号码.
-- 电话号码的格式是:'xxx-yyyyyyy', 其中xxx是国家码(3个字符), yyyyyyy是电话号码(7个字符), x和y都表示数字. 同时, 国家码和电话号码都可以包含前导0.
-- 表 Country:
--
-- +----------------+---------+
-- | Column Name    | Type    |
-- +----------------+---------+
-- | name           | varchar |
-- | country_code   | varchar |
-- +----------------+---------+
-- country_code是该表主键.
-- 该表每一行包含国家名和国家码. country_code的格式是'xxx', x是数字.
--  
--
-- 表 Calls:
--
-- +-------------+------+
-- | Column Name | Type |
-- +-------------+------+
-- | caller_id   | int  |
-- | callee_id   | int  |
-- | duration    | int  |
-- +-------------+------+
-- 该表无主键, 可能包含重复行.
-- 每一行包含呼叫方id, 被呼叫方id和以分钟为单位的通话时长. caller_id != callee_id
-- 一家电信公司想要投资新的国家. 该公司想要投资的国家是:  该国的平均通话时长要严格地大于全球平均通话时长.
--
-- 写一段 SQL,  找到所有该公司可以投资的国家.
--
-- 返回的结果表没有顺序要求.
--
-- 查询的结果格式如下例所示.
--
-- Person 表:
-- +----+----------+--------------+
-- | id | name     | phone_number |
-- +----+----------+--------------+
-- | 3  | Jonathan | 051-1234567  |
-- | 12 | Elvis    | 051-7654321  |
-- | 1  | Moncef   | 212-1234567  |
-- | 2  | Maroua   | 212-6523651  |
-- | 7  | Meir     | 972-1234567  |
-- | 9  | Rachel   | 972-0011100  |
-- +----+----------+--------------+
--
-- Country 表:
-- +----------+--------------+
-- | name     | country_code |
-- +----------+--------------+
-- | Peru     | 051          |
-- | Israel   | 972          |
-- | Morocco  | 212          |
-- | Germany  | 049          |
-- | Ethiopia | 251          |
-- +----------+--------------+
--
-- Calls 表:
-- +-----------+-----------+----------+
-- | caller_id | callee_id | duration |
-- +-----------+-----------+----------+
-- | 1         | 9         | 33       |
-- | 2         | 9         | 4        |
-- | 1         | 2         | 59       |
-- | 3         | 12        | 102      |
-- | 3         | 12        | 330      |
-- | 12        | 3         | 5        |
-- | 7         | 9         | 13       |
-- | 7         | 1         | 3        |
-- | 9         | 7         | 1        |
-- | 1         | 7         | 7        |
-- +-----------+-----------+----------+
--
-- Result 表:
-- +----------+
-- | country  |
-- +----------+
-- | Peru     |
-- +----------+
-- 国家Peru的平均通话时长是 (102 + 102 + 330 + 330 + 5 + 5) / 6 = 145.666667
-- 国家Israel的平均通话时长是 (33 + 4 + 13 + 13 + 3 + 1 + 1 + 7) / 8 = 9.37500
-- 国家Morocco的平均通话时长是 (33 + 4 + 59 + 59 + 3 + 7) / 6 = 27.5000
-- 全球平均通话时长 = (2 * (33 + 4 + 59 + 102 + 330 + 5 + 13 + 3 + 1 + 7)) / 20 = 55.70000
-- 所以, Peru是唯一的平均通话时长大于全球平均通话时长的国家, 也是唯一的推荐投资的国家.
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode.cn/problems/countries-you-can-safely-invest-in
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


-- https://leetcode.cn/problems/countries-you-can-safely-invest-in/solution/by-ecstatic-craygkc-l33d/

SELECT
  name as country
FROM (
  (
    SELECT
      caller_id as user_id,
      duration
    FROM Calls
  )
  UNION ALL
  (
    SELECT
      callee_id as user_id,
      duration
    FROM Calls
  )
) cc
left join (
  SELECT
    Person.id as id,
    Country.name as name
  FROM Person
  LEFT JOIN Country
  ON left(Person.phone_number, 3) = Country.country_code
) kk
ON user_id = id
GROUP BY name
HAVING avg(cc.duration) > (SELECT avg(duration) FROM Calls)


