import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class S22 {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        search(ans, "", 0, 0, n);
        return ans;
    }
    /**
     * 递归查找合理的括号方案，注意需要满足一定的条件才能进行递归，即：
     * 开（闭）括号的个数要小于最大括号对数或者闭括号的个数要小于开括号的个数
     * 一旦当前括号字符串的长度达到最大长度，即为合理的字符串，可以加入结果集。
     *
     * @param ans   合理的结果集
     * @param cur   当前的括号字符串
     * @param open  开括号的个数
     * @param close 闭括号的个数
     * @param max   最大的括号对数
     */
    private void search(List<String> ans, String cur, int open, int close, int max) {

        if (open > max || close > max || close > open)
            return;

        if (open == max && close == max)
            ans.add(cur);

        search(ans, cur + "(", open + 1, close, max);
        search(ans, cur + ")", open, close + 1, max);
    }


    public static void main(String[] args) {
        System.out.println(new S22().generateParenthesis(3));
    }
}
