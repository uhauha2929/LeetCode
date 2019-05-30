package S501_520;

import java.util.*;

/**
 * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
 * (注：分数越高的选手，排名越靠前。)
 * 示例 1:
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
 * 提示:
 * N 是一个正整数并且不会超过 10000。
 * 所有运动员的成绩都不相同。
 */
public class S506 {

    public String[] findRelativeRanks(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < copy.length; i++) {
            map.put(copy[i], copy.length - i);
        }
        String[] res = new String[nums.length];
        for (int i = 0; i < res.length; i++) {
            int index = map.get(nums[i]);
            if (index == 1) {
                res[i] = "Gold Medal";
            } else if (index == 2) {
                res[i] = "Silver Medal";
            } else if (index == 3) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = index + "";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S506().findRelativeRanks(
                new int[]{5, 4, 3, 2, 1})));
    }
}
