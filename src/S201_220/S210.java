package S201_220;

import java.util.*;

/**
 * 现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。给你一个数组prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 *
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是[0,1,2,3] 。另一个正确的排序是[0,2,1,3] 。
 * 示例 3：
 *
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 * 
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S210 {

    // BFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化邻接表
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        // 保存所有节点的入度
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
            adjacencyList.get(pre[1]).add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        // 从入度为0的节点进行层次遍历
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] order = new int[numCourses];
        int top = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order[++top] = cur;
            for (int child : adjacencyList.get(cur)) {
                inDegree[child]--;
                if (inDegree[child] == 0) {
                    queue.offer(child);
                }
            }
        }
        return top == numCourses - 1 ? order : new int[0];
    }

    private int[] visited;
    private boolean valid = true;
    private int[] order;
    private int top = -1;
    private List<List<Integer>> adjacencyList;

    // DFS
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        // 初始化邻接表
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        // 0 未搜索 1 搜索中 2 已完成
        visited = new int[numCourses];
        order = new int[numCourses];
        // 建立邻接表
        for (int[] pre : prerequisites) {
            adjacencyList.get(pre[1]).add(pre[0]);
        }
        // 每次挑选一个未搜索的节点
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
            if (!valid) {
                break;
            }
        }
        // 从栈顶至栈底为拓扑排序，需要反转
        reverse(order);
        return valid ? order : new int[0];
    }

    private void dfs(int cur) {
        visited[cur] = 1;
        for (int child : adjacencyList.get(cur)) {
            if (visited[child] == 0) {
                dfs(child);
            }
            // 如果子节点状态也为搜索中，则说明出现环
            else if (visited[child] == 1) {
                valid = false;
                return;
            }
        }
        // 搜索完成，加入栈
        visited[cur] = 2;
        order[++top] = cur;
    }

    private void reverse(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
