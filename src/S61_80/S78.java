package S61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class S78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        result.add(new ArrayList<>());  // 添加一个空集[]
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            //you'll have to create a new one here, otherwise, it'll throw ConcurrentModificationException.
            // 每次取出当前所有子集，全部添加新的数字（不重复）
            for (List<Integer> list : result) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(nums[i]);
                temp.add(newList);
            }
            // 将添加后的子集全部加入到结果集中
            result.addAll(temp);
        }
        return result;
    }

    /**
     * 使用回溯法解决，相当于对这棵树从左到右进行深度优先遍历，每个结点即为子集。
     * *                        []
     * *                    /    \    \
     * *                   /      \    \
     * *                  /        \    \
     * *              [1]         [2]    [3]
     * *            /    \        /
     * *           /      \      /
     * *        [1 2]    [1 3] [2 3]
     * *       /
     * *   [1 2 3]
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> list,
                              int[] nums, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtracking(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 使用位运算
     * 0->000 对应[]
     * 1->001 对应[3]
     * 2->010 对应[2]
     * 3->011 对应[2,3]
     * 4->100 …
     * 5->101
     * 6->110
     * 7->111
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // i 代表所有可能范围内的数,
        for (int i = 0; i < 1 << nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            // j 则只有一位为1，其它位为0
            for (int j = 0; j < nums.length; j++) {
                if ((i & 1 << j) > 0) {
                    list.add(nums[j]);
                }
            }
            result.add(list);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new S78().subsets(nums));
    }
}
