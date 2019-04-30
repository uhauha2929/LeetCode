package S281_300;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 给定一个迭代器类的接口，接口包含两个方法： next() 和 hasNext()。设计并实现一个支持 peek() 操作的顶端迭代器 -- 其本质就是把原本应由 next() 方法返回的元素 peek() 出来。
 * <p>
 * 示例:
 * <p>
 * 假设迭代器被初始化为列表 [1,2,3]。
 * <p>
 * 调用 next() 返回 1，得到列表中的第一个元素。
 * 现在调用 peek() 返回 2，下一个元素。在此之后调用 next() 仍然返回 2。
 * 最后一次调用 next() 返回 3，末尾元素。在此之后调用 hasNext() 应该返回 false。
 * 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？
 */
public class S284 {

    static class PeekingIterator implements Iterator<Integer> {

        private Iterator<Integer> iterator;
        private Integer cache = null; // 第一次peek时, 缓存迭代的元素

        public PeekingIterator(Iterator<Integer> iter) {
            iterator = iter;
        }

        public Integer peek() {
            if (cache == null)
                cache = iterator.next();
            return cache;
        }

        @Override
        public Integer next() {
            if (cache == null)
                return iterator.next();
            Integer temp = cache;
            cache = null;
            return temp;
        }

        @Override
        public boolean hasNext() {
            return cache != null || iterator.hasNext();
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        PeekingIterator iter = new PeekingIterator(list.iterator());
        System.out.println(iter.peek());
        System.out.println(iter.next());
        System.out.println(iter.peek());
        System.out.println(iter.peek());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
    }
}
