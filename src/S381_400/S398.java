package S381_400;

/**
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。
 * 您可以假设给定的数字一定存在于数组中。
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 * 示例:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S398 {

    static class Solution {

        private int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;
        }

        /**
         * 蓄水池抽样, 类似solution382
         */
        public int pick(int target) {
            int cnt = 0, index = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target && Math.random() < 1.0 / ++cnt) {
                    index = i;
                }
            }
            return index;
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution(new int[]{1, 2, 3, 3, 3}).pick(3));
    }
}
