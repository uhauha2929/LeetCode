import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class S56 {
    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1)
            return intervals;
        // 使用lambda表达式，效率会降低？
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> results = new LinkedList<>();
        Interval temp = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (cur.start <= temp.end) {
                temp = new Interval(temp.start,
                        Math.max(cur.end, temp.end));
            } else {
                // 如果相邻不能合并才将之前合并的结果加入到结果集
                results.add(temp);
                temp = cur;  // 重新开始合并
            }
        }
        results.add(temp); // 将最后一个合并的结果加入结果集
        return results;
    }

    // 和以上写法相同
    public List<Interval> merge2(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1)
            return intervals;
        intervals.sort(Comparator.comparingInt(o -> o.start));
        LinkedList<Interval> results = new LinkedList<>();
        for (Interval interval : intervals) {
            if (results.isEmpty() ||
                    results.getLast().end < interval.start) {
                results.add(interval);
            } else { // 这个是每次添加到结果集，然后取出最后一个合并
                results.getLast().end =
                        Math.max(results.getLast().end, interval.end);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(-1, 0));
        list.add(new Interval(1, 3));
        list.add(new Interval(2, 6));
        list.add(new Interval(5, 10));
        list.add(new Interval(9, 11));
        list.add(new Interval(15, 18));
        System.out.println(new S56().merge(list));
    }
}
