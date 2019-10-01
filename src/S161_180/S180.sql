/**
编写一个 SQL 查询，查找所有至少连续出现三次的数字。
+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
例如，给定上面的 Logs 表， 1 是唯一连续出现至少三次的数字。
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/consecutive-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
# 要求id必须连续
SELECT DISTINCT a.Num AS ConsecutiveNums
FROM Logs a,
     Logs b,
     Logs c
WHERE a.Id = b.Id - 1
  AND b.Id = c.Id - 1
  AND a.Num = b.Num
  AND b.Num = c.Num;

SELECT DISTINCT Num as ConsecutiveNums
FROM (
         SELECT Num,
                CASE
                    WHEN @prev = Num THEN @count := @count + 1
                    WHEN (@prev := Num) IS NOT NULL THEN @count := 1
                    END AS CNT
         FROM Logs,
              (SELECT @prev := NULL, @count := NULL) AS t
     ) AS temp
WHERE temp.CNT >= 3