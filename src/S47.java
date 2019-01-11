import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class S47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);  // 排序是为了相同的数字相邻
        backtracking(res, nums, used, new LinkedList<>());
        return res;
    }

    public void backtracking(List<List<Integer>> res, int[] nums, boolean[] used, LinkedList<Integer> cur) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));  // 达到最大长度加入结果集
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            // 如果相邻的数字重复，并且前一个数字已经访问过了，则跳过
            if (i > 0 && nums[i - 1] == nums[i] && used[i - 1]) continue;
            used[i] = true;
            cur.push(nums[i]);
            backtracking(res, nums, used, cur);
            used[i] = false;  // 重置当前标记
            cur.pop();  // 回溯到上一层
        }
    }

    public static void main(String[] args) {
        System.out.println(new S47().permuteUnique(new int[]{1, 1, 2}));
    }
}
