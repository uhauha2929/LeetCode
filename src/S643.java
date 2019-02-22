/**
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * <p>
 * 注意:
 * <p>
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */
public class S643 {

    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int j = k;
        double maxSum = sum;
        while (j < nums.length) {
            sum += (nums[j] - nums[j - k]);
            if (sum > maxSum) {
                maxSum = sum;
            }
            j++;
        }
        return maxSum / k;
    }


    public static void main(String[] args) {
        System.out.println(new S643().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }
}
