package S761_780;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * 示例 1:
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 */
public class S763 {

    public List<Integer> partitionLabels(String S) {
        // 存放每个字母最后一次在字符串中出现的位置
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        // preIndex表示上个区间的右端点
        // maxIndex表示当前遍历的字符最后出现位置的最大值
        int preIndex = -1, maxIndex = 0;
        for (int i = 0; i < S.length(); i++) {
            int index = last[S.charAt(i) - 'a'];
            // int index = S.lastIndexOf(S.charAt(i));
            // 更新区间的右端点, 向右延展
            if (index > maxIndex) {
                maxIndex = index;
            }
            // 如果当前位置i等于当前所遍历的字符最后出现位置的最大值
            // 说明maxIndex即为区间的右端点
            if (i == maxIndex) {
                // 添加区间的长度
                res.add(maxIndex - preIndex);
                // 保存当前右端点
                preIndex = maxIndex;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S763().partitionLabels("ababcbacadefegdehijhklij"));
    }
}
