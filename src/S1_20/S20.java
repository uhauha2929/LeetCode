package S1_20;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class S20 {

    private HashMap<Character, Character> mappings;

    public S20() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put('(', ')');
        this.mappings.put('{', '}');
        this.mappings.put('[', ']');
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (this.mappings.containsKey(c))
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                if (this.mappings.get(stack.pop()) != c)
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new S20().isValid("{[]}"));
    }
}
