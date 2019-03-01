package S81_100;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class S90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);  // 这步不可缺
        Set<List<Integer>> result = new HashSet<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            Set<List<Integer>> temp = new HashSet<>();
            for (List<Integer> list : result) {
                // 排序是为了这里的一致性
                List<Integer> newList = new ArrayList<>(list);
                newList.add(nums[i]);
                temp.add(newList);
            }
            result.addAll(temp);
        }
        return new ArrayList<>(result);
    }

    // 回溯法
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // 排序为了使得重复数字相邻
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> list,
                           int[] nums, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            // 与solution78类似，只是在同层时判断前一个数字是否与当前相同（之前已经排过序）
            // 如果相同则跳过，不进行递归
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            backtrack(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 4, 4, 1, 4};
        System.out.println(new S90().subsetsWithDup2(nums));
    }
}
