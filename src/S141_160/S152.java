package S141_160;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class S152 {

    // 类似Solution53
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, maxSum = 1, minSum = 1;
        //如果数组的数是负数, 那么会导致最大的变最小的, 最小的变最大的, 因此交换两个的值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = maxSum;
                maxSum = minSum;
                minSum = tmp;
            }
            maxSum = Math.max(maxSum * nums[i], nums[i]);
            minSum = Math.min(minSum * nums[i], nums[i]);
            max = Math.max(max, maxSum);
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new S152().maxProduct(new int[]{-2, 3, -4}));
    }
}
