import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class S39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return null;
        List<List<Integer>> res = new ArrayList<>();
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
            cur.push(candidates[i]);
            // 这里每次减去候选值，最后判断target是否等于0，这样可能要比最后求和判断是否等于target要快
            backtracking(res, cur, candidates, target - candidates[i], i);  // 传递i而非i+1
            cur.pop();
        }
    }


    public static void main(String[] args) {
        System.out.println(new S39().combinationSum(new int[]{2, 5, 3}, 8));
    }
}
