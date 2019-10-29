package S341_360;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 * 示例:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/moving-average-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S346 {

    static class MovingAverage {
        private Queue<Integer> queue = new LinkedList<>();
        private int size = 0;
        private double sum = 0;

        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            sum += val;
            queue.add(val);
            if (queue.size() > size) {
                sum -= queue.remove();
            }
            return sum / queue.size();
        }
    }


    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }
}
