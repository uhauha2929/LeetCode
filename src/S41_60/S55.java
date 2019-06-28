package S41_60;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是0，所以你永远不可能到达最后一个位置。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S55 {

    public boolean canJump(int[] nums) {
        int lastIndex = 0;
        int i = 0;
        do {
            // 表示最右端能到达的最靠右的位置, 等于当前位置i加上步数nums[i]
            lastIndex = Math.max(nums[i] + i, lastIndex);
            // 如果该位置能够超过数组的长度, 则说明能到达最后一个位置
            if (lastIndex >= nums.length - 1)
                return true;
        } while (i++ < lastIndex);
        return false;
    }

    /**
     * 自顶向下的动态规划(回溯法的优化), 用一个数组memo保存从该位置是否能到达终点.
     * 例如这里, 位置0,1,5,6可以到达, memo中用1标记.
     * 位置2,3,4不可到达, memo中用-1标记, memo默认初始化为0表示未知.
     * index 0   1   2   3   4   5   6
     * nums	 2	 4	 2	 1	 0	 2	 0
     * memo	 1	 1	 -1	 -1	 -1	 1	 1
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(2n), 第一个n是栈空间的开销, 第二个n是记忆表的开销.
     */

    private int[] memo;

    public boolean canJump2(int[] nums) {
        memo = new int[nums.length];
        // 最后一个位置肯定能够自己到达自己
        memo[nums.length - 1] = 1;
        return canJumpFromPosition(0, nums);
    }

    private boolean canJumpFromPosition(int pos, int[] nums) {
        if (memo[pos] != 0)
            return memo[pos] > 0;
        // 该位置可能到达的最靠右的位置
        int lastIndex = Math.min(pos + nums[pos], nums.length - 1);
        for (int i = lastIndex; i > pos; --i) {
            if (canJumpFromPosition(i, nums)) {
                memo[pos] = 1;
                return true;
            }
        }
        memo[pos] = -1;
        return false;
    }

    /**
     * 自底向上的动态规划.不用回溯, 消除了栈使用的空间. 从右到左.
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public boolean canJump3(int[] nums) {
        memo = new int[nums.length];
        memo[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int lastIndex = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= lastIndex; j++) {
                if (memo[j] == 1) {
                    memo[i] = 1;
                    break;
                }
            }
        }
        return memo[0] == 1;
    }

    /**
     * 贪心, 类似法1的做法. 只不过是从右到左.
     * 如果该位置能够到达最后, lastPos保留该位置的下标.
     * 同时每次比较当前位置是否能够到达上一次的lastPos.
     * 最后判断是否该下标为0, 即从开始位置能够到达最后.
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean canJump4(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {
        System.out.println(new S55().canJump3(new int[]{3, 2, 1, 0, 4}));
    }
}
