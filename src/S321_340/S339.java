package S321_340;

import java.util.List;
/**
 * 给定一个嵌套的整数列表，请返回该列表按深度加权后所有整数的总和。
 * 每个元素要么是整数，要么是列表。同时，列表中元素同样也可以是整数或者是另一个列表。
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: 10
 * 解释: 因为列表中有四个深度为 2 的 1 ，和一个深度为 1 的 2。
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: 27
 * 解释: 一个深度为 1 的 1，一个深度为 2 的 4，一个深度为 3 的 6。所以，1 + 4*2 + 6*3 = 27。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nested-list-weight-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S339 {
    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    private interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += depth * nestedInteger.getInteger();
            } else {
                sum += dfs(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }
}
