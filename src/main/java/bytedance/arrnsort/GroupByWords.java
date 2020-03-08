package bytedance.arrnsort;

import java.util.*;

/**
 * @author linxu
 * @date 2020/3/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GroupByWords {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String wordStr=String.valueOf(word);
            if (map.containsKey(wordStr)){
                map.get(wordStr).add(strs[i]);
            }else{
                map.put(wordStr,new ArrayList<>(Collections.singletonList(strs[i])));
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] a = {
                "eat", "tea", "tan", "ate", "nat", "bat"
        };
        System.out.println(new GroupByWords().groupAnagrams(a));
    }
}
