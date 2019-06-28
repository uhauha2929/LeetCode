package S661_680;

import java.util.Arrays;

/**
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S674 {

    public int findLengthOfLCIS(int[] nums) {
        int maxLength = 0;
        int i = 0, j = 0;
        while (i < nums.length) {
            while (j + 1 < nums.length && nums[j + 1] > nums[j]) {
                j++;
            }
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
            i = j;
        }
        return maxLength;
    }

    public int findLengthOfLCIS2(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int ans = 1;
        int cnt = 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] > nums[i]) {
                ++cnt;
            } else {
                ans = Math.max(cnt, ans);
                cnt = 1;
            }
        }
        // 可能一直是递增, 最后判断
        return Math.max(cnt, ans);
    }

    // 动态规划
    public int findLengthOfLCIS3(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int ans = 1;
        // 记录到每个位置递增的个数, 默认初始化为1
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                // 前一个位置(状态)的计数加1
                dp[i] = dp[i - 1] + 1;
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S674().findLengthOfLCIS3(new int[]{1, 3, 5, 4, 7}));
    }
}
