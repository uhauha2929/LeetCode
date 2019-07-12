package S541_560;

import java.util.Arrays;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * 示例 1:
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S547 {

    private int[] parent;

    // 并查集
    public int findCircleNum(int[][] M) {
        int N = M.length;
        parent = new int[N];
        // 默认初始化-1自己为根
        Arrays.fill(parent, -1);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        // 查找所有的根(值为-1)的个数即为集合的个数
        int cnt = 0;
        for (int id : parent) {
            if (id == -1)
                cnt++;
        }
        return cnt;
    }

    private void union(int x, int y) {
        int xp = findRoot(x);
        int yp = findRoot(y);
        if (xp != yp)  // 如果已经在同一个集合中则不必合并
            parent[yp] = xp;
    }

    private int findRoot(int id) {
        if (parent[id] == -1)
            return id;
        parent[id] = findRoot(parent[id]); // 路径压缩
        return parent[id];
    }

    // DFS
    public int findCircleNum2(int[][] M) {
        int ans = 0, N = M.length;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                ans++;
                DFS(M, visited, i, N);
            }
        }
        return ans;
    }

    private void DFS(int[][] M, boolean[] visited, int i, int N) {
        visited[i] = true;
        for (int j = 0; j < N; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                DFS(M, visited, j, N);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new S547().findCircleNum(new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        }));
    }
}
