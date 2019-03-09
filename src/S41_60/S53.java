package S41_60;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class S53 {


    // 暴力法 O(n^2)
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0; // 这里做了改进, 使得O(n^3)变为O(n^2)
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max)
                    max = sum;
            }
        }
        return max;
    }

    /**
     * 扫描法 O(n)
     * 当我们加上一个正数时，和会增加；当我们加上一个负数时，和会减少。
     * 如果当前和是负数，那么这个和在接下的累加中应该重新清零
     */
    public int maxSubArray2(int[] nums) {
        int sum = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0)
                sum = nums[i];
            else
                sum += nums[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }

    /**
     * 动态规划, O(n)
     * 以第i个元素结尾和最大的连续子数组
     * 要么是以第i-1个元素结尾和最大的连续子数组加上这个元素
     * 要么是只包含第i个元素
     * sum[i] = max(sum[i-1] + a[i], a[i])
     */
    public int maxSubArray3(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * 分治法 O(nlgn)
     * 任何连续子数组必然位于一下三种情况:
     * 1. 完全位于A[low, mid]
     * 2. 完全位于A[mid, high]
     * 2. 跨越了中点
     */
    public int maxSubArray4(int[] nums) {
        return findMaxSubArray(nums, 0, nums.length - 1);
    }

    private int findMaxSubArray(int[] nums, int low, int high) {
        if (low == high)
            return nums[low];
        else {
            int mid = (low + high) / 2;
            int leftMax = findMaxSubArray(nums, low, mid);
            int rightMax = findMaxSubArray(nums, mid + 1, high);
            int crossMax = findCrossMaxSubArray(nums, low, mid, high);
            return Math.max(crossMax, Math.max(leftMax, rightMax));
        }
    }

    private int findCrossMaxSubArray(int[] nums, int low, int mid, int high) {
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += nums[i];
            if (sum > leftMax)
                leftMax = sum;
        }
        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += nums[i];
            if (sum > rightMax)
                rightMax = sum;
        }
        return leftMax + rightMax;
    }


    public static void main(String[] args) {
        System.out.println(new S53().maxSubArray4(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
