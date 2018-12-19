/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class Solution416 {
    //https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;
        // 这里采用一维数组节省空间
        boolean[] dp = new boolean[sum + 1];  // default all false
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }

        return dp[sum];
    }

    // 动态规划的递归写法，效率会高一些
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1)
            return false;
        sum /= 2;
        int[][] memo = new int[nums.length + 1][sum + 1];
        return helper(nums, 0, sum, memo);
    }

    private boolean helper(int[] nums, int start, int target, int[][] memo) {
        if (target == 0) {
            memo[start][target] = 1;
            return true;
        }
        if (start > nums.length - 1 || target < 0) return false;
        if (memo[start][target] != 0) return memo[start][target] == 1;

        boolean ans = helper(nums, start + 1, target - nums[start], memo)
                || helper(nums, start + 1, target, memo);

        memo[start][target] = ans ? 1 : -1;
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(new Solution416().canPartition2(nums));
    }
}
