package S241_260;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 * 示例 1:
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 * 示例 2:
 * 输入: [[7,10],[2,4]]
 * 输出: true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S252 {
    // 暴力法
    public boolean canAttendMeetings(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[j]))
                    return false;
            }
        }
        return true;
    }

    private boolean overlap(int[] i1, int[] i2) {
        // 如果两个间隔的最小结束时间小于等于最大开始时间则无重叠
        return Math.min(i1[1], i2[1]) > Math.max(i1[0], i2[0]);
    }

    public boolean canAttendMeetings2(int[][] intervals) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        queue.addAll(Arrays.asList(intervals));
        int preEnd = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int[] interval = queue.remove();
            if (interval[0] < preEnd)
                return false;
            preEnd = interval[1];
        }
        return true;
    }

    public boolean canAttendMeetings3(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(new S252().canAttendMeetings(intervals));
    }
}
