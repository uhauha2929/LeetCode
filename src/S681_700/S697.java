package S681_700;

import java.util.*;

/**
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 示例 1:
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2:
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 * 注意:
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 */
public class S697 {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> posMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!posMap.containsKey(nums[i]))
                posMap.put(nums[i], new ArrayList<>());
            posMap.get(nums[i]).add(i);
        }
        int maxCnt = 0;
        for (int key : posMap.keySet()) {
            int size = posMap.get(key).size();
            if (size > maxCnt)
                maxCnt = size;
        }
        int res = nums.length;
        List<Integer> pos;
        for (int key : posMap.keySet()) {
            pos = posMap.get(key);
            if (pos.size() == maxCnt) {
                res = Math.min(pos.get(pos.size() - 1) - pos.get(0) + 1, res);
            }
        }
        return res;
    }

    public int findShortestSubArray2(int[] nums) {
        if (nums.length == 1) return 1;

        int min = nums[0], max = nums[0];
        for (int num : nums) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int len = max - min + 1;
        int[] count = new int[len]; // 记录每个数的个数
        int[] span = new int[len]; // 最大跨度
        int[] firstPos = new int[len]; // 第一次出现的位置


        for (int i = 1; i <= nums.length; i++) {
            // 从1开始, 避免从0开始
            int index = nums[i - 1] - min;
            count[index]++;
            if (firstPos[index] == 0) {
                span[index] = 1; // 跨度至少为1(1个数)
                firstPos[index] = i;
            } else {
                span[index] = i - firstPos[index] + 1;
            }
        }

        int topFreq = 0;  // 出现的最大频数
        for (int c : count)
            topFreq = Math.max(topFreq, c);

        if (topFreq == 1) return 1;

        int res = nums.length;
        for (int i = 0; i < count.length; i++) {
            if (count[i] == topFreq) {
                // 找出出现频率最高的数, 取最小跨度
                res = Math.min(res, span[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new S697().findShortestSubArray2(
                new int[]{1}));
    }
}
