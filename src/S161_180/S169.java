package S161_180;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class S169 {

    // O(nlgn)
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法
     * O(n) 遇到相同count+1, 不同count-1, 如果count==0, 换一个数重新开始
     * 从candidate被赋值到, 计数减到0的那一段可以去除, 余下部分的多数元素依然是原数组的多数元素.
     * 我们在循环的时候，并没有办法判定当前的数字是否为主元素,
     * 所以在移除的时候，我们可能是移除了一个主元素和一个非主元素,也有可能移除的是两个非主元素.
     * 所以最后 count 的值是不确定的，但是它能够保证在最差情况下(两两抵消), 剩余的仍然是主元素.
     * 例如，[1,2,3,3,3] 和 [1,3,2,3,3] 这两个数组最后得到的count分别为3和1,但是这并不影响答案的正确性.
     * <p>
     * 首先，可以证明最终不会一个数字都不剩。
     * 原因： 假设两两抵消之后，最终一个数字都不剩。那么就是说一共有偶数个数字，假设有n个，
     * 那么n = 2k，k是整数。所以是进行了k次两两抵消。又因为一定存在众数 (数量超过⌊n/2⌋ = k的数字 )，
     * 所以该众数出现次数至少为k+1。由抽屉原理，这就会导致前面两两抵消的某一对数字是一样的。这是矛盾的。
     * 所以这就证明了最终不会一个数字都不剩，至少剩下一个。
     *
     * 假设最终剩下的那一种数字是a，假设前面进行了k次两两抵消。
     * 要证明a是欲求的众数，即证明其他数字不可能是众数。由于抽屉原理，在前面抵消的数字中，
     * 同一种数字最多出现k次，即是除了a之外的数字最多出现k次。
     * 而且最终至少剩下一个数字，所以数字的总数量大于等于2k+1。
     * 那么除了a之外的数字出现的频率<= k/(2k+1) < k/2k = 1/2，所以证明了除了a之外的数字均不会是众数。
     * 那么就是说最终剩下的那种数字a是所求众数。
     * 来源：知乎 链接：https://www.zhihu.com/question/49973163/answer/477886752
     */
    public int majorityElement2(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num != candidate) {
                count--;
            } else
                count++;
        }
        return candidate;
    }

    // O(32n)位运算 统计每一位的个数
    public int majorityElement3(int[] nums) {
        int[] bit = new int[32];
        for (int num : nums)
            for (int i = 0; i < 32; i++)
                bit[i] += (num >> (31 - i)) & 1;
        int res = 0;
        for (int i = 0; i < 32; i++)
            if (bit[i] > nums.length / 2)
                res |= (1 << (31 - i));
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S169().majorityElement3(
                new int[]{1, 1, 2}));
    }
}
