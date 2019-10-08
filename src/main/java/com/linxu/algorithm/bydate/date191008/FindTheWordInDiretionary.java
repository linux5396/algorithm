package com.linxu.algorithm.bydate.date191008;

import com.linxu.algorithm.utils.GenerationUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author linxu
 * @date 2019/10/8
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 从二维数组中找到存在于既定字典中的单词并且返回这些单词集合。
 */
public class FindTheWordInDiretionary {
    public static String[] findTheWords() {
        Set<String> wordSet = new HashSet<>();
        String[] directionary = {"eat", "oath", "otha"};
        char[][] board = {
                {'o', 'e', 'k', 'b'},
                {'a', 't', 'e', 'a'},
                {'c', 'h', 'a', 't'},
                {'e', 'r', 'o', 'a'}
        };
        //构造路径数组
        boolean[][] path = new boolean[board.length][board[0].length];
        for (String word : directionary) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    boolean hasPath = hasPath(board, path, i, j, 0, word.toCharArray());
                    if (hasPath) {
                        wordSet.add(word);
                    }
                    //重置路径
                   // GenerationUtil.memset(path, false);
                }
            }
        }
        String[] words = new String[wordSet.size()];
        return wordSet.toArray(words);
    }

    private static boolean hasPath(char[][] arr, boolean[][] path, int i, int j, int k, char[] word) {
        //over bound
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || k >= word.length) {
            return false;
        }
        //找到的条件
        if (!path[i][j] && arr[i][j] == word[word.length - 1] && k == word.length - 1) {
            return true;
        }
        boolean hasPath = false;
        if (!path[i][j] && arr[i][j] == word[k]) {
            //mark
            path[i][j] = true;
            //当前可达
            hasPath = hasPath(arr, path, i + 1, j, k + 1, word)
                    || hasPath(arr, path, i - 1, j, k + 1, word)
                    || hasPath(arr, path, i, j + 1, k + 1, word)
                    || hasPath(arr, path, i, j - 1, k + 1, word);
            if (!hasPath) {
                path[i][j] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        for (String word : findTheWords()
                ) {
            System.out.println(word);
        }
    }
}
