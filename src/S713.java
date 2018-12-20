/**
 * 给定一个正整数数组 nums。
 * <p>
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 说明:
 * <p>
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 */
public class S713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            if (product < k) {
                result++;
                for (int j = i + 1; j < nums.length; j++) {
                    product *= nums[j];
                    if (product < k) {
                        result++;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 双指针遍历
     * 右指针向右移动，累乘右指针的值，当积大于等于K时，不断移动左指针，累除左指针的值。
     * 当积小于K时，滑动窗口内元素的个数即为此区间连续的子数组的个数。
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 0) return 0;
        int n = nums.length;
        int i = 0;
        long p = 1L;
        int c = 0;
        for (int j = 0; j < n; ++j) {
            p *= nums[j];
            while (p >= k && i <= j) {
                p /= nums[i];
                ++i;
            }
            c += (j - i + 1);
        }
        return c;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        System.out.println(new S713()
                .numSubarrayProductLessThanK2(nums, k));
    }
}
