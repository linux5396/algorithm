package bytedance.arrnsort;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author linxu
 * @date 2020/3/6
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * https://segmentfault.com/a/1190000018246661?utm_source=tag-newest
 */
public class KthPermutation {
    static int times = 0;

    public String getPermutation(int n, int k) {
        char[] string = new char[n];
        for (int i = 0; i < n; i++) {
            string[i] = (char) (i + '1');
        }
        int[] cur = {0};
        List<Character>[] finalRes = new ArrayList[1];
        finalRes[0] = new ArrayList<>();
        List<Character> tempRes = new ArrayList<>();
        boolean[] hasVisited = new boolean[n];
        backtrack(string, k, tempRes, hasVisited, cur, finalRes);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : finalRes[0]
                ) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private void backtrack(char[] nums, int k, List<Character> tempIndex, boolean[] haveSeen, int[] cur, List<Character>[] finalRes) {
        if (tempIndex.size() == nums.length) {
            cur[0]++;
        }
        if (cur[0] == k && tempIndex.size() == nums.length) {
            finalRes[0] = new ArrayList<>(tempIndex);
            return;
        } else if (cur[0] < k && tempIndex.size() == nums.length) {
            tempIndex = new ArrayList<>();
        }
        for (int i = 0; i < nums.length; i++) {
            if (haveSeen[i]) {
                continue;
            }
            tempIndex.add(nums[i]);
            haveSeen[i] = true;
            backtrack(nums, k, tempIndex, haveSeen, cur, finalRes);
            haveSeen[i] = false;
            tempIndex.remove(tempIndex.size() - 1);
        }
    }

    /**
     * 这种方式并不会减少次数，反而比交换的更多；
     * 但是，好处是能保证有序；
     *
     * @param chars
     * @param start
     * @param visited
     * @param temp
     */
    public static void permutationInBackTrack(char[] chars, int start, boolean[] visited, List<Character> temp) {
        times++;
        if (start == chars.length) {
            for (Character c : temp
                    ) {
                System.out.print(c + ",");
            }
            System.out.println("");
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            temp.add(chars[i]);
            permutationInBackTrack(chars, start + 1, visited, temp);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }


    public static void main(String[] args) {
        //  System.out.println(new KthPermutation().getPermutation(3, 6));
        boolean[] visited = new boolean[5];
        Arrays.fill(visited, false);

        permutationInBackTrack("abcde".toCharArray(), 0, visited, new ArrayList<>());
        System.out.println(times);
    }

}
