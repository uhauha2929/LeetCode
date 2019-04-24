package S681_700;

/**
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * <p>
 * 示例 1：
 * <p>
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * <p>
 * <p>
 * 注意:
 * <p>
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 */
public class S698 {
    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/180014/Thinking-Process-of-Recursion-in-Java
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % k != 0 || maxNum > sum / k) return false;
        return canKSubsetsSum(nums, k, sum / k, 0, new boolean[nums.length], 0);
    }

    private boolean canKSubsetsSum(int[] nums, int k, int targetSum, int curSum, boolean[] visited, int innerStart) {
        if (k == 0) return true; // Outer base case for number of subsets.
        else if (curSum > targetSum) return false; // Inner base case for current subset sum.
        else if (curSum == targetSum)
            return canKSubsetsSum(nums, k - 1, targetSum, 0, visited, 0); // Inner base case for current subset sum.

        for (int i = innerStart; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (canKSubsetsSum(nums, k, targetSum, curSum + nums[i], visited, i + 1)) return true;
                visited[i] = false;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[] nums = {10, 12, 1, 2, 10, 7, 5, 19, 13, 1};
        System.out.println(new S698()
                .canPartitionKSubsets(nums, 4));
    }
}
