package S441_460;

/**
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 * 示例:
 * 输入:
 * [1,2,3]
 * 输出:
 * 3
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class S453 {

    /**
     * 为了使得所有数相等,n-1个数同时加1可以认为和一个数减1效果一样.
     * 所以所有数与最小值的差值的和就是操作的次数.
     * 也即等于所有数的和-最小值*数组长度.
     */
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (num < min)
                min = num;
            sum += num;
        }
        return sum - min * nums.length;
    }


    public static void main(String[] args) {
        System.out.println(new S453().minMoves(new int[]{1, 2, 3}));
    }
}
