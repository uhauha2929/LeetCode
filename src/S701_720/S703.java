package S701_720;

import java.util.*;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 */

public class S703 {

    static class KthLargest {
        private Queue<Integer> queue;
        private int size;

        public KthLargest(int k, int[] nums) {
            queue = new PriorityQueue<>(k); // 每次返回最小的, 队列中只保留k个
            size = k;
            for (Integer num : nums)
                add(num);
        }

        public int add(int val) {
            if (queue.size() < size)
                queue.offer(val);
            else {
                // 比最小的大, 删除最小的, 添加这个数
                if (queue.peek() < val) {
                    queue.poll();
                    queue.offer(val);
                }

            }
            // 返回最小的数, 就是第k大的数
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(3));   // returns 4
        System.out.println(kthLargest.add(5));   // returns 5
        System.out.println(kthLargest.add(10));  // returns 5
        System.out.println(kthLargest.add(9));   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8
    }
}
