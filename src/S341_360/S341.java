package S341_360;

import java.util.*;

/**
 * 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的项或者为一个整数，或者是另一个列表。
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,4,6]。
 */
public class S341 {

    static class NestedInteger {
        private Integer val;
        private List<NestedInteger> nestedList;

        public NestedInteger(Integer val) {
            this.val = val;
        }

        public NestedInteger(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
        }

        public static NestedInteger createNestedIntegerFromString(String s) {
            Stack stack = new Stack();
            for (char c : s.toCharArray()) {
                if (c == ',' || c == ' ') continue;
                if (c == ']') {
                    LinkedList<NestedInteger> list = new LinkedList<>();
                    while (true) {
                        Object o = stack.pop();
                        if (o instanceof Character) {
                            if ((char) o == '[') break;
                            list.addFirst(new NestedInteger((char) o - '0'));
                            continue;
                        }
                        list.addFirst((NestedInteger) o);
                    }
                    stack.push(new NestedInteger(list));
                } else {
                    stack.push(c);
                }
            }
            return (NestedInteger) stack.pop();
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger() {
            return val != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger() {
            return val;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList() {
            return nestedList;
        }

    }

    static class NestedIterator implements Iterator<Integer> {

        private Iterator<Integer> iterator;

        public NestedIterator(List<NestedInteger> nestedList) {
            ArrayList<Integer> integers = new ArrayList<>();
            unfold(integers, nestedList);
            iterator = integers.iterator();
        }

        private void unfold(List<Integer> integers, List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    integers.add(nestedInteger.getInteger());
                } else {
                    unfold(integers, nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }


    public static void main(String[] args) {
        NestedInteger nestedInteger = NestedInteger
                .createNestedIntegerFromString("[1, [2, 3, [4, 5, 6], 7, [8]], [9]]");
        NestedIterator iterator = new NestedIterator(nestedInteger.getList());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
