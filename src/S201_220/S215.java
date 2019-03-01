package S201_220;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class S215 {

    // 类似题目Solution347,692
    public int findKthLargest(int[] nums, int k) {
        // 保存最大的k个数
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int num : nums) {
            if (heap.size() != k) {
                heap.add(num);
            } else if (num > heap.peek()) {  // 大于k个里面的最小的
                heap.poll();
                heap.add(num);
            }
        }
        return heap.peek(); // 最终是第k大的
    }

    public int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // 快速排序
    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p == k - 1) {
                return nums[p];
            } else if (p > k - 1) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }
        return nums[l];
    }

    /**
     * 以递增排序为例：
     * 6 3 7 4 1 选取6为基准
     * l-------r r的位置1比6小，交换l,r
     * 1 3 7 4 6 移动l到7
     * ----l---r 7大于6交换l,r
     * 1 3 6 4 7 移动r到4
     * ----l-r-- 4小于6，交换l,r
     * 1 3 4 6 7 l,r重合
     * ------l--
     * ------r--
     */
    private int partition(int[] nums, int l, int r) {
        // ---- 取中间值或随机值可以提高效率
        int mid = l + (r - l) / 2;
        if (nums[mid] < nums[r]) {
            swap(nums, mid, r);
        }
        if (nums[mid] < nums[l]) {
            swap(nums, mid, l);
        }
        if (nums[l] < nums[r]) {
            swap(nums, l, r);
        }
        // ----
        int key = nums[l];

        while (l < r) {
            while (nums[r] <= key && l < r) {
                r--;
            }
            nums[l] = nums[r];
            while (nums[l] >= key && l < r) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[r] = key;
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new S215().findKthLargest3(new int[]{3, 2, 1, 5, 6, 4}, 3));
    }
}
