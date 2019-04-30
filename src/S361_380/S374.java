package S361_380;

/**
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 * 示例 :
 * 输入: n = 10, pick = 6
 * 输出: 6
 */

public class S374 {

    private int guess(int n) {
        return Integer.compare(1, n);
    }

    public int guessNumber(int n) {
        int i = 1, j = n;
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (guess(m) == 1) {
                i = m + 1;
            } else if (guess(m) == -1) {
                j = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new S374().guessNumber(10));
    }
}
