package S61_80;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class S79 {
    /**
     * 先找到第一个相等的字符, 然后以这个字符为原点, 对上下左右四个方向进行回溯.
     * 为了避免数字重复, 使用布尔数组(used)记录是否访问过.
     * 可以简单的使用index来记录word中当前字符位置, 如果index达到word末尾,
     * 说明相等字符个数为word中所有字符个数, 返回true.
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (search(board, i, j, word, used, 0))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, int i, int j,
                           String word, boolean[][] used,
                           int index) {
        if (index == word.length())
            return true;
        if (i < 0 || i >= board.length)
            return false;
        if (j < 0 || j >= board[0].length)
            return false;
        if (used[i][j] || board[i][j] != word.charAt(index))
            return false;
        used[i][j] = true;
        boolean exist = search(board, i - 1, j, word, used, index + 1)
                || search(board, i + 1, j, word, used, index + 1)
                || search(board, i, j - 1, word, used, index + 1)
                || search(board, i, j + 1, word, used, index + 1);
        used[i][j] = false;
        return exist;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(new S79().exist(board, "ABCB"));
    }
}
