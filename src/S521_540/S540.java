package S521_540;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 */
public class S540 {

    // O(n)
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int num : nums)
            ans ^= num;
        return ans;
    }

    // 二分法 O(lgn)
    public int singleNonDuplicate2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if ((m & 1) > 0)
                m--; // 使得右半部分个数为偶数
            if (nums[m] == nums[m + 1])
                // 如果中间数等于右半部分第一个数, 则查找区间变为[m+2, r]
                l = m + 2;
            else
                r = m;  // 否则查找区间为[l,m]
        }
        return nums[l];
    }


    public static void main(String[] args) {
        System.out.println(new S540().singleNonDuplicate2(
                new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
    }
}
