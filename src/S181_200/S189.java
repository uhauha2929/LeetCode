package S181_200;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 */
public class S189 {

    // 插入 O(kn), O(1)
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1)
            return;
        k %= nums.length;
        while (k-- > 0) {
            int temp = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                nums[i + 1] = nums[i];
            }
            nums[0] = temp;
        }
    }

    /**
     * 三次翻转 O(n), O(1)
     * 1234567 -> 7654321 -> 5674321 -> 5671234
     */
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return;
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 先将前k个数字交换正确, 再继续处理后n-k个
     * 处理前k个:
     * 1234567 -> 5234167 -> 5634127 -> 5674123
     * 处理后n-k个:
     * -> 5671423 -> 5671243 -> 5671234
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        for (int start = 0; start < nums.length && k != 0; n -= k, start += k, k %= n) {
            for (int i = 0; i < k; i++) {
                swap(nums, start + i, nums.length - k + i);
            }
        }
    }

    // 递归写法 O(n), O(n/k)
    public void rotate4(int[] nums, int k) {
        recursiveSwap(nums, k, 0, nums.length);
    }

    private void recursiveSwap(int[] nums, int k, int start, int length) {
        k %= length;
        if (k == 0) return;
        for (int i = 0; i < k; i++) {
            swap(nums, start + i, nums.length - k + i);
        }
        recursiveSwap(nums, k, start + k, length - k);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        new S189().rotate3(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
