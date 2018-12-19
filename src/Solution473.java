/**
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * <p>
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,2,2,2]
 * 输出: true
 * <p>
 * 解释: 能拼成一个边长为2的正方形，其中一边两根火柴。
 * 示例 2:
 * <p>
 * 输入: [3,3,3,3,4]
 * 输出: false
 * <p>
 * 解释: 不能用所有火柴拼成一个正方形。
 * 注意:
 * <p>
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 */
public class Solution473 {
    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/108730/JavaC%2B%2BStraightforward-dfs-solution
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return new Solution698().canPartitionKSubsets(nums, 4);
    }


    public static void main(String[] args) {
        int[] nums = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        System.out.println(new Solution473().makesquare(nums));
    }
}
