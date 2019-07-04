/*
给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+
例如，根据上述给定的 Weather 表格，返回如下 Id:
+----+
| Id |
+----+
|  2 |
|  4 |
+----+
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rising-temperature
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
 /*
  DATEDIFF() returns expr1 − expr2 expressed as a value in days from one date to the other. expr1 and expr2 are date or date-and-time expressions.
  Only the date parts of the values are used in the calculation.
    mysql> SELECT DATEDIFF('2007-12-31 23:59:59','2007-12-30');
        -> 1
    mysql> SELECT DATEDIFF('2010-11-30 23:59:59','2010-12-31');
        -> -31
  */
SELECT w1.Id
FROM Weather w1,
     Weather w2
WHERE DATEDIFF(w1.RecordDate, w2.RecordDate) = 1
  AND w1.Temperature > w2.Temperature;