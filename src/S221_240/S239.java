package S221_240;

import java.util.*;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 注意：
 * <p>
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 */
public class S239 {

    // 堆
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            return new int[]{};
        int[] res = new int[nums.length - k + 1];
        Queue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
        int i = 0;
        int j = 0;
        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                res[i++] = queue.element();
                queue.remove(nums[j++]);
                queue.offer(num);
            }
        }
        res[i] = queue.element();
        return res;
    }

    private void inQueue(Deque<Integer> deque, int num) {
        // 保证双端队列里总是降序的
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offer(num);
    }

    private void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            return new int[]{};
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) {
            inQueue(deque, nums[i]);
        }
        for (int i = k - 1; i < nums.length; i++) {
            inQueue(deque, nums[i]);
            ans[i - k + 1] = deque.peekFirst();
            outQueue(deque, nums[i - k + 1]);
        }
        return ans;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            return new int[]{};

        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = k - 1;
        // 先求出第一个窗口的最大值
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        result[resultIndex++] = max;
        // 从第二个窗口开始
        while (++end < nums.length) {
            int oldMax = max;
            // 如果当前数字（当前窗口最右边的数）比之前窗口的最大值大，则当前窗口的最大值就是该数
            if (nums[end] > max) {
                max = nums[end];
            }
            int oldStart = start;
            start += 1;
            // 如果当前数没有比之前的窗口的最大值大，并且之前窗口的最大值就是窗口最左边的数，
            // 则之前的最大值被新的窗口丢弃，需要找出新的窗口的最大值
            if (oldMax == max && nums[oldStart] == max) {
                // 先把当前窗口最左边的数当成最大值，遍历该窗口，找出最大值
                max = nums[start];
                for (int i = start; i <= end; i++) {
                    if (nums[i] > max) {
                        max = nums[i];
                    }
                }
            }
            result[resultIndex++] = max;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] res = new S239().maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(res));
    }
}
