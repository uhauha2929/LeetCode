package S301_320;

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * 示例：
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 */
public class S303 {

    static class NumArray {

        private int[] dp;

        public NumArray(int[] nums) {
            dp = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                dp[i + 1] = dp[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return dp[j + 1] - dp[i];
        }
    }


    public static void main(String[] args) {
        NumArray arr = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(arr.sumRange(3, 5));
    }
}
