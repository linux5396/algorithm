package com.linxu.algorithm.bydate.date191013;

/**
 * @author linxu
 * @date 2019/10/12
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 问题：
 * 顺时针打印矩阵
 * 算法要点：
 * 1、找出每轮打印的起点，起始都是arr[i][i];metrix.length > startPoint << 1 && metrix[0].length > startPoint << 1
 * 2、找出分为四个方向的打印的满足条件，并进行打印。
 */
//TODO 逆时针打印矩阵
public class ClockwisePrintMetrix {
    public void print(int[][] metrix) {
        if (metrix != null && metrix.length >= 1 && metrix[0].length >= 1) {
            int startPoint = 0;
            while (metrix.length > startPoint << 1 && metrix[0].length > startPoint << 1) {
                printCore(metrix, startPoint);
                startPoint++;
            }
        }
    }

    private void printCore(int[][] metrix, int startPoint) {
        int endX = metrix[0].length - 1 - startPoint;
        int endY = metrix.length - 1 - startPoint;
        //横向打印
        for (int i = startPoint; i <= endX; i++) {
            System.out.print(metrix[startPoint][i]);
        }
        //竖向打印
        if (startPoint < endY) {
            for (int i = startPoint + 1; i <= endY; i++) {
                System.out.print(metrix[i][endX]);
            }
        }
        //从右到左打印
        if (startPoint < endX && startPoint < endY) {
            for (int i = endX - 1; i >= startPoint; i--) {
                System.out.print(metrix[endY][i]);
            }
        }
        //自下而上打印
        if (startPoint<endX&&startPoint<endY-1){
            for (int i = endY-1; i >=startPoint+1 ; i--) {
                System.out.print(metrix[i][startPoint]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr={
                {1},
                {2},
                {3}
        };
        new ClockwisePrintMetrix().print(arr);
    }
}
