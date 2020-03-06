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
        //存储阶乘
        int[] factor = new int[n - 1];
        int t = 1;
        for (int i = 1; i < n; i++) {
            t *= i;
            factor[i - 1] = t;
        }
        backtrack(string, k, tempRes, hasVisited, cur, finalRes);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : finalRes[0]) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * n 个数字有 n！种全排列，每种数字开头的全排列有 (n - 1)!种。
     * 所以用 k / (n - 1)! 就可以得到第 k 个全排列是以第几个数字开头的。
     * 用 k % (n - 1)! 就可以得到第 k 个全排列是某个数字开头的全排列中的第几个。
     * 核心就是上面的公式
     *
     * @param n
     * @param k
     * @return
     */
    public static String inFast(int n, int k) {
        boolean[] choosed = new boolean[n];//choosed[i]存储的是i+1是否被使用
        int[] factorial = new int[n - 1];//factorial[i]存储的是i+1的阶乘
        int temp = 1;
        for (int i = 1; i < n; i++) {
            temp *= i;
            factorial[i - 1] = temp;
        }
        StringBuilder sb = new StringBuilder();
        //注意
        k--;//这里不能少，毕竟我们是从0开始数，而给的k是从1开始。
        for (int i = n - 2; i >= 0; i--) {
            //由于最长是N-1，因此，N-2用于表示阶乘（N-2）！
            int count = 0;
            int index = -1;
            //用 k / (n - 1)! 就可以得到第 k 个全排列是以第几个数字开头的。
            while (count < (k / factorial[i]) + 1) {
                index++;
                if (!choosed[index]) {
                    count++;
                }
            }
            choosed[index] = true;
            sb.append(index + 1);
            System.err.println(index + 1);
            //k % (n - 1)! 就可以得到第 k 个全排列是某个数字开头的全排列中的第几个。 3** 表示3开头；余数表示3开头的第几个；
            k %= factorial[i];
        }
        //实际只剩下一个
        for (int i = 0; i < choosed.length; i++) {
            if (!choosed[i]) {
                sb.append(i + 1);
                // break;
            }
        }
        return sb.toString();
    }

    /**
     * 基于回溯法的排列，能够保证排列是有序的
     *
     * @param nums
     * @param k
     * @param tempIndex
     * @param haveSeen
     * @param cur
     * @param finalRes
     */
    //TODO 剪枝
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
            for (Character c : temp) {
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
        System.out.println(inFast(4, 7));
        //permutationInBackTrack("abcde".toCharArray(), 0, visited, new ArrayList<>());
        // System.out.println(times);
    }

}
