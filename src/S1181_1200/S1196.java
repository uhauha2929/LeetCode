package S1181_1200;

import java.util.Arrays;

/**
 * 楼下水果店正在促销，你打算买些苹果，arr[i] 表示第 i 个苹果的单位重量。
 * 你有一个购物袋，最多可以装 5000 单位重量的东西，算一算，最多可以往购物袋里装入多少苹果。
 * 示例 1：
 * 输入：arr = [100,200,150,1000]
 * 输出：4
 * 解释：所有 4 个苹果都可以装进去，因为它们的重量之和为 1450。
 * 示例 2：
 * 输入：arr = [900,950,800,1000,700,800]
 * 输出：5
 * 解释：6 个苹果的总重量超过了 5000，所以我们只能从中任选 5 个。
 * 提示：
 * 1 <= arr.length <= 10^3
 * 1 <= arr[i] <= 10^3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-apples-can-you-put-into-the-basket
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1196 {

    // 贪心，先对数组排序
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        int maxWeight = 5000;
        for (int weight : arr) {
            maxWeight -= weight;
            if (maxWeight < 0)
                break;
            ans++;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S1196().maxNumberOfApples(new int[]{900, 950, 800, 1000, 700, 800}));
    }
}
