import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class S46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        backtracking(ans, nums, new LinkedList<>(), new boolean[nums.length]);
        return ans;
    }

    // 回溯法
    private void backtracking(List<List<Integer>> ans, int[] nums,
                              LinkedList<Integer> cur, boolean[] used) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<>(cur));  // 达到最大长度且不重复即排列
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (used[i])  // 如果当前数字访问过，则跳过
                continue;
            cur.push(nums[i]);
            used[i] = true;
            backtracking(ans, nums, cur, used);
            cur.pop();  // 回溯到上一层
            used[i] = false;  // 重置这层的标记
        }
    }


    public static void main(String[] args) {
        System.out.println(new S46().permute(new int[]{1, 2, 3}));
    }
}
