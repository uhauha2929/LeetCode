package S21_40;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 1. 数字 1-9 在每一行只能出现一次。
 * 2. 数字 1-9 在每一列只能出现一次。
 * 3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * Note:
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class S37 {

    public void solveSudoku(char[][] board) {
        assert board.length == 9 && board[0].length == 9;
        // 记录每一行每一列以及9个3乘3的块，已经使用的数字
        boolean[][] usedRow = new boolean[9][10];
        boolean[][] usedColumn = new boolean[9][10];
        boolean[][] usedBlock = new boolean[9][10];
        // 初始化九宫格的初始值的使用
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    usedRow[i][num] = true;
                    usedColumn[j][num] = true;
                    usedBlock[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        backtracking(board, usedRow, usedColumn, usedBlock, 0, 0);
    }

    private boolean backtracking(char[][] board, boolean[][] usedRow,
                                 boolean[][] usedColumn, boolean[][] usedBlock, int i, int j) {
        // 寻空位置直到九宫格已满
        while (board[i][j] != '.') {
            if (++j == 9) {
                i++;
                j = 0;
            }
            if (i == 9)
                return true;
        }
        // 对1~9个数字进行DFS
        for (int num = 1; num <= 9; num++) {
            // 表示(i, j)位置所属的块号
            int blockIndex = i / 3 * 3 + j / 3;
            // 如果该数字在(i,j)所属的行、列、块都没有使用
            if (!usedRow[i][num] && !usedColumn[j][num] && !usedBlock[blockIndex][num]) {
                // 则假定被使用了，标记全置为true，然后进行递归
                board[i][j] = (char) ('0' + num);
                usedRow[i][num] = true;
                usedColumn[j][num] = true;
                usedBlock[blockIndex][num] = true;
                if (backtracking(board, usedRow, usedColumn, usedBlock, i, j)) {
                    // 如果递归的结果真的为true
                    return true;
                } else {
                    // 否则说明递归暂时无法得到解，需要进行回溯，将标记重置回来
                    usedRow[i][num] = false;
                    usedColumn[j][num] = false;
                    usedBlock[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }


    private void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        S37 s = new S37();
        System.out.println("Before:");
        s.print(board);
        System.out.println("After:");
        s.solveSudoku(board);
        s.print(board);
    }
}
