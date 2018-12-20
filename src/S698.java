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
    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/108730/JavaC%2B%2BStraightforward-dfs-solution
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (k <= 0 || sum % k != 0) return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, nums.length - 1,
                k, 0, sum / k);
    }

    private boolean canPartition(int[] nums, int[] visited, int start,
                                 int k, int cur_sum, int target) {
        if (k == 1) return true;
        if (cur_sum > target) return false;
        if (cur_sum == target)
            return canPartition(nums, visited, nums.length - 1,
                    k - 1, 0, target);
        for (int i = start; i > 0; i--) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (canPartition(nums, visited, i - 1,
                        k, cur_sum + nums[i], target))
                    return true;
                visited[i] = 0;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int nums[] = {10, 12, 1, 2, 10, 7, 5, 19, 13, 1};
        System.out.println(new S698()
                .canPartitionKSubsets(nums, 4));
    }
}
