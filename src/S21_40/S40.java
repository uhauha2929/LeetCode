package S21_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class S40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return null;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);  // 使得重复数相邻
        backtracking(res, new LinkedList<>(), candidates, target, 0);
        return res;
    }

    // 回溯法
    private void backtracking(List<List<Integer>> res, LinkedList<Integer> cur,
                              int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target)  // 避免target<0
                continue;
            if (i > start && candidates[i] == candidates[i - 1])
                continue;  // 相邻的重复数跳过
            cur.push(candidates[i]);
            backtracking(res, cur, candidates, target - candidates[i], i + 1); // 从下一个位置开始
            cur.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println(new S40().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
}
