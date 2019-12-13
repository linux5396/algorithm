package com.linxu.algorithm.bydate.date191213;

import com.linxu.algorithm.utils.GenerationUtil;

/**
 * @author linxu
 * @date 2019/12/14
 * <tip>take care of yourself.everything is no in vain.</tip>
 * DJSTRA算法:经典贪婪算法
 */
public class Dijistra {
    private static final int INF = Integer.MAX_VALUE;

    public static int[] dijkstra(int[][] matrix, int startIdx) {
        if (matrix == null || matrix.length != matrix[0].length) {
            return null;
        }
        //locate a array
        int[] dist = new int[matrix.length];
        //init visited[]
        //byte[] s= new byte[matrix.length];
        //boolean[]=byte[]
        boolean[] visited = new boolean[matrix.length];
        //first init dist
        visited[startIdx] = true;
        for (int i = 0; i < matrix.length; i++) {
                dist[i] = matrix[startIdx][i];
        }
        //
        for (int i = 0; i < matrix.length; i++) {
            int min = INF;
            int idx = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (!visited[j] && matrix[i][j] < min) {
                    min = matrix[i][j];
                    idx = j;
                }
            }
            visited[idx] = true;
            for (int j = 0; j < matrix.length; j++) {
                if (!visited[j] && matrix[idx][j] != INF) {
                    dist[j] = dist[j] > (matrix[idx][j] + dist[idx]) ? (matrix[idx][j] + dist[idx]) : dist[j];
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 12, INF, INF, INF},
                {INF, 0, 9, 3, INF, INF},
                {INF, INF, 0, INF, 5, INF},
                {INF, INF, 4, 0, 13, 15},
                {INF, INF, INF, INF, 0, 4},
                {INF, INF, INF, INF, INF, 0},
        };
       GenerationUtil.print( dijkstra(matrix,0),true);
    }
}
