/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class S72 {
    /**
     * 求出两个单词的最小编辑距离，使用动态规划的思想
     * edit[i][j] = {
     * 0, if i = 0 and j = 0
     * j, if i = 0 and j > 0
     * i, if j = 0 and i > 0
     * min{
     * edit[i-1, j] + ins_cost(target_i),
     * edit[i-1, j-1] + sub_cost(source_i+target_j),
     * edit[i, j-1] + del_cost(source_j)
     * }, if i >= 1 and j >= 1
     * }
     *
     * @param word1 任意单词
     * @param word2 任意单词
     * @return 最小编辑距离
     */
    public int minDistance(String word1, String word2) {
        // eidt[i][j]表示word1[0~i]与word2[0~j]的最小编辑距离
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] edit = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++)
            edit[i][0] = i;
        for (int i = 0; i < len2 + 1; i++)
            edit[0][i] = i;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    edit[i + 1][j + 1] = edit[i][j];
                else
                    // 这里的替换的损失设置为1，也可以设置为2
                    edit[i + 1][j + 1] = Math.min(Math.min(edit[i][j + 1] + 1, edit[i][j] + 1), edit[i + 1][j] + 1);
            }
        }
        return edit[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(new S72().minDistance("intention", "execution"));
    }
}
