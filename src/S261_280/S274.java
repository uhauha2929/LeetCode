package S261_280;

import java.util.Arrays;

/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）至多有 h 篇论文分别被引用了至少 h 次。
 * （其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
 * 示例:
 * 输入: citations = [3,0,6,1,5]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * 说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/h-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S274 {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length - i) {
                return citations.length - i;
            }
        }
        return 0;
    }

    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // 计数
        for (int c : citations) // 将超过n的部分变为n不会影响h指数
            papers[Math.min(n, c)]++;
        // 找出最大的 k
        int k = n;
        // s表示至少有k次的论文数量, 找出最大的满足k<=s的k即为h指数
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    public static void main(String[] args) {
        System.out.println(new S274().hIndex(new int[]{3, 0, 6, 1, 5}));
    }
}
